package com.example.testschoolschedule.Activity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.testschoolschedule.Event.LessonEvent;
import com.example.testschoolschedule.R;
import com.example.testschoolschedule.model.ApiServer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnLongClickListener, View.OnDragListener, Spinner.OnItemSelectedListener {


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
    @BindView(R.id.progress)
    ProgressBar progress;
    private Drawable oldbg;
    private LessonEvent response;


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
        ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        studentList.setAdapter(_Adapter);
        ApiServer.getInstance().getLessonApis();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
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
        studentList.setOnItemSelectedListener(this);

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
                view.invalidate();
                View v = (View) event.getLocalState();
                LinearLayout container = (LinearLayout) view;
                switch (v.getId()) {
                    case R.id.unit1:

                        Glide.with(this)
                                .asBitmap()
                                .load(unit1.getTag().toString())
                                .into(new CustomTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        ImageView childView = new ImageView(MainActivity.this);
                                        childView.setImageBitmap(resource);
                                        container.addView(childView);
                                    }

                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {

                                    }
                                });

                        break;

                    case R.id.unit2:

                        Glide.with(this)
                                .asBitmap()
                                .load(unit2.getTag().toString())
                                .into(new CustomTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        ImageView childView = new ImageView(MainActivity.this);
                                        childView.setImageBitmap(resource);
                                        container.addView(childView);
                                    }

                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {

                                    }
                                });
                        break;

                    case R.id.unit3:

                        Glide.with(this)
                                .asBitmap()
                                .load(unit3.getTag().toString())
                                .into(new CustomTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        ImageView childView = new ImageView(MainActivity.this);
                                        childView.setImageBitmap(resource);
                                        container.addView(childView);
                                    }

                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {

                                    }
                                });
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    synchronized public void onDZApiCounts(LessonEvent event) {

        response = event;

        unit1.setText(response.getResponse().getStudentA()[0].getLesson());
        unit2.setText(response.getResponse().getStudentA()[1].getLesson());
        unit3.setText(response.getResponse().getStudentA()[2].getLesson());
        unit1.setTag(response.getResponse().getStudentA()[0].getColor());
        unit2.setTag(response.getResponse().getStudentA()[1].getColor());
        unit3.setTag(response.getResponse().getStudentA()[2].getColor());

        progress.setVisibility(View.GONE);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        if (response != null) {
            switch (position) {
                case 0:
                    unit1.setText(response.getResponse().getStudentA()[0].getLesson());
                    unit2.setText(response.getResponse().getStudentA()[1].getLesson());
                    unit3.setText(response.getResponse().getStudentA()[2].getLesson());
                    unit1.setTag(response.getResponse().getStudentA()[0].getColor());
                    unit2.setTag(response.getResponse().getStudentA()[1].getColor());
                    unit3.setTag(response.getResponse().getStudentA()[2].getColor());
                    break;

                case 1:
                    unit1.setText(response.getResponse().getStudentB()[0].getLesson());
                    unit2.setText(response.getResponse().getStudentB()[1].getLesson());
                    unit3.setText(response.getResponse().getStudentB()[2].getLesson());
                    unit1.setTag(response.getResponse().getStudentB()[0].getColor());
                    unit2.setTag(response.getResponse().getStudentB()[1].getColor());
                    unit3.setTag(response.getResponse().getStudentB()[2].getColor());
                    break;

                case 2:
                    unit1.setText(response.getResponse().getStudentC()[0].getLesson());
                    unit2.setText(response.getResponse().getStudentC()[1].getLesson());
                    unit3.setText(response.getResponse().getStudentC()[2].getLesson());
                    unit1.setTag(response.getResponse().getStudentC()[0].getColor());
                    unit2.setTag(response.getResponse().getStudentC()[1].getColor());
                    unit3.setTag(response.getResponse().getStudentC()[2].getColor());
                    break;
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
