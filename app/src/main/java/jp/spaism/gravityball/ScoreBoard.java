package jp.spaism.gravityball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by spaism on 5/23/16.
 */
public class ScoreBoard {

    static Paint mPaint_ScoreBoard;

    private int mScore = 0;

    public ScoreBoard(){

        mPaint_ScoreBoard = new Paint();
        mPaint_ScoreBoard.setColor(Color.WHITE);
        mPaint_ScoreBoard.setTextSize(75);

    }

    public void draw(Canvas canvas){
        canvas.drawText("Score: " + mScore, 50, 100, mPaint_ScoreBoard);
    }

    public void updateScore(int updatedScore){
        mScore = updatedScore;
    }

}
