package com.example.cluedo;

import android.os.Bundle;

import java.util.ArrayList;

public class Table {
    //region Initializing names of the players and their IDs in the names_list
    public static String NAME_STEPAN = "STEPAN";
    public int ID_STEPAN;
    public static String NAME_ANYA = "ANYA";
    public int ID_ANYA;
    public static String NAME_MAMA = "MAMA";
    public int ID_MAMA;
    public static String NAME_PAPA = "PAPA";
    public int ID_PAPA;

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
    //endregion

    //region Initializing elements states
    public static int NO_INFO = 0;
    public static int HAVE_CARD = 1;
    public static int DONT_HAVE_CARD = 2;
    //endregion

    //region Initializing things states
    public static int THINGS_CLEAR = 0;
    public static int THINGS_GREEN = 1;
    public static int THINGS_RED = 2;
    //endregion

    //region Initializing table_IDs
    public static int ID_PERSON_GREEN = 0;
    public static int ID_PERSON_MASTARD = 1;
    public static int ID_PERSON_PIKOK = 2;
    public static int ID_PERSON_PLAM = 3;
    public static int ID_PERSON_SCARLET = 4;
    public static int ID_PERSON_UAIT = 5;

    public static int ID_THING_G_KEY = 6;
    public static int ID_THING_CANDLESTICK = 7;
    public static int ID_THING_KNIFE = 8;
    public static int ID_THING_REVOLVER = 9;
    public static int ID_THING_TRUMPET = 10;
    public static int ID_THING_ROPE = 11;

    public static int ID_PLACE_BATHROOM = 12;
    public static int ID_PLACE_CABINET = 13;
    public static int ID_PLACE_DINNER_ROOM = 14;
    public static int ID_PLACE_BILLIARD_ROOM = 15;
    public static int ID_PLACE_GARAGE = 16;
    public static int ID_PLACE_BEDROOM = 17;
    public static int ID_PLACE_LIVING_ROOM = 18;
    public static int ID_PLACE_KITCHEN = 19;
    public static int ID_PLACE_YARD = 20;
    //endregion

    private int[][] table; //THING ,NAME
    private int[] things_states;
    private ArrayList<String> names_list;

    Table(ArrayList<String> names_list){
        this.names_list = names_list;
        set_IDs_to_players();

        //Generating table
        table = new int[21][names_list.size()];
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                table[i][j] = NO_INFO;
            }
        }

        //Generating things_states array
        things_states = new int[21];
        for(int i = 0; i < things_states.length; i++){
            things_states[i] = THINGS_CLEAR;
        }
    }

    //region Get functions
    public ArrayList<String> get_names_list() {
        return names_list;
    }

    public int get_names_list_size(){
        return names_list.size();
    }
    //endregion

    //region Functions for Game activity specifically
    public void change_card_state(int thing_id, int name_id, int state) throws IllegalArgumentException{
        if((state != NO_INFO) && (state != HAVE_CARD) && (state != DONT_HAVE_CARD))
            throw new IllegalArgumentException("Wrong state argument");
        table[thing_id][name_id] = state;

        set_cards_that_known_for_dont_having();
    }

    private void set_cards_that_known_for_dont_having(){
        for(int i = 0; i < table.length; i++){
            if((table[i][0] == HAVE_CARD) || (table[i][1] == HAVE_CARD) || (table[i][2] == HAVE_CARD) || (table[i][3] == HAVE_CARD)){
                for(int j = 0; j < table[i].length; j++){
                    if(table[i][j] != HAVE_CARD)
                        table[i][j] = DONT_HAVE_CARD;
                }
                things_states[i] = THINGS_RED;
            }
        }
    }
    //endregion

    //region Get functions for Table activity specifically
    public int[][] get_table_for_drawing(){
        return table;
    }

    public int[] get_things_states(){
        return things_states;
    }
    //endregion

    //region Creating and using bundles
    private String BUNDLE_TABLE = "table";
    private String BUNDLE_NAMES_LIST = "names_list";
    private String BUNDLE_THINGS_STATES = "things_states";

    //To create a table instance from incoming bundle
    Table(Bundle bundle){
        table = (int[][])bundle.getSerializable(BUNDLE_TABLE);
        names_list = bundle.getStringArrayList(BUNDLE_NAMES_LIST);
        set_IDs_to_players();
        things_states = (int[])bundle.getSerializable(BUNDLE_THINGS_STATES);
    }

    public Bundle get_bundle(){
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_TABLE, table);
        bundle.putStringArrayList(BUNDLE_NAMES_LIST, names_list);
        bundle.putSerializable(BUNDLE_THINGS_STATES, things_states);

        return bundle;
    }
    //endregion Creating and using bundles
}
