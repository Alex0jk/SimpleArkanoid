/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelLogic;

/**
 *
 * @author alexm
 */
public class BricksLogic {
    int [][] bricksLayout;
    //every brick size 50 width and 30 height
    //there will be 5 rows and 10 columns with a total size of 500x150

    
    public BricksLogic() {
        bricksLayout=new int[][]{
        {1,1,1,1,1,1,1,1,1,1},
        {1,1,1,1,1,2,1,1,1,1},
        {1,1,1,1,1,2,1,1,2,1},
        {1,2,2,2,1,1,1,1,2,1},
        };
    }

    public int[][] getBricksLayout() {
        return bricksLayout;
    }
    
    public int[] removeBrick(double xLayout,double yLayout){
        int count=0;
        for(int i=3;i>=0;i--){
            for(int j=9;j>=0;j--){
                if(bricksLayout[i][j]==1){
                    if(xLayout>=j*50&&xLayout<=j*50+50&&yLayout<=i*30+30&&yLayout>=i*30){
                        bricksLayout[i][j]=0;
                        return new int[]{i,j};
                    }
                }
                else if(bricksLayout[i][j]==2){
                    count++;
                }
            }
        }
        if(count==10*4) return new int[]{-2,-2};
        return new int[]{-1,-1};
    }
    
}
