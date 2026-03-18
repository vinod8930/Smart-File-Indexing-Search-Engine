package model;

import java.nio.file.Path;
import java.time.Instant;

public class FileRecord {
    private Path path;
    private long size;
    private String extension;
    private Instant lastModified;

    public FileRecord(Path path, long size, String extension, Instant lastModified) {
        this.path = path;
        this.size = size;
        this.extension = extension;
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return path + " | " + extension + " | " + size + " bytes | " + lastModified;
    }
}
