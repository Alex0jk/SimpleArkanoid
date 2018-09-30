/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelLogic;

import java.util.Random;

/**
 *
 * @author alexm
 */
public class BallLogic {
    private double angle=70;
    private int speed=5;
    //Methods

    public BallLogic(int angle,int speed) {
        //ball=new Circle(xPosition,yPosition,radiusBall);
        this.angle=angle;
        this.speed=speed;
    }
    
    public void changeDirection(int direction){
    //direction refers where the change of direction was made 1:up,2:down,3:rigth,4:left
        Random rand=new Random();
        int number=rand.nextInt(90-75+1)+75;
        switch(direction){
            case 2:
                if(angle<=90) angle = 360 - (90-angle);
                else angle=angle+number;
            break;
            case 1:
                if(angle<=270) angle = angle-number;
                else angle=90-(360-angle);
            break;
            case 4:
                if(angle<=270) angle = angle+number;
                else angle=90-(360-angle);
            break;
            case 3:
                if(angle<=180) angle = angle + number;
                else angle=angle-number;
            break;
        }
        //speed=speed+1;
    };
    public double[] changePosition(double xCenter,double yCenter){
        double x=xCenter+Math.cos((angle*Math.PI)/180)*speed;
        double y=yCenter+Math.sin((angle*Math.PI)/180)*speed;       
        return new double[]{x,y};
    };
}
