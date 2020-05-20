package com.example.testschoolschedule.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

public class Dragview extends AppCompatButton {

//    private float moveX;
//    private float moveY;


    public Dragview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                moveX = event.getX();
//                moveY = event.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                setTranslationX(getX() + (event.getX() - moveX));
//                setTranslationY(getY() + (event.getY() - moveY));
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                break;
//        }
//
//        return true;
//    }

}
