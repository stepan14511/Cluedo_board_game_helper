package com.example.cluedo;

import android.database.sqlite.SQLiteDoneException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {
    private int HAVE_CARD_ID;
    private int DONT_HAVE_CARD_ID;
    private int TEXT_COLOR_GREEN_THINGS;

    private Table table;
    private int[][] table_IDs;
    private int[] things_IDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        init_IDs();

        //Get Table object
        Bundle bundle = getIntent().getExtras();
        table = new Table(bundle);

        //Draw it
        draw_table(table.get_table_for_drawing());
        draw_players_names(table.get_names_list());
        colour_green_things(table.get_green_things());
    }

    private void draw_table(int[][] drawable_table){
        for(int i = 0; i < drawable_table.length; i++){
            for(int j = 0; j < drawable_table[i].length; j++){
                if(drawable_table[i][j] == Table.HAVE_CARD) {
                    ImageView imageView = findViewById(table_IDs[i][j]);
                    imageView.setBackgroundColor(getResources().getColor(HAVE_CARD_ID));
                }
                if(drawable_table[i][j] == Table.DONT_HAVE_CARD){
                    ImageView imageView = findViewById(table_IDs[i][j]);
                    imageView.setBackgroundColor(getResources().getColor(DONT_HAVE_CARD_ID));
                }
            }
        }
    }

    private void colour_green_things(ArrayList<Integer> green_things){
        for(int i = 0; i < green_things.size(); i++){
            TextView textView = findViewById(things_IDs[green_things.get(i)]);
            textView.setTextColor(getResources().getColor(TEXT_COLOR_GREEN_THINGS));
        }
    }

    private void draw_players_names(ArrayList<String> names_list){
        TextView textView = findViewById(R.id.name_0);
        textView.setText(names_list.get(0));
        textView = findViewById(R.id.name_1);
        textView.setText(names_list.get(1));
        textView = findViewById(R.id.name_2);
        textView.setText(names_list.get(2));
        textView = findViewById(R.id.name_3);
        textView.setText(names_list.get(3));
    }

    private void init_IDs(){
        HAVE_CARD_ID = R.color.green;
        DONT_HAVE_CARD_ID = R.color.red;
        TEXT_COLOR_GREEN_THINGS = R.color.green;

        table_IDs = new int[][]{
                {R.id.table_0_0, R.id.table_1_0, R.id.table_2_0, R.id.table_3_0},
                {R.id.table_0_1, R.id.table_1_1, R.id.table_2_1, R.id.table_3_1},
                {R.id.table_0_2, R.id.table_1_2, R.id.table_2_2, R.id.table_3_2},
                {R.id.table_0_3, R.id.table_1_3, R.id.table_2_3, R.id.table_3_3},
                {R.id.table_0_4, R.id.table_1_4, R.id.table_2_4, R.id.table_3_4},
                {R.id.table_0_5, R.id.table_1_5, R.id.table_2_5, R.id.table_3_5},
                {R.id.table_0_6, R.id.table_1_6, R.id.table_2_6, R.id.table_3_6},
                {R.id.table_0_7, R.id.table_1_7, R.id.table_2_7, R.id.table_3_7},
                {R.id.table_0_8, R.id.table_1_8, R.id.table_2_8, R.id.table_3_8},
                {R.id.table_0_9, R.id.table_1_9, R.id.table_2_9, R.id.table_3_9},
                {R.id.table_0_10, R.id.table_1_10, R.id.table_2_10, R.id.table_3_10},
                {R.id.table_0_11, R.id.table_1_11, R.id.table_2_11, R.id.table_3_11},
                {R.id.table_0_12, R.id.table_1_12, R.id.table_2_12, R.id.table_3_12},
                {R.id.table_0_13, R.id.table_1_13, R.id.table_2_13, R.id.table_3_13},
                {R.id.table_0_14, R.id.table_1_14, R.id.table_2_14, R.id.table_3_14},
                {R.id.table_0_15, R.id.table_1_15, R.id.table_2_15, R.id.table_3_15},
                {R.id.table_0_16, R.id.table_1_16, R.id.table_2_16, R.id.table_3_16},
                {R.id.table_0_17, R.id.table_1_17, R.id.table_2_17, R.id.table_3_17},
                {R.id.table_0_18, R.id.table_1_18, R.id.table_2_18, R.id.table_3_18},
                {R.id.table_0_19, R.id.table_1_19, R.id.table_2_19, R.id.table_3_19},
                {R.id.table_0_20, R.id.table_1_20, R.id.table_2_20, R.id.table_3_20}};

        things_IDs = new int[]{R.id.thing_1, R.id.thing_2, R.id.thing_3, R.id.thing_4,R.id.thing_5,
                R.id.thing_6, R.id.thing_7, R.id.thing_8, R.id.thing_9, R.id.thing_10, R.id.thing_11,
                R.id.thing_12, R.id.thing_13, R.id.thing_14, R.id.thing_15, R.id.thing_16, R.id.thing_17,
                R.id.thing_18, R.id.thing_19, R.id.thing_20, R.id.thing_21};
    }
}
