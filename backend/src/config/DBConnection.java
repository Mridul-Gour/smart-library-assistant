package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1"; // Or your DB URL
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        props.setProperty("oracle.jdbc.timezoneAsRegion", "false"); // Oracle sometimes misbehaves without this
        props.setProperty("oracle.jdbc.autoCommitSpecCompliant", "false"); // Commit behavior

        Connection conn = DriverManager.getConnection(URL, props);

        // Optional sanity check
        System.out.println("DB connected: " + (conn != null && !conn.isClosed()));

        return conn;
    }
}
