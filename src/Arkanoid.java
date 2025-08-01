// 207810771 Avishai Harshoshanim

import Game.AnimationRunner;
import Levels.DirectHitLevel;
import Levels.LevelInformation;
import Levels.WideEasyLevel;
import Levels.Green3Level;
import Game.GameFlow;
import biuoop.GUI;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class that starts the Arkanoid game.
 */
public class Arkanoid {
    static final int FRAMES_PER_SECONDS = 60;
    static final int SURFACE_WIDTH = 800;
    static final int SURFACE_HEIGHT = 600;

    /**
     * The main method that creates a new game, initializes it, and starts it.
     *
     * @param args the command line arguments, if any
     */
    public static void main(String[] args) {
        AnimationRunner animationRunner = new AnimationRunner(FRAMES_PER_SECONDS);
        animationRunner.setGui(new GUI("Arkanoid", SURFACE_WIDTH, SURFACE_HEIGHT));
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new DirectHitLevel());
        levels.add(new WideEasyLevel());
        levels.add(new Green3Level());
        GameFlow gameFlow = new GameFlow(animationRunner, animationRunner.getGui().getKeyboardSensor());
        if ((args.length > 0) && !args[0].equals("${args}")) {
            List<LevelInformation> newLevels = new ArrayList<>();
            for (String arg : args) {
                try {
                    int level = Integer.parseInt(arg);
                    if (level <= levels.size() && level >= 1) {
                        newLevels.add(levels.get(level - 1));
                    }
                } catch (NumberFormatException  ignored) {
                }
            }
            gameFlow.runLevels(newLevels);
        } else {
            gameFlow.runLevels(levels);
        }
        animationRunner.getGui().close();
    }
}
