package io.hohichh.notesapp.core.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
abstract public class Note {
    protected final UUID id;
    protected String title;
    protected String content;

    public Note(UUID id){
        this.id = id;
    }
}
