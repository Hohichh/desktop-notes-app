package io.hohichh.notesapp.core.exceptions;

public class SqliteRepException extends StorageException{
    public SqliteRepException(){
        super();
    }

    public SqliteRepException(String message) {
        super(message);
    }

    public SqliteRepException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqliteRepException(Throwable cause) {
        super(cause);
    }
}
