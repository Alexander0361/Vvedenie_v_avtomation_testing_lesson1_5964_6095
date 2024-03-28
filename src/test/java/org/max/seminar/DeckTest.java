package org.max.seminar;

import org.junit.jupiter.api.*;
import org.max.seminar.exception.DeckEmptyException;
import org.max.seminar.type.Ranks;
import org.max.seminar.type.Suits;

import java.util.ArrayList;
import java.util.List;

/**
 * Тест кейс для тестирования объекта колоды
 */
public class DeckTest {

    Deck deck;
    static DeckService deckService;

    @BeforeAll
    static void init () {
        System.out.println("Запуск инициализации.");
        deckService = new DeckService();
    }

    @BeforeEach
    void createNewDeck () {
        System.out.println("Запуск перед тестом.");
        deck = new Deck(deckService.getNewDeck().getCards());
    }

    @AfterEach
    void afterEach() {
        System.out.println("Окончание после теста.");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Окончание после всех тестов.");
    }

    @Test
    void checkDeckSize () {
        //given
        //when
        //then
        Assertions.assertEquals(52,deck.getCards().size());
    }

    @Test
    void checkEmptyException() throws DeckEmptyException {
        //given
        //when
        for(int i = 0; i < 52; i++) {
            deck.getCard();
        }
        //then
        Assertions.assertThrows(DeckEmptyException.class, () -> deck.getCard());
    }

    @Test
    void checkEmptyDeck() {
        //given
        List<Card> cards = new ArrayList<>();
        //when
        Deck deckEmpty = new Deck(cards);
        //then
        Assertions.assertThrows(DeckEmptyException.class, () -> deckEmpty.getCard());
    }

    @Test
    void checkCardFromDeck() throws DeckEmptyException {
        //given
        List<Card> cards = new ArrayList<>();
        Card card1 = new Card(Ranks.SEVEN, Suits.CLUBS);
        Card card2 = new Card(Ranks.ACE, Suits.HEARTS);
        cards.add(card1);
        cards.add(card2);
        //when
        Deck deckWithCards = new Deck(cards);
        Card cardFromDeck = deckWithCards.getCard();
        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(card1, cardFromDeck),
                () -> Assertions.assertEquals(0, deckWithCards.getCards().size()),
                () -> Assertions.assertThrows(DeckEmptyException.class, () -> deckWithCards.getCard())
        );
    }

}
