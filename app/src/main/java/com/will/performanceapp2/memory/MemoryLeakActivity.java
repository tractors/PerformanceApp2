package com.will.performanceapp2.memory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.will.performanceapp2.R;

public class MemoryLeakActivity extends AppCompatActivity implements CallBack {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoryleak);

        ImageView imageView = findViewById(R.id.iv_memoryleak);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.splash);
        imageView.setImageBitmap(bitmap);

        CallBackManager.addCallBack(this);
    }

    @Override
    public void dpOperate() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //这句清空回调数据
//        CallBackManager.removeCallBack(this);
    }
}