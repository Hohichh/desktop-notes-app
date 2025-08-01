package io.hohichh.notesapp.core;

import io.hohichh.notesapp.core.db.IRepository;
import io.hohichh.notesapp.core.exceptions.StorageException;
import io.hohichh.notesapp.core.model.ImageWrapper;
import io.hohichh.notesapp.core.model.Media;
import io.hohichh.notesapp.core.model.Note;
import io.hohichh.notesapp.core.storage.FileManager;

import static io.hohichh.notesapp.core.util.TypeMap.*;

import javafx.scene.image.Image;

import java.util.List;
import java.util.UUID;

//todo: проверить что при обновлении заметки корректно меняется время обновления
public class BasicNotebook implements Notebook {
    private final IRepository repository;
    private final FileManager fileManager;

    public BasicNotebook(IRepository repository, FileManager fileManager) {
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
            repository.create(note);
        } catch (StorageException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateNote(Note note) {
        try{
            Note oldNote = repository.get(note.getId());
            for(var media : oldNote.getMediaContent()) {
                fileManager.delete(media.getPath());
            }

            repository.update(note);
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
    public Note getNote(UUID id) {
        try{
            Note note = repository.get(id);
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
            return repository.getAll();
        } catch (StorageException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteNote(UUID id) {
        try{
            //todo перепроверить пути
            fileManager.delete(str(id));
            repository.delete(id);
        } catch (StorageException e) {
            throw new RuntimeException(e);
        }
    }
}
