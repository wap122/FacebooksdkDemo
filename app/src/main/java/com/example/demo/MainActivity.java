package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);

        loginButton = (LoginButton) findViewById(R.id.loginbutton);
        loginButton.setReadPermissions(Collections.singletonList("user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Đã đăng xuất", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void onClick(View view) {
        final Bundle bundle = new Bundle();
        bundle.putString("fields","gender");
        final GraphRequest request = new GraphRequest(AccessToken.getCurrentAccessToken(),
                 "/me/taggable_friends",
                null, HttpMethod.GET, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                JSONObject object = response.getJSONObject();
//                try {
//                    JSONArray array = object.getJSONArray("data");
//                    String name =  array.getJSONObject(0).getString("name");
//
//                    new GraphRequest(AccessToken.getCurrentAccessToken(), "search?type=user&q=Tran Hoang Son"
//                            , null, HttpMethod.GET, new GraphRequest.Callback() {
//                        @Override
//                        public void onCompleted(GraphResponse response) {
//                            Toast.makeText(MainActivity.this, "ahihi", Toast.LENGTH_SHORT).show();
//                        }
//                    }).executeAsync();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        });
        request.executeAsync();
        GraphRequest request2 = GraphRequest.newMyFriendsRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONArrayCallback() {
                    @Override
                    public void onCompleted(JSONArray array, GraphResponse response) {
                        Toast.makeText(MainActivity.this, "ahihi do cho", Toast.LENGTH_SHORT).show();
                    }
                });

        request2.executeAsync();
    }
}
