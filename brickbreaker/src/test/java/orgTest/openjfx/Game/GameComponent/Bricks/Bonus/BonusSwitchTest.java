/*package orgTest.openjfx.Game.GameComponent.Bricks.Bonus;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjfx.Game.GameClass.Level;
import org.openjfx.Game.GameComponent.Racket;
import org.openjfx.Game.GameComponent.Bricks.Bonus.Bonus_switch;

public class BonusSwitchTest {

    private Level levelMock;
    private Racket racketP1Mock;
    private Racket racketP2Mock;

    @BeforeEach
    void setUp() {
        // Création des mocks pour les objets Level et Racket
        levelMock = mock(Level.class);
        racketP1Mock = mock(Racket.class);
        racketP2Mock = mock(Racket.class);
    }

    // Test lorsque RacketP2 n'existe pas
    @Test
    void testApplyWithOnlyP1() {
        // Configurer le mock pour que getRacketP2() retourne null
        when(levelMock.getRacketP1()).thenReturn(racketP1Mock);
        when(levelMock.getRacketP2()).thenReturn(null); // Pas de joueur 2

        // Appeler la méthode apply() de Bonus_switch
        Bonus_switch.apply(levelMock);

        // Vérifier que invertControls() a été appelé sur RacketP1
        verify(racketP1Mock, times(1)).invertControls();

        // Vérifier que invertControls() n'a pas été appelé sur RacketP2
        verify(racketP2Mock, never()).invertControls();
    }

    // Test lorsque RacketP2 existe
    @Test
    void testApplyWithP1AndP2() {
        // Configurer les mocks pour que getRacketP1() et getRacketP2() retournent des
        // objets Racket
        when(levelMock.getRacketP1()).thenReturn(racketP1Mock);
        when(levelMock.getRacketP2()).thenReturn(racketP2Mock);

        // Appeler la méthode apply() de Bonus_switch
        Bonus_switch.apply(levelMock);

        // Vérifier que invertControls() a été appelé sur RacketP1
        verify(racketP1Mock, times(1)).invertControls();

        // Vérifier que invertControls() a été appelé sur RacketP2
        verify(racketP2Mock, times(1)).invertControls();
    }
}
*/