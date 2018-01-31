package com.example.kazukoba.jsonimportlistviewsample;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager assetManager = getApplicationContext().getResources().getAssets();
        try {
            //ファイルの読み込み
            InputStream inputStream = assetManager.open("shop.json");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder strbuilder = new StringBuilder();

            //読み込んだ内容をStringに変換
            String inputStr;
            while ((inputStr = bufferedReader.readLine()) !=null) {
                strbuilder.append(inputStr);
            }
            inputStream.close();
            bufferedReader.close();

            //JSONオブジェクトに変換
            JSONObject jsonObject = new JSONObject(strbuilder.toString());
            JSONArray datas = jsonObject.getJSONArray("data");
            Log.d("jsonObject",datas.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
