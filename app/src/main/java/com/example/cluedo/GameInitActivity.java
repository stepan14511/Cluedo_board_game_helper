package com.example.cluedo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Vector;

public class GameInitActivity extends AppCompatActivity {

    private static int NUMBER_OF_PLAYERS = 4;
    private ArrayList<String> names_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_init);

        initButtons();
    }

    private void initButtons(){
        Button button = findViewById(R.id.btn_anya);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press_btn(GameActivity.NAME_ANYA);
                Button button = findViewById(R.id.btn_anya);
                button.setVisibility(View.INVISIBLE);
            }
        });
        button = findViewById(R.id.btn_mama);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press_btn(GameActivity.NAME_MAMA);
                Button button = findViewById(R.id.btn_mama);
                button.setVisibility(View.INVISIBLE);
            }
        });
        button = findViewById(R.id.btn_papa);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press_btn(GameActivity.NAME_PAPA);
                Button button = findViewById(R.id.btn_papa);
                button.setVisibility(View.INVISIBLE);
            }
        });
        button = findViewById(R.id.btn_stepan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press_btn(GameActivity.NAME_STEPAN);
                Button button = findViewById(R.id.btn_stepan);
                button.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void press_btn(String name){
        names_list.add(name);
        if(names_list.size() >= NUMBER_OF_PLAYERS){
            Intent intent = new Intent(GameInitActivity.this, GameActivity.class);
            intent.putExtra("names_list", names_list);
            startActivity(intent);
        }
    }
}
