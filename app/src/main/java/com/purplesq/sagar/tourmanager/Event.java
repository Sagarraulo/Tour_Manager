package com.purplesq.sagar.tourmanager;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo-pc on 07-Jan-16.
 */
public class Event implements Parcelable {
    public final String id;
    public final String name;
    public final String overview;
    public final String city;
    public final int days;
    public final String thumbnail;
    public final Itinerary[] itineraries;
    public final Student[] students;

    public Event(String id, String name, String overview, String city, int days, String thumbnail, Itinerary[] itineraries, Student[] students) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.city = city;
        this.days = days;
        this.thumbnail = thumbnail;
        this.itineraries = itineraries;
        this.students = students;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.overview);
        dest.writeString(this.city);
        dest.writeInt(this.days);
        dest.writeString(this.thumbnail);
        dest.writeTypedArray(this.itineraries, 0);
        dest.writeTypedArray(this.students, 0);
    }

    protected Event(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.overview = in.readString();
        this.city = in.readString();
        this.days = in.readInt();
        this.thumbnail = in.readString();
        this.itineraries = (Itinerary[]) in.createTypedArray(Itinerary.CREATOR);
        this.students = (Student[]) in.createTypedArray(Student.CREATOR);
    }

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}
