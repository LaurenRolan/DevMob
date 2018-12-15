package ensicaen.fr.tp3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*
References:
   http://www.vogella.com/tutorials/RSSFeed/article.html
   https://github.com/musiKk/rss-sax/blob/master/src/main/java/com/github/musikk/rsssax/RssHandler.java
   https://stackoverflow.com/questions/21638199/get-and-show-a-sequence-of-images-from-url
   https://stackoverflow.com/questions/32280747/rss-reader-using-sax-parser-losing-characters-from-title
*/


public class MyRSSsaxHandler extends DefaultHandler {
    private String url = null ;// l'URL du flux RSS à parser
    // Ensemble de drapeau permettant de savoir où en est le parseur dans le flux XML
    private boolean inTitle = false ;
    private boolean inDescription = false ;
    private boolean inItem = false ;
    private boolean inDate = false ;
    private boolean inEnclosure = false;
    // L'image référencée par l'attribut url du tag <enclosure>
    private Bitmap image = null ;
    private String imageURL = null ;
    // Le titre, la description et la date extraits du flux RSS
    private String title;
    private String description;
    private String date;
    private int numItem = 0; // Le numéro de l'item à extraire du flux RSS
    private int numItemMax = - 1; // Le nombre total d’items dans le flux RSS
    private List<Item> liste;
    StringBuilder mSb;
    boolean isBuilding;
    Item current = new Item();

    public void setUrl(String url){
        this.url= url;
    }

    public void processFeed(){
        try {
            liste = new ArrayList<>();
            Log.d("MyRSS", "ProcessFeed");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(this);
            InputStream inputStream = new URL(url).openStream();
            reader.parse(new InputSource(inputStream));
            image = getBitmap(imageURL);
            numItemMax = numItem;
        }catch(Exception e) {
            Log.e("smb116rssview", "processFeed Exception" + e.getMessage());
        }
    }

    private Bitmap getBitmap(String imageURL) {
        try {
            URL url = new URL(imageURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setState(String current, boolean value)
    {
        switch(current) {
            case "item":
                inTitle = false ;
                inDescription = false ;
                inItem = value ;
                inDate = false ;
                inEnclosure = false ;
                break;
            case "title":
                inTitle = value ;
                inDescription = false ;
                inItem = false ;
                inDate = false ;
                inEnclosure = false ;
                break;
            case "descripion":
                inTitle = false ;
                inDescription = value ;
                inItem = false ;
                inDate = false ;
                inEnclosure = false ;
                break;
            case "date":
                inTitle = false ;
                inDescription = false ;
                inItem = false ;
                inDate = value ;
                inEnclosure = false ;
                break;
            case "enclosure":
                inTitle = false ;
                inDescription = false ;
                inItem = false ;
                inDate = false ;
                inEnclosure = value ;
                break;
            default:
                inTitle = false ;
                inDescription = false ;
                inItem = false ;
                inDate = false ;
                inEnclosure = false;
                break;
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        mSb = new StringBuilder();
        isBuilding = true;

        if(!uri.isEmpty())
            return;

        switch (localName) {
            case "item":
                Log.d("MyRSS", String.valueOf(numItem));
                setState("item", true);
                break;
            case "title":
                setState("title", true);
                title = attributes.getValue(qName);
                break;
            case "description":
                setState("description", true);
                description = attributes.getValue(qName);
                break;
            case "pubDate":
                setState("pubDate", true);
                date = attributes.getValue(qName);
                break;
            case "enclosure":
                setState("enclosure", true);
                break;
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (localName) {
            case "item":
                numItem++;
                setState("item", false);
                Log.d("MyRSS", current.getPubDate() + " " + current.getDescription());
                liste.add(current);
                current = new Item();
                break;
            case "title":
                setState("title", false);
                title = String.valueOf(mSb);
                current.setTitle(title);
                break;
            case "description":
                setState("description", false);
                description = String.valueOf(mSb);
                current.setDescription(description);
                break;
            case "pubDate":
                setState("pubDate", false);
                date = String.valueOf(mSb);
                current.setPubDate(date);
                break;
            case "enclosure":
                setState("enclosure", false);
                Log.d("MyRSS", "Enclosure: " + String.valueOf(mSb));
                current.setEnclosure(String.valueOf(mSb));
                imageURL = current.getLinkImage();
                Log.d("MyRSS", "URL: " + imageURL);
                break;
        }
    }

    public void characters(char ch[], int start, int length){
        if (mSb != null && isBuilding) {
            for (int i = start; i < start + length; i++) {
                mSb.append(ch[i]);
            }
        }
    }

    public void setCurrent(int index)
    {
        Item current = liste.get(index);
        title = current.getTitle();
        Log.d("MyRSS", title);
        description = current.getDescription();
        date = current.getPubDate();
        imageURL = current.getLinkImage();
        image = getBitmap(imageURL);
    }

    public String getNumber() {
        return String.valueOf(numItem);
    }

    public void nextItem() {
        if(numItem < numItemMax )
            numItem++;
        setCurrent(numItem);
    }

    public void previousItem() {
        if(numItem > 0)
            numItem--;
        setCurrent(numItem);
    }

    public void goFirstItem() {
        numItem = 0;
        setCurrent(numItem);
    }

    public void goLastItem() {
        numItem = numItemMax;
        setCurrent(numItem - 1);
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

}
