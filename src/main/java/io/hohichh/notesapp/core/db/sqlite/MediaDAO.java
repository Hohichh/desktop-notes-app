package io.hohichh.notesapp.core.db.sqlite;

import io.hohichh.notesapp.core.db.IMediaDAO;
import io.hohichh.notesapp.core.model.Media;

import static io.hohichh.notesapp.core.db.queries.MediaQueries.*;
import static io.hohichh.notesapp.core.util.TypeMap.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MediaDAO implements IMediaDAO {
    public MediaDAO(){}

    @Override
    public void create(List<Media> listOfMedia) throws SQLException {
        var conn = Connector.getConnection();
        conn.setAutoCommit(false);

        try(var ps = conn.prepareStatement(CREATE_MEDIA)){
            for(Media media: listOfMedia){
                ps.setString(1, str(media.getId()));
                ps.setString(2, str(media.getNoteId()));
                ps.setString(3, media.getPath());
                ps.setString(4, media.getInsertLabel());
                ps.addBatch();
            }
            ps.executeBatch();

            conn.commit();
        } catch (SQLException e){
            conn.rollback();
            throw new SQLException("\nError while executing '"
                    + CREATE_MEDIA +"' query: "
                    + e.getMessage(), e);
        }
    }

    @Override
    public void delete(UUID noteId) throws SQLException {
        var conn = Connector.getConnection();
        conn.setAutoCommit(false);

        try(var ps = conn.prepareStatement(DELETE_MEDIA_BY_NOTE_ID)){
            ps.setString(1, str(noteId));
            ps.executeUpdate();

            conn.commit();
        }catch (SQLException e){
            conn.rollback();
            throw new SQLException("\nError while executing '" +
                    DELETE_MEDIA_BY_NOTE_ID +"' query: "
                    + e.getMessage(), e);
        }
    }

    @Override
    public List<Media> getAll(UUID noteId) throws SQLException {
        var conn = Connector.getConnection();
        conn.setAutoCommit(true);

        List<Media> mediaList = new ArrayList<>();
        try(var ps = conn.prepareStatement(SELECT_MEDIA_BY_NOTE_ID)){
            ps.setString(1, str(noteId));
            var rs = ps.executeQuery();

            while(rs.next()){
                mediaList.add(new Media(
                        obj(rs.getString( "id")),
                        obj(rs.getString("note_id")),
                        rs.getString("path"),
                        rs.getString("insert_label")
                        )
                );
            }
        }catch (SQLException e){
            throw new SQLException("\nError while executing '"
                    + SELECT_MEDIA_BY_NOTE_ID +"' query: "
                    + e.getMessage(), e);
        }
        return mediaList;
    }
}
