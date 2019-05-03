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

    public static String NAME_STEPAN = "STEPAN";
    public  int ID_STEPAN;
    public static String NAME_ANYA = "ANYA";
    public  int ID_ANYA;
    public static String NAME_MAMA = "MAMA";
    public  int ID_MAMA;
    public static String NAME_PAPA = "PAPA";
    public  int ID_PAPA;

    public static int NO_INFO = 0;
    public static int HAVE_CARD = 1;
    public static int DONT_HAVE_CARD = 2;

    public int ID_PERSON_GREEN = 0;
    public int ID_PERSON_MASTARD = 1;
    public int ID_PERSON_PIKOK = 2;
    public int ID_PERSON_PLAM = 3;
    public int ID_PERSON_SCARLET = 4;
    public int ID_PERSON_UAIT = 5;

    public int ID_THING_G_KEY = 6;
    public int ID_THING_CANDLESTICK = 7;
    public int ID_THING_KNIFE = 8;
    public int ID_THING_REVOLVER = 9;
    public int ID_THING_TRUMPET = 10;
    public int ID_THING_ROPE = 11;

    public int ID_PLACE_BATHROOM = 12;
    public int ID_PLACE_CABINET = 13;
    public int ID_PLACE_DINNER_ROOM = 14;
    public int ID_PLACE_BILLIARD_ROOM = 15;
    public int ID_PLACE_GARAGE = 16;
    public int ID_PLACE_BEDROOM = 17;
    public int ID_PLACE_LIVING_ROOM = 18;
    public int ID_PLACE_KITCHEN = 19;
    public int ID_PLACE_YARD = 20;

    private ArrayList<String> names_list = new ArrayList<>();
    private int current_asker = -1;
    private int current_answerer;
    private int[][] table; //THING ,NAME
    private ArrayList<Integer> green_things = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if((getIntent() != null ) && (getIntent().hasExtra("names_list")))
            names_list = getIntent().getStringArrayListExtra("names_list");

        set_IDs_to_players();

        //Added listener on TABLE button
        Button table_btn = findViewById(R.id.table_btn);
        table_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, TableActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("table", table);
                bundle.putStringArrayList("names_list", names_list);
                bundle.putIntegerArrayList("green_things", green_things);
                intent.putExtras(bundle);
                //Send table to intent
                startActivity(intent);
            }
        });

        first_init();
    }

    private void set_IDs_to_players(){
        for(int i = 0; i < names_list.size(); i++){
            if(names_list.get(i).equals(NAME_STEPAN))
                ID_STEPAN = i;
            if(names_list.get(i).equals(NAME_ANYA))
                ID_ANYA = i;
            if(names_list.get(i).equals(NAME_MAMA))
                ID_MAMA = i;
            if(names_list.get(i).equals(NAME_PAPA))
                ID_PAPA = i;
        }
    }

    //Picking users cards
    private void first_init(){
        //Generating table
        table = new int[21][names_list.size()];
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                table[i][j] = NO_INFO;
            }
        }

        TextView textView = findViewById(R.id.asker);
        textView.setText("YOUR CARDS");

        Button button = findViewById(R.id.ready_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_cards_to_table(ID_STEPAN, HAVE_CARD);
                add_unmarked_cards_to_table(ID_STEPAN);

                clear_toggle_buttons();
                change_current_asker();
            }
        });
    }

    private void add_cards_to_table(int who, int state){
        ToggleButton toggleButton = findViewById(R.id.mr_green);
        if(toggleButton.isChecked())
            table[ID_PERSON_GREEN][who] = state;
        toggleButton = findViewById(R.id.mr_mastard);
        if(toggleButton.isChecked())
            table[ID_PERSON_MASTARD][who] = state;
        toggleButton = findViewById(R.id.mrs_pikok);
        if(toggleButton.isChecked())
            table[ID_PERSON_PIKOK][who] = state;
        toggleButton = findViewById(R.id.mr_plam);
        if(toggleButton.isChecked())
            table[ID_PERSON_PLAM][who] = state;
        toggleButton = findViewById(R.id.mr_scarlet);
        if(toggleButton.isChecked())
            table[ID_PERSON_SCARLET][who] = state;
        toggleButton = findViewById(R.id.mr_uait);
        if(toggleButton.isChecked())
            table[ID_PERSON_UAIT][who] = state;
        toggleButton = findViewById(R.id.g_key);
        if(toggleButton.isChecked())
            table[ID_THING_G_KEY][who] = state;
        toggleButton = findViewById(R.id.candlestick);
        if(toggleButton.isChecked())
            table[ID_THING_CANDLESTICK][who] = state;
        toggleButton = findViewById(R.id.knife);
        if(toggleButton.isChecked())
            table[ID_THING_KNIFE][who] = state;
        toggleButton = findViewById(R.id.revolver);
        if(toggleButton.isChecked())
            table[ID_THING_REVOLVER][who] = state;
        toggleButton = findViewById(R.id.trumpet);
        if(toggleButton.isChecked())
            table[ID_THING_TRUMPET][who] = state;
        toggleButton = findViewById(R.id.rope);
        if(toggleButton.isChecked())
            table[ID_THING_ROPE][who] = state;
        toggleButton = findViewById(R.id.bathroom);
        if(toggleButton.isChecked())
            table[ID_PLACE_BATHROOM][who] = state;
        toggleButton = findViewById(R.id.cabinet);
        if(toggleButton.isChecked())
            table[ID_PLACE_CABINET][who] = state;
        toggleButton = findViewById(R.id.dinner_room);
        if(toggleButton.isChecked())
            table[ID_PLACE_DINNER_ROOM][who] = state;
        toggleButton = findViewById(R.id.billiard_room);
        if(toggleButton.isChecked())
            table[ID_PLACE_BILLIARD_ROOM][who] = state;
        toggleButton = findViewById(R.id.garage);
        if(toggleButton.isChecked())
            table[ID_PLACE_GARAGE][who] = state;
        toggleButton = findViewById(R.id.bedroom);
        if(toggleButton.isChecked())
            table[ID_PLACE_BEDROOM][who] = state;
        toggleButton = findViewById(R.id.living_room);
        if(toggleButton.isChecked())
            table[ID_PLACE_LIVING_ROOM][who] = state;
        toggleButton = findViewById(R.id.kitchen);
        if(toggleButton.isChecked())
            table[ID_PLACE_KITCHEN][who] = state;
        toggleButton = findViewById(R.id.yard);
        if(toggleButton.isChecked())
            table[ID_PLACE_YARD][who] = state;
        check_table();
    }

    private void add_unmarked_cards_to_table(int who){
        for(int i = 0; i < table.length; i++){
            if(table[i][who] == NO_INFO)
            table[i][who] = DONT_HAVE_CARD;
        }
        check_table();
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
        if(current_asker >= names_list.size())
            current_asker = 0;

        //set answerer
        current_answerer = current_asker;

        //Set current asker to header
        TextView textView = findViewById(R.id.asker);
        textView.setText("Asker: " + names_list.get(current_asker));

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
                        add_cards_to_table(current_answerer, DONT_HAVE_CARD);
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
        if(current_answerer >= names_list.size())
            current_answerer = 0;

        TextView textView = findViewById(R.id.asker);
        textView.setText("Answerer: " + names_list.get(current_answerer));
    }

    private void check_table(){
        for(int i = 0; i < table.length; i++){
            if((table[i][0] == HAVE_CARD) || (table[i][1] == HAVE_CARD) || (table[i][2] == HAVE_CARD) || (table[i][3] == HAVE_CARD))
                for(int j = 0; j < table[i].length; j++)
                    if(table[i][j] != HAVE_CARD)
                        table[i][j] = DONT_HAVE_CARD;
            if((table[i][0] == DONT_HAVE_CARD) && (table[i][1] == DONT_HAVE_CARD) && (table[i][2] == DONT_HAVE_CARD) && (table[i][3] == DONT_HAVE_CARD)) {
                //TODO: add colouring thing name
                green_things.add(i);
            }
        }
    }
}
//TODO: add table class
//TODO: pick which card was shown
//TODO: add array of toggle_button IDs(idk if it's needed)
