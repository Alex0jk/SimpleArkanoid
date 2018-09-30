/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelLogic;

import javafx.scene.shape.Circle;

/**
 *
 * @author alexm
 */
public class PlatformLogic {
    //Atributes
    private int speed;
    private double mousex,mousey;
    private double x,xcoord;
    //Methods     

    public PlatformLogic(int speed,double xplatform) {
        this.speed = speed;
        x=0;
        xcoord=xplatform;
        mousex=0;
    }

    public double getXcoord() {
        return xcoord;
    }
    
    public void changePreparation(double xLayout,double xDestination){
        mousex = xDestination;
        x = xLayout;
    }
    public double changePosition(double xLayout,double xDestination,double limit){
        double offsetX = xDestination - mousex;
        x += offsetX;
        double scaledX = x;
        mousex = xDestination;
        //xcoord=xcoord+scaledX;
        
        if((250+scaledX)<=limit&&(250+scaledX)>=-20)
            return scaledX;
        else
            return xLayout;
    };
}
