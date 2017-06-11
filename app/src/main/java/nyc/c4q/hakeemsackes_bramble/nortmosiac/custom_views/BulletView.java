package nyc.c4q.hakeemsackes_bramble.nortmosiac.custom_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hakeemsackes-bramble on 4/28/17.
 */

public class BulletView extends View {
    private final float scale;
    private Paint paint;
    private float xPosition;
    private float yPosition;
    private float xSpeed;
    private float ySpeed;
    final int CIRCLE = 1;
    final int SQUARE = 2;
    final int HEART = 3;
    final int FLOWER = 4;
    private Long initTime = 0L;
    Boolean isShot;
    private final int BUTTERFLY = 0;
    private float bulletSize = 20.0f;
    private float sizePX;


    public void setBulletType(int bulletType) {
        this.bulletType = bulletType;
    }

    private int bulletType;

    public void setColorB(int colorB) {
        this.colorB = colorB;
    }

    private int colorB;

    public BulletView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        scale = context.getResources().getDisplayMetrics().density;
        bulletSize = 10.0f * scale;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (initTime <= 16) {
            canvas.drawCircle(getPivotX(), getPivotY(), getHeight() / 16, paint);

            if (isShot) {
                xPosition += xSpeed;
                yPosition += ySpeed;
            } else {

            }
            this.setX(xPosition);
            this.setY(yPosition);
            initTime++;
            invalidate();
        } else {
            this.setMeasuredDimension((int) sizePX, (int) sizePX);
            drawBullet(bulletType, canvas);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension((int) sizePX, (int) sizePX);

    }

    private void drawBullet(int type, Canvas canvas) {
        paint.setColor(colorB);
        if (type == CIRCLE) {
            canvas.drawCircle(getPivotX(), getPivotY(), getHeight() / 2, paint);
        } else if (type == SQUARE) {
            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        } else if (type == HEART) {
            canvas.rotate(45, getPivotX(), (float) (getPivotY() - Math.sqrt(2) * getWidth() / 4));
            canvas.translate(0, (float) -Math.sqrt(2) * getWidth() / 4);
            canvas.drawCircle(3 * getHeight() / 4, getPivotY(), getHeight() / 4, paint);
            canvas.drawCircle(getPivotX(), 3 * getHeight() / 4, getHeight() / 4, paint);
            canvas.drawRect(getHeight() / 2, getPivotY(), getWidth(), getHeight(), paint);

        } else if (type == FLOWER) {
            canvas.drawCircle(getWidth() / 3, getHeight() / 3, getHeight() / 4, paint);
            canvas.drawCircle(getWidth() / 3, getHeight() * 2 / 3, getHeight() / 4, paint);
            canvas.drawCircle(getWidth() * 2 / 3, getHeight() / 3, getHeight() / 4, paint);
            canvas.drawCircle(getWidth() * 2 / 3, getHeight() * 2 / 3, getHeight() / 4, paint);
        } else if (type == BUTTERFLY) {
            canvas.drawCircle(getWidth() / 3, getHeight() / 3, getHeight() / 3, paint);
            canvas.drawCircle(getWidth() / 3, getHeight() * 2 / 3, getHeight() / 4, paint);
            canvas.drawCircle(getWidth() * 2 / 3, getHeight() / 3, getHeight() / 3, paint);
            canvas.drawCircle(getWidth() * 2 / 3, getHeight() * 2 / 3, getHeight() / 4, paint);
        }
    }

    public void setxPosition(float xPosition) {
        this.xPosition = xPosition - (80*scale);
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition- (80*scale);
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed * scale;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed * scale;
    }

    public void setIsShot(boolean isShot) {
        this.isShot = isShot;
    }

    public void setSize(float sizedp) {
        this.sizePX = sizedp * scale;
    }
}
