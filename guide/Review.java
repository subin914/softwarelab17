package com.example.guide;

import java.util.HashMap;
import java.util.Map;

public class Review {
    public int num;
    public String[] review;
    public String[] place_name;

    public Review(){}

    public Review(int _num, String[] _review, String[] _place_name){
        this.num = _num;
        for(int i=0; i<num; i++){
            this.place_name[i] = _place_name[i];
        }
        this.place_name = new String[num];
        this.review = new String[num];

        for(int i=0; i<num; i++){
            place_name[i] = _place_name[i];
        }
        for(int i=0; i<num; i++){
            this.review[i] = _review[i];
        }
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        for(int i=0; i<num; i++){
            result.put(place_name[i],review[i]);
        }

        return result;
    }
}
