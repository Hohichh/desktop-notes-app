package io.hohichh.notesapp.core.db;

public class ImageQueries {
    public static final String CREATE_IMAGE =
            "INSERT INTO images " +
                    "(id, note_id, path, insert_label) " +
                    "VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_IMAGES_BY_NOTE_ID =
            "SELECT (id, note_id, path, insert_label) " +
                    "FROM images " +
                    "WHERE note_id = ?";
    public static final String DELETE_IMAGES_BY_NOTE_ID =
            "DELETE FROM images " +
                    "WHERE note_id = ?";
    public static final String DELETE_IMAGE =
            "DELETE FROM images " +
                    "WHERE id = ?";
}
