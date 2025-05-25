package com.k2senterprise.filehandler_example;

public class MyFile implements IFileHandler {
    int size;
    String name;
    String content;

    MyFile(int size, String name, String content) {
        this.size = size;
        this.name = name;
        this.content = content;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
