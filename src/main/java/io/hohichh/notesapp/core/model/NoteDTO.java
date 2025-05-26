package io.hohichh.notesapp.core.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class NoteDTO extends Note {

    public NoteDTO() {
        super(UUID.randomUUID());
    }

    public void addImage(ImageDTO image) {
        mediaContent.add(image);
    }

    public void removeImage(ImageDTO image) {
        mediaContent.remove(image);
    }

    public void removeImage(UUID id) {
        mediaContent.removeIf(image -> image.getId().equals(id));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteDTO noteDTO = (NoteDTO) o;
        return getId().equals(noteDTO.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
