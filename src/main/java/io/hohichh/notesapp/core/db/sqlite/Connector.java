package io.hohichh.notesapp.core.db.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static io.hohichh.notesapp.core.db.queries.InitTablesQueries.*;

public class Connector {
    private static String url = "jdbc:sqlite:notes.db";
    private static Connection conn;

    private Connector() {
    }

    public static void useDataBase(String dbName){
        url = "jdbc:sqlite:" + dbName;
    }

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(url);
            return conn;
        }
        return conn;
    }

    public static void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
