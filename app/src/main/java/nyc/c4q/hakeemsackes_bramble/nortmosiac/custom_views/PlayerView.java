package nyc.c4q.hakeemsackes_bramble.nortmosiac.custom_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hakeemsackes-bramble on 4/7/17.
 */

public class PlayerView extends View {
    private final float scale;
    private Paint paint;
    private float xPosition;
    private float yPosition;
    private int dimension = 40;
    private int playColor;


    public PlayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        scale = context.getResources().getDisplayMetrics().density;
        dimension *= scale;
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
        canvas.drawRect(0, getHeight() / 2, getWidth(), getHeight(), paint);
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

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setPlayColor(int playerColor) {
        this.playColor = playerColor;
    }

    public void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition;
    }

}
