package com.demo.moviedbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private static String JSON_URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=68d92eb03776345576ee9a901e9ed479&language=en-US&page=1";


    List<MovieModelClass> movieList;
    RecyclerView recyclerView;
    ImageView img_header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recycle_view);

        img_header = (ImageView)findViewById(R.id.img_header);

        Glide.with(this)
                .load(R.drawable.header_reel)
                .into(img_header);

        GetData getData = new GetData();
        getData.execute();
    }

    public class GetData extends AsyncTask<String , String , String>
    {

        @Override
        protected String doInBackground(String... strings)
        {
            String current = "";

            try
            {
                URL url;
                HttpURLConnection urlConnection = null;

                try
                {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while (data != -1)
                    {
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;
                }
                catch (MalformedURLException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    if (urlConnection != null)
                    {
                        urlConnection.disconnect();
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }


            return current;
        }

        @Override
        protected void onPostExecute(String s)
        {
            try
            {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0 ; i<jsonArray.length() ; i++)
                {

                    JSONObject JSONObject1 = jsonArray.getJSONObject(i);

                    MovieModelClass model = new MovieModelClass();
                    model.setId_rating(JSONObject1.getString("vote_average"));
                    model.setName(JSONObject1.getString("title"));
                    model.setImg(JSONObject1.getString("poster_path"));
                    model.setCard_id(JSONObject1.getString("id"));

                    movieList.add(model);

                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            //super.onPostExecute(s);

            PutDataIntoRecyclerView(movieList);
        }

    }

    private void PutDataIntoRecyclerView(List<MovieModelClass> movieList)
    {
        Adaptery adaptery = new Adaptery(this,movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adaptery);
    }
}