package jp.spaism.gravityball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
