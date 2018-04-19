package com.example.artem.films;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 2018-04-11.
 */

public class Film implements Parcelable{
    private String title;
    private String category;
    private int image;
    private ArrayList<Actor> actors = new ArrayList<>();

    public Film(String title, String category, int image, ArrayList<Actor> actors) {
        this.title = title;
        this.category = category;
        this.image = image;
        this.actors = actors;
    }
    //write object values to parcel for storage
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(title);
        dest.writeString(category);
        dest.writeInt(image);
        dest.writeTypedList(actors);
    }

    //constructor used for parcel
    public Film(Parcel parcel){
        title = parcel.readString();
        category = parcel.readString();
        image = parcel.readInt();
        parcel.readTypedList(actors,Actor.CREATOR);
    }

    //creator - used when un-parceling our parcle (creating the object)
    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>(){

        @Override
        public Film createFromParcel(Parcel parcel) {
            return new Film(parcel);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[0];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }


}
