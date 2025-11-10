package ro.pub.cs.systems.eim.model_colocviu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class model_colocviu extends AppCompatActivity {

    private EditText text;
    private Button north;
    private Button south;
    private Button east;
    private Button west;
    private Button navigate_to_secondary_activity;
    private int b = 0;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private Button NavigateToSecondaryActivity;

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.navigate_to_sencondary_activity) {
                /*if (view.getId() == R.id.navigate_to_sencondary_activity) {
    Intent intent = new Intent(getApplicationContext(), SecondActivityModelColocviu.class);

    // aici text este un TextView sau EditText
    String direction = text.getText().toString();

    intent.putExtra(Constants.DIRECTION, direction);

    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
}
*/
                Intent intent = new Intent(getApplicationContext(), SecondActivityModelColocviu.class);
                //aici a trebuit sa fac suma lor
                /*int numberOfClicks = b;
intent.putExtra(Constants.NUMBER_OF_CLICKS, numberOfClicks);
intent.putExtra(Constants.NUMBER_OF_CLICKS, b);

*/
                int numberOfClicks = Integer.parseInt(text.getText().toString()) +
                        Integer.parseInt(text.getText().toString());
                intent.putExtra(Constants.NUMBER_OF_CLICKS, numberOfClicks);
                //cere un int aceasta metoda de aia trebuie sa am secondary cu int
                startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
            }
            int northclick = 0;
            int southclick = 0;
            int eastclick = 0;
            int westclick = 0;
            if (view.getId() == R.id.north) {
                text.append(String.valueOf("north"));
                text.append(String.valueOf(" "));
                increment();
            } else if (view.getId() == R.id.south) {
                text.append((String.valueOf("south")));
                text.append(String.valueOf(" "));
                increment();
            } else if (view.getId() == R.id.east) {
                text.append((String.valueOf("east")));
                text.append(String.valueOf(" "));
                increment();
            } else if (view.getId() == R.id.west) {
                text.append((String.valueOf("west")));
                text.append(String.valueOf(" "));
                increment();
            }
        }
    }

    private void increment() {
        b++;
        Log.d("CLICK_DEBUG", "Buton apăsat de " + b + " ori");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.model_colocviu);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        text = findViewById(R.id.editTextText);
        north = findViewById(R.id.north);
        south = findViewById(R.id.south);
        east = findViewById(R.id.east);
        west = findViewById(R.id.west);
        navigate_to_secondary_activity = findViewById(R.id.navigate_to_sencondary_activity);
        //text.setText("North, south,east,west");
        north.setOnClickListener(new ButtonClickListener());
        south.setOnClickListener(new ButtonClickListener());
        east.setOnClickListener(new ButtonClickListener());
        west.setOnClickListener(new ButtonClickListener());

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.buton_count)) {
                text.setText(savedInstanceState.getString(Constants.buton_count));
            } else {
                text.setText(String.valueOf("0"));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.buton_count, text.getText().toString());

    }

    //pentru restaurare
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.buton_count)) {
            text.setText(savedInstanceState.getString(Constants.buton_count));
        } else {
            text.setText(String.valueOf(0));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent inte) {
        super.onActivityResult(requestCode, resultCode, inte);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}
