package io.hohichh.notesapp.core.storage.loaders;

import io.hohichh.notesapp.core.exceptions.ImageLoadException;

import javafx.scene.image.Image;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class FXImageLoader implements FileLoader<Image>  {
    @Override
    public Image deserialize(String path) throws ImageLoadException{
        Image img = null;
        try{
            img = new Image(new FileInputStream(path));
            return img;
        }catch (FileNotFoundException e) {
            throw new ImageLoadException("Failed to deserialize image: " + e.getMessage(), e);
        }
    }

    @Override
    public void serialize(Image image, String pathStr) throws ImageLoadException{
        try {
            String fileExtension = extensionByPath(pathStr);
            String[] writers = ImageIO.getWriterFormatNames();
            boolean supported = Arrays.stream(writers)
                    .anyMatch(f -> f.equalsIgnoreCase(fileExtension));

            if (!supported) {
                throw new IllegalArgumentException("Unsupported image format: " + fileExtension);
            }

            BufferedImage buffImg = SwingFXUtils.fromFXImage(image, null);
            Path path = Path.of(pathStr);
            Path parent = path.getParent();

            if (parent != null && Files.notExists(parent)) {
                Files.createDirectories(parent);
            }

            if (!ImageIO.write(buffImg, fileExtension, path.toFile())) {
                throw new IOException("Error while writing image to file: " + pathStr);
            }

        } catch (IllegalArgumentException | IOException e) {
            throw new ImageLoadException("Failed to serialize image: " + e.getMessage(), e);
        }
    }

    private String extensionByPath(String path) throws IllegalArgumentException{
        String ext = "";
        int idx = path.lastIndexOf(".");
        if (idx == -1 || idx > path.length()-1) {
            throw new IllegalArgumentException("Invalid path string: can't define file extension");
        }
        ext = path.substring(idx+1).toLowerCase();
        return ext;
    }
}
