package com.example.cluedo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

public class GameActivity extends AppCompatActivity {

    public static String NAME_STEPAN = "STEPAN";
    public static String NAME_ANYA = "ANYA";
    public static String NAME_MAMA = "MAMA";
    public static String NAME_PAPA = "PAPA";

    private ArrayList<String> names_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if((getIntent() != null ) && (getIntent().hasExtra("names_list")))
            names_list = getIntent().getStringArrayListExtra("names_list");

        TextView textView = findViewById(R.id.name_1);
        textView.setText(names_list.get(0));
        textView = findViewById(R.id.name_2);
        textView.setText(names_list.get(1));
        textView = findViewById(R.id.name_3);
        textView.setText(names_list.get(2));
        textView = findViewById(R.id.name_4);
        textView.setText(names_list.get(3));
    }
}
