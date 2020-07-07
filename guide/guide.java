package com.example.guide;

import java.util.HashMap;
import java.util.Map;

public class guide {

    public String guide_place;
    public int num;
    public String place1;
    public String place2;
    public String place3;
    public String place4;
    public String place5;

    public int likes;

    public guide(){ }

    public guide(String guide_place, int num, String[] list){

        this.guide_place = guide_place;
        this.num= num;
        this.place1 = list[0];
        this.place2 = list[1];
        this.place3 = list[2];
        this.place4 = list[3];
        this.place5 = list[4];
        this.likes = 0;
    }

    public String getGuide_place(){
        return this.guide_place;
    }



    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("guide_place", guide_place);
        result.put("num", num);
        result.put("place1",place1);
        result.put("place2",place2);
        result.put("place3",place3);
        result.put("place4",place4);
        result.put("place5",place5);
        result.put("likes",likes);

        return result;
    }
}
