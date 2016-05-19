package jp.spaism.gravityball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by spaism on 5/19/16.
 */
public class GravityBallView extends SurfaceView implements SurfaceHolder.Callback {

    private DrawThread drawThread;
    private Paint paint_Background = new Paint();

    private Circle circle100, circle200, circle300;
    private AimField aimField;

    private float canvasWidth, canvasHeight;


    public GravityBallView(Context context) {
        super(context);

        getHolder().addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.v(getClass().toString(), "X: " + touchX + ", Y: " + touchY);

            shootDown();
        }

        return true;
    }

    public void drawView(Canvas canvas){

        if(circle100 == null){
            circle100 = new Circle(getCanvasWidth() / 2, 0);
            circle100.setListener(this);
        }

        if(circle200 == null){
            circle200 = new Circle(getCanvasWidth() / 2, 300);
            circle200.setListener(this);
        }

        if(circle300 == null){
            circle300 = new Circle(getCanvasWidth() / 2, 600);
            circle300.setListener(this);
        }

        if(aimField == null){
            aimField = new AimField(getCanvasWidth() / 2, getCanvasHeight() / 2);
            aimField.setListener(this);
        }

        circle100.move(0, 10);
        circle200.move(0, 10);
        circle300.move(0, 10);

        canvas.drawRect(0, 0, getCanvasWidth(), getCanvasHeight(), paint_Background);
        circle100.draw(canvas);
        circle200.draw(canvas);
        circle300.draw(canvas);

        aimField.draw(canvas);

        determineInScreen();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        paint_Background.setColor(Color.BLACK);

        startThread();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopThread();
    }

    public float getCanvasWidth(){
        return canvasWidth;
    }

    public float getCanvasHeight(){
        return canvasHeight;
    }

    public void startThread(){
        stopThread();

        drawThread = new DrawThread();
        drawThread.start();
    }

    public void stopThread(){
        if(drawThread != null){
            drawThread.isFinished = true;
            drawThread = null;
        }
    }

    public void determineInScreen(){
        if(!circle100.isInScreen()){
            circle100 = null;
        }
        if(!circle200.isInScreen()){
            circle200 = null;
        }

        if(!circle300.isInScreen()){
            circle300 = null;
        }
    }

    public void shootDown(){
        float aimRadius = aimField.getRadius();
        float aimX = aimField.getX();
        float aimY = aimField.getY();

        if (circle100.isInScreen()) {
            // Try catching the exception of NullPointerException because the ball might be out of the screen.
            try {
                if (aimX - aimRadius <= circle100.getX() &&
                        circle100.getX() <= aimX + aimRadius &&
                            aimY - aimRadius <= circle100.getY() &&
                                circle100.getY() <= aimY + aimRadius) {
                    circle100 = null;
                }
            } catch (NullPointerException e) {

            }
        }

        if (circle200.isInScreen()) {
            try {
                if (aimX - aimRadius <= circle200.getX() &&
                        circle200.getX() <= aimX + aimRadius &&
                        aimY - aimRadius <= circle200.getY() &&
                        circle200.getY() <= aimY + aimRadius) {
                    circle200 = null;
                }
            } catch (NullPointerException e) {

            }
        }

        if (circle300.isInScreen()) {
            try {
                if (aimX - aimRadius <= circle300.getX() &&
                        circle300.getX() <= aimX + aimRadius &&
                        aimY - aimRadius <= circle300.getY() &&
                        circle300.getY() <= aimY + aimRadius) {
                    circle300 = null;
                }
            } catch (NullPointerException e) {

            }
        }
    }



    private class DrawThread extends Thread{
        private boolean isFinished = false;

        @Override
        public void run() {

            while(!isFinished){
                Canvas canvas = getHolder().lockCanvas();

                canvasWidth = canvas.getWidth();
                canvasHeight = canvas.getHeight();

                if(canvas != null){
                    drawView(canvas);
                    getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
