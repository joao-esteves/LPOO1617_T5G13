package com.mygdx.tetris.facebook;

import com.badlogic.gdx.Gdx;
import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultWebRequestor;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.WebRequestor;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.FacebookType;
import com.restfb.FacebookClient.AccessToken;

import java.io.IOException;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by joaof on 31/05/2017.
 */

public class FB {
    private String appId = "1333570703393561";
    private String appSecret = "a86d5fd7a1503aefc9656dc204ec60f2";
    private String redirectUri = "https://www.facebook.com/connect/login_success.html";

    private FacebookClient facebookClient;

    public void login() {
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        if (facebookClient == null) {
            AccessToken appAccessToken = new DefaultFacebookClient(Version.VERSION_2_9).obtainAppAccessToken(appId, appSecret);
//            AccessToken userAccessToken = new DefaultFacebookClient(Version.VERSION_2_9).obtainUserAccessToken(appId, appSecret, redirectUri, );
//            AccessToken extendedAccessToken = new DefaultFacebookClient(Version.VERSION_2_9).obtainExtendedAccessToken(appId, appSecret, userAccessToken.getAccessToken());
            facebookClient = new DefaultFacebookClient(appAccessToken.getAccessToken(), Version.VERSION_2_9);
        //    facebookClient = new DefaultFacebookClient("EAAS84CAPAxkBAMMFgCZAbYITkQJduXd9nCXTPotraaZA1tM6LgDzVeaAxv8Er1HoKRwtlyqP89p4OBwl11el52cEfrSf8Uv2rzgloAQhSeZBc2duvwTwZAo2mbmfUNWyVpxkkh3WT4MnBcibKkL0NlQ0032sL7SENuTd1778hAZDZD", Version.VERSION_2_9);
        }

        String loginDialogUrl = facebookClient.getLoginDialogUrl(appId, redirectUri, scopeBuilder);
//        WebRequestor wr = new DefaultWebRequestor();
//        WebRequestor.Response loginResponse;
//        try {
//            loginResponse = wr.executeGet(loginDialogUrl);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Gdx.net.openURI(loginDialogUrl);

    }

    public void shareScore(int score) {

        FacebookType publishMessageResponse = facebookClient.publish("me/feed", FacebookType.class, Parameter.with("message", "RestFB test"), Parameter.with("link", "http://www.google.com"));
    }
}
