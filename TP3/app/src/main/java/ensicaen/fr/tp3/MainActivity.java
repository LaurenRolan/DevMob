package ensicaen.fr.tp3;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyRSSsaxHandler handler = new MyRSSsaxHandler();
        handler.setUrl("https://www.nasa.gov/rss/image_of_the_day.rss");
        Toast.makeText(this,"chargement image :" + handler.getNumber(), Toast.LENGTH_LONG).show();
        new DownloadRssTask(this).execute(handler);
    }

    public void resetDisplay(String title, String date, Bitmap image, String description){
        TextView dateView = (TextView) findViewById(R.id.imageDate);
        dateView.setText(date);

        TextView titleView = (TextView) findViewById(R.id.imageTitle);
        titleView.setText(title);

        ImageView imageView = (ImageView) findViewById(R.id.imageDisplay);
        imageView.setImageBitmap(image);

        TextView descriptionView = (TextView) findViewById(R.id.imageDescription);
        descriptionView.setText(description);
    }
}
