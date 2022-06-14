package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("4DyLBcKLAvNAZWkoEr4m3SDX85f7SXBQvhljE4eM")
                .clientKey("3EUNZESuciVR2i27k2jkfxhd88ogW4s2h4X7X1ra")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
