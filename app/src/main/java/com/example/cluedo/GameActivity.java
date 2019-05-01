package com.example.cluedo;

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
    private static int ID_STEPAN = 0;
    public static String NAME_ANYA = "ANYA";
    private static int ID_ANYA = 1;
    public static String NAME_MAMA = "MAMA";
    private static int ID_MAMA = 2;
    public static String NAME_PAPA = "PAPA";
    private static int ID_PAPA = 3;

    private static int NO_INFO = 0;
    private static int HAVE_CARD = 1;
    private static int DONT_HAVE_CARD = 2;

    private int ID_PERSON_GREEN = 0;
    private int ID_PERSON_MASTARD = 1;
    private int ID_PERSON_PIKOK = 2;
    private int ID_PERSON_PLAM = 3;
    private int ID_PERSON_SCARLET = 4;
    private int ID_PERSON_UAIT = 5;

    private int ID_THING_G_KEY = 6;
    private int ID_THING_CANDLESTICK = 7;
    private int ID_THING_KNIFE = 8;
    private int ID_THING_REVOLVER = 9;
    private int ID_THING_TRUMPET = 10;
    private int ID_THING_ROPE = 11;

    private int ID_PLACE_BATHROOM = 12;
    private int ID_PLACE_CABINET = 13;
    private int ID_PLACE_DINNER_ROOM = 14;
    private int ID_PLACE_BILLIARD_ROOM = 15;
    private int ID_PLACE_GARAGE = 16;
    private int ID_PLACE_BEDROOM = 17;
    private int ID_PLACE_LIVING_ROOM = 18;
    private int ID_PLACE_KITCHEN = 19;
    private int ID_PLACE_YARD = 20;

    private ArrayList<String> names_list = new ArrayList<>();
    private int current_asker = -1;
    private int current_answerer;
    private int[][] table; //NAME, THING

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if((getIntent() != null ) && (getIntent().hasExtra("names_list")))
            names_list = getIntent().getStringArrayListExtra("names_list");

        first_init();
    }

    //Picking users cards
    private void first_init(){
        //Generating table
        table = new int[21][names_list.size()];
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                table[i][j] = 0;
            }
        }

        TextView textView = findViewById(R.id.asker);
        textView.setText("YOUR CARDS");

        Button button = findViewById(R.id.ready_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_cards_to_table(ID_STEPAN, HAVE_CARD);

                clear_toggle_buttons();
                change_current_asker();
            }
        });
    }

    private void add_cards_to_table(int who, int state){
        ToggleButton toggleButton = findViewById(R.id.mr_green);
        if(toggleButton.isActivated())
            table[who][ID_PERSON_GREEN] = state;
        toggleButton = findViewById(R.id.mr_mastard);
        if(toggleButton.isActivated())
            table[who][ID_PERSON_MASTARD] = state;
        toggleButton = findViewById(R.id.mrs_pikok);
        if(toggleButton.isActivated())
            table[who][ID_PERSON_PIKOK] = state;
        toggleButton = findViewById(R.id.mr_plam);
        if(toggleButton.isActivated())
            table[who][ID_PERSON_PLAM] = state;
        toggleButton = findViewById(R.id.mr_scarlet);
        if(toggleButton.isActivated())
            table[who][ID_PERSON_SCARLET] = state;
        toggleButton = findViewById(R.id.mr_uait);
        if(toggleButton.isActivated())
            table[who][ID_PERSON_UAIT] = state;
        toggleButton = findViewById(R.id.g_key);
        if(toggleButton.isActivated())
            table[who][ID_THING_G_KEY] = state;
        toggleButton = findViewById(R.id.candlestick);
        if(toggleButton.isActivated())
            table[who][ID_THING_CANDLESTICK] = state;
        toggleButton = findViewById(R.id.knife);
        if(toggleButton.isActivated())
            table[who][ID_THING_KNIFE] = state;
        toggleButton = findViewById(R.id.revolver);
        if(toggleButton.isActivated())
            table[who][ID_THING_REVOLVER] = state;
        toggleButton = findViewById(R.id.trumpet);
        if(toggleButton.isActivated())
            table[who][ID_THING_TRUMPET] = state;
        toggleButton = findViewById(R.id.rope);
        if(toggleButton.isActivated())
            table[who][ID_THING_ROPE] = state;
        toggleButton = findViewById(R.id.bathroom);
        if(toggleButton.isActivated())
            table[who][ID_PLACE_BATHROOM] = state;
        toggleButton = findViewById(R.id.cabinet);
        if(toggleButton.isActivated())
            table[who][ID_PLACE_CABINET] = state;
        toggleButton = findViewById(R.id.dinner_room);
        if(toggleButton.isActivated())
            table[who][ID_PLACE_DINNER_ROOM] = state;
        toggleButton = findViewById(R.id.billiard_room);
        if(toggleButton.isActivated())
            table[who][ID_PLACE_BILLIARD_ROOM] = state;
        toggleButton = findViewById(R.id.garage);
        if(toggleButton.isActivated())
            table[who][ID_PLACE_GARAGE] = state;
        toggleButton = findViewById(R.id.bedroom);
        if(toggleButton.isActivated())
            table[who][ID_PLACE_BEDROOM] = state;
        toggleButton = findViewById(R.id.living_room);
        if(toggleButton.isActivated())
            table[who][ID_PLACE_LIVING_ROOM] = state;
        toggleButton = findViewById(R.id.kitchen);
        if(toggleButton.isActivated())
            table[who][ID_PLACE_KITCHEN] = state;
        toggleButton = findViewById(R.id.yard);
        if(toggleButton.isActivated())
            table[who][ID_PLACE_YARD] = state;
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

    private void change_current_asker(){
        clear_toggle_buttons();

        //set asker
        current_asker++;
        if(current_asker >= names_list.size())
            current_asker = 0;

        //set answerer
        current_answerer = current_asker + 1;
        if(current_answerer >= names_list.size())
            current_answerer = 0;

        //Set current asker to header
        TextView textView = findViewById(R.id.asker);
        textView.setText(names_list.get(current_asker));

        //Change buttons(in the bottom)
        Button btn = findViewById(R.id.ready_btn);
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set current answerer to header
                TextView textView = findViewById(R.id.asker);
                textView.setText(names_list.get(current_answerer));

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

                        //Set current answerer to header
                        TextView textView = findViewById(R.id.asker);
                        textView.setText(names_list.get(current_answerer));
                    }
                });
            }
        });

        btn = findViewById(R.id.btn_yes);
        btn.setVisibility(View.INVISIBLE);

        btn = findViewById(R.id.btn_no);
        btn.setVisibility(View.INVISIBLE);
    }

    private void change_current_answerer(){
        current_answerer++;
        if(current_answerer >= names_list.size())
            current_answerer = 0;
    }
}
