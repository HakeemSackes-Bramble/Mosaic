package nyc.c4q.hakeemsackes_bramble.nortmosiac.model;

import com.google.android.gms.games.Player;

import java.util.ArrayList;

/**
 * Created by hakeemsackes-bramble on 4/24/17.
 */

public class GameValues {


    public float[] getOutputAccel() {
        return outputAccel;
    }

    private float[] outputAccel;
    private final float ALPHA = 0.9f;
    public GameValues() {
    }

    public void setAccel(float[] Accel) {
       lowPass(Accel);
    }

    ArrayList<Player> players = new ArrayList<>();

    private void lowPass( float[] accelValues) {
        if (outputAccel == null) {
            outputAccel = accelValues;
        }
        for (int i = 0; i < accelValues.length; i++) {
            outputAccel[i] = outputAccel[i] + ALPHA * (accelValues[i] - outputAccel[i]);
        }
    }



}
