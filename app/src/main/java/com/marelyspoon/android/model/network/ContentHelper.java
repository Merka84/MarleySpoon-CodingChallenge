package com.marelyspoon.android.model.network;

import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.marelyspoon.android.model.Recipe;

/**
 * Created on 2019-07-21, 14:48.
 *
 * @author Akram Shokri
 */
public class ContentHelper {

    final static String SPACE_ID = "kk2bw5ojx476";
    final static String  TOKEN = "7ac531648a1b5e1dab6c18b0979f822a5aad0fe5f1109829b8a197eb2be4b84c";

    private CDAClient client = contentDeliveryClient();

    private CDAClient contentDeliveryClient() {
        return CDAClient.builder()
                .setSpace(SPACE_ID)
                .setToken(TOKEN)
                .build();
    }

    public void fetchAll(){

        client.observeAndTransform(Recipe.class)
                .where("content_type", "recipe")
                .include(1)
                .all().blockingFirst();

        /*
         client.fetch(CDAEntry.class)
         .where("content_type", "chef")
                .all();


//client.observeAndTransform(Recipe.class)
//                .where("content_type", "recipe")
//                .include(1)
//                .all().blockingFirst();
client.fetch(CDAEntry.class)
        .where("content_type", "recipe")
        .all();



         */
    }



}
