package com.arhiser.todolist.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.arhiser.todolist.data.NoteDao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MyUserData implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "mail")
    public String mail;

    @ColumnInfo(name = "password")
    public String password;


    public MyUserData() {
    }

    public static final Creator<MyUserData> CREATOR = new Creator<MyUserData>() {
        @Override
        public MyUserData createFromParcel(Parcel in) {
            return new MyUserData(in);
        }

        @Override
        public MyUserData[] newArray(int size) {
            return new MyUserData[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyUserData)) return false;

        MyUserData that = (MyUserData) o;

        if (uid != that.uid) return false;
        if (password != that.password) return false;
        return mail != null ? mail.equals(that.mail) : that.mail == null;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (password != null ? mail.hashCode() : 0);
        return result;
    }

    protected MyUserData(Parcel in) {
        uid = in.readInt();
        mail = in.readString();
        password = in.readString();

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(mail);
        dest.writeString(password);
    }
}
