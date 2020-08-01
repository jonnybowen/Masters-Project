package com.example.sdla_quiz;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private String title;
    private String content;
    private String timestamp;
    private String subjectID;

    public Note(String title, String content, String timestamp, String subjectID) {
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
        this.subjectID = subjectID;
    }

    public Note() {
    }

    //Accessor Methods

    protected Note(Parcel in) {
        title = in.readString();
        content = in.readString();
        timestamp = in.readString();
        subjectID = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(timestamp);
        dest.writeString(subjectID);
    }
}
