package io.hohichh.notesapp.core.db.sqlite;

import io.hohichh.notesapp.core.db.INoteDAO;
import io.hohichh.notesapp.core.model.Media;
import io.hohichh.notesapp.core.model.Note;
import static io.hohichh.notesapp.core.db.queries.NoteQueries.*;
import static io.hohichh.notesapp.core.util.TypeMap.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//todo: оформить вывод исключений
public class NoteDAO implements INoteDAO {
    public NoteDAO() {
    }

    @Override
    public void create(Note note) throws SQLException {
        var conn = Connector.getConnection();
        conn.setAutoCommit(false);

        try(var ps = conn.prepareStatement(CREATE_NOTE)) {
            ps.setString(1, note.getId().toString());
            ps.setString(2, note.getTitle());
            ps.setString(3, note.getContent());
            ps.setLong(4, num(note.getCreatedAt()));
            ps.setLong(5, num(note.getUpdatedAt()));
            ps.executeUpdate();

            conn.commit();
        }catch (SQLException e) {
            conn.rollback();
            throw new SQLException(e);
        }
    }

    @Override
    public void update(Note note) throws SQLException {
        var conn = Connector.getConnection();
        conn.setAutoCommit(false);

        try(var ps = conn.prepareStatement(UPDATE_NOTE)){
            ps.setString(1, note.getTitle());
            ps.setString(2, note.getContent());
            ps.setLong(3, num(note.getUpdatedAt()));
            ps.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw new SQLException(e);
        }
    }

    @Override
    public void delete(UUID id) throws SQLException {
        var conn = Connector.getConnection();
        conn.setAutoCommit(false);

        try(var ps = conn.prepareStatement(DELETE_NOTE)){
            ps.setString(1, id.toString());
            ps.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw new SQLException(e);
        }
    }

    @Override
    public Note get(UUID id) throws SQLException {
        var conn = Connector.getConnection();
        conn.setAutoCommit(true);

        Note note = null;
        try(var ps = conn.prepareStatement(SELECT_NOTE)){
            ps.setString(1, id.toString());
            var rs = ps.executeQuery();

            if(rs.next()) {
                note = new Note(
                        obj(rs.getString("id")),
                        rs.getString("title"),
                        rs.getString("content"),
                        obj(rs.getLong("created_at")),
                        obj(rs.getLong("update_at")),
                        new ArrayList<Media>()
                );
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return note;
    }

    @Override
    public List<Note> getAll() throws SQLException {
        var conn = Connector.getConnection();
        conn.setAutoCommit(true);

        List<Note> notes = new ArrayList<>();
        try(var ps = conn.prepareStatement(SELECT_ALL_NOTES)){
            var rs = ps.executeQuery();

            while(rs.next()) {
                notes.add(new Note(
                        obj(rs.getString("id")),
                        rs.getString("title"),
                        rs.getString("content"),
                        obj(rs.getLong("updated_at")),
                        obj(rs.getLong("created_at")),
                        new ArrayList<Media>()
                        )
                );
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        return notes;
    }
}
