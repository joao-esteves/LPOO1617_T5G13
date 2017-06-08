package com.mygdx.tetris.facebook;

import com.badlogic.gdx.Gdx;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;


/**
 * Created by joaof on 31/05/2017.
 */

public class FB {
    private String appId = "1333570703393561";
    private String appSecret = "a86d5fd7a1503aefc9656dc204ec60f2";
    private String redirectUri = "https://www.facebook.com/connect/login_success.html";

    private FacebookClient facebookClient;
    private ScopeBuilder scopeBuilder;

    public FB() {
        scopeBuilder = new ScopeBuilder();
        facebookClient = new DefaultFacebookClient(Version.VERSION_2_9);
    }

    public void login() {
        String loginDialogUrl = facebookClient.getLoginDialogUrl(appId, redirectUri, scopeBuilder);
        Gdx.net.openURI(loginDialogUrl);
    }

    public String getName() {
        User me = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "first_name,last_name"));
        return me.getName();
    }

//    public void shareScore(int score) {
//        FacebookType publishMessageResponse = facebookClient.publish("me/feed", FacebookType.class, Parameter.with("message", "RestFB test"), Parameter.with("link", "http://www.google.com"));
//    }
}
