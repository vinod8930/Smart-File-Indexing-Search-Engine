package util;

import java.nio.file.Path;

public class Fileutils {

    public static String getExtension(Path path) {
        String name = path.getFileName().toString();
        int dot = name.lastIndexOf(".");
        return dot == -1 ? "" : name.substring(dot + 1);
    }
}
