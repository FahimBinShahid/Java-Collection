package dev.lpa;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        String aText = "Hello";
        String bText = "Hello";
        String cText = String.join("l", "He", "lo");
        String dText = "He".concat("llo");
        String eText = "hello";

        List<String> hellos = Arrays.asList(aText, bText, cText, dText, eText);

        hellos.forEach(s -> System.out.println(s + ": " + s.hashCode()));

        Set<String> mySet = new HashSet<>(hellos);

        System.out.println("my Set: " + mySet);
        System.out.println("my elements: " + mySet.size());

        for(String setValues : mySet) {
            System.out.print(setValues + ": ");
            for(int i = 0; i < hellos.size(); i++){
                if(setValues == hellos.get(i)) {
                    System.out.print(i + ", ");
                }
            }
            System.out.println(" ");
        }
        PlayingCard aceOfHearts = new PlayingCard("Hearts" , "Ace");
        PlayingCard kingOfClubs = new PlayingCard("Clubs", "King");
        PlayingCard queenOfSpade = new PlayingCard("Spade" , "queen");

        List<PlayingCard> cards = Arrays.asList(aceOfHearts,kingOfClubs,queenOfSpade);
        cards.forEach(s-> System.out.println(s+ ": " + s.hashCode()));

        Set<PlayingCard>deck = new HashSet<>();
        for(PlayingCard c : cards){
            if(!deck.add(c)){
                System.out.println("Duplicate Card Found " + c);
            }
        }
        System.out.println(deck);
    }
}