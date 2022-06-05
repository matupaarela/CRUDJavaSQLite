package system.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionDAO {
    private String stringConn;
    private Connection conn = null;
    public ConnectionDAO() {
        stringConn = "jdbc:sqlite:D:/Descargas/Documents/SQLite/crud-test.s3db";
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(stringConn);
            //System.out.println("Connection OK!");
        } catch (Exception e){
            System.out.println("Connection Filed: " + e.getMessage());
        }
    }

    public boolean executeNonQuery(String sqlQuery) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            preparedStatement.execute();
            return true;
        } catch (Exception e){
            System.out.println("Query execute failed: " + e.getMessage());
            return false;
        }
    }

    public ResultSet reader(String sqlQuery) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            return preparedStatement.executeQuery();
        } catch (Exception e){
            System.out.println("Query reader failed: " + e.getMessage());
            return null;
        }
    }
}
