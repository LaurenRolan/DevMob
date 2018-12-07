package com.example.lrolan.rss;

public class Item {
    private String title;
    private String description;
    private String pubDate;
    private Enclosure enclosure;

    public Item(String title, String description, String pubDate, Enclosure enclosure) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.enclosure = enclosure;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    public String getLinkImage() {

    }

}
