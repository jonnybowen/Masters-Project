package com.example.sdla_quiz;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Custom data class to represent a note.
 * A note, assigned to a subject, consists of a title, some textual content,
 * and a timestamp of when it was made.
 */
public class Note implements Parcelable {
    private String title;
    private String content;
    private String timestamp;
    private int subjectID;


    /**
     * Constructor
     *
     * @param title the note's title
     * @param content the note's content
     * @param timestamp the note's timestamp
     * @param subjectID the note's subject ID
     */
    public Note(String title, String content, String timestamp, int subjectID) {
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
        this.subjectID = subjectID;
    }

    /**
     * No-arg constructor.
     */
    public Note() {
    }

    /**
     * Constructor that creates a note from a parcel
     */
    protected Note(Parcel in) {
        title = in.readString();
        content = in.readString();
        timestamp = in.readString();
        subjectID = in.readInt();
    }

    /**
     * Returns a parcel created from a note.
     */
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


    /**
     * Accessor method to get the subject id
     *
     * @return subjectId
     */
    public int getSubjectID() {
        return subjectID;
    }

    /**
     * Accessor method to set the subject id
     *
     * @param subjectID
     */
    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * Accessor method to get the title
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Accessor method to set the title
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Accessor method to get the content
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Accessor method to set the content
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Accessor method to get the timestamp
     *
     * @return timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Accessor method to set the timestamp
     *
     * @param timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Override of toString method, returns all of the note's fields as a string.
     *
     * @return the note's contents in string format
     */
    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    //Required implementation by android
    @Override
    public int describeContents() {
        return 0;
    }

    //Writes data to a parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(timestamp);
        dest.writeInt(subjectID);
    }
}
