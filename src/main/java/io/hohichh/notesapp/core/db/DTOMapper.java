package io.hohichh.notesapp.core.db;

import io.hohichh.notesapp.core.model.Media;
import io.hohichh.notesapp.core.model.Note;

import java.sql.ResultSet;
import java.sql.Statement;

public class DTOMapper {
    public static void noteToStatement(final Note note, final Statement statement) {

    }
    public static Note resultSetToNote(final ResultSet resultSet) {
        return null;
    }
    public static void mediaToStatement(final Media media, final Statement statement) {

    }
    public static Media resultSetToMedia(final ResultSet resultSet) {
        return null;
    }
}
