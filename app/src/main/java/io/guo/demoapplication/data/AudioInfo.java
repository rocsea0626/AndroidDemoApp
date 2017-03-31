package io.guo.demoapplication.data;

public class AudioInfo {

    private final int id;
    private final long size;
    private final String uri;
    private final long duration;
    private final String artist;
    private final String title;

    public AudioInfo(int id, long size, String uri, long duration, String artist, String title){

        this.id = id;
        this.size = size;
        this.uri = uri;
        this.duration = duration;
        this.artist = artist;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public long getSize() {
        return size;
    }

    public String getUri() {
        return uri;
    }

    public long getDuration() {
        return duration;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "AudioInfo{" +
                "id=" + id +
                ", size=" + size +
                ", uri='" + uri + '\'' +
                ", duration=" + duration +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
