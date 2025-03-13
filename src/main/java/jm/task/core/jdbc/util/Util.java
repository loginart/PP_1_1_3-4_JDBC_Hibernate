package jm.task.core.jdbc.util;
import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_URL = "jdbc:MySQL://localhost:3306/my_db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "F010994ff010994F";


    public  Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch ( SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
