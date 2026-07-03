package com.k2senterprise.practice.data.structure.songdedup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Demo: Song Deduplication in Java 6
 * Shows adding songs, finding duplicates, and deduplication.
 */
public class SongDeduplicatorMain {
    public static void main(String[] args) {
        SongDeduplicator dedup = new SongDeduplicator();

        // Add songs (some duplicates intentionally)
        dedup.addSong(new Song("1", "Bohemian Rhapsody", "Queen", 354, "A Night at the Opera"));
        dedup.addSong(new Song("2", "Imagine", "John Lennon", 183, "Imagine"));
        dedup.addSong(new Song("3", "Bohemian Rhapsody", "Queen", 354, "A Night at the Opera")); // duplicate
        dedup.addSong(new Song("4", "Stairway to Heaven", "Led Zeppelin", 482, "Led Zeppelin IV"));
        dedup.addSong(new Song("5", "Imagine", "John Lennon", 183, "Imagine")); // duplicate
        dedup.addSong(new Song("6", "Hotel California", "Eagles", 391, "Hotel California"));
        dedup.addSong(new Song("7", "Bohemian Rhapsody", "Queen", 354, "A Night at the Opera")); // another duplicate
        dedup.addSong(new Song("8", "Like a Rolling Stone", "Bob Dylan", 369, "Highway 61 Revisited"));

        System.out.println("=== Song Deduplicator (Java 6) ===\n");

        System.out.println("Original Song List (" + dedup.getOriginalCount() + " songs):");
        List original = new ArrayList();
        for (int i = 0; i < dedup.getOriginalCount(); i++) {
            // Note: no direct iteration API in this simplified version, so we demonstrate conceptually
        }
        printSongs(getOriginalSongs(dedup), 1);

        System.out.println("\n--- Running Deduplication ---");
        List dedupedList = dedup.deduplicate();
        System.out.println("Result: " + dedup.getDedupedCount() + " unique songs (removed " + dedup.getDuplicateCount() + " duplicates)\n");

        System.out.println("Deduplicated Song List:");
        printSongs(dedupedList, 1);

        System.out.println("\nDuplicate Songs Found:");
        List duplicates = dedup.getDuplicates();
        if (duplicates.size() == 0) {
            System.out.println("  (None in this view - duplicates were removed)");
        } else {
            printSongs(duplicates, 1);
        }

        System.out.println("\n--- Grouping by Signature ---");
        Map groups = dedup.groupBySignature();
        java.util.Iterator sigIterator = groups.keySet().iterator();
        int groupNum = 1;
        while (sigIterator.hasNext()) {
            String sig = (String) sigIterator.next();
            List group = (List) groups.get(sig);
            System.out.println("Group " + groupNum + " (count=" + group.size() + "): " + sig);
            printSongs(group, 2);
            groupNum++;
        }

        System.out.println("\n--- Statistics ---");
        System.out.println("Original songs: " + dedup.getOriginalCount());
        System.out.println("Unique songs: " + dedup.getDedupedCount());
        System.out.println("Duplicates removed: " + dedup.getDuplicateCount());
        System.out.println("Dedup ratio: " + String.format("%.1f%%", 
            (dedup.getDuplicateCount() * 100.0 / dedup.getOriginalCount())));
    }

    private static void printSongs(List songs, int indent) {
        String prefix = indent == 1 ? "" : "  ";
        for (int i = 0; i < songs.size(); i++) {
            Song s = (Song) songs.get(i);
            System.out.println(prefix + (i + 1) + ". " + s.getTitle() + " - " + s.getArtist() + 
                             " (" + s.getDurationSeconds() + "s)");
        }
    }

    // Helper to get original songs (simulating internal access)
    private static List getOriginalSongs(SongDeduplicator dedup) {
        List result = new ArrayList();
        for (int i = 0; i < dedup.getOriginalCount(); i++) {
            result.add("Song " + (i + 1));
        }
        return result;
    }
}
