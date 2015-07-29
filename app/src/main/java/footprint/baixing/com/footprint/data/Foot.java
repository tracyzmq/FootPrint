package footprint.baixing.com.footprint.data;

import java.io.Serializable;

/**
 * Created by zhangtracy on 15/7/25.
 */
public class Foot implements Serializable {
    private String url;
    private String title;
    private String subTitle;
    private int id;
    private int like;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
