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
//todo: подумать над инкапсуляцией методов коллекций в полях
public class Note {
    protected final UUID id;
    protected String title;
    protected String content;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected final List<Media> mediaContent;

    public Note(){
        id = UUID.randomUUID();
        title = "";
        content = "";
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
        mediaContent = new ArrayList<>();
    }

    @Builder
    public Note(UUID id, String title, String content,
                LocalDateTime createdAt, LocalDateTime updatedAt,
                List<Media> mediaContent){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.mediaContent = mediaContent;
    }

    public void addMedia(Media media) {
        mediaContent.add(media);
    }

    public void removeMedia(Media media) {
        mediaContent.remove(media);
    }

    public void removeMedia(UUID id) {
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
