package pro.viksit.com.viksit.dummy.pojo;

import java.io.Serializable;

/**
 * Created by Akshay on 26/04/2017.
 */

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class StudentRankPOJO implements Serializable {

    private Integer id;
    private String name;
    private String imageURL;
    private Integer batchRank;
    private Integer points;
    private Integer coins;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Integer getBatchRank() {
        return batchRank;
    }

    public void setBatchRank(Integer batchRank) {
        this.batchRank = batchRank;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }
}
