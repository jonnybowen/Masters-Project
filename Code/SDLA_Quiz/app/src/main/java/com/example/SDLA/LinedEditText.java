package com.example.SDLA;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * Custom editText that resembles lined paper.
 * Requires a light yellow background to look like a notepad. (not included)
 */
public class LinedEditText extends AppCompatEditText {

    private static final String TAG = "LinedEditText";

    // Declare Rect and Paint objects.
    private Rect mRect;
    private Paint mPaint;

    // Need this constructor for LayoutInflater
    public LinedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(0xFFFFD966); // Color of the lines on paper
    }

    /**
     * Uses Rect and Paint objects to create lines within the editText.
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {

        // get the height of the view
        int height = ((View) this.getParent()).getHeight();

        int lineHeight = getLineHeight();
        int numberOfLines = height / lineHeight;

        Rect r = mRect;
        Paint paint = mPaint;

        int baseline = getLineBounds(0, r);

        for (int i = 0; i < numberOfLines; i++) {

            canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);

            baseline += lineHeight;
        }

        super.onDraw(canvas);
    }

}