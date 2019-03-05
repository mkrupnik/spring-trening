package pl.mkrupnik.springtraining;

public class GeoConsummer {

    private final long id;
    private final String content;

    public GeoConsummer(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;

    }
}