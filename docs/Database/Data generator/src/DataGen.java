import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sqlitetutorial.net
 */
public class DataGen {

    private Connection connect () {
        // SQLite connection string
        String url = "jdbc:sqlite:test.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Insert a new row into the warehouses table
     *
     * @param name
     * @param capacity
     */
    public void insertPatient (String id, String name, int phone, String add, String birthday, String Gender,
            String alrg, String docid, String illid) {
        String sql = "INSERT INTO Patient(Patient_ID,Patient_Name,Phone,Address,Birthday,Gender,Allergies_History,Doctor_ID,Illness_ID) VALUES(?,?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, name);
            pstmt.setInt(3, phone);
            pstmt.setString(4, add);
            pstmt.setString(5, birthday);
            pstmt.setString(6, Gender);
            pstmt.setString(7, alrg);
            pstmt.setString(8, docid);
            pstmt.setString(9, illid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args
     *            the command line arguments
     */
    public static void main (String[] args) {

        DataGen app = new DataGen();
        // insert three new rows
        app.insert("Raw Materials", 3000);
        app.insert("Semifinished Goods", 4000);
        app.insert("Finished Goods", 5000);
    }

}

class Doctor {

}