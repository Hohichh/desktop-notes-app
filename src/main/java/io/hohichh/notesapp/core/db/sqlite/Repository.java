package io.hohichh.notesapp.core.db.sqlite;

import io.hohichh.notesapp.core.db.IMediaDAO;
import io.hohichh.notesapp.core.db.INoteDAO;
import io.hohichh.notesapp.core.db.IRepository;
import io.hohichh.notesapp.core.exceptions.SqliteRepException;
import io.hohichh.notesapp.core.model.Note;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import static io.hohichh.notesapp.core.db.queries.InitTablesQueries.CREATE_MEDIA_TABLE;
import static io.hohichh.notesapp.core.db.queries.InitTablesQueries.CREATE_NOTES_TABLE;


public class Repository implements IRepository {
    private INoteDAO noteDAO;
    private IMediaDAO mediaDAO;

    public Repository(INoteDAO noteDAO, IMediaDAO mediaDAO)  {
        this.noteDAO = noteDAO;
        this.mediaDAO = mediaDAO;
        try {
            initTables();
        } catch (SQLException e) {
            throw new SqliteRepException("Can't create tables: " + e.getMessage(), e);
        }
    }

    @Override
    public void create(Note note)  {
        try{

        }catch (SQLException e){

        }
    }

    @Override
    public void delete(UUID id)  {
        try{

        }catch (SQLException e){

        }
    }

    @Override
    public void update(Note note)  {
        try{

        }catch (SQLException e){

        }
    }

    @Override
    public Note get(UUID id)  {
        try{

        }catch (SQLException e){

        }
        return null;
    }

    @Override
    public List<Note> getAll()  {
        try{

        }catch (SQLException e){

        }
        return List.of();
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
