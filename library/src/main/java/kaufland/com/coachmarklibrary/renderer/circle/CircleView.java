package kaufland.com.coachmarklibrary.renderer.circle;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import org.androidannotations.annotations.EView;
import org.androidannotations.annotations.EViewGroup;

@EView
public class CircleView extends View {

    private float mCenterX;

    private float mCenterY;

    private float mRadius;

    private float mTransparentRadius;

    private int defaultColor = Color.TRANSPARENT;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(defaultColor);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawCircle(mCenterX, mCenterY, mRadius, paint);
        if(mTransparentRadius != 0){
            Paint paintTransparent = new Paint();
            paintTransparent.setColor(Color.YELLOW);
            paintTransparent.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
            canvas.drawCircle(mCenterX, mCenterY, mTransparentRadius, paintTransparent);
        }
        super.dispatchDraw(canvas);
    }

    public void setCenterX(float mCenterX) {
        this.mCenterX = mCenterX;
    }

    public void setCenterY(float mCenterY) {
        this.mCenterY = mCenterY;
    }

    public void setRadius(float radius) {
        this.mRadius = radius;
    }

    public void setColor(int color){
        defaultColor = color;
    }

    public void setTransparentRadius(float radius){
        mTransparentRadius = radius;
    }
}
