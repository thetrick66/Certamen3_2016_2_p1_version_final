package cl.telematica.android.certamen3.models.contracts;

import android.os.Parcel;

/**
 * Created by Patricio on 18-11-2016.
 */

public interface Feed {
    String getTitle();
    void setTitle(String title);
    String getLink();
    void setLink(String link);
    String getAuthor();
    void setAuthor(String author);
    String getPublishedDate();
    void setPublishedDate(String publishedDate);
    String getContent();
    void setContent(String content);
    String getImage();
    void setImage(String image);
    int describeContents();
    void writeToParcel(Parcel parcel, int i);
    boolean isFavorite();
    void setFavorite(boolean favorite);
}
