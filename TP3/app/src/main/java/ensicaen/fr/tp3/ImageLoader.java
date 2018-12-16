package ensicaen.fr.tp3;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoader extends AsyncTask<Context, Void, Bitmap> {

    private MyRSSsaxHandler mainHandler;
    private Activity mainActivity;


    ImageLoader(Activity activity, MyRSSsaxHandler handler)
    {
        mainHandler = handler;
        mainActivity = activity;
    }


    @Override
    protected Bitmap doInBackground(Context... arg0) {

        try {
            URL url = new URL(mainHandler.getImageURL());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            Log.d("MyRSS", "Sucess!");
            return bitmap;
        } catch (IOException e) {
            Log.d("MyRSS", "Failure!");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        Log.d("MyRSS", "On post!");
        ImageView imageView = (ImageView) mainActivity.findViewById(R.id.imageDisplay);
        imageView.setImageBitmap(result);
    }

}