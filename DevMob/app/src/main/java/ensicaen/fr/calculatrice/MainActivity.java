package ensicaen.fr.calculatrice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        Button zero = (Button) findViewById(R.id.init_button);
        zero.setOnClickListener(this); // calling onClick() method
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.init_button:
                Intent i = new Intent(getApplicationContext(), Calculatrice.class);
                startActivity(i);
                break;

            default:
                break;
        }
    }

}
