package io.hohichh.notesapp.core.db.queries;

public class InitTablesQueries {
    public static final String CREATE_NOTES_TABLE =
            "CREATE TABLE IF NOT EXISTS notes ("
            + "id TEXT NOT NULL PRIMARY KEY, "
            + "title TEXT NOT NULL, "
            + "content TEXT NOT NULL, "
            + "created_at INTEGER NOT NULL, "
            + "updated_at INTEGER NOT NULL );";

    public static final String CREATE_MEDIA_TABLE =
            "CREATE TABLE IF NOT EXISTS media ("
            + "id TEXT NOT NULL PRIMARY KEY, "
            + "note_id TEXT NOT NULL, "
            + "path TEXT NOT NULL, "
            + "insert_label TEXT NOT NULL );";
}
