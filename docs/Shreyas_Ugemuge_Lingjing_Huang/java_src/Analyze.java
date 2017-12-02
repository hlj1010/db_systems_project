package QueryPerformance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Analyze {

    private static Connection connect (int i) {
        // a ternary operator to make sure 1st database is read as d.db
        String url = "jdbc:sqlite:../d" + (i == 1 ? "" : i) + ".db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private static long timeQuery (int databaseNumber, int queryNumber) throws Exception {
        String query = queryFromFile("../Queries/query_" + queryNumber + "_db" + databaseNumber + ".sql");
        Connection c = connect(databaseNumber);
        Statement s = c.createStatement();
        long start = System.nanoTime();
        s.executeQuery(query);
        long end = System.nanoTime() - start;
        return end;

    }

    private static String queryFromFile (String filename) throws Exception {
        String query = "", temp;
        BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
        while ((temp = br.readLine()) != null)
            query += temp + " ";
        br.close();
        return query;
    }

    public static void main (String[] args) throws Exception {
        long[][] times = new long[1000][9];
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                for (int k = 0; k < 1000; k++) {
                    times[k][(3 * (i - 1)) + (j - 1)] = timeQuery(i, j);
                }
            }
        }
        toCSV(times);
    }

    private final static String HEADERS = "\"Query_1_Database_1\",\"Query_1_Database_2\",\"Query_1_Database_3\",\"Query_2_Database_1\",\"Query_2_Database_2\",\"Query_2_Database_3\",\"Query_3_Database_1\",\"Query_3_Database_2\",\"Query_3_Database_3\"";

    // ugly code, but it aint stupid if it works
    private static void toCSV (long[][] times) throws Exception {
        PrintWriter bw = new PrintWriter(new FileWriter(new File("../results.csv")));
        bw.println(HEADERS);
        for (int i = 0; i < 1000; i++) {
            bw.println(times[i][0] + "," + times[i][1] + "," + times[i][2] + "," + times[i][3] + "," + times[i][4] + ","
                    + times[i][5] + "," + times[i][6] + "," + times[i][7] + "," + times[i][8]);
        }
        bw.close();
    }
}
