package io.hohichh.notesapp.core;

import io.hohichh.notesapp.core.db.Repository;
import io.hohichh.notesapp.core.exceptions.StorageException;
import io.hohichh.notesapp.core.model.ImageWrapper;
import io.hohichh.notesapp.core.model.Media;
import io.hohichh.notesapp.core.model.Note;
import io.hohichh.notesapp.core.storage.FXImageLoader;
import io.hohichh.notesapp.core.storage.FileLoader;
import io.hohichh.notesapp.core.storage.FileLoaderFactory;
import javafx.scene.image.Image;

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
            var mediaContent = note.getMediaContent();
            for(var media : mediaContent) {
                if (media instanceof ImageWrapper wrapper) {
                    var image = wrapper.getImage();
                    FileLoader<Image> loader = FileLoaderFactory.getLoader(image.getClass());
                    loader.serialize(image, wrapper.getPath());
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
            //todo тут по хорошему куда-то бы вынести логику удаления файлов
            //чтобы потом сохранить новые
            repository.updateNote(note);
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
                String path = mediaObj.getPath();
                String ext = path.substring(path.lastIndexOf(".") + 1);

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
            repository.deleteNote(id);
        } catch (StorageException e) {
            throw new RuntimeException(e);
        }
    }
}
