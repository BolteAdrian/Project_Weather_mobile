package com.example.proiectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;
    class MyItem{
        int image;
        String name;
        public MyItem(int image, String name) {
            this.image = image;
            this.name = name;
        }
    }
    private MyItem myitems[] = {
            new MyItem(R.drawable.img3, "three"),
            new MyItem(R.drawable.img2,"two"),
            new MyItem(R.drawable.img1,"one"),
    };
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeTextOnce();
        new LoadWebPageASYNC().execute("");
    }

    private class LoadWebPageASYNC extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... urls) {
            textView = (TextView) findViewById(R.id.item_name);
            imageView = (ImageView) findViewById(R.id.item_picture);
            textView.setText(myitems[0].name);
            imageView.setImageResource(myitems[0].image);
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (currentIndex == myitems.length) break;
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(myitems[currentIndex].name);
                        imageView.setImageResource(myitems[currentIndex].image);
                        currentIndex++;

                    }
                });
            }



            return null;
        }
        @Override
        protected void onPostExecute(String result) {
        }


    }

    //schimba textul daca apasam click pe buton
    public void changeTextOnce() {

        Button changeTextButton = (Button) findViewById(R.id.btn_1);


        changeTextButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent in = new Intent(MainActivity.this,MainActivity2.class);


                    startActivity(in);


            }
        });
    }





}