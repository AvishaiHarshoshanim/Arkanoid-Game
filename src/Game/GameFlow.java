// 207810771 Avishai Harshoshanim
package Game;

import Levels.LevelInformation;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * The GameFlow class manages the flow of the game, including running multiple levels and handling the game outcome.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;

    /**
     * Constructs a GameFlow object with the specified AnimationRunner and KeyboardSensor.
     *
     * @param ar the AnimationRunner used to run the game animations.
     * @param ks the KeyboardSensor used to read user input.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.score = new Counter();
        this.score.setCounter(0);
    }

    /**
     * Runs the given list of levels, progressing through each level until the player wins or loses.
     *
     * @param levels the list of LevelInformation objects representing the levels to be played.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean lastLevelCompleted = false;
        for (LevelInformation levelInfo : levels) {
            lastLevelCompleted = false;
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score);
            level.initialize();
            level.run();
            if (level.getBallNum().getValue() == 0) {
                break;
            }
            if (levelInfo.equals(levels.get(levels.size() - 1))) {
                lastLevelCompleted = true;
            }
        }
        if (lastLevelCompleted) {
            animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new EndGameAnimation("win", this.score)));
        }
    }
}
