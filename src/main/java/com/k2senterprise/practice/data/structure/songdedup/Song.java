package com.k2senterprise.practice.data.structure.songdedup;

/**
 * Song representation with metadata for deduplication.
 * Two songs are considered duplicates if title, artist, and duration match.
 */
public class Song {
    private String id;
    private String title;
    private String artist;
    private int durationSeconds;
    private String album;

    public Song(String id, String title, String artist, int durationSeconds, String album) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
        this.album = album;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public int getDurationSeconds() { return durationSeconds; }
    public String getAlbum() { return album; }

    public String getSignature() {
        return (title == null ? "" : title) + "|" + 
               (artist == null ? "" : artist) + "|" + 
               durationSeconds;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return id != null && id.equals(song.id);
    }

    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", duration=" + durationSeconds + "s" +
                ", album='" + album + '\'' +
                '}';
    }
}
