/*
 * Experiment 4: JDBC (Java Database Connectivity)
 *
 * Database setup (run once in MySQL before running this program):
 *
 *   CREATE DATABASE college;
 *   USE college;
 *   CREATE TABLE student(
 *       id INT PRIMARY KEY,
 *       name VARCHAR(30),
 *       branch VARCHAR(20)
 *   );
 *
 * Requires the MySQL Connector/J jar (JDBC driver) on the classpath.
 * Change the username/password below to match your MySQL installation.
 */

import java.sql.*;

public class JdbcDemo {

    public static void main(String args[]) throws Exception {

        // Step 1: Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Step 2: Make the connection
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/college",
                "root",
                "root");

        // Step 3: Insert a record using PreparedStatement
        PreparedStatement ps = con.prepareStatement(
                "insert into student values(?,?,?)");

        ps.setInt(1, 101);
        ps.setString(2, "Rahul");
        ps.setString(3, "CSE");

        ps.executeUpdate();
        System.out.println("Record Inserted");

        // Step 4: Read all records using Statement and ResultSet
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from student");

        while (rs.next()) {
            System.out.println(
                    rs.getInt(1) + " "
                    + rs.getString(2) + " "
                    + rs.getString(3));
        }

        // Step 5: Close the connection
        con.close();
    }
}