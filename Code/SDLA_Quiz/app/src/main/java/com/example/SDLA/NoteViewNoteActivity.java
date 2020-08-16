package com.example.SDLA;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * This activity allows users to view and edit notes by enabling or disabling an edit mode.
 */
public class NoteViewNoteActivity extends AppCompatActivity implements View.OnTouchListener,
        GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, View.OnClickListener {

    //Constants to establish whether edit mode is active or not.
    public static final int EDIT_MODE_ENABLED = 1;
    public static final int EDIT_MODE_DISABLED = 0;

    //Declare Views
    private LinedEditText mContent;
    private EditText mEditTitle;
    private TextView mViewTitle;
    private RelativeLayout mTickContainer, mBackArrowContainer;
    private ImageButton mTick, mBackArrow;

    //Declare Variables
    private boolean mIsNewNote;
    private Note mInitialNote;
    private GestureDetector mGestureDetector;
    private int mMode;

    /**
     * onCreate - Initialise ui, determine whether or not to enter edit mode.
     *
     * @param savedInstanceState a saved instance of an activity (if there is one)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view_note);

        //Init views
        mContent = findViewById(R.id.viewNote_content);
        mEditTitle = findViewById(R.id.edit_note_title);
        mViewTitle = findViewById(R.id.view_note_title);
        mTickContainer = findViewById(R.id.tick_container);
        mBackArrowContainer = findViewById(R.id.back_arrow_container);
        mTick = findViewById(R.id.tick);
        mBackArrow = findViewById(R.id.toolbar_back_arrow);
        setListeners();

        if (getIncomingIntent()) {
            // new note, go to edit mode
            setNewNoteProperties();
            enableEditMode();
        } else {
            //old note, go to view mode
            setNoteProperties();
            disableContentInteraction();
        }
    }


    /**
     * A method to initialise gesture listeners.
     */
    private void setListeners() {
        mContent.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this, this);
        mViewTitle.setOnClickListener(this);
        mTick.setOnClickListener(this);
        mBackArrow.setOnClickListener(this);
    }

    /**
     * Determine whether note is new and should enter edit mode, or old and should enter view mode.
     *
     * @return whether or not note is new (true or false)
     */
    private boolean getIncomingIntent() {
        if (getIntent().hasExtra("selected_note")) {
            //Retrieve Note from previous(browse) activity
            mInitialNote = getIntent().getParcelableExtra("selected_note");
            mMode = EDIT_MODE_DISABLED;
            mIsNewNote = false;
            return false;
        }
        mMode = EDIT_MODE_ENABLED;
        mIsNewNote = true;
        return true;
    }

    /**
     * Method to disable interaction with on-screen elements for view-mode.
     */
    private void disableContentInteraction() {
        mContent.setKeyListener(null);
        mContent.setFocusable(false);
        mContent.setFocusableInTouchMode(false);
        mContent.setCursorVisible(false);
        mContent.clearFocus();
    }

    /**
     * Method to enable interaction with on-screen elements for edit-mode.
     */
    private void enableContentInteraction() {
        mContent.setKeyListener(new EditText(this).getKeyListener());
        mContent.setFocusable(true);
        mContent.setFocusableInTouchMode(true);
        mContent.setCursorVisible(true);
        mContent.requestFocus();
    }

    /**
     * Change ui to reflect entering Edit mode.
     */
    private void enableEditMode() {
        mBackArrowContainer.setVisibility(View.GONE);
        mTickContainer.setVisibility(View.VISIBLE);

        mViewTitle.setVisibility(View.GONE);
        mEditTitle.setVisibility(View.VISIBLE);

        mMode = EDIT_MODE_ENABLED;

        enableContentInteraction();
    }

    /**
     * Change ui to reflect entering view mode.
     */
    private void disableEditMode() {
        mBackArrowContainer.setVisibility(View.VISIBLE);
        mTickContainer.setVisibility(View.GONE);

        mViewTitle.setVisibility(View.VISIBLE);
        mEditTitle.setVisibility(View.GONE);

        mMode = EDIT_MODE_DISABLED;

        disableContentInteraction();
    }

    /**
     * Method to hide the virtual keyboard.
     */
    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Assign note data to UI.
     */
    private void setNoteProperties() {
        mViewTitle.setText(mInitialNote.getTitle());
        mEditTitle.setText(mInitialNote.getTitle());
        mContent.setText(mInitialNote.getContent());
    }

    /**
     * Set textviews to default for a new note.
     */
    private void setNewNoteProperties() {
        mViewTitle.setText("Note Title");
        mEditTitle.setText("Note Title");
    }

    //Gesture detector parses touch event to detect gestures.
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    //Required implementation, no functionality added
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    //Required implementation, no functionality added
    @Override
    public void onShowPress(MotionEvent e) {

    }

    //Required implementation, no functionality added
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    //Required implementation, no functionality added
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    //Required implementation, no functionality added
    @Override
    public void onLongPress(MotionEvent e) {

    }

    //Required implementation, no functionality added
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    //Required implementation, no functionality added
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    //Override doubleTap - enables edit mode
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        enableEditMode();
        return false;
    }

    //Required implementation, no functionality added
    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    /**
     * OnClick - tick - return to view mode
     * title - activate edit mode and set text cursor to end of current title.
     * back arrow - finish activity.
     *
     * @param v the view being clicked
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // enter view mode
            case R.id.tick: {
                hideSoftKeyboard();
                disableEditMode();
                break;
            }
            // enable edit mode, move editing cursor to end of title string.
            case R.id.view_note_title: {
                enableEditMode();
                mEditTitle.requestFocus();
                mEditTitle.setSelection(mEditTitle.length());
                break;
            }
            //end activity
            case R.id.toolbar_back_arrow: {
                finish();
                break;
            }
        }
    }

    /**
     * Override for dual functionality of dynamic tick/back button.
     */
    @Override
    public void onBackPressed() {
        if (mMode == EDIT_MODE_ENABLED) {
            onClick(mTick);
        } else {
            super.onBackPressed();
        }
    }


    /**
     * Saves screen state when orientation is rotated.
     *
     * @param outState the saved instance
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mode", mMode);
    }

    /**
     * Restores screen state after orientation change.
     *
     * @param savedInstanceState the save instance to restore
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mMode = savedInstanceState.getInt("mode");
        if (mMode == EDIT_MODE_ENABLED) {
            enableEditMode();
        }
    }
}
