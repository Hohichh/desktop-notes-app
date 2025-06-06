package io.hohichh.notesapp.core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static io.hohichh.notesapp.core.db.queries.InitTablesQueries.*;

public class SQLiteDBManager {
    private static String DB_URL= "jdbc:sqlite:notes.db";

    private SQLiteDBManager() {}

    public static void useDataBase(String dbName){
        DB_URL = "jdbc:sqlite:" + dbName;
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initTables() throws SQLException {
        try(Connection conn = SQLiteDBManager.getConnection();
            Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(false);

            stmt.execute(CREATE_NOTES_TABLE);
            stmt.execute(CREATE_MEDIA_TABLE);
            try{
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }
}
