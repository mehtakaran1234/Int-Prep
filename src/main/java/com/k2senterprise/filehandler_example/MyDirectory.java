package com.k2senterprise.filehandler_example;

import java.util.HashMap;
import java.util.Map;

public class MyDirectory implements IFileHandler{
    int size;
    String name;
    private Map<String, IFileHandler> files;

    public MyDirectory(int size, String name) {
        this.size = size;
        this.name = name;
        this.files = new HashMap<>();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    public Map<String, IFileHandler> getFiles() {
        return files;
    }
}
