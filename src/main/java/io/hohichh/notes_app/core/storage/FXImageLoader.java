package io.hohichh.notes_app.core.storage;

import javafx.scene.image.Image;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.Arrays;

public class FXImageLoader implements Serializer<Image>, Deserializer<Image> {
    @Override
    public Image deserialize(String path) throws FileNotFoundException {
        return new Image(new FileInputStream(path));
    }

    @Override
    public void serialize(Image image, String path) {
        try{
            String fileExtension = extensionByPath(path);
            String[] writers = ImageIO.getWriterFormatNames();
            boolean supported = Arrays.stream(writers)
                    .anyMatch(f -> f.equalsIgnoreCase(fileExtension));
            if (!supported){
                throw new IllegalArgumentException("Unsupported image format: " + fileExtension);
            }

//            BufferedImage buffImg =

        }catch (IllegalArgumentException e){
            return;
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
