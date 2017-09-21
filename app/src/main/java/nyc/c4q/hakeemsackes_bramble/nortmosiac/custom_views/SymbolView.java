package nyc.c4q.hakeemsackes_bramble.nortmosiac.custom_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hakeemsackes-bramble on 5/13/17.
 */

public class SymbolView extends View {


    private float sizePX;
    private float angle;
    private Paint paint;
    private float scale;
    private float symbolSize;
    private float xPosition;
    private float yPosition;


    public SymbolView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
        scale = context.getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) sizePX, (int) sizePX);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        symbolSize = getWidth();
        drawSymbol(canvas);
        this.setRotation(angle);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        invalidate();
    }

    void drawSymbol(Canvas canvas) {
        float[] lines = new float[]{
                symbolSize / 8, symbolSize / 8,
                symbolSize / 8, 7 * symbolSize / 8,
                symbolSize / 8, symbolSize / 8,
                3 * symbolSize / 8, symbolSize / 2,
                5 * symbolSize / 8, symbolSize / 8,
                3 * symbolSize / 8, symbolSize / 2,
                5 * symbolSize / 8, symbolSize / 8,
                5 * symbolSize / 8, 7 * symbolSize / 8,
                3 * symbolSize / 8, symbolSize / 8,
                3 * symbolSize / 8, 7 * symbolSize / 8,
                3 * symbolSize / 8, symbolSize / 8,
                5 * symbolSize / 8, symbolSize / 2,
                7 * symbolSize / 8, symbolSize / 8,
                5 * symbolSize / 8, symbolSize / 2,
                7 * symbolSize / 8, symbolSize / 8,
                7 * symbolSize / 8, 7 * symbolSize / 8
        };
        canvas.drawCircle(symbolSize / 4, symbolSize / 7, symbolSize / 8, paint);
        canvas.drawCircle(3 * symbolSize / 4, symbolSize / 7, symbolSize / 8, paint);
        canvas.drawCircle(symbolSize / 4, 6 * symbolSize / 7, symbolSize / 8, paint);
        canvas.drawCircle(3 * symbolSize / 4, 6 * symbolSize / 7, symbolSize / 8, paint);
        canvas.drawCircle(symbolSize / 2, symbolSize / 2, symbolSize / 8, paint);
        canvas.drawLines(lines, paint);
        Rect rect = new Rect(0, 0, (int) symbolSize, (int) symbolSize);
        RectF rectF = new RectF(rect);
        canvas.drawRoundRect(rectF, 40, 40, paint);

    }

    public void setSize(float sizedp) {
        this.sizePX = sizedp * scale;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition;
    }
}
