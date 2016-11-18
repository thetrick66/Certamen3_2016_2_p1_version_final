package cl.telematica.android.certamen3.models;

import android.os.Parcel;
import android.os.Parcelable;

import cl.telematica.android.certamen3.models.contracts.Feed;

/**
 * Created by franciscocabezas on 11/18/16.
 */

public class FeedImpl implements Parcelable, Feed{

    private String title;
    private String link;
    private String author;
    private String publishedDate;
    private String content;
    private String image;
    private boolean isFavorite;

    public FeedImpl() {

    }

    protected FeedImpl(Parcel in) {
        title = in.readString();
        link = in.readString();
        author = in.readString();
        publishedDate = in.readString();
        content = in.readString();
        image = in.readString();
        isFavorite = in.readInt() == 1;
    }

    public static final Creator<FeedImpl> CREATOR = new Creator<FeedImpl>() {
        @Override
        public FeedImpl createFromParcel(Parcel in) {
            return new FeedImpl(in);
        }

        @Override
        public FeedImpl[] newArray(int size) {
            return new FeedImpl[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(link);
        parcel.writeString(author);
        parcel.writeString(publishedDate);
        parcel.writeString(content);
        parcel.writeString(image);
        parcel.writeInt(isFavorite ? 1 : 0);
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
