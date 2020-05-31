package com.example.guide;

import java.util.HashMap;
import java.util.Map;

public class guide {

    public String guide_place;
    public String days;

    public guide(){ }

    public guide(String guide_place, String days){

        this.guide_place = guide_place;
        this.days = days;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("guide place", guide_place);
        result.put("days", days);

        return result;
    }
}
