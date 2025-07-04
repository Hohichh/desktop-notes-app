package io.hohichh.notesapp.core.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class TypeMap {
    public static LocalDateTime obj(long timestamp){
        return LocalDateTime.ofInstant(
                Instant.ofEpochSecond(timestamp),
                ZoneId.systemDefault());
    }

    public static UUID obj(String uuid){
        return UUID.fromString(uuid);
    }

    public static long num(final LocalDateTime ldt){
        return ldt.atZone(
                ZoneId.systemDefault()).toEpochSecond();
    }

    public static String str(final UUID uuid){
        return uuid.toString();
    }
}
