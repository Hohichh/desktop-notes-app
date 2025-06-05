package io.hohichh.notesapp.core.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public final class ImageWrapper extends Media{
    private Image image;

    public ImageWrapper(UUID id, UUID noteId, String path, String insertLabel) {
        super(id, noteId, path, insertLabel);
    }

    public ImageWrapper(Media media){
        super(media.getId(), media.getNoteId(), media.getPath(), media.getInsertLabel());
    }

    public ImageView getImageView() {
        return new ImageView(image);
    }
}
