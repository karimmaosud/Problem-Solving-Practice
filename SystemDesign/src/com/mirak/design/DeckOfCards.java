package com.mirak.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class DeckOfCards {

  private enum Suit {
    Club, Diamond, Heart, Spade
  }

  abstract class Card {

    String label;
    Suit suit;
    int value;
    boolean available;

    Card(String label, Suit suit) {
      this.label = label;
      this.suit = suit;
      available = true;
    }

    abstract int getValue();

    public void setValue(int value) {
      this.value = value;
    }

    void markUnavailable() {
      this.available = false;
    }
  }

  abstract class Hand<T extends Card> {

    ArrayList<T> cards;

    abstract int getScore();

    void addCard(T card) {
      cards.add(card);
    }
  }

  class Deck<T extends Card> {

    ArrayList<T> cards;
    int dealtIndex = 0;

    Deck(ArrayList<T> cards) {
      this.cards = cards;
    }

    void shuffle() {
      Collections.shuffle(cards);
    }

    ArrayList<T> dealHand(int number) {
      ArrayList<T> handCards = new ArrayList<>();
      for (int i = 0; i < number; i++) {
        T dealtCard = cards.get(dealtIndex++);
        dealtCard.markUnavailable();
        handCards.add(dealtCard);
      }
      return handCards;
    }

    T dealCard() {
      T dealtCard = cards.get(dealtIndex++);
      dealtCard.markUnavailable();
      return dealtCard;
    }
  }

  // BlackJack.

  class BlackJackCard extends Card {

    BlackJackCard(String label, Suit suit) {
      super(label, suit);
    }

    @Override
    int getValue() {
      if (isPicture()) {
        return 10;
      }
      if (isAce()) {
        return 1;
      }
      return Integer.parseInt(this.label);
    }

    int minValue() {
      return getValue();
    }

    int maxValue() {
      return this.label.equals("1") ? 11 : getValue();
    }

    boolean isAce() {
      return label.equals("1");
    }

    boolean isPicture() {
      return this.label.equals('J') || this.label.equals('Q') || this.label.equals('K');
    }
  }

  class BlackJackHand extends Hand<BlackJackCard> {

    @Override
    int getScore() {
      HashSet<Integer> scores = new HashSet<>();
      int minScore = 0;
      int maxScore = 0;
      for (BlackJackCard blackJackCard : cards) {
        minScore += blackJackCard.minValue();
        maxScore += blackJackCard.maxValue();
      }
      scores.add(minScore);
      scores.add(maxScore);
      int minOver = Integer.MAX_VALUE;
      int maxUnder = Integer.MIN_VALUE;
      for (int score : scores) {
        if (score <= 21) {
          maxUnder = Math.max(maxUnder, score);
        } else {
          minOver = Math.min(minOver, score);
        }
      }
      return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
    }

    boolean isBlackJack() {
      return false;
    }

    boolean is21() {
      return getScore() == 21;
    }

    boolean isBusted() {
      return getScore() > 21;
    }
  }
}
