package businessLayer.entities.maps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * La classe 'Mobility' ens indica les diferents opcions de moviment que tenim
 * d'una cela determinada
 *
 * Els mèttodes implementats retornen els atributs per poder fer comprovacions
 * de moviment dels jugadors
 */
public class Mobility {
    // Attributes
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
    public Integer getUp() {
        return up;
    }
    public Integer getRight() {
        return right;
    }
    public Integer getDown() {
        return down;
    }

}
