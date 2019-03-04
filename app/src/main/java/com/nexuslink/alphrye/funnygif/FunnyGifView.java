package com.nexuslink.alphrye.funnygif;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.io.InputStream;

public class FunnyGifView extends AppCompatImageView {

    private Movie mMovie;

    private long mStartTime;

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

            float scaleWidth = (float) getWidth()/ (float) mMovie.width();
            float scaleHeight = (float) getHeight() / (float) mMovie.height();
            float scale = scaleHeight < scaleWidth ? scaleHeight : scaleWidth;
            canvas.scale(scale, scale);
            mMovie.draw(canvas, (getWidth() / 2 - scale * mMovie.width() / 2) / scale,  (getHeight() / 2 - scale * mMovie.height() / 2) / scale);
            invalidate();
        }
    }

    /**
     * 设置gif资源
     */
    public void setGifResource(InputStream inputStream) {
        mMovie = Movie.decodeStream(inputStream);
        mStartTime = 0;
    }
}
