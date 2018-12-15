package ensicaen.fr.tp3;

import android.os.AsyncTask;

public class DownloadRssTask extends AsyncTask<MyRSSsaxHandler, Void, MyRSSsaxHandler> {
    private MainActivity monActivity;
    public DownloadRssTask(MainActivity monActivity){
        this.monActivity = monActivity;
    }
    protected MyRSSsaxHandler doInBackground(MyRSSsaxHandler... handler){
        handler[0].processFeed();
        return handler[0];
    }
    /** The system calls this to perform work in the UI thread and delivers
     * the result from doInBackground() */
    protected void onPostExecute(MyRSSsaxHandler handler)
    {
        // Mise à jour des données de notre vue à partir
        // du titre, date, image et description lus
        monActivity.resetDisplay(handler.getTitle(),handler.getDate(),handler.getImage(),
                handler.getDescription().toString());
    }
}
