package com.wrgroup.homework;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            new Thread(){
                public void run(){
                    sendQuest();
                }
            }.start();
        }catch (Exception e){
            Log.d("wrong","bug!!!!");
        }
    }
    public void StartNewGame(View view){
        Intent myintent=new Intent(MainActivity.this,SelectActivity.class);
        startActivity(myintent);
    }
    public void GetStory(View view){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //builder.setIcon()
        builder.setTitle("背景故事");
        builder.setMessage("在克里特岛上有一座迷宫，迷宫深处藏着怪物米诺陶诺斯和诸多怪物。岛上的" +
                "国王在四处召集勇者来攻略迷宫，而您就是一名应召而来的勇者。请您勇敢的探索迷宫，" +
                "为岛屿的安宁战胜米诺陶诺斯！");
        builder.setPositiveButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    public void GetSetting(View view){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //builder.setIcon()
        builder.setTitle("提示信息");
        builder.setMessage("愤怒药剂：获得持续3回合的攻击Buff\n" +
                "石化药剂：获得持续3回合的防御Buff\n" +
                "生命药剂：回复生命值");
        builder.setPositiveButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    public void sendQuest(){
        String myresult="";
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("judge","myapp");
        String content=jsonObject.toString();
        Log.d("content",content);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        String ur="https://myfgo.top:80";
        URL url = null;
        try {
            url = new URL(ur);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("ser-Agent", "Fiddler");
            conn.setRequestProperty("Content-Type","application/json");
            OutputStream os = conn.getOutputStream();
            os.write(content.getBytes());
            os.close();
            InputStream inStream = conn.getInputStream();
            while ((len = inStream.read(data)) != -1) {
                outStream.write(data, 0, len);
            }
            inStream.close();
            myresult = outStream.toString();
            outStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(myresult!=""){
            Log.d("myserver",myresult);
        }
    }
}
