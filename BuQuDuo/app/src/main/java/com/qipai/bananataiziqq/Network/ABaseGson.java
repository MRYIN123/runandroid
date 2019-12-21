package com.qipai.bananataiziqq.Network;

import com.google.gson.Gson;

public class ABaseGson {

    private static volatile Gson gson;
    public static Gson getInstance() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;

    }

}
