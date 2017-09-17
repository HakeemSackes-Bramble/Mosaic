package nyc.c4q.hakeemsackes_bramble.nortmosiac;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

import nyc.c4q.hakeemsackes_bramble.nortmosiac.custom_views.BulletView;
import nyc.c4q.hakeemsackes_bramble.nortmosiac.custom_views.ColorWheelView;
import nyc.c4q.hakeemsackes_bramble.nortmosiac.custom_views.PlayerView;
import nyc.c4q.hakeemsackes_bramble.nortmosiac.model.GameValues;

public class MainActivity extends AppCompatActivity {


    private final float bulletSpeed = 20.0f;
    private float angle;
    private float xPosition;
    private float yPosition;
    private float[] accelValues;
    private View gameBoard;
    private PlayerView player;
    private RelativeLayout display;
    Sensor accelerometer;
    SensorManager sensorManager;
    GoogleApiClient.Builder googleApiClient;
    private float[] outputAccel;
    //customizer page
    private ColorWheelView colorwheel;

    Button finishButton;
    GameValues gameValues = new GameValues();
    private boolean isInGame;
    SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // gameValues.setAccel(event.values);
            accelValues = event.values;
            lowPass();
            xPosition += outputAccel[1];
            yPosition += outputAccel[0];
                if (xPosition > gameBoard.getWidth()) {
                    xPosition = gameBoard.getWidth();
                }
                if (xPosition < 0) {
                    xPosition = 0;
                }
                if (yPosition > gameBoard.getHeight()) {
                    yPosition = gameBoard.getHeight();
                }
                if (yPosition < 0) {

                    yPosition = 0;
                }

            angle = (float) Math.atan2(outputAccel[1], 0 - outputAccel[0]);
            player.setAngle((float) (angle * (180 / Math.PI)));
            player.setxPosition(xPosition);
            player.setyPosition(yPosition);
           // Log.d("TAGger", "onSensorChanged: " + outputAccel);


//            gameBoard.setxPosition(xPosition);
//            gameBoard.setyPosition(yPosition);
//            if(gameBoard.getIsDrawing()){
//            gameBoard.invalidate();
//            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    private final float ALPHA = 0.9f;
    private int typeNumb;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        googleApiClient = new GoogleApiClient.Builder(getApplicationContext());
        setContentView(R.layout.customizer_page);
        finishButton = (Button) findViewById(R.id.customizer_finish_button);
        colorwheel = (ColorWheelView) findViewById(R.id.customizer_colorwheel);
        player = (PlayerView) findViewById(R.id.customizer_player);
        colorwheel.setPlayer(player);
        gameBoard=findViewById(R.id.trial_gameboard);
        setUpAccelerometer();
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ViewGroup view = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
                setContentView(R.layout.activity_main);
                player = new PlayerView(getApplicationContext(),null);
                display = (RelativeLayout) findViewById(R.id.display_layout_main);
                gameBoard = findViewById(R.id.game_board);
                player.setPlayColor(colorwheel.getColorValue());
                isInGame = true;
                createPlayer();

                gameBoard.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        BulletView bullet = new BulletView(getApplicationContext(), null);
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                shootBullet(bullet);
                                break;
                            case MotionEvent.ACTION_MOVE:
                                break;
                            case MotionEvent.ACTION_UP:
                                break;
                        }
                        return false;
                    }
                });
            }
        });


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    private void lowPass() {
        if (outputAccel == null) {
            outputAccel = accelValues;
        }
        for (int i = 0; i < accelValues.length; i++) {
            outputAccel[i] = outputAccel[i] + ALPHA * (accelValues[i] - outputAccel[i]);
        }
    }

    private void setUpAccelerometer(){
        accelValues = new float[]{0, 0, 0};
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        xPosition = 50;
        yPosition = 50;
    }

    private void shootBullet(BulletView bullet){
        bullet.setBulletType(typeNumb);
        bullet.setSize(150);
        typeNumb++;
        typeNumb %= 5;
        bullet.setyPosition(yPosition);
        bullet.setxPosition(xPosition);
        bullet.setColorB(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
        bullet.setRotation((float) (angle * (180 / Math.PI)));
        display.addView(bullet);
        bullet.setxSpeed((float) (bulletSpeed * Math.cos(angle - (Math.PI / 2))));
        bullet.setySpeed((float) (bulletSpeed * Math.sin(angle - (Math.PI / 2))));
        bullet.setIsShot(true);
        bullet.setAlpha(0.6f);
        Log.d("d", "onTouch: hyt" + 9.8 * Math.sin(angle));
    }

    private void createPlayer(){

        Log.d("TAG", "onCreate: " + display + "  "+ player);
        display.addView(player);
    }
}
