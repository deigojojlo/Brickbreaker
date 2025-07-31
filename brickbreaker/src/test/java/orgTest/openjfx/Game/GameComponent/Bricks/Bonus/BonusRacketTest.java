/*package orgTest.openjfx.Game.GameComponent.Bricks.Bonus;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjfx.Game.GameClass.Level;
import org.openjfx.Game.GameComponent.Racket;
import org.openjfx.Game.GameComponent.Bricks.Bonus.Bonus_racket;
import org.mockito.MockedStatic;

public class BonusRacketTest {

    private Level levelMock;
    private Racket racketP1Mock;
    private Racket racketP2Mock;

    @BeforeEach
    void setUp() {
        // Création des mocks pour les raquettes des joueurs
        racketP1Mock = mock(Racket.class);
        racketP2Mock = mock(Racket.class);

        // Création du mock pour l'objet Level
        levelMock = mock(Level.class);

        // Définir les comportements des méthodes mockées
        when(levelMock.getRacketP1()).thenReturn(racketP1Mock);
        when(levelMock.getRacketP2()).thenReturn(racketP2Mock);
    }

    // Test de la méthode apply() quand la taille est augmentée
    @Test
    void testApplyIncreaseSizeWithP1AndP2() {

        // Appeler la méthode apply() de Bonus_racket
        Bonus_racket.apply(levelMock);

        // Vérifier que adjustSize() a été appelé
        verify(racketP1Mock, times(1)).adjustSize(anyInt(), eq(0));
        System.out.println("Adjust size called on P1");

        // Vérifier que adjustSize() a été appelé
        verify(racketP2Mock, times(1)).adjustSize(anyInt(), eq(0));
        System.out.println("Adjust size called on P2");
    }

    @Test
    void testApplyIncreaseSizeWithP1() {
        when(levelMock.getRacketP2()).thenReturn(null);
        // Appeler la méthode apply() de Bonus_racket
        Bonus_racket.apply(levelMock);

        // Vérifier que adjustSize() a été appelé
        verify(racketP1Mock, times(1)).adjustSize(anyInt(), eq(0));
        System.out.println("Adjust size called on P1");
    }
}*/
