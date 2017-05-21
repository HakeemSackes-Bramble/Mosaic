package nyc.c4q.hakeemsackes_bramble.nortmosiac.custom_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hakeemsackes-bramble on 4/7/17.
 */

public class PlayerView extends View {
    private Paint paint;
    private float xPosition;
    private float yPosition;
    private int dimension = 40;

    public void setPlayColor(int playColor) {
        this.playColor = playColor;
    }

    private int playColor;

    public void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    public PlayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        playColor = Color.rgb(255, 0, 0);
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    private float angle;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(playColor);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getHeight() / 2, paint);
        canvas.drawRect(0, getHeight() / 2, getWidth() , getHeight() , paint);
        this.setRotation(angle);
        this.setTranslationX(xPosition);
        this.setTranslationY(yPosition);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        invalidate();

    }

}
