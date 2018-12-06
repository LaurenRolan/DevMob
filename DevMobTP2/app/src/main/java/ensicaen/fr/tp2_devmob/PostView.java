package ensicaen.fr.tp2_devmob;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class PostView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        System.out.println("Olar");
        Post post = (Post) getIntent().getSerializableExtra("PostView");
        System.out.println("Como vai");

        TextView title = findViewById(R.id.idTitle);
        title.setText(post.title);

        TextView body = findViewById(R.id.idBody);
        body.setText(post.body);
    }

}
