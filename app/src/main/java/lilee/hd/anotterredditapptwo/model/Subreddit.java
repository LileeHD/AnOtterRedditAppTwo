package lilee.hd.anotterredditapptwo.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "subreddit_table")
public class Subreddit {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="id")
    private String id;

    @SerializedName("display_name")
    @Expose
    private String name;

    @SerializedName("icon_img")
    @Expose
    private String iconUrl;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("over18")
    @Expose
    private boolean isNsfw;


    public Subreddit() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    @Ignore
    public Subreddit(String name, String iconUrl, String description) {
        this.name = name;
        this.iconUrl = iconUrl;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isNsfw() {
        return isNsfw;
    }

    public void setNsfw(boolean nsfw) {
        isNsfw = nsfw;
    }
}
