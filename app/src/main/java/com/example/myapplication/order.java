package com.example.myapplication;

public class order {

    String announcemnet_title;
    String announcemnet_tag;
    String announcemnet_recruitment;
    String announcemnent_contents;

    public String getAnnouncemnet_title(){
        return announcemnet_title;
    }

    public String getAnnouncemnet_tag() {
        return announcemnet_tag;
    }

    public String getAnnouncemnet_recruitment(){
        return announcemnet_recruitment;
    }

    public String getAnnouncemnent_contents(){
        return announcemnent_contents;
    }

    public void setAnnouncemnet_title(String announcemnet_title) {
        this.announcemnet_title = announcemnet_title;
    }

    public void setAnnouncemnet_tag(String announcemnet_tag) {
        this.announcemnet_tag = announcemnet_tag;
    }

    public void setAnnouncemnet_recruitment(String announcemnet_recruitment) {
        this.announcemnet_recruitment = announcemnet_recruitment;
    }

    public void setAnnouncemnent_contents(String announcemnent_contents) {
        this.announcemnent_contents = announcemnent_contents;
    }

    public order(String announcemnet_title, String announcemnet_tag, String announcemnet_recruitment, String announcemnent_contents) {
        this.announcemnet_title = announcemnet_title;
        this.announcemnet_tag = announcemnet_tag;
        this.announcemnet_recruitment = announcemnet_recruitment;
        this.announcemnent_contents = announcemnent_contents;
    }
}
