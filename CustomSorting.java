import java.util.*;
// enum for all the possible values in a deck of cards with custom value associated with each value such has ace = 14
enum Value {
    Two(2), Three(3), Four(4), Five(5), Six(6),
    Seven(7), Eight(8), Nine(9), Ten(10), Jack(11), Queen(12), King(13), Ace(14);

    private final int value;

    public int getValue(){
        return this.value;
    }

    Value(int value){
        this.value = value;
    }
}
// enum for all the suits in a deck of cards with custom value associated with each suit such has Spades = 4
enum Suit {
    Hearts(1), Diamonds(2), Clubs(3), Spades(4);
    private final int value;
    public int getValue(){
        return this.value;
    }
    Suit(int value){
        this.value = value;
    }
}
// Creating a object of card which has two properties (value and suit)
class Card {
    public Suit suit;
    public Value value;

    Card(){

    }

    Card(Suit suit, Value value){
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }
}
//Custom sort to sort the deck of card by suits
class SortBySuits implements Comparator<Card>{
    public int compare(Card a, Card b){
        return a.suit.getValue() - b.suit.getValue();
    }
}

public class CustomSorting {
    //Store the shuffled deck of card in an arraylist
    private static final ArrayList<Card> deckOfCards = new ArrayList<>();
    //Store sorted deck of card in an arraylist
    private static final ArrayList<Card> sortedDeckOfCards = new ArrayList<>();
    //Store all the values in an arraylist
    private static final ArrayList<Value> values = new ArrayList<>(Arrays.asList(Value.values()));
    //Store all the suits in an arraylist
    private static final ArrayList<Suit> suites = new ArrayList<>(Arrays.asList(Suit.values()));
    //TreeMap to create buckets for use with bucket sort
    private static final TreeMap<Integer, ArrayList<Card>> buckets = new TreeMap<>();
    //Method to shuffle the deck of cards
    private static void shuffleDeckOfCards(){
        Collections.shuffle(deckOfCards);
    }
    //Method to shuffle the values and suits
    private static void shuffleValuesAndSuites(){
        Collections.shuffle(values);
        Collections.shuffle(suites);
    }
    //Method to create a deck of cards
    private static void createDeckOfCards(){
        for(Suit suit : suites){
            for(Value value : values){
                deckOfCards.add(new Card(suit, value));
            }
        }
    }
    //Method to sort the deck of cards that takes a string as a parameter and returns the sorted deck of cards based on the flag.
    private static void sortDeckOfCards(String flag){
        //Create buckets and stores cards in the buckets 
        for(Card card : deckOfCards){
            buckets.computeIfAbsent(card.value.getValue(), k -> new ArrayList<>()).add(card);
        }
        //switch statement to parse the flag
        switch(flag.toLowerCase()){
            case "ascending" :
                buckets.forEach((key, val)->{   //Iterate through the buckets
                    val.sort(new SortBySuits()); //sorting the individual bucket by the suit of the card
                    sortedDeckOfCards.addAll(val); // storing the sorted bucket in the arraylist 
                }); break;
            case "descending" :
                buckets.descendingMap().forEach((key, val)->{ //Iterate through the buckets in decending order
                    val.sort(new SortBySuits());         //sorting the individual bucket by the suit of the card
                    Collections.reverse(val);           // reversing the sorted bucket to sort the bucket in descending order
                    sortedDeckOfCards.addAll(val);   // storing the sorted bucket in the arraylist
                }); break;
        }
    }
    //Method to sort the deck of cards if no flag is provided
    private static void sortedDeckOfCards(){
        sortedDeckOfCards("ascending");
    }

    public static void main(String[] args){
    // Uncomment the below code to test the program
    /*
        shuffleValuesAndSuites();  // shuffling the values and suites to create a semi-random deck of cards
        createDeckOfCards();
        shuffleDeckOfCards(); // shuffling the deck of cards to create a random deck of cards

        System.out.println("Shuffled Deck of Cards: ");
        System.out.println(deckOfCards);
        System.out.println(""); 

        sortDeckOfCards("ascending"); //Sort the deck of cards in ascending order

        System.out.println("Sorted Deck of Cards (Ascending): ");
        System.out.println(sortedDeckOfCards);
        System.out.println("");

        buckets.clear(); //clearing the buckets for another use
        sortedDeckOfCards.clear(); //clearing the deck for another use
 
        sortDeckOfCards("descending"); //Sort the deck of cards in descending order

        System.out.println("Sorted Deck of Cards (Descending): ");
        System.out.println(sortedDeckOfCards);
        System.out.println("");

        buckets.clear(); //clearing the buckets for another use
        sortedDeckOfCards.clear(); //clearing the deck for another use

        sortDeckOfCards(); //sort the deck of cards when no flag is specified
        
        System.out.println("Sorted Deck of Cards (No Flag): ");
        System.out.println(sortedDeckOfCards);
        System.out.println("");

        buckets.clear();  //clearing the buckets for another use
        sortedDeckOfCards.clear(); //clearing the deck for another use

        
    */
    }
}
