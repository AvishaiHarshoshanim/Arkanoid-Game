// 207810771 Avishai Harshoshanim
package Game;
import Collision.Block;
import Collision.Collidable;
import Collision.GameEnvironment;
import Geometry.Point;
import Levels.LevelInformation;
import Sprites.Ball;
import Sprites.SpriteCollection;
import Sprites.ScoreIndicator;
import Sprites.Sprite;
import Sprites.LevelIndicator;
import Sprites.Paddle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The GameLevel class represents a level in the Arkanoid game. It contains the game's sprites, game environment,
 * and handles the game logic for a specific level.
 */
public class GameLevel implements Animation {
    static final int SURFACE_WIDTH = 800;
    static final int SURFACE_HEIGHT = 600;
    static final int ZERO = 0;
    static final int ONE = 1;
    static final int SCORE_TEXT_X = 250;
    static final int TEXT_Y = 18;
    static final int TEXT_FONT = 20;
    static final int WALL_WIDTH = 20;
    static final int PADDLE_POSE_Y = 583;
    static final int PADDLE_HEIGHT = 15;
    static final int LEVEL_SCORE = 100;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockNum;
    private Counter ballNum;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;
    private Counter score;

    /**
     * Constructs a GameLevel object with the specified level information, keyboard sensor, animation runner, and score.
     *
     * @param levelInformation The LevelInformation object representing the level to be played.
     * @param keyboard        The KeyboardSensor used to read user input.
     * @param animationRunner The AnimationRunner used to run the game animations.
     * @param score           The Counter object to keep track of the player's score.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard, AnimationRunner animationRunner,
                     Counter score) {
        this.levelInformation = levelInformation;
        this.runner = animationRunner;
        this.keyboard = keyboard;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockNum = new Counter();
        this.ballNum = new Counter();
        this.score = score;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c The collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * Adds a sprite object to the game's sprite collection.
     *
     * @param s The sprite object to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * Removes a collidable object from the game environment.
     *
     * @param c The collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * Removes a sprite object from the game's sprite collection.
     *
     * @param s The sprite object to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initializes the game level by setting up the initial state of the game, including adding blocks, balls,
     * paddle, score indicator, and initializing counters.
     */
    public void initialize() {
        // Initialize counters
        this.ballNum.setCounter(ZERO);
        Sprite background = this.levelInformation.getBackground();
        if (background != null) {
            background.addToGame(this);
        }
        // Create and add ScoreIndicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(SCORE_TEXT_X, TEXT_Y, this.score, TEXT_FONT);
        scoreIndicator.addToGame(this);
        // Create and add LevelIndicator
        LevelIndicator levelName = new LevelIndicator(450, TEXT_Y, this.levelInformation.levelName(), TEXT_FONT);
        levelName.addToGame(this);
        // Create and add balls
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = this.levelInformation.balls().get(i);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ball.setEnvironment(this.environment);
            ball.addToGame(this);
            this.ballNum.increase(ONE);
        }
        // Initialize block counter and create HitListeners
        this.blockNum.setCounter(ZERO);
        BlockRemover blockRemover = new BlockRemover(this, this.blockNum);
        BallRemover ballRemover = new BallRemover(this, this.ballNum);
        ScoreTrackingListener scoreIncrease = new ScoreTrackingListener(this.score);
        // Create and add walls
        Block block = new Block(new Point(ZERO, WALL_WIDTH), SURFACE_WIDTH, WALL_WIDTH, Color.gray);
        block.addToGame(this);
        Block b2 = new Block(new Point(ZERO, WALL_WIDTH), WALL_WIDTH,
                SURFACE_HEIGHT - WALL_WIDTH, Color.gray);
        b2.addToGame(this);
        Block deathBlock = new Block(new Point(ZERO, SURFACE_HEIGHT - ONE),
                SURFACE_WIDTH, ONE, Color.gray);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(ballRemover);
        Block b4 = new Block(new Point(SURFACE_WIDTH - WALL_WIDTH, WALL_WIDTH),
                WALL_WIDTH, SURFACE_HEIGHT - WALL_WIDTH, Color.gray);
        b4.addToGame(this);
        // Create and add paddle
        Paddle p = new Paddle(new Point(400 - ((double) this.levelInformation.paddleWidth() / 2), PADDLE_POSE_Y),
                this.levelInformation.paddleWidth(), PADDLE_HEIGHT, this.levelInformation.paddleSpeed(), keyboard);
        p.addToGame(this);
        // Create and add blocks
        for (int i = 0; i < this.levelInformation.blocks().size(); i++) {
            Block blockToAdd = this.levelInformation.blocks().get(i);
            blockToAdd.addToGame(this);
            blockToAdd.addHitListener(blockRemover);
            blockToAdd.addHitListener(scoreIncrease);
            this.blockNum.increase(ONE);
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // stopping condition
        if (this.ballNum.getValue() == ZERO) {
            this.running = false;
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new EndGameAnimation("failed", this.score)));
        }
        if (this.blockNum.getValue() == ZERO
                || ((this.levelInformation.blocks().size() - this.blockNum.getValue())
                == this.levelInformation.numberOfBlocksToRemove())) {
            score.increase(LEVEL_SCORE);
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Runs the game level, starting with a countdown animation, then running the game logic until the level
     * is completed or the player loses.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(0.666666667, 3, this.sprites)); // countdown before turn starts.
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Returns the Counter object representing the number of balls in the game.
     *
     * @return The Counter object representing the number of balls.
     */
    public Counter getBallNum() {
        return ballNum;
    }
}