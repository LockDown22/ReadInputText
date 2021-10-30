package com.example.bai68;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView hienthi;
    Button doc,ghi;
    EditText nhap;
    Boolean w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }
    public void init(){
        hienthi = findViewById(R.id.tv1);
        doc = findViewById(R.id.btnRead);
        ghi = findViewById(R.id.btnWrite);
        nhap = findViewById(R.id.edt1);

        doc.setOnClickListener(this);
        ghi.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRead:{
                xulyRead();
                break;
            }
            case R.id.btnWrite:{
                xulyWrite();
                break;
            }


        }
    }



    public void xulyWrite(){

        try {
            @SuppressLint("SdCardPath") String url = "/sdcard/hello.txt";
            File file = new File(url);
            w = file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutPut = new OutputStreamWriter(fOut, StandardCharsets.UTF_8);
            myOutPut.append(nhap.getText());
            myOutPut.close();
            fOut.close();
            Toast.makeText(MainActivity.this, "Luu Thanh cong vao SDCARD", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void xulyRead() {

        try {
            String url = "/sdcard/hello.txt";
            File file = new File(url);
            FileInputStream fIn = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fIn);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder builder = new StringBuilder();
            String data;
            while ((data = bufferedReader.readLine()) != null){
                builder.append(data);
            }

            fIn.close();
            inputStreamReader.close();
            hienthi.setText(builder.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}