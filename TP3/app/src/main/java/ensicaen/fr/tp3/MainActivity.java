package ensicaen.fr.tp3;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyRSSsaxHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new MyRSSsaxHandler();
        handler.setUrl("https://www.nasa.gov/rss/image_of_the_day.rss");
        Toast.makeText(this,"chargement image :" + handler.getNumber(), Toast.LENGTH_LONG).show();
        new DownloadRssTask(this).execute(handler);
    }

    public void goFirst(View v) {
        handler.goFirstItem();
        Log.d("MyRSS", String.valueOf(handler.getNumber()));
        resetDisplay(handler);
    }

    public void goLast(View v) {
        handler.goLastItem();
        resetDisplay(handler);
    }

    public void goNext(View v) {
        handler.nextItem();
        resetDisplay(handler);
    }

    public void goPrevious(View v) {
        handler.previousItem();
        resetDisplay(handler);
    }

    public void resetDisplay(MyRSSsaxHandler handler){
        Log.d("MyRSS", "Desc -- " + handler.getDescription());
        TextView dateView = (TextView) findViewById(R.id.imageDate);
        dateView.setText(handler.getDate());

        TextView titleView = (TextView) findViewById(R.id.imageTitle);
        titleView.setText(handler.getTitle());

        ImageLoader task = new ImageLoader(this, handler);
        task.execute();

        TextView descriptionView = (TextView) findViewById(R.id.imageDescription);
        descriptionView.setText(handler.getDescription());
    }
}
