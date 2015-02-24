package net.com.io.ihd.model;

/**
 * Created by root1 on 2/24/15.
 */
public class Posts {

    String nameMovie;
    String imageMovie;
    String urlVideolMovie;
    String createTime;

    public Posts(String nameMovie,String imageMovie,String urlVideolMovie,String createTime){
        this.nameMovie = nameMovie;
        this.imageMovie = imageMovie;
        this.urlVideolMovie = urlVideolMovie;
        this.createTime = createTime;

    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getImageMovie() {
        return imageMovie;
    }

    public void setImageMovie(String imageMovie) {
        this.imageMovie = imageMovie;
    }

    public String getUrlVideolMovie() {
        return urlVideolMovie;
    }

    public void setUrlVideolMovie(String urlVideolMovie) {
        this.urlVideolMovie = urlVideolMovie;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
