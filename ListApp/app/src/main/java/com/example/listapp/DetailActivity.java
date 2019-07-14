package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent in = new Intent();
        //in.getIntExtra("com.example.detail",-1); // its not get the value from MainActivity.class// just keep the defaultValue
        int index = (int) getIntent().getExtras().getInt("com.example.detail");

        Log.e("index in DetailActivity", index+"");

         if (index > -1){
             int pic = getImg(index);
             ImageView img = (ImageView) findViewById(R.id.imageView);

             scaleImg(img,pic);
         }
    }

    private int getImg(int index){
        switch(index){
            case 0: return R.drawable.peach;
            case 1: return R.drawable.tomato;
            case 2: return R.drawable.squach;
            default: return -1;
        }
    }

    private void scaleImg(ImageView img, int pic){

        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),pic,options);

        int imgWigth = options.outWidth;
        int screenWigth = screen.getWidth();

        if (imgWigth > screenWigth){
            int ratio = Math.round((float)imgWigth/ (float) screenWigth);
            options.inSampleSize =ratio;

        }
        options.inJustDecodeBounds= false;
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(),pic, options);
        img.setImageBitmap(scaledImg);

    }

}
