package com.k2senterprise.practice.data.structure.songdedup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Java 6 Song Deduplicator - removes duplicate songs from a list.
 * Uses HashMap to track seen songs by signature (title + artist + duration).
 * Keeps first occurrence, removes duplicates.
 */
public class SongDeduplicator {
    private Map seenSignatures; // String -> Song
    private List originalSongs;
    private List dedupedSongs;

    public SongDeduplicator() {
        this.seenSignatures = new HashMap();
        this.originalSongs = new ArrayList();
        this.dedupedSongs = new ArrayList();
    }

    public void addSong(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Song cannot be null");
        }
        originalSongs.add(song);
    }

    public void addAllSongs(List songs) {
        for (int i = 0; i < songs.size(); i++) {
            addSong((Song) songs.get(i));
        }
    }

    public List deduplicate() {
        dedupedSongs.clear();
        seenSignatures.clear();

        for (int i = 0; i < originalSongs.size(); i++) {
            Song song = (Song) originalSongs.get(i);
            String signature = song.getSignature();

            if (!seenSignatures.containsKey(signature)) {
                dedupedSongs.add(song);
                seenSignatures.put(signature, song);
            }
        }

        return dedupedSongs;
    }

    public List getDuplicates() {
        List duplicates = new ArrayList();
        Map localSeen = new HashMap();

        for (int i = 0; i < originalSongs.size(); i++) {
            Song song = (Song) originalSongs.get(i);
            String signature = song.getSignature();

            if (localSeen.containsKey(signature)) {
                duplicates.add(song);
            } else {
                localSeen.put(signature, song);
            }
        }

        return duplicates;
    }

    public Map groupBySignature() {
        Map groups = new HashMap();

        for (int i = 0; i < originalSongs.size(); i++) {
            Song song = (Song) originalSongs.get(i);
            String sig = song.getSignature();

            if (!groups.containsKey(sig)) {
                groups.put(sig, new ArrayList());
            }
            List group = (List) groups.get(sig);
            group.add(song);
        }

        return groups;
    }

    public int getOriginalCount() {
        return originalSongs.size();
    }

    public int getDedupedCount() {
        return dedupedSongs.size();
    }

    public int getDuplicateCount() {
        return getOriginalCount() - getDedupedCount();
    }
}
