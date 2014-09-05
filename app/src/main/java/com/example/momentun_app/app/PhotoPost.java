package com.example.momentun_app.app;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by dell on 03/09/2014.
 */
@ParseClassName("PhotoPost")
public class PhotoPost extends ParseObject{
    public PhotoPost(){

    }

    public void setImage(ParseFile file) {
        put("Image", file);
    }

    public ParseFile getImage(){
        return getParseFile("Image");
    }

    public void setThumbnail(ParseFile file) {
        put("Thumbnail",file);
    }

    public ParseFile getThumbnail(){
        return getParseFile("Thumbnail");
    }

    public ParseUser getAuthor() {
        return getParseUser("author");
    }

    public void setAuthor(ParseUser user) {
        put("author", user);
    }

    public String getDescription(){
        return getString("Description");
    }
    public void setDescription(String description){
        put("description",description);
    }


}
