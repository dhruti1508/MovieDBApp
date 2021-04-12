package com.demo.moviedbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MovieDetails extends AppCompatActivity
{

    String JSON_URL;

    TextView txt_title,txt_rs_date,txt_or_lng,txt_rating,txt_tagline,txt_pop,txt_overview;
    String s_title,s_release_date,s_or_lng,s_vote_average,s_tagline,s_popularity,s_overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent i1 = getIntent();
        String id = i1.getStringExtra("id");
        JSON_URL = "https://api.themoviedb.org/3/movie/"+id+"?api_key=68d92eb03776345576ee9a901e9ed479&language=en-US";

        txt_title = (TextView)findViewById(R.id.txt_title);
        txt_rs_date = (TextView)findViewById(R.id.txt_rs_date);
        txt_or_lng = (TextView)findViewById(R.id.txt_or_lng);
        txt_rating = (TextView)findViewById(R.id.txt_rating);
        txt_tagline = (TextView)findViewById(R.id.txt_tagline);
        txt_pop = (TextView)findViewById(R.id.txt_pop);
        txt_overview = (TextView)findViewById(R.id.txt_overview);


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
                //JSONArray jsonArray = jsonObject.getJSONArray("results");

                s_title = jsonObject.getString("title");
                s_release_date = jsonObject.getString("release_date");
                s_or_lng = jsonObject.getString("original_language");
                s_vote_average = jsonObject.getString("vote_average");
                s_tagline = jsonObject.getString("tagline");
                s_popularity = jsonObject.getString("popularity");
                s_overview = jsonObject.getString("overview");

                txt_title.setText(s_title);
                txt_rs_date.setText(s_release_date);
                txt_or_lng.setText(s_or_lng);
                txt_rating.setText(s_vote_average);
                txt_tagline.setText(s_tagline);
                txt_pop.setText(s_popularity);
                txt_overview.setText(s_overview);


            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

    }




}