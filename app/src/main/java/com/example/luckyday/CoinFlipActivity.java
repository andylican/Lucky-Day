package com.example.luckyday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.InputStream;
import java.net.HttpCookie;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class CoinFlipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_flip);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.yourName2);
        textView.setText(message);
    }
    public void onFlip(View v)
    {
        int rand = (int)(Math.random()*2);
        if(rand == 0) {
            ((TextView)findViewById(R.id.Coin)).setText("H");
            String newScore = (Integer.parseInt(((TextView)findViewById(R.id.score)).getText().toString()) + 1)+"";
            ((TextView)findViewById(R.id.score)).setText(newScore);
        } else {
            ((TextView)findViewById(R.id.Coin)).setText("T");
           // ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            //nameValuePairs.add(new BasicNameValuePair("year","1980"));
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try{
                        HttpClient httpclient = new DefaultHttpClient();
                        //HttpGet httpget = new HttpGet("https://192.168.2.58/insertScore.php");

                        System.out.println("GENERAL");
                        /*
                        HttpPost httppost = new HttpPost("http://192.168.2.58/insertScore.php?name=abc&score=6");
                        Intent intent = getIntent();

                         String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
                        int score = Integer.parseInt(((TextView)findViewById(R.id.score)).getText().toString());
                        HttpEntity entity = MultipartEntityBuilder
                                .create()
                                .addTextBody("number", "5555555555")
                                .addTextBody("clip", "rickroll")
                                .addBinaryBody("upload_file", new File(filePath), ContentType.create("application/octet-stream"), "filename")
                                .addTextBody("tos", "agree")
                                .build();

                        HttpPost httpPost = new HttpPost("http://some-web-site");
                        httpPost.setEntity(entity);
                        HttpResponse response = httpclient.execute(httppost);
                        System.out.println(response);
                        //HttpEntity entity = response.getEntity();
                        //InputStream is = entity.getContent();

                         */
                        Intent intent = getIntent();

                        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
                        int score = Integer.parseInt(((TextView)findViewById(R.id.score)).getText().toString());
                        HttpPost httppost = new HttpPost("http://192.168.2.58/insertScore.php");
                        List<NameValuePair> form = new ArrayList<>();
                        form.add(new BasicNameValuePair("name", message));
                        form.add(new BasicNameValuePair("score", score+""));

                        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form);
                        httppost.setEntity(entity);
                        HttpResponse response = httpclient.execute(httppost);
                        finish();
                    }catch(Exception e){
                        Log.e("log_tag", "Error in http connection "+e.toString());
                    }
                }
            });

            thread.start();


        }
    }
}