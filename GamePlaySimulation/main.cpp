#include <iostream>
#include <string>

using namespace std;

class Card{
public:
    Card(int index){
        this -> index = index;
    }
    string get_card_name(){
        return names[index];
    }
private:
    int index;
    string names[21] = {"Green", "Mastard", "Pikok", "Plam", "Scarlet", "Uait", "G_key" , "Candlestick", "Knife", "Revolver", "Trumpet", "Rope", "Bathroom", "Cabinet", "Dinner room", "Billiard room", "Garage", "Bedroom", "Living room", "Kitchen", "Yard"};
};

int main(){
    Card card(20);
    cout << card.get_card_name() << "\n";
    return 0;
}