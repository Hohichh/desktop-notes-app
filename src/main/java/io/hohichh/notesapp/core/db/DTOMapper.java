package io.hohichh.notesapp.core.db;

import io.hohichh.notesapp.core.model.Media;
import io.hohichh.notesapp.core.model.Note;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

public class DTOMapper {
    public static void noteToStatement(final Note note,
                                       final PreparedStatement ps) throws SQLException {
        ps.setString(1, note.getId().toString());
        ps.setString(2, note.getTitle());
        ps.setString(3, note.getContent());
        ps.setLong(4, note.getCreatedAt()
                .atZone(ZoneId.systemDefault()).toEpochSecond()
        );
        ps.setLong(5, note.getUpdatedAt()
                .atZone(ZoneId.systemDefault()).toEpochSecond()
        );
    }
    public static Note resultSetToNote(final ResultSet rsNote, ResultSet rsMedia)
            throws SQLException {
        Note note = null;
        if(rsNote.next()){
            note = new Note(UUID.fromString(
                    rsNote.getString("id")));
            note.setTitle(rsNote.getString("title"));
            note.setContent(rsNote.getString("content"));
            note.setCreatedAt(LocalDateTime.ofInstant(
                    Instant.ofEpochSecond(rsNote.getLong("created_at")),
                    ZoneId.systemDefault()
            ));
            note.setUpdatedAt(LocalDateTime.ofInstant(
                    Instant.ofEpochSecond(rsNote.getLong("updated_at")),
                    ZoneId.systemDefault()
            ));

            while(rsMedia.next()){
                Media mediaObj = new Media(
                        UUID.fromString(rsNote.getString("id")),
                        UUID.fromString(rsNote.getString("note_id")),
                        rsMedia.getString("path"),
                        rsMedia.getString("insert_label")
                );
                note.addMedia(mediaObj);
            }
        }
        return note;
    }
    public static void mediaToStatement(final List<Media> media,
                                        final PreparedStatement ps) throws SQLException {
        for(Media mediaObj : media){
            ps.setString(1, mediaObj.getId().toString());
            ps.setString(2, mediaObj.getNoteId().toString());
            ps.setString(3, mediaObj.getPath());
            ps.setString(4, mediaObj.getInsertLabel());
            ps.addBatch();
        }
    }
}
