package io.hohichh.notesapp.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Note {
    protected final UUID id;
    protected String title;
    protected String content;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected final List<Media> mediaContent;

    public Note(UUID id){
        this.id = id;
        title = "";
        content = "";
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
        mediaContent = new ArrayList<>();
    }


    public void addMedia(Media media) {
        mediaContent.add(media);
    }

    public void removeImage(Media media) {
        mediaContent.remove(media);
    }

    public void removeImage(UUID id) {
        mediaContent.removeIf(media -> media.getId().equals(id));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return getId().equals(note.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
