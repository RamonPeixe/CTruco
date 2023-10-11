package com.tatayrapha.leonardabot;

import com.bueno.spi.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LeonardaBotTest {

    @Mock
    GameIntel intel;

    LeonardaBot leonardaBot;

    @BeforeEach
    void setUp() {
        leonardaBot = new LeonardaBot();
    }

    @Test
    @DisplayName("Should throw random card.")
    void shouldThrowRandomCard() {
        final List<TrucoCard> trucoCardList = List.of(TrucoCard.of(CardRank.ACE, CardSuit.CLUBS), TrucoCard.of(CardRank.TWO, CardSuit.CLUBS), TrucoCard.of(CardRank.THREE, CardSuit.CLUBS));
        when(intel.getCards()).thenReturn(trucoCardList);
        CardToPlay chosenCard = leonardaBot.chooseCard(intel);
        assertThat(trucoCardList).contains(chosenCard.value());
    }

    @Test
    @DisplayName("Should ask for a raise when having a zap.")
    void shouldAskForRaiseWithZap() {
        final List<TrucoCard> trucoCardList = List.of(TrucoCard.of(CardRank.ACE, CardSuit.CLUBS), TrucoCard.of(CardRank.TWO, CardSuit.CLUBS), TrucoCard.of(CardRank.THREE, CardSuit.CLUBS));
        final TrucoCard vira = TrucoCard.of(CardRank.ACE, CardSuit.CLUBS);
        when(intel.getVira()).thenReturn(vira);
        when(intel.getCards()).thenReturn(trucoCardList);
        assertThat(leonardaBot.decideIfRaises(intel)).isTrue();
    }
}