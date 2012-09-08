package com.brndev.findthegummy;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Sprite {

    private int x = 0;

    private int y = 0;

    private Bitmap bmp;

    private int width, height;
    
    private	Random rnd = new Random();
        
    
    public Sprite(GameView gameView, Bitmap bmp) {

          this.width = bmp.getWidth();

          this.height = bmp.getHeight();

          this.bmp = bmp;
          
          x = gameView.fetchWidth() - 150;
          y = gameView.fetchHeight() - 257;

          
          setX(rnd.nextInt(x));
          setY(rnd.nextInt(y));
          
    }



    public void onDraw(Canvas canvas) {

          canvas.drawBitmap(bmp,x,y,null);
    }
    
    
    public void setX(int X)
    {
    	x = X;
    }
    
    public void setY(int Y)
    {
    	y = Y;
    }
    
    public int getX()
    {    	
    	return x;
    }
    
    public int getY()
    {
    	return y;
    }
    
    public boolean isCollition(float x2, float y2) {

    	
        return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
    	
    }
    
    
}
