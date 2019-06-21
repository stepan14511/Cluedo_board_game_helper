package com.example.cluedo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private Table table;
    private int current_asker = -1;
    private int current_answerer;

    //region System Overrided functions
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

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(GameActivity.this, MenuActivity.class);
        //intent.putExtras(table.get_bundle());
        startActivity(intent);
    }
    //endregion

    //Picking users cards
    private void first_init(){
        TextView textView = findViewById(R.id.asker);
        textView.setText("YOUR CARDS");

        Button button = findViewById(R.id.ready_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_cards_to_table(get_chosen_ids(), table.ID_STEPAN, Table.HAVE_CARD);

                clear_toggle_buttons();
                change_current_asker();
            }
        });
    }

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
                        btn_yes_onClick_func();
                    }
                });

                btn = findViewById(R.id.btn_no);
                btn.setVisibility(View.VISIBLE);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        add_cards_to_table(get_chosen_ids(), current_answerer, Table.DONT_HAVE_CARD);
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

    private void btn_yes_onClick_func() {
        //if asker is user
        if(table.get_names_list().get(current_asker).equals(Table.NAME_STEPAN)){
            clear_toggle_buttons();
            Button btn = findViewById(R.id.btn_yes);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    add_cards_to_table(get_chosen_ids(), current_answerer, Table.HAVE_CARD);
                    change_current_asker();
                }
            });
        }
        //if asker is other player
        else {
            if(!table.get_names_list().get(current_answerer).equals(Table.NAME_STEPAN)) {
                //region Check if 2 of chosen cards are definitely not shown
                ArrayList<Integer> chosen_ids = get_chosen_ids();
                int count = 0;
                for (int i = 0; i < chosen_ids.size(); i++) {
                    if(table.get_state_of_the_field(chosen_ids.get(i), current_answerer) == Table.DONT_HAVE_CARD){
                        count++;
                    }
                }
                if(count == 2){
                    for (int i = 0; i < chosen_ids.size(); i++) {
                        if(table.get_state_of_the_field(chosen_ids.get(i), current_answerer) != Table.DONT_HAVE_CARD){
                            table.change_card_state(chosen_ids.get(i), current_answerer, Table.HAVE_CARD);
                        }
                    }
                }
                //endregion
            }
            change_current_asker();
        }

    }

    private void change_current_answerer(){
        current_answerer++;
        if(current_answerer >= table.get_names_list_size())
            current_answerer = 0;

        TextView textView = findViewById(R.id.asker);
        textView.setText("Answerer: " + table.get_names_list().get(current_answerer));
    }

    private ArrayList<Integer> get_chosen_ids(){
        //TODO: create an array of IDs in layout and in table
        ArrayList<Integer> chosen_ids = new ArrayList<>();
        ToggleButton toggleButton = findViewById(R.id.mr_green);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_PERSON_GREEN);
        toggleButton = findViewById(R.id.mr_mastard);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_PERSON_MASTARD);
        toggleButton = findViewById(R.id.mrs_pikok);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_PERSON_PIKOK);
        toggleButton = findViewById(R.id.mr_plam);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_PERSON_PLAM);
        toggleButton = findViewById(R.id.mr_scarlet);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_PERSON_SCARLET);
        toggleButton = findViewById(R.id.mr_uait);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_PERSON_UAIT);
        toggleButton = findViewById(R.id.g_key);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_THING_G_KEY);
        toggleButton = findViewById(R.id.candlestick);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_THING_CANDLESTICK);
        toggleButton = findViewById(R.id.knife);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_THING_KNIFE);
        toggleButton = findViewById(R.id.revolver);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_THING_REVOLVER);
        toggleButton = findViewById(R.id.trumpet);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_THING_TRUMPET);
        toggleButton = findViewById(R.id.rope);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_THING_ROPE);
        toggleButton = findViewById(R.id.bathroom);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_PLACE_BATHROOM);
        toggleButton = findViewById(R.id.cabinet);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_PLACE_CABINET);
        toggleButton = findViewById(R.id.dinner_room);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_PLACE_DINNER_ROOM);
        toggleButton = findViewById(R.id.billiard_room);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_PLACE_BILLIARD_ROOM);
        toggleButton = findViewById(R.id.garage);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_PLACE_GARAGE);
        toggleButton = findViewById(R.id.bedroom);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_PLACE_BEDROOM);
        toggleButton = findViewById(R.id.living_room);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_PLACE_LIVING_ROOM);
        toggleButton = findViewById(R.id.kitchen);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_PLACE_KITCHEN);
        toggleButton = findViewById(R.id.yard);
        if(toggleButton.isChecked())
            chosen_ids.add(Table.ID_PLACE_YARD);

        return chosen_ids;
    }

    private void add_cards_to_table(ArrayList<Integer> chosen_ids, int who, int state){
        for(int i = 0; i < chosen_ids.size(); i++){
            table.change_card_state(chosen_ids.get(i), who, state);
        }
    }

    private void clear_toggle_buttons(){
        //TODO: add array of toggle_button IDs(idk if it's needed)
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
}
