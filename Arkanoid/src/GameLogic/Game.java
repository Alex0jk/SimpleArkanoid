/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic;

import ModelLogic.BallLogic;
import ModelLogic.BricksLogic;
import ModelLogic.PlatformLogic;
import ModelObjects.Bricks;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author alexm
 */
public class Game {
    private int screenWidth;
    private int screenHeight;
    
    private int lives;
    private boolean gameStart;
    private int[] positionToRemove;
    
    private BallLogic ballLogic;
    private PlatformLogic platformLogic;
    private BricksLogic bricksLogic;
    
    private Circle ball;
    private Rectangle platform;
    private Bricks bricks;
    private Group root;
    
    public Game(int screenWidth, int screenHeight,double sizeBall) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        
        lives=3;
        gameStart=true;      
        
        ballLogic=new BallLogic(30,5);
        ball=new Circle(250, 250, sizeBall);
        
        platform = new Rectangle(250,500-35,75,35);
        platformLogic=new PlatformLogic(10,platform.getX());
 
        bricksLogic=new BricksLogic();
        bricks=new Bricks(bricksLogic.getBricksLayout());
        
        root=new Group();
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public Group getRoot() {
        return root;
    }
    
    public void gameRun(){
        root.getChildren().addAll(ball,platform);
        for(int i=0;i<4;i++){
            for(int j=0;j<10;j++){
                 root.getChildren().add(bricks.getBricks()[i][j]);
            }
        }
        
            new AnimationTimer(){
                public void handle(long currentNanoTime) {
                   if(gameStart){
                        updateBallPostion();
                        if(lives==0) gameStart=false; 
                        positionToRemove=bricksLogic.removeBrick(ball.getCenterX(), ball.getCenterY());
                        if(positionToRemove[0]>=0)
                           bricks.deactivateBrick(positionToRemove[0], positionToRemove[1]);
                        if(positionToRemove[0]==-2) gameStart=false;
                        
                   }
                }
            }.start();   
            platform.onMousePressedProperty().set(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                   if(gameStart){
                        platformLogic.changePreparation(platform.getLayoutX(), event.getSceneX());
                   }
                }
            });


            //Event Listener for MouseDragged
            platform.onMouseDraggedProperty().set(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(gameStart){
                        double x=platformLogic.changePosition(platform.getLayoutX(), event.getSceneX(), screenWidth);
                        platform.setLayoutX(x);
                        event.consume();
                    }
                }
            });
        
    }
   
    private void checkCollisions(){
        //Collision with wall
        if(ball.getCenterX()+ball.getRadius()>=screenWidth){
            ballLogic.changeDirection(3);
        }
        else if(ball.getCenterX()-ball.getRadius()<=0){
            ballLogic.changeDirection(4);
        }
        else if(ball.getCenterY()-ball.getRadius()<=0){
            ballLogic.changeDirection(1);
        }
        else if(ball.getCenterY()+ball.getRadius()>=screenHeight){
            lives=lives-1;
            ballLogic.changeDirection(2);
            ball.setCenterX(250);
            ball.setCenterY(250);
        }
        
        //Collision with Platform
        if(((ball.getCenterY()+ball.getRadius())>=platform.getY()&&(ball.getCenterY()+ball.getRadius())<=(platform.getY()+platform.getHeight()))){
            if((ball.getCenterX()>=platform.getX()+platform.getLayoutX())&&(ball.getCenterX()<=(platform.getX()+platform.getLayoutX()+platform.getWidth()))){
            ballLogic.changeDirection(2);
            }
        }
        
        
        System.out.println(platform.getX()+platform.getLayoutX());
    }
    public void checkBrickCollision(){
        //collision with brick
        for(int i=3;i>=0;i--){
            for(int j=9;j>=0;j--){
                if(bricksLogic.getBricksLayout()[i][j]!=2){
                    if(ball.getCenterX()>=bricks.getBricks()[i][j].getX()&&ball.getCenterX()<=bricks.getBricks()[i][j].getX()+10
                       &&
                       ball.getCenterY()<=bricks.getBricks()[i][j].getY()+bricks.getBricks()[i][j].getHeight()&&ball.getCenterY()>=bricks.getBricks()[i][j].getY()){
                        
                        ballLogic.changeDirection(3);
                        bricksLogic.getBricksLayout()[i][j]=2;
                    }
                    else if(ball.getCenterX()<=bricks.getBricks()[i][j].getX()+bricks.getBricks()[i][j].getWidth()&&ball.getCenterX()>=bricks.getBricks()[i][j].getX()+bricks.getBricks()[i][j].getWidth()-10
                            &&
                            ball.getCenterY()<=bricks.getBricks()[i][j].getY()+bricks.getBricks()[i][j].getHeight()&&ball.getCenterY()>=bricks.getBricks()[i][j].getY()){
                        
                        ballLogic.changeDirection(4);
                        bricksLogic.getBricksLayout()[i][j]=2;
                    }
                    else if(ball.getCenterY()<=bricks.getBricks()[i][j].getY()+bricks.getBricks()[i][j].getHeight()&&ball.getCenterY()>=bricks.getBricks()[i][j].getY()+bricks.getBricks()[i][j].getHeight()-10
                            &&
                            ball.getCenterX()<=bricks.getBricks()[i][j].getX()+bricks.getBricks()[i][j].getWidth()&&ball.getCenterX()>=bricks.getBricks()[i][j].getX()){
                        ballLogic.changeDirection(1);
                        bricksLogic.getBricksLayout()[i][j]=2;
                    }
                    else if(ball.getCenterY()>=bricks.getBricks()[i][j].getY()&&ball.getCenterY()<=bricks.getBricks()[i][j].getY()+10
                            &&
                             ball.getCenterX()<=bricks.getBricks()[i][j].getX()+bricks.getBricks()[i][j].getWidth()&&ball.getCenterX()>=bricks.getBricks()[i][j].getX()){
                        ballLogic.changeDirection(2);
                        bricksLogic.getBricksLayout()[i][j]=2;
                    }
                    
                }
            }
        }
    }
    private void updateBallPostion(){
        checkCollisions();
        checkBrickCollision();
        double[] coord=ballLogic.changePosition(ball.getCenterX(),ball.getCenterY());
        double x=coord[0];
        double y=coord[1];
        
        //Fix position with walls
        if(x+ball.getRadius()>=screenWidth) x=screenWidth-ball.getRadius();
        else if(x-ball.getRadius()<=0) x=0+ball.getRadius();
        if(y+ball.getRadius()>=screenHeight) {y=screenHeight-ball.getRadius();}
        else if(y-ball.getRadius()<=0) y=0+ball.getRadius();
        
        //Fix Position with platform
        if(((ball.getCenterY()+ball.getRadius())>=platform.getY()&&(ball.getCenterY()+ball.getRadius())<=(platform.getY()+35))){
            if((ball.getCenterX()>=platform.getX()+platform.getLayoutX())&&(ball.getCenterX()<=(platform.getX()+platform.getLayoutX()+75))){
               y=platform.getY()-ball.getRadius()-1;
            }
        }
        
        ball.setCenterX(x);
        ball.setCenterY(y);
    }
    
    
}
