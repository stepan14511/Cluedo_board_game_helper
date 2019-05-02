package com.example.cluedo;

import android.database.sqlite.SQLiteDoneException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class TableActivity extends AppCompatActivity {
    private int HAVE_CARD_ID;
    private int DONT_HAVE_CARD_ID;

    int[][] table;
    int[][] table_IDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        Bundle b = getIntent().getExtras();
        table = (int[][])b.getSerializable("table");

        init_IDs();

        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                if(table[i][j] == GameActivity.HAVE_CARD) {
                    ImageView imageView = findViewById(table_IDs[i][j]);
                    imageView.setBackgroundColor(getResources().getColor(HAVE_CARD_ID));
                }
                if(table[i][j] == GameActivity.DONT_HAVE_CARD){
                    ImageView imageView = findViewById(table_IDs[i][j]);
                    imageView.setBackgroundColor(getResources().getColor(DONT_HAVE_CARD_ID));
                }
            }
        }
    }

    private void init_IDs(){
        HAVE_CARD_ID = R.color.green;
        DONT_HAVE_CARD_ID = R.color.red;

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
    }
}
