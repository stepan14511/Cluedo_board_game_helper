package com.example.cluedo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.ArrayList;

public class GameInitActivity extends AppCompatActivity {
    private static int NUMBER_OF_PLAYERS = 4;
    private ArrayList<String> names_list = new ArrayList<>();
    private int picked_max_num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_init);

        initButtons();
    }

    private void initButtons(){
        //region Buttons with Names
        Button button = findViewById(R.id.btn_anya);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names_list.add(Table.NAME_ANYA);
                press_btn();
                Button button = findViewById(R.id.btn_anya);
                button.setVisibility(View.INVISIBLE);
            }
        });
        button = findViewById(R.id.btn_mama);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names_list.add(Table.NAME_MAMA);
                press_btn();
                Button button = findViewById(R.id.btn_mama);
                button.setVisibility(View.INVISIBLE);
            }
        });
        button = findViewById(R.id.btn_papa);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names_list.add(Table.NAME_PAPA);
                press_btn();
                Button button = findViewById(R.id.btn_papa);
                button.setVisibility(View.INVISIBLE);
            }
        });
        button = findViewById(R.id.btn_stepan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names_list.add(Table.NAME_STEPAN);
                press_btn();
                Button button = findViewById(R.id.btn_stepan);
                button.setVisibility(View.INVISIBLE);
            }
        });
        //endregion

        //region RadioButtons
        RadioButton radioButton = findViewById(R.id.rb_2);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press_btn();
            }
        });

        radioButton = findViewById(R.id.rb_3);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press_btn();
            }
        });

        radioButton = findViewById(R.id.rb_4);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press_btn();
            }
        });

        radioButton = findViewById(R.id.rb_5);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press_btn();
            }
        });

        radioButton = findViewById(R.id.rb_6);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press_btn();
            }
        });

        radioButton = findViewById(R.id.rb_7);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press_btn();
            }
        });
        //endregion
    }

    private void press_btn(){
        if((names_list.size() >= NUMBER_OF_PLAYERS) && check_max_num()){
            Intent intent = new Intent(GameInitActivity.this, GameActivity.class);
            Table table = new Table(names_list, picked_max_num);
            intent.putExtras(table.get_bundle());
            startActivity(intent);
        }
    }

    private boolean check_max_num(){
        RadioButton radioButton = findViewById(R.id.rb_2);
        if(radioButton.isChecked())
            picked_max_num = 2;
        radioButton = findViewById(R.id.rb_3);
        if(radioButton.isChecked())
            picked_max_num = 3;
        radioButton = findViewById(R.id.rb_4);
        if(radioButton.isChecked())
            picked_max_num = 4;
        radioButton = findViewById(R.id.rb_5);
        if(radioButton.isChecked())
            picked_max_num = 5;
        radioButton = findViewById(R.id.rb_6);
        if(radioButton.isChecked())
            picked_max_num = 6;
        radioButton = findViewById(R.id.rb_7);
        if(radioButton.isChecked())
            picked_max_num = 7;
        return (picked_max_num != 0);
    }
}
