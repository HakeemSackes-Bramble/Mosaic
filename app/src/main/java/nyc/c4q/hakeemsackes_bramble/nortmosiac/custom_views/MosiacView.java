package nyc.c4q.hakeemsackes_bramble.nortmosiac.custom_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by hakeemsackes-bramble on 4/15/17.
 */

public class MosiacView extends View {

    private Path drawPath;
    private Canvas drawCanvas;
    ArrayList<Path> pathArrays;
    private Paint pathPaint;
    private float touchX;
    private float touchY;
    private boolean isDrawing;
    private float xPosition;
    private float yPosition;

    public void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    public MosiacView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        pathArrays = new ArrayList<>();
        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(4);
        pathPaint.setColor(Color.BLACK);
        drawPath = new Path();
        drawCanvas = new Canvas();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw code back here

    }
    public boolean getIsDrawing() {
        return isDrawing;
    }
}


//        if (isDrawing) {
//            pathArrays.get(pathArrays.size() - 1).moveTo(touchX, touchY);
//            pathArrays.get(pathArrays.size() - 1).lineTo(xPosition + 10, yPosition + 10);
//            touchX = xPosition + 10;
//            touchY = yPosition + 10;
//        }
//        for (Path path : pathArrays) {
//            canvas.drawPath(path, pathPaint);
//        }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        touchX = xPosition + 10;
//        touchY = yPosition + 10;
//        switch (event.getAction()) {
//
//            case MotionEvent.ACTION_DOWN:
//                isDrawing = true;
//                break;
//            case MotionEvent.ACTION_MOVE:
//
//                break;
//            case MotionEvent.ACTION_UP:
//                drawCanvas.drawPath(drawPath, pathPaint);
//                isDrawing = false;
//                break;
//            default:
//                return false;
//        }
//        return true;
//    }
//

