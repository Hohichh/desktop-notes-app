package io.hohichh.notesapp.core;

import io.hohichh.notesapp.core.db.Repository;
import io.hohichh.notesapp.core.exceptions.StorageException;
import io.hohichh.notesapp.core.model.ImageWrapper;
import io.hohichh.notesapp.core.model.Media;
import io.hohichh.notesapp.core.model.Note;
import io.hohichh.notesapp.core.storage.FileManager;
import javafx.scene.image.Image;

import java.util.List;


public class BasicNotebook implements Notebook {
    private final Repository repository;
    private final FileManager fileManager;

    public BasicNotebook(Repository repository, FileManager fileManager) {
        this.repository = repository;
        this.fileManager = fileManager;
    }

    @Override
    public void createNote(Note note) {
        try{
            var mediaContent = note.getMediaContent();
            for(var media : mediaContent) {
                if (media instanceof ImageWrapper wrapper) {
                    var image = wrapper.getImage();
                    fileManager.save(image, wrapper.getPath());
                }
            }
            repository.createNote(note);
        } catch (StorageException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateNote(Note note) {
        try{
            Note oldNote = repository.getNote(note.getId().toString());
            for(var media : oldNote.getMediaContent()) {
                fileManager.delete(media.getPath());
            }

            repository.updateNote(note);
            for(var media : note.getMediaContent()) {
                if (media instanceof ImageWrapper wrapper) {
                    var image = wrapper.getImage();
                    fileManager.save(image, wrapper.getPath());
                }
            }
        } catch (StorageException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Note getNote(String id) {
        try{
            Note note = repository.getNote(id);
            List<Media> media = note.getMediaContent();
            for (var mediaObj: media){
                var obj = fileManager.load(mediaObj.getPath());
                if(obj instanceof Image image){
                    ImageWrapper wrapper = new ImageWrapper(mediaObj);
                    wrapper.setImage(image);
                }
            }
            return note;
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
            //todo перепроверить пути
            fileManager.delete(id);
            repository.deleteNote(id);
        } catch (StorageException e) {
            throw new RuntimeException(e);
        }
    }
}
