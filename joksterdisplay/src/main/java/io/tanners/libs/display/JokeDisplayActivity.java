package io.tanners.libs.display;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import java.security.SecureRandom;

public class JokeDisplayActivity extends AppCompatActivity {
    public final static String JOKSTER_DISPLAY_DATA_KEY = "JOKSTER_DISPLAY_DATA_KEY";
    private String mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokedisplay);

        getIntentData();
        setLayoutColor();
        displayData();
    }

    private void getIntentData()
    {
        mJoke = getIntent().getStringExtra(JOKSTER_DISPLAY_DATA_KEY);
    }

    private void displayData()
    {
        TextView mJokeDisplay = findViewById(R.id.joke_display);

        mJokeDisplay.setText(mJoke);
    }

    private void setLayoutColor()
    {
        ScrollView mLayout = findViewById(R.id.main_container);

        mLayout.setBackgroundColor(Color.parseColor(
                getRandomColor()
        ));
    }

    private String getRandomColor()
    {
        SecureRandom mRand = new SecureRandom();

        String[] planets = getResources().getStringArray(R.array.color_list);

        return planets[
                mRand.nextInt(planets.length)
        ];
    }
}
