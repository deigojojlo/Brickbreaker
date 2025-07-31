/* package orgTest.openjfx.Game.GameComponent.Bricks.Bonus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openjfx.GUI.GUIGame;
import org.openjfx.Game.GameClass.ScoreboardController;
import org.openjfx.Game.GameComponent.Bricks.Bonus.*;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.application.Platform;
import javafx.scene.control.Label;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

 
  class BonusTest extends ApplicationTest {
  
  @Mock
  private Aleatoire aleatoire;
  
  @Mock
  private GUIGame guiGame;
  
  @Mock
  private ScoreboardController scoreboardController;
  
  private Bonus bonus;
  
  @BeforeEach
  void setUp() {
  // Initialisation des mocks
  MockitoAnnotations.openMocks(this);
  bonus = new Bonus(aleatoire, guiGame, scoreboardController);
  }
  
  @Test
  void testApplyBonus() {
  // Vérifier que le bon label est ajouté à la GUI
  bonus.setEst_bonus(true);
  Platform.runLater(() -> {
  bonus.applybonus();
  verify(guiGame, times(1)).addBonusList(any(Label.class));
  });
  }
  
  @Test
  void testApplyBonusDoesNothingIfNoBonus() {
  Bonus bonusMocked = new Bonus(aleatoire, guiGame, scoreboardController);
  bonusMocked.setEst_bonus(false);
  
  // Vérifier que applybonus() ne fait rien si aucun bonus n'est généré
  bonusMocked.applybonus();
  verify(guiGame, times(0)).addBonusList(any(Label.class));
  }
  
  @Test
  void testSetAndGetEstBonus() {
  // Tester le getter et le setter de est_bonus
  bonus.setEst_bonus(true);
  assertTrue(bonus.est_bonus(),
  "Le bonus devrait être activé après avoir utilisé le setter");
  
  bonus.setEst_bonus(false);
  assertFalse(bonus.est_bonus(),
  "Le bonus devrait être désactivé après avoir utilisé le setter");
  }
  }
 */
