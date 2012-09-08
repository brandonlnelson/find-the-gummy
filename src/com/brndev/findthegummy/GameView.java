package com.brndev.findthegummy;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.appbrain.AppBrain;


public class GameView extends SurfaceView {
	
	private List<Sprite> sprites = new ArrayList<Sprite>();
	
	private List<Bitmap> bits = new ArrayList<Bitmap>();

    private SurfaceHolder holder;

    private GameLoopThread gameLoopThread;
    
    private float a,b;
    
    private Resources res = getResources();
    
    private int Width = 1, Height = 1;
    
    final BitmapFactory.Options options = new BitmapFactory.Options();
    
    private Bitmap bit;
    
    private long lastClick;
    
    private Boolean bo = false, yo = false;
    
    private int level = 0;
    
    final Paint countPaint = new Paint(), killPaint = new Paint(), 
    			scorePaint = new Paint(), winPaint = new Paint(), 
    			incomingPaint = new Paint(), warningPaint = new Paint(),
    			highScorePaint = new Paint(), diffPaint = new Paint();
    
    final Bitmap orangegummy = BitmapFactory.decodeResource(res, R.drawable.orangegummy),
	    		 bluegummy = BitmapFactory.decodeResource(res, R.drawable.bluegummy),
	    		 yellowgummy = BitmapFactory.decodeResource(res, R.drawable.yellowgummy),
	    		 redgummy = BitmapFactory.decodeResource(res, R.drawable.redgummy),
    			greengummy = BitmapFactory.decodeResource(res, R.drawable.greengummy);
    

    private int timeLeft = 180;
    
    private Boolean won = false, lost = false;
    
