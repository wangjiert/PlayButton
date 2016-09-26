package com.konka.playbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    PlayButton playButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = (PlayButton) findViewById(R.id.button);
    }

    public void click(View view) {
        playButton.startAnimator();
    }

}
