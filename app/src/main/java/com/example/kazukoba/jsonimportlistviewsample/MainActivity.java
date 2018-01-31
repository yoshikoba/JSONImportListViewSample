package com.example.kazukoba.jsonimportlistviewsample;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> dataList = new ArrayList<>();

    ListView listView;

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
            for (int i = 0; i < datas.length(); i++) {
                JSONObject data = datas.getJSONObject(i);
                // 名前を取得
                String shopName = data.getString("shopName");
                dataList.add(shopName);
            }

            Log.d("jsonObject",datas.toString());

            listView = findViewById(R.id.listView);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataList);

            listView.setAdapter(arrayAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
