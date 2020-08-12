package com.example.sdla_quiz;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Custom UI class that separates entries in a recyclerview more clearly.
 */
public class VerticalSpacingItemDecorator extends RecyclerView.ItemDecoration {

    //Declare constant
    private final int verticalSpaceHeight;

    /**
     * Constructor
     *
     * @param verticalSpaceHeight
     */
    public VerticalSpacingItemDecorator(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    /**
     * Override, required implementation. Determines space for separation based off of
     * bottom side view.
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = verticalSpaceHeight;
    }
}
