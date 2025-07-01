package io.hohichh.notesapp.core.db.sqlite;

import io.hohichh.notesapp.core.model.Media;
import io.hohichh.notesapp.core.model.Note;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DTOMapper {
    public static void noteToCreateStmt(final Note note,
                                       final PreparedStatement ps) throws SQLException {
        ps.setString(1, note.getId().toString());
        ps.setString(2, note.getTitle());
        ps.setString(3, note.getContent());
        ps.setLong(4, toTimestamp(note.getCreatedAt()));
        ps.setLong(5, toTimestamp(note.getUpdatedAt()));
    }

    public static void noteToUpdateStmt(final Note note,
                                        final PreparedStatement ps) throws SQLException {
        ps.setString(1, note.getTitle());
        ps.setString(2, note.getContent());
        ps.setLong(3, toTimestamp(note.getUpdatedAt()));
    }
    public static Note resultSetToNote(final ResultSet rsNote, ResultSet rsMedia)
            throws SQLException {
        Note note = null;
        if(rsNote.next()){
            note = Note.builder()
                    .id(UUID.fromString(rsNote.getString("id")))
                    .title(rsNote.getString("title"))
                    .content(rsNote.getString("content"))
                    .createdAt(toLocalDateTime(rsNote.getLong("created_at")))
                    .updatedAt(toLocalDateTime(rsNote.getLong("updated_at")))
                    .mediaContent(new ArrayList<>())
                    .build();
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

    public static List<Note> resultSetToNoteList(final ResultSet rsNoteList)
        throws SQLException {
        List<Note> noteList = new ArrayList<>();
        while(rsNoteList.next()){
            Note note = Note.builder()
                    .id(UUID.fromString(rsNoteList.getString("id")))
                    .title(rsNoteList.getString("title"))
                    .content(rsNoteList.getString("content"))
                    .createdAt(toLocalDateTime(rsNoteList.getLong("created_at")))
                    .updatedAt(toLocalDateTime(rsNoteList.getLong("updated_at")))
                    .mediaContent(new ArrayList<>())
                    .build();
            noteList.add(note);
        }
        return noteList;
    }
    public static void mediaToCreateStmt(final List<Media> media,
                                        final PreparedStatement ps) throws SQLException {
        for(Media mediaObj : media){
            ps.setString(1, mediaObj.getId().toString());
            ps.setString(2, mediaObj.getNoteId().toString());
            ps.setString(3, mediaObj.getPath());
            ps.setString(4, mediaObj.getInsertLabel());
            ps.addBatch();
        }
    }

    private static LocalDateTime toLocalDateTime(long timestamp){
        return LocalDateTime.ofInstant(
                Instant.ofEpochSecond(timestamp),
                ZoneId.systemDefault());
    }

    private static long toTimestamp(final LocalDateTime ldt){
        return ldt.atZone(
                ZoneId.systemDefault()).toEpochSecond();
    }
}
