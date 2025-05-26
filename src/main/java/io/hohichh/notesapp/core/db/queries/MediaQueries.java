package io.hohichh.notesapp.core.db.queries;

public class MediaQueries {
    public static final String CREATE_MEDIA =
            "INSERT INTO media " +
                    "(id, note_id, path, insert_label) " +
                    "VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_MEDIA_BY_NOTE_ID =
            "SELECT (id, note_id, path, insert_label) " +
                    "FROM media " +
                    "WHERE note_id = ?";
    public static final String DELETE_MEDIA_BY_NOTE_ID =
            "DELETE FROM media " +
                    "WHERE note_id = ?";
    public static final String DELETE_MEDIA =
            "DELETE FROM media " +
                    "WHERE id = ?";
}
