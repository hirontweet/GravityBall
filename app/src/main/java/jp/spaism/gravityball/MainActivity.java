package jp.spaism.gravityball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    GravityBallView ballView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ballView = new GravityBallView(this);
        setContentView(ballView);
    }

    @Override
    protected void onPause() {
        super.onPause();

        ballView.stopThread();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //ballView.startThread();
    }
}
