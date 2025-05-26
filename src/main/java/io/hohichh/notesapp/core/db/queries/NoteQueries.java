package io.hohichh.notesapp.core.db.queries;

public class NoteQueries {
    public static final String CREATE_NOTE =
            "INSERT INTO notes " +
            "(id, title, content, created_at, updated_at) " +
            "VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_NOTE =
            "SELECT (id, title, content, created_at, updated_at) " +
            "FROM notes " +
            "WHERE id = ?";
    public static final String UPDATE_NOTE =
            "UPDATE notes " +
            "SET title = ?, " +
            "content = ?, " +
            "updated_at = ? " +
            "WHERE id = ?";
    public static final String DELETE_NOTE =
            "DELETE FROM notes " +
            "WHERE id = ?";
    public static final String SELECT_ALL_NOTES =
            "SELECT * FROM notes " +
            "ORDER BY updated_at DESC";
}   