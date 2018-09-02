package how.remote;

public class RA {
    private final String name;
    private final long id;

    public RA(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
