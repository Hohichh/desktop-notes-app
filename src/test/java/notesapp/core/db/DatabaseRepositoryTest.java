package notesapp.core.db;


import io.hohichh.notesapp.core.db.IMediaDAO;
import io.hohichh.notesapp.core.db.INoteDAO;
import io.hohichh.notesapp.core.db.IRepository;
import io.hohichh.notesapp.core.db.sqlite.Connector;
import io.hohichh.notesapp.core.db.sqlite.MediaDAO;
import io.hohichh.notesapp.core.db.sqlite.NoteDAO;
import io.hohichh.notesapp.core.db.sqlite.Repository;
import io.hohichh.notesapp.core.model.Media;
import io.hohichh.notesapp.core.model.Note;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//todo чекнуть добавление медиа инстансов (покрыть лайны с добавлением медиа)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Database test cases")
public class DatabaseRepositoryTest {
    private IRepository repo;

    @BeforeAll
    public void initTestDB(){
        Connector.useDataBase("file:testdb?mode=memory&cache=shared&trace=true");
        INoteDAO noteDAO = new NoteDAO();
        IMediaDAO mediaDAO = new MediaDAO();
        repo = new Repository(noteDAO, mediaDAO);
    }

    @Test
    public void createNoteTest(){
        Note note = new Note();
        for (int i = 0; i < 10; i++){
            Media mediaObj = new Media(
                    UUID.randomUUID(),
                    note.getId(),
                    "path",
                    "[" + i + "]"
            );
            note.addMedia(mediaObj);
        }

        repo.create(note);
    }

    @Test
    public void deleteNoteTest(){
        Note note = new Note();
        for (int i = 0; i < 10; i++){
            Media mediaObj = new Media(
                    UUID.randomUUID(),
                    note.getId(),
                    "path",
                    "[" + i + "]"
            );
            note.addMedia(mediaObj);
        }

        repo.create(note);
        repo.delete(note.getId());
    }

    @Test
    public void updateNoteTest(){
        Note note = new Note();
        for (int i = 0; i < 10; i++){
            Media mediaObj = new Media(
                    UUID.randomUUID(),
                    note.getId(),
                    "path",
                    "[" + i + "]"
            );
            note.addMedia(mediaObj);
        }

        repo.create(note);
        note.setTitle("New Title");
        repo.update(note);
    }

    @Test
    public void getNoteTest(){
        Note note = new Note();
        for (int i = 0; i < 10; i++){
            Media mediaObj = new Media(
                    UUID.randomUUID(),
                    note.getId(),
                    "path",
                    "[" + i + "]"
            );
            note.addMedia(mediaObj);
        }

        repo.create(note);
        Note newNote = repo.get(note.getId());
    }

    @Test
    public void getAllNotesTest(){
        for(int i = 0; i < 3; i++){
            Note note = new Note();
            for (int j = 0; j < 10; j++){
                Media mediaObj = new Media(
                        UUID.randomUUID(),
                        note.getId(),
                        "path",
                        "[" + i + "]"
                );
                note.addMedia(mediaObj);
            }
            repo.create(note);
        }
        List<Note> notes = repo.getAll();
    }

    @AfterAll
    public void clearTestDB(){
        try (Connection conn = Connector.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM media");
            stmt.execute("DELETE FROM notes");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
