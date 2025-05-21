package io.hohichh.notesapp.core.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
abstract public class Note {
    protected final UUID id;
    protected String title;
    protected String content;
    protected LocalDateTime createdAt; //todo: почитать про хранение времени в бд, про работу с localdatetime
    protected LocalDateTime updatedAt;

    public Note(UUID id){
        this.id = id;
    }
}
