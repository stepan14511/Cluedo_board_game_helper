#include <iostream>
#include <string>
#include <vector>
#include <cstdio>
#include <cstdlib>
#include <ctime> 

using namespace std;

const int NUMBER_OF_CARDS = 21;

class Card{
private:
    int index;
    string names[22] = {"Green", "Mastard", "Pikok", "Plam", "Scarlet", "Uait", "G_key" , "Candlestick", "Knife", "Revolver", "Trumpet", "Rope", "Bathroom", "Cabinet", "Dinner room", "Billiard room", "Garage", "Bedroom", "Living room", "Kitchen", "Yard", ""};

public:
    Card(int index){
        if(index <= NUMBER_OF_CARDS)
            this -> index = index;
        else
            index = 0;    
    }

    string get_card_name(){return names[index];}

    const bool operator==(const Card& lhs) const{
        if(lhs.index == this -> index)
            return true;
        return false;
    }
};

class Player{
private:
    vector<Card> cards;
    string name;

public:
    Player(){name = "";}

    Player(vector<Card> cards, string name){
        this -> cards = cards;
        this -> name = name;
    }

    Card check_cards(vector<Card> get_cards){
        for(int i = 0; i < cards.size(); i++){
            for(int j = 0; j < get_cards.size(); j++){
                if(cards[i] == get_cards[j])
                    return get_cards[j];
            }
        }
        Card nt_card(21);
        return nt_card;
    }

    string get_name(){return name;}

    Card get_card(int i){
        if(i >= cards.size()){
            Card card(21);
            return card;
        }
        return cards[i];
    }
};

class Queue{
private:
    vector<Player> queue;
    Player killer;
    int asker_id, answerer_id;

public:
    Queue(){
        //Something thats are real constructor made for
        asker_id = -1;
        answerer_id = -1;

        //Generating random queue
        vector<int> cards{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        //Generating killers cards
        vector<Card> killers_cards;
        for(int i = 0; i < 3; i++){
            int card_id = rand() % (i == 2 ? 9 : 6);
            card_id += i * 5;
            Card card(cards[card_id]);
            cards.erase(cards.begin() + card_id);
            killers_cards.push_back(card);
        }
        Player killer_temp(killers_cards, "Killer");
        killer = killer_temp;

        //Generating players
        vector<string> names{"Stepan", "Anna", "Mama", "Papa"};
        for(int i = 0; i < 4; i++){
            vector<Card> players_cards;
            int name_id = rand() % names.size();
            for(int j = 0; j < (names[name_id] == "Stepan" ? 6 : 4); j++){
                int card_id = rand() % cards.size();
                Card players_card(cards[card_id]);
                cards.erase(cards.begin() + card_id);
                players_cards.push_back(players_card);
            }
            Player player(players_cards, names[name_id]);
            names.erase(names.begin() + name_id);
            queue.push_back(player);
        }
    }

    void change_asker(){
        asker_id++;
        if(asker_id >= queue.size())
            asker_id = 0;
        
        answerer_id = asker_id;
    }

    bool change_answerer(){
        answerer_id++;
        if(answerer_id >= queue.size())
            answerer_id = 0;
        if(answerer_id == asker_id)
            return false;
        return true;
    }

    string return_names(){
        //Names
        string output = "";
        output += string(13 - killer.get_name().size(), ' ');
        output += killer.get_name();
        for(int i = 0; i < queue.size(); i++){
            if(i != 0)
                output += "  ";
            output += string(13 - queue[i].get_name().size(), ' ');
            output += queue[i].get_name();
        }
        output += "\n";

        //Cards
        for(int i = 0; i < 6; i++){
            output += string(13 - killer.get_card(i).get_card_name().size(), ' ');
            output += killer.get_card(i).get_card_name();
            for(int j = 0; j < 4; j++){
                output += "  ";
                output += string(13 - queue[j].get_card(i).get_card_name().size(), ' ');
                output += queue[j].get_card(i).get_card_name();
            }
            output += "\n";
        }
        return output;
    }

    int get_asker_id(){return asker_id;}
    
    int get_answerer_id(){return answerer_id;}

    Player get_player(int i){return queue[i];}
};

//Defining functions
vector<Card> pick_3_random_cards();

int main(){
    srand (time(NULL));
    Queue queue; //Generating queue while initializing
    //Output card distribution
    cout << "TYPE 'E' IF U WANT TO CLOSE THE PRORAM!!!" << "\n\n\n";
    cout << queue.return_names() << "\n";
    if(getchar() == 'e')
        return 0;
    //Starting gameplay
    while(true){
        queue.change_asker();
        cout << "Asker: " << queue.get_player(queue.get_asker_id()).get_name() << "\n";
        vector<Card> picked_cards = pick_3_random_cards();
        cout << "Picked cards: ";
        for(int i = 0; i < picked_cards.size(); i++){
            cout << picked_cards[i].get_card_name();
            if(i != picked_cards.size() - 1)
                cout << ", ";
        }
        cout << "\n";
        while(queue.change_answerer()){
            if(queue.get_player(queue.get_answerer_id()).check_cards(picked_cards).get_card_name() == ""){
                continue;
            }
            cout << queue.get_player(queue.get_answerer_id()).get_name() << " have a card.\n";
            if(queue.get_player(queue.get_asker_id()).get_name() == "Stepan"){
                cout << "Card: " << queue.get_player(queue.get_answerer_id()).check_cards(picked_cards).get_card_name() << "\n";
            }
            break;
        }
        if(queue.get_asker_id() == queue.get_answerer_id())
            cout << "NO ONE";
        if(getchar() == 'e')
            return 0;
    }
    return 0;
}

vector<Card> pick_3_random_cards(){
    vector<int> cards{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    vector<Card> result;
    for(int i = 0; i < 3; i++){
        int card_id = rand() % (i == 2 ? 9 : 6);
        card_id += i * 6;
        Card card(card_id);
        result.push_back(card);
    }
    return result;
}