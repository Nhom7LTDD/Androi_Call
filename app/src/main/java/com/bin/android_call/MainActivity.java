package com.bin.android_call;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CALL=1;
    private EditText textnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textnumber=findViewById(R.id.edit_text);
        ImageView imageCall=findViewById(R.id.image_phone);

        imageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
            else{
                Toast.makeText(this,"Permission Denied ",Toast.LENGTH_SHORT).show();
            }
        }
    }


    private  void makePhoneCall(){
        String number=textnumber.getText().toString();
        if(number.trim().length() >0) {
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new  String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
                }else{
                    String dial="tel:" +number;
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }
        } else{
            Toast.makeText(MainActivity.this,"Nhap so dien thoai",Toast.LENGTH_SHORT);

        }
    }
}