    public void createScaleBits() {

		if(Width>1 && Height>1)
		{
	    	if(yo == false)
	    	{
	    		//put levels in array list
		    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.bg_orange,options)));
		    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.bg_blue,options)));
		    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.bg_yellow,options)));
		    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.bg_red,options)));
		    	bits.add(scaleBits(BitmapFactory.decodeResource(res, R.drawable.bg_green,options)));

		    	yo = true;
	    	}
		}
    }
    
    
	    	
	    	public Bitmap scaleBits(Bitmap b) {

	    			bit = b;
	    			
		    		{{ bit = Bitmap.createScaledBitmap(b,Width,Height,true);}};
		    		
		    		return bit;

	    	}

	    	
	    	
	    	public void createSprites() {
	    		sprites.add(createSprite(orangegummy));
	    		sprites.add(createSprite(bluegummy));
	    		sprites.add(createSprite(yellowgummy));
	    		sprites.add(createSprite(redgummy));
	    		sprites.add(createSprite(greengummy));
	    		
	    		bo=true;
	    	}



    //create sprite function takes in resource as argument
    //and returns a new sprite
    public Sprite createSprite(Bitmap bmp) {
    	
        
        return new Sprite(this,bmp);

  }
    

    
    public GameView(Context context, AttributeSet attrs) {
    	

          super(context);

          gameLoopThread = new GameLoopThread(this);

          holder = getHolder();

          holder.addCallback(new SurfaceHolder.Callback() {



                 @Override

                 public void surfaceDestroyed(SurfaceHolder holder) {

                        boolean retry = true;

                        gameLoopThread.setRunning(false);

                        while (retry) {

                               try {

                                     gameLoopThread.join();

                                     retry = false;

                               } catch (InterruptedException e) {

                               }

                        }

                 }



                 @Override

                 public void surfaceCreated(SurfaceHolder holder) {

                        gameLoopThread.setRunning(true);

                        gameLoopThread.start();
                        

                 }
                 



                 @Override

                 public void surfaceChanged(SurfaceHolder holder, int format,

                               int width, int height) {
                	 
	                	 Width = width;
	                	 Height = height;
	                	 
	                	 if(bo==false&&yo==false) {
		                	 createSprites();
		                	 createScaleBits();
	                	 }


	                	 
                 }

          });
          

          
          
    }
    
    

    //onDraw(canvas) method called when gameview is drawn which happens continuously once the game is running
    @Override
    protected void onDraw(Canvas canvas) {
    	
    		win();

    		if(yo==true)
    		{

				        	if(level==0 && lost==false && won==false)
				        	{
				        		canvas.drawBitmap(bits.get(0), 0, 0, null);
				        	}
				        	else if(level==1 && lost==false && won==false)
				        	{
				        		canvas.drawBitmap(bits.get(1), 0, 0,null);
				        	}
				        	else if(level==2 && lost==false && won==false)
				        	{
				        		canvas.drawBitmap(bits.get(2), 0, 0, null);
				        	}
				        	else if(level==3 && lost==false && won==false)
				        	{
				        		canvas.drawBitmap(bits.get(3), 0, 0, null);
				        	}
				        	else if(level==4 && lost==false && won==false) {
				        		canvas.drawBitmap(bits.get(4), 0, 0, null);
				        	}
				        	else if(won==false && lost==true && timeLeft<=0)
				        	{
				        		canvas.drawColor(Color.BLACK);
				        	}
				        	else{
				        		canvas.drawColor(Color.BLACK);
				        	}
    		}
	    		else {
	    				canvas.drawColor(Color.BLACK);
	    			}

    		/*

        	//if 1000ms (1 second) has passed subtract 1 from timeLeft
            if(System.currentTimeMillis() - 1000 > lg)
            {
            	lg = System.currentTimeMillis();
            	
            	if(bo==true) {
	            	if(timeLeft>0) {
	            		timeLeft -= 1;
	            			            		
	            	}
            	}
            }


    		int mins = timeLeft/60;
    		int secs = timeLeft%60;
        	
    		if(secs!=0 && secs > 9) {          
    			//set x,y parameters for text as g,h respectively
                g = (float) (.01*Width);
                h = (float) (.045*Height);
                
                y = (float) (.4*Width);
                z = (float) (.01*Height);
                
                paint.setColor(Color.BLACK);
                
                canvas.drawRect(g, h, y, z, paint );
                
            	y = (float) (.025*Width);
            	z = (float) (.035*Height);
                
    			canvas.drawText("Time: --  " + mins + ":" + secs , y, z, countPaint);  
    		}
    		else if(secs!=0 && secs<10) {
    			//set x,y parameters for text as g,h respectively
                g = (float) (.01*Width);
                h = (float) (.045*Height);
                
                y = (float) (.4*Width);
                z = (float) (.01*Height);
                
                paint.setColor(Color.BLACK);
                
                canvas.drawRect(g, h, y, z, paint );
                
            	y = (float) (.025*Width);
            	z = (float) (.035*Height);
                
    			canvas.drawText("Time: --  " + mins + ":" + "0" + secs , y, z, countPaint);
    		}
    		else {
    			//set x,y parameters for text as g,h respectively
                g = (float) (.01*Width);
                h = (float) (.045*Height);
                
                y = (float) (.4*Width);
                z = (float) (.01*Height);
                
                paint.setColor(Color.BLACK);
                
                canvas.drawRect(g, h, y, z, paint );
                
            	y = (float) (.025*Width);
            	z = (float) (.035*Height);
                
    			canvas.drawText("Time: --  " + mins + ":" + "00", y, z, countPaint);
    		}
			
        
        //if time runs out you lose
        if(timeLeft <= 0 && won == false && lost == false)
        {
        	
        	for(int count = sprites.size();count>0;count--)
        	{
        		sprites.remove(count-1);
        	}
        	
        	timeLeft = 0;
        	
        	lost = true;
        }
        
*/
  
//if player hasn't won draw timer and pop counter
if(won == false && lost == false)
{
	    
        if(level==0) {
        	sprites.get(0).onDraw(canvas);
        }
        else if(level==1) {
        	sprites.get(1).onDraw(canvas);
        }
        else if(level==2) {
        	sprites.get(2).onDraw(canvas);
        }
        else if(level==3) {
        	sprites.get(3).onDraw(canvas);
        }
        else if(level==4) {
        	sprites.get(4).onDraw(canvas);
        }
        else {
        	
        }
}

//ends onDraw(canvas)
}

    
    
    
    

    public void win() {

    	
	    	
    	} //end if !won

    
    public Sprite sprite;
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	    
    	a = event.getX();
    	b = event.getY();
    	
    	
    	if(bo==true) 
    	{ //if wrapper makes sure level isn't still creating
    		
    	
    	//checks to see if it has been at least 300ms since that last touch event fired
        if (System.currentTimeMillis() - lastClick > 300) {

            lastClick = System.currentTimeMillis();

            synchronized (getHolder()) {

            	//loop checks for sprites hit by touch event and removes
            	//them from the array and draws a tempSprite to the screen
            	//in it's place and plays mediaplayer sound
               if(level==0) {
                    sprite = sprites.get(0);
               } 
               else if(level==1) {
            	   sprite = sprites.get(1); 
               }
               else if(level==2) {
            	   sprite = sprites.get(2);
               }
               else if(level==3) {
            	   sprite = sprites.get(3);
               }
               else if(level==4) {
            	   sprite = sprites.get(4);
               }

                    if (sprite.isCollition(a, b)) {
                    
	                   if(level<=3) {
	                	   level+=1;
	                    }
	                    else {
	                    		level=0;
	                    		
	                    		AppBrain.getAds().showInterstitial((MainActivity) getContext());
	
	                    		((MainActivity) getContext()).finish();                   		
                    		}
                    }
		            else{
		                    	
		                }

                } //end getHolder()

    	
        	win();

        	
        	} //end 300ms check
        
        
    	} //end if loaded wrapper
    	
    	a = 0;
    	b = 0;
    	
    	return true;
    } //end onTouchEvent()
    
    
    
    
  

    
    
    public void setTimeLeft(int time) {
    	timeLeft = time;
    }
    
    
    public int fetchHeight()
    {
    	return Height;
    }
    

    
    public int fetchWidth()
    {
    	return Width;
    }
    
    
    

} //end class GameView





