package com.example.testschoolschedule.Activity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.testschoolschedule.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnLongClickListener, View.OnDragListener {


    @BindView(R.id.unit1)
    Button unit1;
    @BindView(R.id.unit2)
    Button unit2;
    @BindView(R.id.unit3)
    Button unit3;
    @BindView(R.id.motherlayout)
    ConstraintLayout motherlayout;
    @BindView(R.id.childTest1)
    LinearLayout childTest1;
    @BindView(R.id.childTest2)
    LinearLayout childTest2;
    @BindView(R.id.childTest3)
    LinearLayout childTest3;
    @BindView(R.id.childTest4)
    LinearLayout childTest4;
    @BindView(R.id.childTest5)
    LinearLayout childTest5;
    @BindView(R.id.childTest6)
    LinearLayout childTest6;
    @BindView(R.id.childTest7)
    LinearLayout childTest7;
    @BindView(R.id.childTest8)
    LinearLayout childTest8;
    @BindView(R.id.studentList)
    AppCompatSpinner studentList;
    private Drawable oldbg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        setListeners();
    }


    @Override
    public void init() {
        String[] mItems = getResources().getStringArray(R.array.spinnername);
        ArrayAdapter<String> _Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);
        studentList.setAdapter(_Adapter);
    }


    @Override
    public void setListeners() {

        unit1.setOnLongClickListener(this);
        unit2.setOnLongClickListener(this);
        unit3.setOnLongClickListener(this);
        childTest1.setOnDragListener(this);
        childTest2.setOnDragListener(this);
        childTest3.setOnDragListener(this);
        childTest4.setOnDragListener(this);
        childTest5.setOnDragListener(this);
        childTest6.setOnDragListener(this);
        childTest7.setOnDragListener(this);
        childTest8.setOnDragListener(this);

    }


    @Override
    public boolean onLongClick(View v) {

        ClipData data = ClipData.newPlainText("Label", "課程選擇成功");
        switch (v.getId()) {
            case R.id.unit1:

                unit1.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);

                View.DragShadowBuilder builder = new View.DragShadowBuilder(unit1);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    unit1.startDragAndDrop(data, builder, unit1, 0);
                } else {
                    unit1.startDrag(data, builder, unit1, 0);
                }
                break;

            case R.id.unit2:
                unit2.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);

                View.DragShadowBuilder builder2 = new View.DragShadowBuilder(unit2);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    unit2.startDragAndDrop(data, builder2, unit2, 0);
                } else {
                    unit2.startDrag(data, builder2, unit2, 0);
                }
                break;

            case R.id.unit3:
                unit3.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);

                View.DragShadowBuilder builder3 = new View.DragShadowBuilder(unit3);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    unit3.startDragAndDrop(data, builder3, unit3, 0);
                } else {
                    unit3.startDrag(data, builder3, unit3, 0);
                }
                break;

        }

        return true;
    }

    @Override
    public boolean onDrag(View view, DragEvent event) {

        int action = event.getAction();
        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {

                    return true;

                }
                break;

            case DragEvent.ACTION_DRAG_ENTERED:

                if (view.getBackground() == null) {
                    view.setBackgroundColor(0xff23de36);
                } else {
                    oldbg = view.getBackground();
                    view.setBackgroundColor(0xff23de36);
                }


                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                break;
            case DragEvent.ACTION_DRAG_EXITED:

                view.setBackground(oldbg);
                oldbg = null;
                break;
            case DragEvent.ACTION_DROP:

                ClipData.Item item = event.getClipData().getItemAt(0);
                String dragData = item.getText().toString();
                view.invalidate();
                View v = (View) event.getLocalState();
                LinearLayout container = (LinearLayout) view;
                switch (v.getId()) {
                    case R.id.unit1:

                        Toast.makeText(this, "課程A選課成功" + dragData, Toast.LENGTH_SHORT).show();
                        container.setBackgroundResource(R.drawable.lessonble);
                        break;

                    case R.id.unit2:

                        Toast.makeText(this, "課程B選課成功" + dragData, Toast.LENGTH_SHORT).show();
                        container.setBackgroundResource(R.drawable.lessongre);
                        break;

                    case R.id.unit3:

                        Toast.makeText(this, "課程C選課成功" + dragData, Toast.LENGTH_SHORT).show();
                        container.setBackgroundResource(R.drawable.lessonylw);
                        break;
                }

//                ViewGroup owner = (ViewGroup) v.getParent();
//                owner.removeView(v);
//                LinearLayout container = (LinearLayout) view;
//                container.addView(v);
                v.setVisibility(View.VISIBLE);


                break;
            case DragEvent.ACTION_DRAG_ENDED:


                view.invalidate();

                break;
        }
        return true;
    }



}
