package com.purplesq.sagar.tourmanager;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo-pc on 07-Jan-16.
 */
public class SLocation implements Parcelable {
    public final double lat;
    public final double lng;


    public SLocation(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lng);
    }

    protected SLocation(Parcel in) {
        this.lat = in.readDouble();
        this.lng = in.readDouble();
    }

    public static final Parcelable.Creator<SLocation> CREATOR = new Parcelable.Creator<SLocation>() {
        public SLocation createFromParcel(Parcel source) {
            return new SLocation(source);
        }

        public SLocation[] newArray(int size) {
            return new SLocation[size];
        }
    };
}
