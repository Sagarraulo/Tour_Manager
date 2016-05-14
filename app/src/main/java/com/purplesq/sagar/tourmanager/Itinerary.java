package com.purplesq.sagar.tourmanager;

import android.os.Parcel;
import android.os.Parcelable;

public class Itinerary implements Parcelable {
    public final long date;
    public final int day;
    public final String title;
    public final String start_time;
    public final String description;
    public final SLocation location;

    public Itinerary(int date, int day, String title, String start_time, String description, SLocation location) {
        this.date = date;
        this.day = day;
        this.title = title;
        this.start_time = start_time;
        this.description = description;
        this.location = location;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.date);
        dest.writeInt(this.day);
        dest.writeString(this.title);
        dest.writeString(this.start_time);
        dest.writeString(this.description);
        dest.writeParcelable(this.location, 0);
    }

    protected Itinerary(Parcel in) {
        this.date = in.readLong();
        this.day = in.readInt();
        this.title = in.readString();
        this.start_time = in.readString();
        this.description = in.readString();
        this.location = in.readParcelable(SLocation.class.getClassLoader());
    }

    public static final Parcelable.Creator<Itinerary> CREATOR = new Parcelable.Creator<Itinerary>() {
        public Itinerary createFromParcel(Parcel source) {
            return new Itinerary(source);
        }

        public Itinerary[] newArray(int size) {
            return new Itinerary[size];
        }
    };
}
