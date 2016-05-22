package jp.spaism.gravityball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by spaism on 5/19/16.
 */
public class AimField {

    private float mPoint_X;
    private float mPoint_Y;
    private float mRadius_Outer;

    private float mCanvasHeight;
    private float mCanvasWidth;

    private Paint mPaint_Outer;

    private float mStrokeWidth = 20;

    public AimField(float initX, float initY){

        mPoint_X = initX;
        mPoint_Y = initY;

        mPaint_Outer = new Paint();
        mPaint_Outer.setColor(Color.RED);
        mPaint_Outer.setAntiAlias(true);
        mPaint_Outer.setStyle(Paint.Style.STROKE);
        mPaint_Outer.setStrokeWidth(mStrokeWidth);

    }

    public AimField(float initX, float initY, float radius){
        this(initX, initY);
        mRadius_Outer = radius;
    }

    public void draw(Canvas canvas){

        /*
         *
         * Couldn't use Canvas.drawArc() because the minSdk is 16, it has to be over 21
         *
         * This problem is solved by using Paint.setStyle(Paint.Style.STROKE) and Paint.setStrokeWidth()
         *
          * */
        canvas.drawCircle(mPoint_X, mPoint_Y, mRadius_Outer, mPaint_Outer);
    }

    public void setListener(View view){
        mCanvasHeight = ((GravityBallView)view).getHeight();
        mCanvasWidth = ((GravityBallView)view).getWidth();

        mRadius_Outer = ((GravityBallView)view).getWidth() / 8;
    }

    public float getRadius(){
        return mRadius_Outer;
    }

    public float getX(){
        return mPoint_X;
    }

    public float getY(){
        return mPoint_Y;
    }
}
