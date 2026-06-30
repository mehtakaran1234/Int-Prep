package com.k2senterprise.practice.data.structure.songdedup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for SongDeduplicator
 */
public class SongDeduplicatorTest {
    private SongDeduplicator dedup;

    @BeforeEach
    public void setUp() {
        dedup = new SongDeduplicator();
    }

    @Test
    public void testAddSingleSong() {
        Song song = new Song("1", "Imagine", "John Lennon", 183, "Imagine");
        dedup.addSong(song);
        
        assertEquals(1, dedup.getOriginalCount(), "Should have 1 song");
    }

    @Test
    public void testDeduplicateNoDuplicates() {
        dedup.addSong(new Song("1", "Song A", "Artist A", 180, "Album 1"));
        dedup.addSong(new Song("2", "Song B", "Artist B", 200, "Album 2"));
        dedup.addSong(new Song("3", "Song C", "Artist C", 220, "Album 3"));

        List dedupedList = dedup.deduplicate();
        
        assertEquals(3, dedupedList.size(), "No duplicates, all should remain");
        assertEquals(0, dedup.getDuplicateCount(), "No duplicates should be found");
    }

    @Test
    public void testDeduplicateWithDuplicates() {
        dedup.addSong(new Song("1", "Imagine", "John Lennon", 183, "Imagine"));
        dedup.addSong(new Song("2", "Imagine", "John Lennon", 183, "Imagine")); // duplicate
        dedup.addSong(new Song("3", "Imagine", "John Lennon", 183, "Imagine")); // duplicate

        List dedupedList = dedup.deduplicate();
        
        assertEquals(1, dedupedList.size(), "Should have only 1 unique song");
        assertEquals(2, dedup.getDuplicateCount(), "Should have 2 duplicates");
    }

    @Test
    public void testGetDuplicates() {
        dedup.addSong(new Song("1", "Song A", "Artist A", 180, "Album 1"));
        dedup.addSong(new Song("2", "Song A", "Artist A", 180, "Album 1")); // duplicate
        dedup.addSong(new Song("3", "Song B", "Artist B", 200, "Album 2"));
        dedup.addSong(new Song("4", "Song B", "Artist B", 200, "Album 2")); // duplicate

        dedup.deduplicate();
        List duplicates = dedup.getDuplicates();
        
        assertTrue(duplicates.size() > 0, "Should find duplicate songs");
    }

    @Test
    public void testGroupBySignature() {
        dedup.addSong(new Song("1", "Imagine", "John Lennon", 183, "Album 1"));
        dedup.addSong(new Song("2", "Imagine", "John Lennon", 183, "Album 2"));
        dedup.addSong(new Song("3", "Bohemian", "Queen", 354, "Album 3"));
        dedup.addSong(new Song("4", "Bohemian", "Queen", 354, "Album 4"));
        dedup.addSong(new Song("5", "Different", "Artist", 200, "Album 5"));

        Map groups = dedup.groupBySignature();
        
        assertEquals(3, groups.size(), "Should have 3 unique signatures");
        
        String sig1 = "Imagine|John Lennon|183";
        String sig2 = "Bohemian|Queen|354";
        
        assertTrue(groups.containsKey(sig1), "Should contain Imagine signature");
        assertTrue(groups.containsKey(sig2), "Should contain Bohemian signature");
        
        List group1 = (List) groups.get(sig1);
        assertEquals(2, group1.size(), "Imagine group should have 2 songs");
        
        List group2 = (List) groups.get(sig2);
        assertEquals(2, group2.size(), "Bohemian group should have 2 songs");
    }

    @Test
    public void testSongSignature() {
        Song song1 = new Song("1", "Title", "Artist", 180, "Album");
        Song song2 = new Song("2", "Title", "Artist", 180, "Album");
        Song song3 = new Song("3", "Title", "Different Artist", 180, "Album");

        assertEquals(song1.getSignature(), song2.getSignature(), "Same metadata should have same signature");
        assertNotEquals(song1.getSignature(), song3.getSignature(), "Different artist should have different signature");
    }

    @Test
    public void testEmptyDeduplicator() {
        List dedupedList = dedup.deduplicate();
        assertEquals(0, dedupedList.size(), "Empty deduplicator should result in empty list");
        assertEquals(0, dedup.getDedupedCount(), "Should have 0 unique songs");
        assertEquals(0, dedup.getDuplicateCount(), "Should have 0 duplicates");
    }

    @Test
    public void testAddNullSongThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            dedup.addSong(null);
        }, "Should throw exception for null song");
    }

    @Test
    public void testDuplicateCountCalculation() {
        dedup.addSong(new Song("1", "A", "B", 100, "C"));
        dedup.addSong(new Song("2", "A", "B", 100, "C")); // duplicate
        dedup.addSong(new Song("3", "A", "B", 100, "C")); // duplicate
        
        dedup.deduplicate();
        
        assertEquals(3, dedup.getOriginalCount());
        assertEquals(1, dedup.getDedupedCount());
        assertEquals(2, dedup.getDuplicateCount());
    }
}
