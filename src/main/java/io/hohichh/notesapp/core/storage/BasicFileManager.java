package io.hohichh.notesapp.core.storage;

import io.hohichh.notesapp.core.exceptions.StorageException;
import io.hohichh.notesapp.core.storage.loaders.FileLoader;
import io.hohichh.notesapp.core.storage.loaders.FileLoaderFactory;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Stream;

public class BasicFileManager implements FileManager{
    public BasicFileManager() {}

    @Override
    public void save(Object obj, String path) {
        switch(obj.getClass().getName()){
            case "Image" -> {
                FileLoader<Image> loader = FileLoaderFactory.getLoader(Image.class);
                loader.serialize((Image)obj, path);
            }
            default -> {
                throw new StorageException("Can't serialize current object: " + obj.getClass().getName());
            }
        }
    }

    @Override
    public Object load(String path) {
        String ext = getExtension(path);
        switch(ext.toLowerCase(Locale.ROOT)){
            case "png", "jpg", "jpeg", "gif", "bmp", "wbmp" -> {
                FileLoader<Image> loader = FileLoaderFactory.getLoader(Image.class);
                return loader.deserialize(path);
            }
            default -> throw new IllegalArgumentException("Unsupported media type " + ext);
        }
    }

    @Override
    public void delete(String path) {
        Path file = Paths.get(path);
        if(Files.exists(file)) {
            if(Files.isDirectory(file)) deleteDirectory(file);
            else deleteFile(file);
        }
    }

    private void deleteFile(Path file) throws StorageException {
        try{
            Files.deleteIfExists(file);
        } catch (IOException e){
            throw new StorageException("Can't delete file: " + e.getMessage(), e);
        }
    }

    private void deleteDirectory(Path dirPath) throws StorageException {
        try(Stream<Path> paths = Files.walk(dirPath)){
            paths.sorted(Comparator.reverseOrder())
                    .forEach( path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            throw new StorageException("Can't delete directory: " + path, e);
                        }
                    });
        }catch (IOException e){
            throw new StorageException("Can't delete directory: " + dirPath.toString(), e);
        }
    }

    private String getExtension(final String path){
        int dotInd = path.lastIndexOf('.');
        return (dotInd != -1 || dotInd < path.length()-1)
                ? path.substring(dotInd+1)
                : "";
    }
}
