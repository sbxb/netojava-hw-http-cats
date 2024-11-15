import com.fasterxml.jackson.annotation.JsonProperty;

public class CatFact {
    private String id;
    private String text;
    private String type;
    private String user;
    private Integer upvotes;

    public CatFact(
            @JsonProperty String id,
            @JsonProperty String text,
            @JsonProperty String type,
            @JsonProperty String user,
            @JsonProperty Integer upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    public CatFact() {}

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public String getUser() {
        return user;
    }

    public Integer getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "CatFact{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", upvotes=" + upvotes +
                '}';
    }
}