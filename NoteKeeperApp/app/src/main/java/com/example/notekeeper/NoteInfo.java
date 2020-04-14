package com.example.notekeeper;

import android.os.Parcel;
import android.os.Parcelable;

public final class NoteInfo implements Parcelable{
    private CourseInfo mCourse;
    private String mTitle;
    private String mText;

    public NoteInfo(CourseInfo course, String title, String text) {
        mCourse = course;
        mTitle = title;
        mText = text;
    }

    private NoteInfo(Parcel source) {
        mCourse = source.readParcelable(CourseInfo.class.getClassLoader());   // class loader information type
        mTitle = source.readString();
        mText = source.readString();
    }

    public CourseInfo getCourse() {
        return mCourse;
    }

    public void setCourse(CourseInfo course) {
        mCourse = course;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    private String getCompareKey() {
        return mCourse.getCourseId() + "|" + mTitle + "|" + mText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteInfo that = (NoteInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() {
        return getCompareKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeParcelable(mCourse, 0);    //to make mcourse parcelable
        dest.writeString(mTitle);
        dest.writeString(mText);

    }

    public static final Parcelable.Creator<NoteInfo> CREATOR =              // noteInfo - as a generic argument to parcelable.Creator
        new Parcelable.Creator<NoteInfo>() {
            /**
             * Create a new instance of the Parcelable class, instantiating it
             * from the given Parcel whose data had previously been written by
             * {@link Parcelable#writeToParcel Parcelable.writeToParcel()}.
             *
             * @param source The Parcel to read the object's data from.
             * @return Returns a new instance of the Parcelable class.
             */
            @Override
            public NoteInfo createFromParcel(Parcel source) {
                return new NoteInfo(source);
            }

            /**
             * Create a new array of the Parcelable class.
             *
             * @param size Size of the array.
             * @return Returns an array of the Parcelable class, with every entry
             * initialized to null.
             */
            @Override
            public NoteInfo[] newArray(int size) {
                return new NoteInfo[size];
            }

    };     // anonymous class and we are using anonymous class to initialize field, we need to be sure to put a ;

}

