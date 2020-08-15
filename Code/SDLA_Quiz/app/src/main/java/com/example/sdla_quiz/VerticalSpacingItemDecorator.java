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
     * @param verticalSpaceHeight the measurement of height between entries
     */
    public VerticalSpacingItemDecorator(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    /**
     * Override, required implementation. Determines space for separation based off of
     * bottom side view.
     *
     * @param outRect a rectanlge object
     * @param view
     * @param parent the recyclerview
     * @param state state from the recyclerview
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = verticalSpaceHeight;
    }
}
