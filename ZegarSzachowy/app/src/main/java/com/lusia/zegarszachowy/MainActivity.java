package com.lusia.zegarszachowy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button1, button2;
    Gracz gracz1, gracz2;
    private boolean isGameRunning = false;
    private static final String KEY_GAME_RUNNING = "isGameRunning";
    private static final String KEY_TIME_USER1 = "timeUser1";
    private static final String KEY_TIME_USER2 = "timeUser2";
    private static final String KEY_ACTIVE_USER = "isUser1Active";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        gracz1 = new Gracz(true, button1);
        gracz2 = new Gracz(false, button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gracz1.odwrocAktywnosc();
                gracz2.odwrocAktywnosc();
                gracz1.dodajCzas();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gracz1.odwrocAktywnosc();
                gracz2.odwrocAktywnosc();
                gracz2.dodajCzas();
            }
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (isGameRunning) {
            user.stopTimer();
        }

        outState.putBoolean(KEY_GAME_RUNNING, isGameRunning);
        outState.putLong(KEY_TIME_USER1, user.getTimeUser1());
        outState.putLong(KEY_TIME_USER2, user.getTimeUser2());
        outState.putBoolean(KEY_ACTIVE_USER, user.isUser1Active());
    }
}