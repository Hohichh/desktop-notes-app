package io.hohichh.notesapp.core.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ImageDTO extends Media{
    private String insertLabel;
    public ImageDTO(UUID note_id, String note_path) {
        super(UUID.randomUUID(),note_id,note_path);
    }
}
