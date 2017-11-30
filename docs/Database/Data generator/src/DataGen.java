import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import au.com.bytecode.opencsv.CSVReader;

/**
 *
 * @author sqlitetutorial.net
 */
public class DataGen {
    private final static String[] depts = { "A&E", "Cardiology", "Imaging", "ENT", "Surgery", "Gynaecology",
            "Neurology", "Physiotherapy" };

    private static Connection connect () {
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
    public static void insertPatient (int id, String name, long phone, String add, String birthday, String Gender,
            String alrg, int docid, int illid) {
        String sql = "INSERT INTO Patient(Patient_ID,Patient_Name,Phone,Address,Birthday,Gender,Allergies_History,Doctor_ID,Illness_ID) VALUES(?,?,?,?,?,?,?,?,?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setLong(3, phone);
            pstmt.setString(4, add);
            pstmt.setString(5, birthday);
            pstmt.setString(6, Gender);
            pstmt.setString(7, alrg);
            pstmt.setInt(8, docid);
            pstmt.setInt(9, illid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertDoctor (int id, String name, long phone, String add, String birthday, int exp,
            String dept) {
        String sql = "INSERT INTO Doctor(Doctor_ID,Doctor_Name,Phone,Address,Birthday,Experiences_Year,Department) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setLong(3, phone);
            pstmt.setString(4, add);
            pstmt.setString(5, birthday);
            pstmt.setInt(6, exp);
            pstmt.setString(7, dept);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertIllness (int id, String name, String dept, String sympt, int emer) {
        String sql = "INSERT INTO Illness(Illness_ID,Illness_Name,Department,Symptoms,Emergency_Level) VALUES(?,?,?,?,?)";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, dept);
            pstmt.setString(4, sympt);
            pstmt.setInt(5, emer);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args
     *            the command line arguments
     * @throws IOException
     */
    public static void main (String[] args) throws IOException {
        ArrayList<Long> PhoneNos = GeneratePhoneNos(10000);
        ArrayList<String> names = GenerateNames(10000);
        ArrayList<String> diseases = GenerateDiseases(1000);
        ArrayList<String> symptoms = GenerateSymptoms(1000);
        ArrayList<String> docnames = new ArrayList<String>();
        ArrayList<String[]> Allergies = new ArrayList<String[]>(
                new CSVReader(new FileReader(new File("etc/alrg.csv"))).readAll());
        ArrayList<String[]> datadoc = new ArrayList<String[]>(
                new CSVReader(new FileReader(new File("etc/random.csv"))).readAll());
        ArrayList<String[]> datapat = new ArrayList<String[]>(
                new CSVReader(new FileReader(new File("etc/random1.csv"))).readAll());
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            String name = names.remove(r.nextInt(names.size()));
            docnames.add(name);
            insertDoctor(i + 1, name, PhoneNos.remove(r.nextInt(PhoneNos.size())), datadoc.get(i)[0], datadoc.get(i)[1],
                    Integer.parseInt(datadoc.get(i)[2]), depts[r.nextInt(depts.length)]);

            insertIllness(i + 1, diseases.remove(r.nextInt(diseases.size())), depts[r.nextInt(depts.length)],
                    symptoms.remove(r.nextInt(symptoms.size())), r.nextInt(11));
        }
        for (int i = 0; i < 1000; i++) {
            String name = names.remove(r.nextInt(names.size()));
            insertPatient(i + 1, name, PhoneNos.remove(r.nextInt(PhoneNos.size())), datapat.get(i)[0],
                    datapat.get(i)[1], datapat.get(i)[2], Allergies.get(i)[0], r.nextInt(100) + 1, r.nextInt(100) + 1);
        }

        // dump(names);
    }

    private static ArrayList<String> GenerateDiseases (int i) throws IOException {
        ArrayList<String> Diseases = new ArrayList<>();
        Random r = new Random(System.currentTimeMillis());
        FileReader f = new FileReader(new File("etc/disease.txt"));
        BufferedReader br = new BufferedReader(f);
        String s;
        for (int j = 0; j < i; j++) {
            for (int k = 0; k < r.nextInt(20); k++)
                br.readLine();
            s = br.readLine().split(" ")[1];
            if (!Diseases.contains(s))
                Diseases.add(s);
        }
        br.close();
        return Diseases;
    }

    private static ArrayList<String> GenerateSymptoms (int i) throws IOException {
        ArrayList<String> Diseases = new ArrayList<>();
        Random r = new Random(System.currentTimeMillis());
        FileReader f = new FileReader(new File("etc/sym.txt"));
        BufferedReader br = new BufferedReader(f);
        String s;
        for (int j = 0; j < i; j++) {
            for (int k = 0; k < r.nextInt(20); k++)
                br.readLine();
            s = br.readLine();
            Diseases.add(s.split("^[^\\s]*\\s")[1]);
        }
        br.close();
        return Diseases;
    }

    private static void dump (ArrayList<String> a) {
        for (String s : a) {
            System.out.println(s);
        }

    }

    private static ArrayList<String> GenerateNames (int i) throws IOException {
        ArrayList<String> n = new ArrayList<String>();
        Random r = new Random(System.currentTimeMillis());
        CSVReader f = new CSVReader(new FileReader(new File("etc/first.csv")));
        CSVReader l = new CSVReader(new FileReader(new File("etc/last.csv")));
        ArrayList<String[]> first = new ArrayList<String[]>(f.readAll());
        ArrayList<String[]> last = new ArrayList<String[]>(l.readAll());
        for (int k = 0; k < i; k++) {
            n.add(first.get(r.nextInt(first.size()))[0] + " " + last.get(r.nextInt(last.size()))[0]);
        }
        f.close();
        l.close();
        return n;

    }

    private static ArrayList<Long> GeneratePhoneNos (int i) {
        ArrayList<Long> temp = new ArrayList<>();
        Random r = new Random(System.currentTimeMillis());
        for (int j = 0; j < i; j++)
            temp.add(Math.abs(r.nextLong() % (new Long("9999999999") - 2000000000 + 1)));
        return temp;
    }

}