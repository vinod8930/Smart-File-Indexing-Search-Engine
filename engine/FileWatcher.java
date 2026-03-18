package engine;

import java.io.IOException;
import java.nio.file.*;

public class FileWatcher implements Runnable {

    private String root;

    public FileWatcher(String root) {
        this.root = root;
    }

    @Override
    public void run() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Paths.get(root).register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY
            );

            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println("Change detected: " + event.context());
                }
                key.reset();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
