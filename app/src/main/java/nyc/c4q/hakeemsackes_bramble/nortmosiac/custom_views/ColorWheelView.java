package nyc.c4q.hakeemsackes_bramble.nortmosiac.custom_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hakeemsackes-bramble on 5/14/17.
 */
public class ColorWheelView extends View {

    final float radius;
    float xValue;
    float yValue;
    /* my transparency value*/
    float distFromCenter;
    float centerPointX;
    float centerPointY;
    Paint paint;
    Color color;
    private final float scale = this.getResources().getDisplayMetrics().density;
    private int colorValue;
    private PlayerView player;


    public ColorWheelView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        radius = 100 * scale;
        color = new Color();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        displayColor();

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                xValue = event.getX();
                yValue = event.getY();
                distFromCenter = distanceFromPoint(centerPointX, centerPointY);
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        displayColor();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        displayColor();
                        break;
                    case MotionEvent.ACTION_UP:
                        setPlayerColor(colorValue);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });


    }

    private void displayColor(){
        if (distFromCenter < radius) {
            float transparency = 255;//distFromCenter / radius * 255;
            float redValue = distanceFromPoint(centerPointX, centerPointY - radius) / (radius*2) * 255;
            float greenValue = (distanceFromPoint(centerPointX + (radius * Math.sqrt(3) / 2), centerPointY - (radius / 2)) / (radius*2)) * 255;
            float blueValue = (distanceFromPoint(centerPointX - (radius * Math.sqrt(3) / 2), centerPointY - (radius / 2)) / (radius*2)) * 255;

            colorValue = color.argb((int) transparency, (int) redValue % 256, (int) greenValue % 256, (int) blueValue % 256);
            paint.setColor(colorValue);
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerPointX = getPivotX();
        centerPointY = getPivotY();
        canvas.drawCircle(centerPointX, centerPointY, radius, paint);
    }

    public float distanceFromPoint(double x, double y) {
        return (float) Math.sqrt(Math.pow(x - xValue, 2) + Math.pow(y - yValue, 2));
    }
    public int getColorValue() {
        return colorValue;
    }

    public void setPlayer(PlayerView player) {
        this.player = player;
    }

    public void setPlayerColor(int playerColor) {
        player.setPlayColor(playerColor);
    }
}