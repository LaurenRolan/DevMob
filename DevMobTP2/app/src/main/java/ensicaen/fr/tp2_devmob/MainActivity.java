package ensicaen.fr.tp2_devmob;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private ListView lv;
    private ArrayAdapter<Post> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new DownloadFilesTask().execute("http://jsonplaceholder.typicode.com/posts?userId=1");

    }

    private void affiche(List<Post> posts) {
        lv = findViewById(R.id.idList);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, posts);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), PostView.class);
                intent.putExtra("PostView", arrayAdapter.getItem(i));
                startActivity(intent);
            }
        });
        lv.setAdapter(arrayAdapter);
    }

    private class DownloadFilesTask extends AsyncTask<String, Integer, List<Post>> {
        List<Post> posts = new ArrayList<>();
        protected List<Post>  doInBackground(String... strings) {
            try {
                String download = HttpUtils.downloadUrl(strings[0]);
                posts = JsonUtils.parsePosts(download);
                for( Post post : posts) {
                    System.out.println("title = " + post.title);
                }
            } catch (IOException e) {
                System.out.println("Error while loading URL: " + e.getMessage());
            }
            return posts;

        }

        protected void onPostExecute(List<Post> posts) {  affiche(posts); }
    }

}
