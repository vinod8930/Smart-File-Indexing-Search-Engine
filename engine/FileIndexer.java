package engine;

import model.FileRecord;
import util.Fileutils;

import java.io.IOException;
import java.nio.file.*;
import java.time.Instant;
import java.util.*;

public class FileIndexer {

    private Map<String, List<FileRecord>> nameIndex = new HashMap<>();
    private Map<String, List<FileRecord>> extensionIndex = new HashMap<>();

    public void indexDirectory(String root) throws IOException {
        Files.walk(Paths.get(root))
                .filter(Files::isRegularFile)
                .forEach(this::addFile);
    }

    private void addFile(Path path) {
        try {
            long size = Files.size(path);
            String ext = Fileutils.getExtension(path).toLowerCase();
            Instant modified = Files.getLastModifiedTime(path).toInstant();

            FileRecord record = new FileRecord(path, size, ext, modified);

            String name = path.getFileName().toString().toLowerCase();

            nameIndex.computeIfAbsent(name, k -> new ArrayList<>()).add(record);
            extensionIndex.computeIfAbsent(ext, k -> new ArrayList<>()).add(record);

        } catch (IOException e) {
            System.out.println("Error reading: " + path);
        }
    }

    public Map<String, List<FileRecord>> getNameIndex() {
        return nameIndex;
    }

    public Map<String, List<FileRecord>> getExtensionIndex() {
        return extensionIndex;
    }
}
