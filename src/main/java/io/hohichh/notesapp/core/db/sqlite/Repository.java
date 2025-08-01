package io.hohichh.notesapp.core.db.sqlite;

import io.hohichh.notesapp.core.db.IMediaDAO;
import io.hohichh.notesapp.core.db.INoteDAO;
import io.hohichh.notesapp.core.db.IRepository;
import io.hohichh.notesapp.core.exceptions.RepositoryException;
import io.hohichh.notesapp.core.model.Media;
import io.hohichh.notesapp.core.model.Note;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import static io.hohichh.notesapp.core.db.queries.InitTablesQueries.CREATE_MEDIA_TABLE;
import static io.hohichh.notesapp.core.db.queries.InitTablesQueries.CREATE_NOTES_TABLE;


public class Repository implements IRepository {
    private final INoteDAO noteDAO;
    private final IMediaDAO mediaDAO;

    public Repository(INoteDAO noteDAO, IMediaDAO mediaDAO)  {
        this.noteDAO = noteDAO;
        this.mediaDAO = mediaDAO;
        try {
            initTables();
        } catch (SQLException e) {
            throw new RepositoryException("Can't create tables: " + e.getMessage(), e);
        }
    }

    @Override
    public void create(Note note)  {
        try{
            noteDAO.create(note);
            mediaDAO.create(note.getMediaContent());
        }catch (SQLException e){
            throw new RepositoryException("\nCan't create note: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(UUID id)  {
        try{
            mediaDAO.delete(id);
            noteDAO.delete(id);
        }catch (SQLException e){
            throw new RepositoryException("Can't delete note: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Note note)  {
        try{
            mediaDAO.delete(note.getId());
            noteDAO.update(note);
            mediaDAO.create(note.getMediaContent());
        }catch (SQLException e){
            throw new RepositoryException("Can't update note: " + e.getMessage(), e);
        }
    }

    @Override
    public Note get(UUID id)  {
        Note note = null;
        try{
            var listOfMedia = mediaDAO.getAll(id);
            note = noteDAO.get(id);

            for(Media media: listOfMedia){
                note.addMedia(media);
            }
        }catch (SQLException e){
            throw new RepositoryException("Can't get note: " + e.getMessage(), e);
        }
        return note;
    }

    @Override
    public List<Note> getAll()  {
        List<Note> notes = null;
        try{
            notes = noteDAO.getAll();
        }catch (SQLException e){
            throw new RepositoryException("Can't get list of notes: " + e.getMessage(), e);
        }
        return notes;
    }

    private void initTables() throws SQLException {
        var conn = Connector.getConnection();
        conn.setAutoCommit(false);

        try(Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_NOTES_TABLE);
            stmt.execute(CREATE_MEDIA_TABLE);

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        }
    }

}
