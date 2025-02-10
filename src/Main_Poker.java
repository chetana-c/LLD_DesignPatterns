/*
Poker Hand

You want to create a method that receives a hand of 5 cards and returns the best poker hand you can make with those cards.
For simplification, we'll assume cards have a number and a symbol. Numbers go from 1 to 10 and symbols are 'a', 'b', 'c' and 'd'.
To start off, assume there are only 3 types of poker hands, from better to worse. Take into consideration you will have to add the other ones later.
- Flush: 5 cards of the same symbol. Example: 10a 4a 2a 7a 9a
- Three of a Kind: 3 cards with the same number. Example: 4a 3b 1c 4d 4b
- Pair: Two cards with the same number. Example: 3a 2b 8d 1a 8c
Return the best poker hand you have. For example. If you have a pair, return "Pair" or any structure that symbolizes you have a pair. If you have a three of a kind, return "Three of a Kind" or any structure that represents it.
Notes:
- This question is best suited for Logical and Maintainable as it has a very low Problem Solving but a good solution requires good modularized code
- Don't ask the candidate to code the entire evaluation of all hands. Only a few are good enough. The important part of this problem is code organization
Follow Up Questions




- Let's say we introduce a new type of hand. How would you add it?
- Full House: Having both a pair and a three of a kind. Example: 2a 3a 3b 2c 2d
- Let's say we introduce the additional rule that every round there's a "lucky hand" that wins over all the other ones for that round only. So if a Two Pair gets picked as the lucky hand, it wins against all other hands, but next round a Flush might be the lucky hand. How would you add this change?
Common Expected Good Solutions



This is a rule evaluation problem. A good candidate would separate rule evaluation from each individual rule implementation. Optimally using interfaces or any similar mechanism to generalize rules.
A good solution would be to:
1. Create a rule interface
2. Code each hand evaluation an implementation of this interface
3. Create a list of these rules in order of priority and evaluate the hand input against each rule, returning when one matches
4. Having a default rule when none matches
Also a good candidate would encapsulate the cards and hand in some structure that holds the cards.
For example:
public class Card {
    private int value;
    private String symbol;
}

*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Card class representing a card with value and symbol
class Card {
    private int value;
    private char symbol;

    public Card(int value, char symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    public int getValue() {
        return value;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "[" + value + "," + symbol + "]";
    }
}

// Hand class representing a hand of cards
class Hand {
    private List<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    public Hand(List<Card> hand) {
        this.hand = hand;
    }

    public int getSize() {
        return hand.size();
    }

    public Card get(int index) {
        return hand.get(index);
    }

    public void add(Card card) {
        hand.add(card);
    }

    @Override
    public String toString() {
        StringBuilder outStr = new StringBuilder();
        for (Card card : hand) {
            outStr.append(card).append(",");
        }
        return outStr.substring(0, outStr.length() - 1);
    }

    public List<Card> getHand() {
        return hand;
    }
}

// Rule interface for evaluating poker hands
interface Rule {
    boolean evaluate(Hand hand);
    int getPriority();
    String getRuleName();
}

// FlushRule class for evaluating Flush hand
class FlushRule implements Rule {
    int priority;
    FlushRule(int p){
        this.priority = p;
    }

    @Override
    public int getPriority(){
        return this.priority;
    }

    @Override
    public boolean evaluate(Hand hand) {
        char symbolToCompare = hand.get(0).getSymbol();
        int symbolCount = 0;
        for (Card card : hand.getHand()) {
            if (symbolToCompare == card.getSymbol()) {
                symbolCount++;
            }
        }
        return symbolCount >= 5;
    }

    @Override
    public String getRuleName() {
        return "FLUSH";
    }
}

// ThreeOfAKindRule class for evaluating Three of a Kind hand
class ThreeOfAKindRule implements Rule {
    int priority;
    ThreeOfAKindRule(int p){
        this.priority = p;
    }

    @Override
    public int getPriority(){
        return this.priority;
    }
    @Override
    public boolean evaluate(Hand hand) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (Card card : hand.getHand()) {
            frequencyMap.put(card.getValue(), frequencyMap.getOrDefault(card.getValue(), 0) + 1);
            if (frequencyMap.get(card.getValue()) == 3) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getRuleName() {
        return "THREE_OF_A_KIND";
    }
}

// PairRule class for evaluating Pair hand
class PairRule implements Rule {
    int priority;
    PairRule(int p){
        this.priority = p;
    }

    @Override
    public int getPriority(){
        return this.priority;
    }
    @Override
    public boolean evaluate(Hand hand) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (Card card : hand.getHand()) {
            frequencyMap.put(card.getValue(), frequencyMap.getOrDefault(card.getValue(), 0) + 1);
            if (frequencyMap.get(card.getValue()) == 2) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getRuleName() {
        return "PAIR";
    }
}

// Game class to manage the poker game
class PokerRound {
    private List<Hand> hands;
    private List<Rule> rules;
    public PokerRound(List<Hand> hand, List<Rule> rules) {
        this.hands = hand;
        this.rules = rules;
    }

    public void rearrangeRules(Rule luckyRule) {
        rules.remove(luckyRule);
        rules.add(0, luckyRule);
    }

    public String findWinnger(Rule luckyRule) {
        if (luckyRule != null) {
            rearrangeRules(luckyRule);
        }
        Hand winning = null;
        Rule winningHandRule = null;
        for(Hand curr : hands){
            for (Rule rule : rules) {
                if (rule.evaluate(curr)) {
                    if(winningHandRule == null || winningHandRule.getPriority() > rule.getPriority()){
                        winningHandRule = rule;
                        winning = curr;
                        break;
                    }
                }
            }
        }

        if(winning == null)  return "No rule matches for the current hand";

        return winningHandRule.getRuleName() + " rule matches with the hand: " + winning;
    }
}

// Utility class to build a random hand of cards
class Utility {
    public static Hand buildRandomCards() {
        char[] setOfSymbols = {'a', 'b', 'c', 'd'};
        Hand hand = new Hand();
        while (hand.getSize() < 5) {
            int randomValue = (int) (Math.random() * 10) + 1;
            char randomSymbol = setOfSymbols[(int) (Math.random() * 4)];
            hand.add(new Card(randomValue, randomSymbol));
        }
        return hand;
    }
}

// Main class to run the game
public class Main_Poker {
    public static void main(String[] args) {
        List<Rule> rules = new ArrayList<>();
        rules.add(new FlushRule(1));
        rules.add(new ThreeOfAKindRule(2));
        rules.add(new PairRule(3));
        List<Hand> hands =  new ArrayList<>();
        Hand a = new Hand();
        Card c1 = new Card(3,'a');
        Card c2 = new Card(4,'a');
        Card c3 = new Card(5,'a');
        Card c4 = new Card(6,'a');
        Card c5 = new Card(7,'a');
        a.add(c1);a.add(c2);a.add(c3);a.add(c4);a.add(c5);
        hands.add(a);
        for (int i = 0; i < 5; i++) {
            Hand hand = Utility.buildRandomCards();
            hands.add(hand);
            System.out.println(" current hand : " + hand);
        }

        PokerRound pokerGame = new PokerRound(hands, rules);
        System.out.println(pokerGame.findWinnger(null));
    }
}

