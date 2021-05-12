package presentationLayer.controllers;

import businessLayer.entities.maps.*;
import presentationLayer.views.MapView;

public class MapCotroller{

    private Map map;
    private Cell cell;
    private Mobility mobility;
    private MapView mv;

    public MapCotroller(Map map,MapView mv){
        this.map = map;
        this.mv = mv;
        System.out.println(map.getWidth());
        System.out.println(map.getHeight());
    }

    public int getWidth(){
        return map.getWidth();
    }

    public int getHeight(){
        return map.getHeight();
    }

    public int trobaCela (int x, int y){
        for (int i = 0; i < map.getCells().size(); i++){
            if(map.getCells().get(i).getX() == x || map.getCells().get(i).getY() == y){
                return i;
            }
        }
        return -1;
    }

    public String getType(int i){
        return map.getCells().get(i).getType();
    }

    public String getColor(int i){
        return map.getCells().get(i).getColor();
    }

   /* public int getMobility(int i, String dir){

    }*/
}

