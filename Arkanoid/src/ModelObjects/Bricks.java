/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelObjects;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author alexm
 */
public class Bricks {
    Rectangle [][] bricks;

    public Bricks(int [][] bricksLayout) {
        int randNum =0;
        Random rand= new Random();
        bricks=new Rectangle[4][10];
        
        for(int i=0;i<4;i++){
            for(int j=0;j<10;j++){
                randNum=rand.nextInt(4)+1;
                bricks[i][j]=new Rectangle(j*50, i*30,50, 30);
                switch(randNum){
                    case 1:
                        bricks[i][j].setFill(Color.GREEN);
                    break;
                    case 2:
                        bricks[i][j].setFill(Color.BLUE);
                    break;
                    case 3:
                        bricks[i][j].setFill(Color.RED);
                    break;
                    case 4:
                        bricks[i][j].setFill(Color.YELLOW);
                    break;
                }
                
                if(bricksLayout[i][j]==2) bricks[i][j].setVisible(false);
            }
        }
    }

    public Rectangle[][] getBricks() {
        return bricks;
    }
    
    public void deactivateBrick(int row,int column){
        bricks[row][column].setVisible(false);
    }
}
