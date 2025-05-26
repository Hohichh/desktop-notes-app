package io.hohichh.notesapp.core.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
public class ImageDTO extends Media{

    public ImageDTO(UUID noteId, String notePath, String insertLabel) {
        super(UUID.randomUUID(), noteId, notePath, insertLabel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDTO imageDTO = (ImageDTO) o;
        return getNoteId().equals(imageDTO.getNoteId());
    }

    @Override
    public int hashCode() {
        return getNoteId().hashCode();
    }
}
