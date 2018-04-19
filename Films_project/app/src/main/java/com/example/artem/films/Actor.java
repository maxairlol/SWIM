package com.example.artem.films;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Artem on 2018-04-12.
 */

public class Actor implements Parcelable{
    private String name;
    private int year;
    private int icon;

    public Actor(String name, int year, int icon) {
        this.name = name;
        this.year = year;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    //write object values to parcel for storage
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(name);
        dest.writeInt(year);
        dest.writeInt(icon);
    }

    //constructor used for parcel
    public Actor(Parcel parcel){
        name = parcel.readString();
        year = parcel.readInt();
        icon = parcel.readInt();
    }

    //creator - used when un-parceling our parcle (creating the object)
    public static final Parcelable.Creator<Actor> CREATOR = new Parcelable.Creator<Actor>(){

        @Override
        public Actor createFromParcel(Parcel parcel) {
            return new Actor(parcel);
        }

        @Override
        public Actor[] newArray(int size) {
            return new Actor[0];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }
}

