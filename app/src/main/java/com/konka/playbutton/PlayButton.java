package com.konka.playbutton;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PlayButton extends Button {
    Path path = new Path();
    Paint paint = new Paint();
    RectF rectF;
    float lengthValue = 0.05f;
    float rotationValue = 0f;
    public PlayButton(Context context) {
        super(context);
        init(null, 0);
    }

    public PlayButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public PlayButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        rectF = new RectF(100, 100, 600 - 100, 600 - 100);
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(30);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);

        ObjectAnimator lengthAnimator = ObjectAnimator.ofFloat(this, "lengthValue", 60f);
        lengthAnimator.setDuration(4000);
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(this, "rotationValue", 360f);
        rotationAnimator.setDuration(4000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(lengthAnimator).before(rotationAnimator);
        animatorSet.start();
    }

    private void setLengthValue(float lengthValue){
        this.lengthValue = lengthValue;
        invalidate();
    }

    private void setRotationValue(float rotationValue){
        this.rotationValue = rotationValue;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int saveCount = canvas.save();
        canvas.rotate(rotationValue, 300f, 300f);
        path.addArc(rectF, -30f, lengthValue);
        path.addArc(rectF, 90f, lengthValue);
        path.addArc(rectF, 210f, lengthValue);
        canvas.drawPath(path, paint);
        super.onDraw(canvas);
        canvas.restoreToCount(saveCount);
    }

}
