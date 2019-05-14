package com.example.cluedo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.net.SecureCacheResponse;
import java.util.ArrayList;
import java.util.Vector;

public class GameActivity extends AppCompatActivity {

    private Table table;
    private int current_asker = -1;
    private int current_answerer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Get Table object
        Bundle bundle = getIntent().getExtras();
        table = new Table(bundle);

        //Added listener on TABLE button
        Button table_btn = findViewById(R.id.table_btn);
        table_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, TableActivity.class);
                intent.putExtras(table.get_bundle());
                startActivity(intent);
            }
        });

        first_init();
    }

    //Picking users cards
    private void first_init(){
        TextView textView = findViewById(R.id.asker);
        textView.setText("YOUR CARDS");

        Button button = findViewById(R.id.ready_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_cards_to_table(table.ID_STEPAN, Table.HAVE_CARD);

                clear_toggle_buttons();
                change_current_asker();
            }
        });
    }

    private void add_cards_to_table(int who, int state){
        //TODO: create an array of IDs in layout and in table
        ToggleButton toggleButton = findViewById(R.id.mr_green);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_PERSON_GREEN, who, state);
        toggleButton = findViewById(R.id.mr_mastard);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_PERSON_MASTARD, who, state);
        toggleButton = findViewById(R.id.mrs_pikok);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_PERSON_PIKOK, who, state);
        toggleButton = findViewById(R.id.mr_plam);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_PERSON_PLAM, who, state);
        toggleButton = findViewById(R.id.mr_scarlet);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_PERSON_SCARLET, who, state);
        toggleButton = findViewById(R.id.mr_uait);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_PERSON_UAIT, who, state);
        toggleButton = findViewById(R.id.g_key);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_THING_G_KEY, who, state);
        toggleButton = findViewById(R.id.candlestick);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_THING_CANDLESTICK, who, state);
        toggleButton = findViewById(R.id.knife);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_THING_KNIFE, who, state);
        toggleButton = findViewById(R.id.revolver);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_THING_REVOLVER, who, state);
        toggleButton = findViewById(R.id.trumpet);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_THING_TRUMPET, who, state);
        toggleButton = findViewById(R.id.rope);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_THING_ROPE, who, state);
        toggleButton = findViewById(R.id.bathroom);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_PLACE_BATHROOM, who, state);
        toggleButton = findViewById(R.id.cabinet);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_PLACE_CABINET, who, state);
        toggleButton = findViewById(R.id.dinner_room);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_PLACE_DINNER_ROOM, who, state);
        toggleButton = findViewById(R.id.billiard_room);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_PLACE_BILLIARD_ROOM, who, state);
        toggleButton = findViewById(R.id.garage);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_PLACE_GARAGE, who, state);
        toggleButton = findViewById(R.id.bedroom);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_PLACE_BEDROOM, who, state);
        toggleButton = findViewById(R.id.living_room);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_PLACE_LIVING_ROOM, who, state);
        toggleButton = findViewById(R.id.kitchen);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_PLACE_KITCHEN, who, state);
        toggleButton = findViewById(R.id.yard);
        if(toggleButton.isChecked())
            table.change_card_state(Table.ID_PLACE_YARD, who, state);
    }

    private void clear_toggle_buttons(){
        ToggleButton toggleButton = findViewById(R.id.mr_green);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.mr_mastard);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.mrs_pikok);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.mr_plam);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.mr_scarlet);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.mr_uait);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.g_key);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.candlestick);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.knife);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.revolver);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.trumpet);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.rope);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.bathroom);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.cabinet);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.dinner_room);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.billiard_room);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.garage);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.bedroom);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.living_room);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.kitchen);
        toggleButton.setChecked(false);
        toggleButton = findViewById(R.id.yard);
        toggleButton.setChecked(false);
    }

    //TODO: change current asker
    private void change_current_asker(){
        clear_toggle_buttons();

        //set asker
        current_asker++;
        if(current_asker >= table.get_names_list_size())
            current_asker = 0;

        //set answerer
        current_answerer = current_asker;

        //Set current asker to header
        TextView textView = findViewById(R.id.asker);
        textView.setText("Asker: " + table.get_names_list().get(current_asker));

        //Change buttons(in the bottom)
        Button btn = findViewById(R.id.ready_btn);
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_current_answerer();

                //Change buttons(in the bottom)
                Button btn = findViewById(R.id.ready_btn);
                btn.setVisibility(View.INVISIBLE);

                btn = findViewById(R.id.btn_yes);
                btn.setVisibility(View.VISIBLE);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        change_current_asker();
                    }
                });

                btn = findViewById(R.id.btn_no);
                btn.setVisibility(View.VISIBLE);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        add_cards_to_table(current_answerer, Table.DONT_HAVE_CARD);
                        change_current_answerer();
                        if(current_answerer == current_asker)
                            change_current_asker();
                    }
                });
            }
        });

        btn = findViewById(R.id.btn_yes);
        btn.setVisibility(View.INVISIBLE);

        //Set current answerer to header
        btn = findViewById(R.id.btn_no);
        btn.setVisibility(View.INVISIBLE);
    }

    //TODO: change current answerer
    private void change_current_answerer(){
        current_answerer++;
        if(current_answerer >= table.get_names_list_size())
            current_answerer = 0;

        TextView textView = findViewById(R.id.asker);
        textView.setText("Answerer: " + table.get_names_list().get(current_answerer));
    }
}
//TODO: change name to separate function
//TODO: pick which card was shown
//TODO: add array of toggle_button IDs(idk if it's needed)
//TODO: Do an algorithm for each turn
