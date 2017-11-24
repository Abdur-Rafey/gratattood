package com.gratattood.gratattood.helper;

import android.media.MediaRecorder;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by abdur on 11/1/2017.
 */

public class MyMediaRecorder implements Parcelable{
    MediaRecorder mediaRecorder;
   public MyMediaRecorder(MediaRecorder m)
    {
       this.mediaRecorder=m;
    }

    protected MyMediaRecorder(Parcel in) {
        mediaRecorder = in.readParcelable(MyMediaRecorder.class.getClassLoader());
    }


    public static final Creator CREATOR = new Creator<MyMediaRecorder>() {
        @Override
        public MyMediaRecorder createFromParcel(Parcel in) {
            return new MyMediaRecorder(in);
        }

        @Override
        public MyMediaRecorder[] newArray(int size) {
            return new MyMediaRecorder[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) mediaRecorder, flags);
    }
}
