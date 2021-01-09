package com.example.luckyday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    String leaderboard;
    public static final String EXTRA_MESSAGE = "com.example.luckyday.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("CFREATTTTTED");

    }
    public void sendMessage(View view) {
        System.out.println("Hello there!");
        Intent intent = new Intent(this, CoinFlipActivity.class);
        EditText editText = (EditText) findViewById(R.id.yourName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
    public void showLeaderboard(View view) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String leader="";
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpGet httpget = new HttpGet("http://192.168.2.58/getLeaderboard.php");
                    HttpResponse response = httpclient.execute(httpget);
                    String responseString = EntityUtils.toString(response.getEntity());
                    String[]arr = responseString.split("\"\\],\\[\"");
                    ;
                    for(int i=0; i<arr.length; i++) {
                        String temp = arr[i];
                        if(i == 0) {
                            temp = temp.substring(3,temp.length());
                        }
                        if(i == arr.length-1) {
                            temp = temp.substring(0,temp.length()-3);
                        }
                        leader += temp + "\n";

                    }
                    setLeaderboard(leader);
                } catch(Exception e){
                    Log.e("log_tag", "Error in http connection "+e.toString());
                }
            }
        });
        thread.start();

        ((TextView)findViewById(R.id.leaderboard)).setText(leaderboard);

    }
    public void setLeaderboard (String leaderb) {
        leaderboard = leaderb;
    }
}