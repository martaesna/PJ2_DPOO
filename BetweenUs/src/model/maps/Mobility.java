package model.maps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mobility {

    @SerializedName("left")
    @Expose
    private Integer left;
    @SerializedName("up")
    @Expose
    private Integer up;
    @SerializedName("right")
    @Expose
    private Integer right;
    @SerializedName("down")
    @Expose
    private Integer down;

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Integer getDown() {
        return down;
    }

    public void setDown(Integer down) {
        this.down = down;
    }

}
