package com.example.parcelabledemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Details implements Parcelable {

    private String name;
    private String tech;
    private String location;




    public Details(String name, String tech, String location) {
        this.name = name;
        this.tech = tech;
        this.location = location;

    }


    protected Details(Parcel in) {
        name = in.readString();
        tech = in.readString();
        location = in.readString();

    }

    public static final Creator<Details> CREATOR = new Creator<Details>() {
        @Override
        public Details createFromParcel(Parcel in) {
            return new Details(in);
        }

        @Override
        public Details[] newArray(int size) {
            return new Details[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getTech() {
        return tech;
    }

    public String getLocation() {
        return location;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(tech);
        dest.writeString(location);

    }
}
