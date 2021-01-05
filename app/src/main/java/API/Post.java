package API;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int userId;
    private String id;
    private String title;

    @SerializedName("body")
    private String text;

    public int getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
