package io.hohichh.notesapp.core.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Media {
    protected final UUID id;
    protected final UUID noteId;
    protected String path;
    protected String insertLabel;

    public Media(UUID id, UUID noteId, String path, String insertLabel) {
        this.id = id;
        this.noteId = noteId;
        this.path = path;
        this.insertLabel = insertLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media mediaDTO = (Media) o;
        return id.equals(mediaDTO.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
