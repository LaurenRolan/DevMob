package com.example.lrolan.rss;

import android.graphics.Bitmap;
import android.util.Log;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/*
References:
   http://www.vogella.com/tutorials/RSSFeed/article.html
   https://github.com/musiKk/rss-sax/blob/master/src/main/java/com/github/musikk/rsssax/RssHandler.java
   https://stackoverflow.com/questions/21638199/get-and-show-a-sequence-of-images-from-url
*/




public class MyRSSsaxHandler extends DefaultHandler {
    private String url = null ;// l'URL du flux RSS à parser
    // Ensemble de drapeau permettant de savoir où en est le parseur dans le flux XML
    private boolean inTitle = false ;
    private boolean inDescription = false ;
    private boolean inItem = false ;
    private boolean inDate = false ;
    // L'image référencée par l'attribut url du tag <enclosure>
    private Bitmap image = null ;
    private String imageURL = null ;
    // Le titre, la description et la date extraits du flux RSS
    private StringBuffer title = new StringBuffer();
    private StringBuffer description = new StringBuffer();
    private StringBuffer date = new StringBuffer();
    private int numItem = 0; // Le numéro de l'item à extraire du flux RSS
    private int numItemMax = - 1; // Le nombre total d’items dans le flux RSS
    private List<Item> liste;
    private Rss rss;

    public void setUrl(String url){
        this.url= url;
    }

    public void processFeed(){
        try {
            numItem = 0; //A modifier pour visualiser un autre item
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

    public void setState(String current)
    {
        switch(current) {
            case "item":
                inTitle = false ;
                inDescription = false ;
                inItem = true ;
                inDate = false ;
                break;
            case "title":
                inTitle = true ;
                inDescription = false ;
                inItem = false ;
                inDate = false ;
                break;
            case "descripion":
                inTitle = false ;
                inDescription = true ;
                inItem = false ;
                inDate = false ;
                break;
            case "date":
                inTitle = false ;
                inDescription = false ;
                inItem = false ;
                inDate = true ;
                break;
             default:
                 inTitle = false ;
                 inDescription = false ;
                 inItem = false ;
                 inDate = false ;
                 break;
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if(!uri.isEmpty())
            return;

        switch (localName) {
            case "rss":
                rss.setVersion(attributes.getValue("version"));;
                break;
            case "item":
                numItem++;
                setState("item");
                break;
            case "title":
                setState("title");
                title = attributes.getValue("title");
                break;
            case "description":
                setState("description");
                description = attributes.getValue("description"));
                break;
            case "pubDate":
                setState("pubDate");
                date = attributes.getValue("pubDate");
                break;
            case "enclosure":
                current.setEnclosure(attributes.getValue("enclosure"));
                break;

            }
            liste.add(current);
        }
    } catch (XMLStreamException e) {
        throw new RuntimeException(e);
    }
        return feed;

    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (!uri.isEmpty()) {
            string = "";
            return;
        }
        string = string.trim();
        if

        switch (qName)
        {
            case "item":
                current.;
                numItemMax += 1;
                break;
            case "title":
                list.
                break;
        }
    }

    public void characters(char ch[], int start, int length){
        String chars = new String(ch).substring(start, start+length);
    }

    public Bitmap getBitmap(String imageURL) {

        return null;
    }

    public class RSSViewHolder
    {
        String title;
        String description;
        String date;
        String linkImage;

        public RSSViewHolder(String title, String description, String date, String linkImage) {
            this.title = title;
            this.description = description;
            this.date = date;
            this.linkImage = linkImage;
        }
    }

}
