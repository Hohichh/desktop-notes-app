package io.hohichh.notesapp.core;

import io.hohichh.notesapp.core.db.Repository;
import io.hohichh.notesapp.core.exceptions.StorageException;
import io.hohichh.notesapp.core.model.Note;

import java.util.List;


//todo:подумать как скомпоновать репозиторий с стореджем картинок
public class BasicNotebook implements Notebook {
    private final Repository repository;

    public BasicNotebook(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void createNote(Note note) {
        try{
            repository.createNote(note);
        } catch (StorageException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateNote(Note note) {
        try{
            repository.updateNote(note);
        } catch (StorageException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Note getNote(String id) {
        try{
            return repository.getNote(id);
        } catch (StorageException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Note> getAllNotes() {
        try{
            return repository.getAllNotes();
        } catch (StorageException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteNote(String id) {
        try{
            repository.deleteNote(id);
        } catch (StorageException e) {
            throw new RuntimeException(e);
        }
    }
}
