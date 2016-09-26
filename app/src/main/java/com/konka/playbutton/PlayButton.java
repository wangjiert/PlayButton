package com.konka.playbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PlayButton extends Button {
    AnimatorSet animatorSet = new AnimatorSet();
    Path path = new Path();
    Paint paint = new Paint();
    Paint cleanPaint = new Paint();
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
        //cleanPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        cleanPaint.setColor(getDrawingCacheBackgroundColor());
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

        animatorSet.play(lengthAnimator).before(rotationAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animatorSet.start();
            }
        });
        animatorSet.start();
    }

    private void setLengthValue(float lengthValue){
        this.lengthValue = lengthValue;
        invalidate();
    }

    private void setRotationValue(float rotationValue){
        if(rotationValue > 180) {
            lengthValue = (360f - rotationValue) / 3 > 0.05 ? (360f - rotationValue) / 3 : 0.05f;
        }
        this.rotationValue = rotationValue;
        invalidate();
    }

    public void startAnimator(){
        animatorSet.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(cleanPaint);
        int saveCount = canvas.save();
        canvas.rotate(rotationValue, 300f, 300f);
        path.addArc(rectF, -30f, lengthValue);
        path.addArc(rectF, 90f, lengthValue);
        path.addArc(rectF, 210f, lengthValue);
        canvas.drawPath(path, paint);
        path.reset();
        super.onDraw(canvas);
        canvas.restoreToCount(saveCount);
    }

}
