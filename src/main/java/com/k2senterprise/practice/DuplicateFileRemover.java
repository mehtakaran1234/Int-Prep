import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DuplicateFileRemover {

    private static final int BUFFER_SIZE = 64 * 1024; // 64KB buffer for large files

    public static void main(String[] args) {
        // Replace with your target directory path
        Path rootDirectory = Paths.get("/path/to/your/music/directory");

        try {
            System.out.println("Step 1: Scanning directory structure...");
            Map<Long, List<Path>> filesBySize = groupFilesBySize(rootDirectory);

            System.out.println("Step 2: Processing potential duplicates...");
            processAndRemoveDuplicates(filesBySize);

            System.out.println("Execution completed successfully.");
        } catch (IOException e) {
            System.err.println("Error processing directory: " + e.getMessage());
        }
    }

    /**
     * Recursively walks the directory tree and groups regular files by their size.
     */
    private static Map<Long, List<Path>> groupFilesBySize(Path rootDir) throws IOException {
        try (Stream<Path> walk = Files.walk(rootDir)) {
            return walk.filter(Files::isRegularFile)
                    .collect(Collectors.groupingBy(path -> {
                        try {
                            return Files.size(path);
                        } catch (IOException e) {
                            return -1L; // Fallback for inaccessible files
                        }
                    }));
        }
    }

    /**
     * Identifies identical files using SHA-256 hashes and removes duplicates.
     */
    private static void processAndRemoveDuplicates(Map<Long, List<Path>> filesBySize) {
        filesBySize.forEach((size, paths) -> {
            // Skip unique sizes or error fallbacks
            if (size <= 0 || paths.size() < 2) {
                return;
            }

            // Group the files of the same size by their SHA-256 hash
            Map<String, List<Path>> filesByHash = paths.stream()
                    .collect(Collectors.groupingBy(DuplicateFileRemover::calculateSHA256));

            // Process each hash group
            filesByHash.forEach((hash, duplicateList) -> {
                if (hash.equals("ERROR") || duplicateList.size() < 2) {
                    return;
                }

                // Keep the first file, delete the remaining duplicates
                Path original = duplicateList.get(0);
                System.out.printf("Found %d duplicate(s) for original file: %s (Size: %d bytes)%n",
                        duplicateList.size() - 1, original.toAbsolutePath(), size);

                for (int i = 1; i < duplicateList.size(); i++) {
                    Path duplicate = duplicateList.get(i);
                    try {
                        System.out.println("  -> Deleting duplicate: " + duplicate.toAbsolutePath());
                        Files.delete(duplicate); // Safe deletion via NIO
                    } catch (IOException e) {
                        System.err.println("  [ERROR] Failed to delete file: " + duplicate + " -> " + e.getMessage());
                    }
                }
            });
        });
    }

    /**
     * Computes SHA-256 hash by streaming data through a fixed buffer.
     * Keeps memory footprint extremely low regardless of file size.
     */
    private static String calculateSHA256(Path filePath) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            try (InputStream is = Files.newInputStream(filePath)) {
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    digest.update(buffer, 0, bytesRead);
                }
            }

            // Convert byte array to hexadecimal string
            byte[] hashBytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException | IOException e) {
            System.err.println("Error hashing file " + filePath + ": " + e.getMessage());
            return "ERROR";
        }
    }
}
