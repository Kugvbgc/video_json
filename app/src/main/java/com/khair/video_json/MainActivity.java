package com.khair.video_json;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();
    HashMap<String,String>hashMap;
    TextView textView;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listview);
        progressBar=findViewById(R.id.progress);

        progressBar.setVisibility(View.VISIBLE);


        String url="https://abulk77912.000webhostapp.com/apps/video.json";
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                progressBar.setVisibility(View.GONE);


                try {
                   for (int x=0;x<jsonArray.length();x++){
                       JSONObject jsonObject=jsonArray.getJSONObject(x);
                       String title=jsonObject.getString("title");
                       String video_id=jsonObject.getString("video_id");

                       hashMap=new HashMap<>();
                       hashMap.put("title",title);
                       hashMap.put("video",video_id);
                       arrayList.add(hashMap);
                   }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                MyAdapter myAdapter=new MyAdapter();
                listView.setAdapter(myAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressBar.setVisibility(View.GONE);


            }
        });

        RequestQueue  requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(arrayRequest);




    }
   //======================================================================================================

    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater= (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView=layoutInflater.inflate(R.layout.item,parent,false);

            TextView textView=myView.findViewById(R.id.tv_title);
            ImageView imageView=myView.findViewById(R.id.item_image);



            HashMap<String,String> hashMap=arrayList.get(position);
            String title=hashMap.get("title");
            String video_id=hashMap.get("video");

            textView.setText(title);



            Picasso.get().load("https://img.youtube.com/vi/"+video_id+"/0.jpg")

                    .into(imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity2.string=video_id;
                    startActivity(new Intent(MainActivity.this, MainActivity2.class));
                }
            });


            return myView;
        }
    }

 //***********************************************************************************************************
}