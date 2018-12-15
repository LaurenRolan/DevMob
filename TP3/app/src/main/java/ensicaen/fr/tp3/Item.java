package ensicaen.fr.tp3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Item {
    private String title;
    private String description;
    private String pubDate;
    private String enclosure;

    public Item(String title, String description, String pubDate, String enclosure) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.enclosure = enclosure;
    }

    public Item( ) { }

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

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    public String getLinkImage() {
        String link = "";
        Pattern p = Pattern.compile("\"(.*?)\"");
        Matcher m = p.matcher(enclosure);
        while(m.find())
        {
            link = m.group(1);
        }
        return link;

    }

}
