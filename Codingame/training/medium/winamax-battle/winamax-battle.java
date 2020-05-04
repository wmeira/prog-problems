/**
Author: William Meira
Date: 2015
Platform: CodingGame
Link: https://www.codingame.com/training/medium/winamax-battle
*/

import java.util.*;
import java.io.*;
import java.math.*;

class Solution {

    private static LinkedList<String> cards1 = new LinkedList<String>();
    private static LinkedList<String> cards2 = new LinkedList<String>();
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        boolean PAT = false;

        int n = in.nextInt(); // the number of cards for player 1
        for (int i = 0; i < n; i++) {
            cards1.add(in.next()); // the n cards of player 1
        }
        int m = in.nextInt(); // the number of cards for player 2
        for (int i = 0; i < m; i++) {
            cards2.add(in.next()); // the m cards of player 2
        }

        int rounds = 0;
        while(!cards1.isEmpty() && !cards2.isEmpty() && !PAT) {
            rounds++;
            //Step 1: the fight
            if(cardValue(cards1.getFirst()) == cardValue(cards2.getFirst())) {
                System.err.println("WAR " + cardValue(cards1.getFirst()) + " " + cardValue(cards2.getFirst()) );
                //Start war:
                int warIndex = 4;
                while(true) {
                    if(cards1.size() - 1 >= warIndex && cards2.size() - 1 >= warIndex) {
                        if(cardValue(cards1.get(warIndex)) > cardValue(cards2.get(warIndex))) {
                            //Spoils to the war winner 1
                            //System.err.println(cards1);
                            cards1.addAll(cards1.subList(0, warIndex + 1));
                            cards1.subList(0, warIndex + 1).clear();
                            cards1.addAll(cards2.subList(0, warIndex + 1));
                            cards2.subList(0, warIndex + 1).clear();
                            break;
                        } else if (cardValue(cards1.get(warIndex)) < cardValue(cards2.get(warIndex))) {
                            //Spoils to the war winner 2
                            cards2.addAll(cards1.subList(0, warIndex + 1));
                            cards1.subList(0, warIndex + 1).clear();
                            cards2.addAll(cards2.subList(0, warIndex + 1));
                            cards2.subList(0, warIndex + 1).clear();
                            break;
                        } else {
                            warIndex = warIndex + 4; //the war continues
                        }
                    } else {
                        //Players don't have enough cards to battle the war
                        PAT = true;
                        break;
                    }
                }
            } else {
                //Spoils to the fight winner
                if(cardValue(cards1.getFirst()) > cardValue(cards2.getFirst())) {
                    cards1.addLast(cards1.removeFirst());
                    cards1.addLast(cards2.removeFirst());
                } else {
                    cards2.addLast(cards1.removeFirst());
                    cards2.addLast(cards2.removeFirst());
                }
            }
        }
        //Winner announcement
        if(PAT) {
            System.out.println("PAT");
        } else {
            int winner = cards2.isEmpty()?1:2;
            System.out.println(winner + " " + rounds);
        }
    }

    private static int cardValue(String card) {
        String value = card.substring(0, card.length() - 1);
        switch(value) {
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            case "A": return 14;
            default: return Integer.parseInt(value);
        }
    }
}
