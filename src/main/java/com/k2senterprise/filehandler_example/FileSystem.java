package com.k2senterprise.filehandler_example;

public class FileSystem {

    private MyDirectory root;

    FileSystem(){
        this.root = new MyDirectory(0, "/");
    }

    public MyDirectory getRoot() {
        return root;
    }

    public MyDirectory getDirectory(String path){
        return new MyDirectory(0, path);
    }
    public MyFile getFile(String path){
        return new MyFile(0, "file.txt", "content");
    }


}
