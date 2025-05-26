package io.hohichh.notesapp.core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static io.hohichh.notesapp.core.db.InitTablesQueries.*;

public class SQLiteDBManager {
    private static String DB_URL= "jdbc:sqlite:notes.db";

    private SQLiteDBManager() {}

    public static void useDataBase(String dbName){
        DB_URL = "jdbc:sqlite:" + dbName;
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initTables() throws SQLException{
        try(Connection conn = SQLiteDBManager.getConnection();
            Statement stmt = conn.createStatement();
                ) {
            stmt.execute(CREATE_NOTES_TABLE);
            stmt.execute(CREATE_IMAGES_TABLE);
        }
    }
}
