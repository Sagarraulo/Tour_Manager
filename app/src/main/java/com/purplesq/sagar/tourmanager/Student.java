package com.purplesq.sagar.tourmanager;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenovo-pc on 07-Jan-16.
 */
public class Student implements Parcelable {
    public final String name;
    public final String email;
    public final String phone;

    public Student(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.phone);
    }

    protected Student(Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
