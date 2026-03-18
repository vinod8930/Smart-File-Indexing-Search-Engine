package engine;

import model.FileRecord;

import java.util.Collections;
import java.util.List;

public class FileSearcher {

    private FileIndexer indexer;

    public FileSearcher(FileIndexer indexer) {
        this.indexer = indexer;
    }

    public List<FileRecord> searchByName(String name) {
        return indexer.getNameIndex()
                .getOrDefault(name.toLowerCase(), Collections.emptyList());
    }

    public List<FileRecord> searchByExtension(String ext) {
        return indexer.getExtensionIndex()
                .getOrDefault(ext.toLowerCase(), Collections.emptyList());
    }
}
