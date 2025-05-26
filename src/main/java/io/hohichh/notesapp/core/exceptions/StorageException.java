package io.hohichh.notesapp.core.exceptions;

public class StorageException extends Exception{
    public StorageException(){
        super();
    }

    public StorageException(final String message){
        super(message);
    }

    public StorageException(final String message, final Throwable cause){
        super(message, cause);
    }

    public StorageException(final Throwable cause){
        super(cause);
    }
}
