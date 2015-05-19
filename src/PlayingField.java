import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.awt.Font;

/**
 * The main playing field for the game.
 *
 * Created by oskarek on 2015-05-07.
 */
public class PlayingField extends BasicGameState {
    private int contWidth, contHeight;
    private LangFileReader langReader;
    private CollideChecker collideChecker;
    private String pauseString;
    private TrueTypeFont font;
    private boolean gamePaused;
    private ArrayList<PlayingFieldItem> items;
    private Board board;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private int timer, timer2; private int prevPowerUp=-1;
    private PowerUp powerUp;
    private boolean powerUpInvoked;
    private ArrayList<Laser> lasers; private ArrayList<Ball> balls; private ArrayList<Projectile> projectiles;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

        // dimensions of the container
        contHeight = container.getHeight();
        contWidth = container.getWidth();

        langReader = new LangFileReader();
        collideChecker = new CollideChecker(container);
        // load a default java font
        Font awtFont = new Font("Times New Roman", Font.BOLD, 18);
        font = new TrueTypeFont(awtFont, true);
        gamePaused = true;
        items = new ArrayList<>();
        board = new Board(70,contHeight-30,80,15);
        float ballRadius = 10;
        float ballXPos = board.getX()+board.getLength()/2;
        float ballYPos = board.getY()-ballRadius;
        float ballSpeed = 10;
        ball = new Ball(ballXPos,ballYPos,ballRadius,ballSpeed);
        balls = new ArrayList<>();
        balls.add(ball);
        bricks = new ArrayList<>();
        items.add(ball);
        items.add(board);
        MapMaker mapmaker = new MapMaker();
        mapmaker.writeMap(container);
        initBricks();
        Points.getInstance().addPoints(500);
        projectiles = new ArrayList<>(); lasers = new ArrayList<>();


    }

    public void initBricks() throws SlickException {
        MapReader mapReader = new MapReader();
        ArrayList<ArrayList<Integer>> mapInfo = mapReader.readMap(3);
        ArrayList<Integer> brickXPositions = mapInfo.get(0);
        ArrayList<Integer> brickYPositions = mapInfo.get(1);
        ArrayList<Integer> brickLevels = mapInfo.get(2);

        for(int i=0;i<brickXPositions.size();i++){
            int x = brickXPositions.get(i); int y = brickYPositions.get(i); int life = brickLevels.get(i);
            switch (life) {
                case 1: bricks.add(new RegularBrick(x,y));
                    break;
                case 2: bricks.add(new WoodBrick(x,y));
                    break;
                case 3: bricks.add(new MetalBrick(x,y));
                    break;
            }
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

        for (PlayingFieldItem item : items) { item.draw(g);}
        for(Projectile projectile : projectiles){ projectile.draw(g);}
        for(Brick brick : bricks){ if(brick.getBrickImage() != null){ brick.draw(g); }}
        for(Laser laser: lasers){ laser.draw(g);}
        g.drawString("Points : " + Points.getInstance().getPoints(), container.getWidth() - 120, 0);

        if (gamePaused) {
            int stringWidth = font.getWidth(pauseString);
            font.drawString(contWidth/2-stringWidth/2, contHeight/2, pauseString);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();

        if (!gamePaused) {
            // update the ball position
            updateBallPos(delta);

            updatePowerUpPos();

            updateProjectilePos();

            updateLaserPos();

            // move the board to the left if the left key is pressed
            if (input.isKeyDown(Input.KEY_LEFT)) {
                updateBoardPos(Direction.LEFT, delta);
            }
            // move the board to the right if the right key is pressed
            if (input.isKeyDown(Input.KEY_RIGHT)) {
                updateBoardPos(Direction.RIGHT, delta);
            }
            // go to the main menu if the escape key is pressed.
            if (input.isKeyPressed(Input.KEY_ESCAPE)) {
                gamePaused = true;
                game.enterState(0, new FadeOutTransition(), new FadeInTransition());
            }
            //check if the game has been beaten
            if (bricks.size() <= 0) {
                game.enterState(4, new FadeOutTransition(), new FadeInTransition());
            }
            //update all the bricks
            updateBricks();

            if (input.isKeyPressed(Input.KEY_SPACE)) {
                if (board.ableToShootSmallCannons()) {
                    projectiles.add(new Projectile(board.getXPosFirstCannon(), board.getYPosFirstCannon(), 1));
                    projectiles.add(new Projectile(board.getXPosSecondCannon(), board.getYPosSecondCannon(), 1));
                }
                if(board.ableToShootBigCannon()){
                    lasers.add(new Laser(board.getXPosBigCannon(),board.getYPosBigCannon()-80, 10));
                }
            }

            timer++;
            if (timer % 50 == 0) {
                Points.getInstance().decrementPoints();
            }
            if (timer % 800 == 0) {
                Random rand = new Random();
                int r= rand.nextInt(7);
                while(r == prevPowerUp){
                    r = rand.nextInt(7);
                }
                int x = rand.nextInt(container.getWidth());
                int y = -50;
                r=6;
                switch (r) {
                    case 0:
                        powerUp = new FastBall(x, y, ball);
                        break;
                    case 1:
                        powerUp = new SlowBall(x, y, ball);
                        break;
                    case 2:
                        powerUp = new BigBoard(x, y, board);
                        break;
                    case 3:
                        powerUp = new SmallBoard(x, y, board);
                        break;
                    case 4:
                        powerUp = new BigBall(x, y, ball);
                        break;
                    case 5:
                        powerUp = new CannonPowerUp(x, y, board);
                        break;
                    case 6:
                        powerUp = new LaserPowerUp(x,y,board);
                        break;
                }
                items.add(powerUp);
            }

            // go to the main menu if the escape key is pressed.
            if (input.isKeyPressed(Input.KEY_ESCAPE)) {
                gamePaused = true;
                game.enterState(0, new FadeOutTransition(), new FadeInTransition());
            }
            // pause the game if the space bar is pressed
            if (input.isKeyPressed(Input.KEY_P)) {
                gamePaused = true;
            }
            //check if the game has been beaten
            if (bricks.size() <= 0) {
                MainMenu mainMenu = (MainMenu) game.getState(0);
                mainMenu.gameIsFinished();
                game.enterState(4, new FadeOutTransition(), new FadeInTransition());
            }

            if (powerUpInvoked) {
                timer2++;
                int duration = powerUp.getDuration();
                if (timer2 % duration == 0) {
                    powerUp.reverse();
                    powerUpInvoked = false;
                }
            }
        } else {
            // start game if key p is pressed
            if (input.isKeyPressed(Input.KEY_P)) {
                gamePaused = !gamePaused;
            }
            // go to the main menu if the escape key is pressed.
            if (input.isKeyPressed(Input.KEY_ESCAPE)) {
                game.enterState(0, new FadeOutTransition(), new FadeInTransition());
            }
        }

    }



    private void updateBallPos(int delta) throws SlickException {
        for(Ball ball : balls){
            float dist = ball.getSpeed()*0.05f*delta;
            ball.move(dist);

            // check if the ball has collided with any object.
            collideChecker.checkBallCollisions(ball, board, bricks);
        }
    }

    public void updateBricks(){
        Iterator<Brick> it = bricks.iterator();
        while(it.hasNext()){
            if(it.next().getLives() <= 0) {
                it.remove();
            }
        }
    }
    public void updateProjectilePos(){
        for(Projectile projectile : projectiles){
            projectile.setY(projectile.getY()-5);

        }
        Iterator<Projectile> it = projectiles.iterator();
        while(it.hasNext()){
            if(collideChecker.checkProjectileCollision(bricks,it.next())){
                it.remove();
            }

        }
    }
    public void updateLaserPos() throws SlickException {
        if(lasers.size()>0) {
            lasers.add(new Laser(lasers.get(lasers.size() - 1).getX(), lasers.get(lasers.size() - 1).getY() - 4, 10));
            for(Laser laser: lasers){
                laser.setCenterX((board.getXPosBigCannon()+10));
            }
        }


    }




    public void updatePowerUpPos(){
        if(powerUp != null) {
            powerUp.setY(powerUp.getY()+3);
            powerUp.updateHitBox();
            if (collideChecker.checkPowerUpCollision(powerUp, board) && !powerUpInvoked) {
                powerUp.invoke();
                powerUpInvoked = true;
                items.remove(powerUp);
            }
        }
    }

    /**
     * Update the position of the board.
     * @param d The direction in which the board should move (left or right).
     * @param delta The delta value from the update method.
     */
    private void updateBoardPos(Direction d, int delta) {
        float currentxPos = board.getX();
        float boardLength = board.getLength();
        int speed = board.getSpeed();
        if(d == Direction.LEFT){
            if(currentxPos > 0) {
                float xPos = currentxPos - speed*0.05f*delta;
                board.setX(xPos);
            }
        }
        if(d == Direction.RIGHT){
            if(currentxPos+boardLength < contWidth) {
                float xPos;
                if(currentxPos+5 >= contWidth){
                    xPos = contWidth-boardLength;
                } else {
                    xPos = currentxPos + speed*0.05f*delta;

                }
                board.setX(xPos);
            }
        }
    }

    public void initPauseString() {
        String currLangFileName = "lang/currentlang.txt";
        String langFile = null;
        try (BufferedReader file = new BufferedReader(new FileReader(currLangFileName))) {
            langFile = file.readLine();
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find the currentlang file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (langFile == null) {
            throw new RuntimeException("The currentlang file is empty.");
        }

        pauseString = langReader.getString(TranslationAreas.PAUSE_STRING,langFile);
    }
}