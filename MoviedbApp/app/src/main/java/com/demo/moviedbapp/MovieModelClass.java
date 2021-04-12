package com.demo.moviedbapp;

public class MovieModelClass
{
    String id_rating;
    String name;
    String img;
    String card_id;

    String release_date;
    String tagline;
    String popularity;
    String overview;

    public MovieModelClass(String id_rating , String name , String img , String card_id , String release_date , String tagline , String popularity , String overview)
    {
        this.id_rating = id_rating;
        this.name = name;
        this.img = img;
        this.card_id = card_id;
        this.release_date = release_date;
        this.tagline = tagline;
        this.popularity = popularity;
        this.overview = overview;
    }

    public MovieModelClass()
    {

    }


    public String getId_rating()
    {
        return id_rating;
    }
    public void setId_rating(String id_rating)
    {
        this.id_rating = id_rating;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getImg()
    {
        return img;
    }
    public void setImg(String img)
    {
        this.img = img;
    }


    public String getCard_id()
    {
        return card_id;
    }
    public void setCard_id(String card_id)
    {
        this.card_id = card_id;
    }


    public String getRelease_date()
    {
        return release_date;
    }
    public void setRelease_date(String release_date)
    {
        this.release_date = release_date;
    }


    public String getTagline()
    {
        return release_date;
    }
    public void setTagline(String tagline)
    {
        this.tagline = tagline;
    }


    public String getPopularity()
    {
        return popularity;
    }
    public void setPopularity(String popularity)
    {
        this.popularity = popularity;
    }


    public String getOverview()
    {
        return overview;
    }
    public void setOverview(String overview)
    {
        this.overview = overview;
    }

}








