package jp.spaism.gravityball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Random;

/**
 * Created by spaism on 5/19/16.
 */
public class Circle {

    private float mPoint_X;
    private float mPoint_Y;
    private Paint mPaint;
    private float mRadius;

    private double mTime = 0.0;
    static float GRAVITY = 9.81f;
    static float TIMERATIO = 0.25f;

    public static final int CIRCLE_INITIAL_SIZE = 50;

    private float mTargetCanvasHeight;
    private float mTargetCanvasWidth;

    private GravityBallView mBallView;

    public Circle(float init_X, float init_Y){

        mPoint_X = init_X;
        mPoint_Y = init_Y;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);

        Random rnd = new Random();
        mRadius = rnd.nextInt(CIRCLE_INITIAL_SIZE) + 30;
    }

    public Circle(float init_X, float init_Y, int radius){
        this(init_X, init_Y);

        Random rnd = new Random();
        mRadius = rnd.nextInt(radius - 50) + 50;
    }

    public void draw(Canvas canvas){
        canvas.drawCircle(mPoint_X, mPoint_Y, mRadius, mPaint);
    }

    public void move(float dx, float dy){
        mPoint_X += dx;
        mPoint_Y += dy;
    }

    public void move(){
        mTime += TIMERATIO;
        mPoint_Y = ((float)(mTime * mTime) * GRAVITY) / 2;
    }

    public boolean isInScreen(){

        return !((mPoint_X - mRadius > mTargetCanvasWidth) || (mPoint_Y - mRadius > mTargetCanvasHeight) || (mPoint_X + mRadius < 0) || (mPoint_Y + mRadius < 0));

    }

    public void setListener(View context){
        mTargetCanvasHeight = ((GravityBallView)context).getHeight();
        mTargetCanvasWidth = ((GravityBallView)context).getWidth();
    }

    public float getX(){
        return mPoint_X;
    }

    public float getY(){
        return mPoint_Y;
    }
}
