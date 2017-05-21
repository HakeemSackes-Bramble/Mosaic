package nyc.c4q.hakeemsackes_bramble.nortmosiac;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Random;

import nyc.c4q.hakeemsackes_bramble.nortmosiac.custom_views.BulletView;
import nyc.c4q.hakeemsackes_bramble.nortmosiac.custom_views.SymbolView;

/**
 * Created by hakeemsackes-bramble on 5/12/17.
 */

public class LoadScreenActivity extends AppCompatActivity {

    private Sensor accelerometer;
    private SensorManager sensorManager;
    private float[] accelValues;
    private float angleM;
    private SymbolView player;
    private RelativeLayout loadLayout;
    private final float ALPHA = 0.9f;
    private float[] outputAccel;
    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            accelValues = event.values;
            lowPass();
            angleM = (float) Math.atan2(outputAccel[1], 0 - outputAccel[0]);
            //player.setAngle((float) (angleM * (180 / Math.PI)));

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    private float xPosition;
    private float yPosition;
    private View decorView;
    private int typeNumb = 0;
    Random rand = new Random();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_screen_layout);
        loadLayout = (RelativeLayout) findViewById(R.id.load_layout);
        player = new SymbolView(getApplicationContext(), null);
        xPosition = loadLayout.getWidth() / 2;
        yPosition = loadLayout.getHeight() / 2;
        Log.d("TAG", "onCreate: " + xPosition + " " + yPosition);
        player.setSize(120);
        player.setxPosition(xPosition);
        player.setyPosition(yPosition);
        loadLayout.addView(player);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        decorView = getWindow().getDecorView();
        loadLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                BulletView bullet = new BulletView(getApplicationContext(), null);
                xPosition = loadLayout.getWidth() / 2;
                yPosition = loadLayout.getHeight() / 2;
                float bulletSpeed;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        bulletSpeed = 2.0f;
                        bullet.setBulletType(typeNumb);
                        bullet.setSize(150);
                        typeNumb++;
                        typeNumb %= 5;
                        bullet.setRotation((float) (angleM * (180 / Math.PI)));
                        bullet.setyPosition(yPosition  - 150);
                        bullet.setxPosition(xPosition  - bullet.getHeight()/2);
                        loadLayout.addView(bullet);
                        bullet.setColorB(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
                        bullet.setxSpeed((float) (bulletSpeed * Math.cos(angleM - (Math.PI / 2))));
                        bullet.setySpeed((float) (bulletSpeed * Math.sin(angleM - (Math.PI / 2))));
                        bullet.setIsShot(true);
                        bullet.setAlpha(0.6f);
                        Log.d("d", "onTouch: hyt" + 9.8 * Math.sin(angleM));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
        player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(
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
}
