package umn.ac.bigboss.modelauth;

import java.util.HashMap;
import java.util.Map;

public class FCMRequest {
    String to;
    Map<String, Object> common;

    public FCMRequest(String to) {
        this.to = to;
        common = new HashMap<>();
    }
}
