package com.nexuslink.alphrye.funnygif;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.io.InputStream;

public class FunnyGifView extends AppCompatImageView {

    private Movie mMovie;

    private long mStartTime;

    private int resId;

    public FunnyGifView(Context context) {
        this(context, null);
    }

    public FunnyGifView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public FunnyGifView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mMovie != null){
            long nowTime = System.currentTimeMillis();
            if (mStartTime == 0) {
                mStartTime = nowTime;
            }
            int duration = mMovie.duration();
            long timePlay = nowTime - mStartTime;
            mMovie.setTime((int) (duration - timePlay % duration));
            mMovie.draw(canvas, getWidth() / 2 - mMovie.width() / 2, getHeight() / 2 - mMovie.height() / 2);
            invalidate();
        }
    }

    /**
     * 设置gif资源
     */
    public void setGifResource(int resId) {
        this.resId = resId;
        //测试本地资源
        @SuppressLint("ResourceType") InputStream inputStream = getContext().getResources().openRawResource(R.drawable.test0);
        mMovie = Movie.decodeStream(inputStream);
        mStartTime = 0;
    }
}
