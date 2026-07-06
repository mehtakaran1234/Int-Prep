package com.k2senterprise.practice.duplicatefile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class SongDeduplicator {

    private static final int BUFFER_SIZE = 8192; // 8 KB
    private static final int PARTIAL_READ_SIZE = 4 * 1024 * 1024; // 4 MB

    public static void main(String[] args) {
        String rootDir = "/path/to/music/directory"; // Update target directory here

        try {
            System.out.println("Starting duplicate scan...");
            Map<Long, List<Path>> sizeMap = groupFilesBySize(rootDir);
            Map<String, List<Path>> duplicates = findTrueDuplicates(sizeMap);
            deleteDuplicates(duplicates);
            System.out.println("Deduplication process completed.");
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // Phase 1: Group files by size
    private static Map<Long, List<Path>> groupFilesBySize(String rootDir) throws IOException {
        Map<Long, List<Path>> sizeMap = new HashMap<>();

        Files.walkFileTree(Paths.get(rootDir), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (!attrs.isDirectory() && attrs.size() > 0) {
                    long size = attrs.size();
                    sizeMap.computeIfAbsent(size, k -> new ArrayList<>()).add(file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                return FileVisitResult.CONTINUE;
            }
        });

        // Filter out groups that have no potential duplicates
        sizeMap.entrySet().removeIf(entry -> entry.getValue().size() < 2);
        return sizeMap;
    }

    // Phase 2 & 3: Find true duplicates by hashing
    private static Map<String, List<Path>> findTrueDuplicates(Map<Long, List<Path>> sizeMap)
            throws NoSuchAlgorithmException, IOException {
        Map<String, List<Path>> duplicateMap = new HashMap<>();

        for (List<Path> files : sizeMap.values()) {
            for (Path file : files) {
                String hash = generateFileHash(file);
                if (hash != null) {
                    duplicateMap.computeIfAbsent(hash, k -> new ArrayList<>()).add(file);
                }
            }
        }

        // Filter out unique files
        duplicateMap.entrySet().removeIf(entry -> entry.getValue().size() < 2);
        return duplicateMap;
    }

    // Memory-efficient Hashing Strategy ( handles >1GB files )
    private static String generateFileHash(Path file) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        long fileSize = Files.size(file);

        try (InputStream is = Files.newInputStream(file)) {
            // Partial read: Hash first 4MB and last 4MB for faster initial check
            if (fileSize > PARTIAL_READ_SIZE * 2) {
                byte[] buffer = new byte[PARTIAL_READ_SIZE];

                // First 4MB
                is.read(buffer);
                md.update(buffer);

                // Skip to last 4MB
                long bytesToSkip = fileSize - PARTIAL_READ_SIZE;
                long skipped = 0;
                while (skipped < bytesToSkip) {
                    long n = is.skip(bytesToSkip - skipped);
                    if (n == 0) break;
                    skipped += n;
                }

                // Last 4MB
                is.read(buffer);
                md.update(buffer);
            } else {
                // Small file or slightly over limit: stream fully but in chunks
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    md.update(buffer, 0, bytesRead);
                }
            }
        }

        // Convert byte array to string
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // Action Stage: Delete duplicates, keep the first one
    private static void deleteDuplicates(Map<String, List<Path>> duplicateMap) {
        for (List<Path> duplicates : duplicateMap.values()) {
            // Keep the first file, delete the rest
            Path originalFile = duplicates.get(0);
            System.out.println("\nRetaining: " + originalFile);

            for (int i = 1; i < duplicates.size(); i++) {
                Path duplicateFile = duplicates.get(i);
                try {
                    Files.delete(duplicateFile);
                    System.out.println("Deleted duplicate: " + duplicateFile);
                } catch (IOException e) {
                    System.err.println("Failed to delete: " + duplicateFile + " | " + e.getMessage());
                }
            }
        }
    }
}
