package com.example.testschoolschedule.Activity;

import android.content.ClipData;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.DragEvent;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.testschoolschedule.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnLongClickListener{


    @BindView(R.id.react_area)
    LinearLayout reactArea;
    @BindView(R.id.unit1)
    Button unit1;
    @BindView(R.id.unit2)
    Button unit2;
    @BindView(R.id.unit3)
    Button unit3;
    @BindView(R.id.motherlayout)
    ConstraintLayout motherlayout;


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

    }

    @Override
    public void setListeners() {

        unit1.setOnLongClickListener(this);
        unit2.setOnLongClickListener(this);
        unit3.setOnLongClickListener(this);
        reactArea.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        Log.i("111", "开始拖拽");

                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.i("111", "结束拖拽");

                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.i("111", "拖拽的view进入监听的view时");
                        reactArea.setBackgroundColor(0xffe19830);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.i("111", "拖拽的view离开监听的view时");
                        reactArea.removeAllViews();
                        reactArea.setBackgroundColor(0xffcd3457);
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        float x = event.getX();
                        float y = event.getY();
                        long l = SystemClock.currentThreadTimeMillis();
                        Log.i("111", "拖拽的view在BLUE中的位置:x =" + x + ",y=" + y);
                        break;
                    case DragEvent.ACTION_DROP:
                        Log.i("111", "释放拖拽的view");
                        Button localState = (Button) event.getLocalState();
                        ((ViewGroup) localState.getParent()).removeView(localState);

                        switch (localState.getId())
                        {
                            case R.id.unit1:
                                reactArea.setBackgroundColor(0xffab3598);
                                break;

                            case R.id.unit2:
                                reactArea.setBackgroundColor(0xff26cc98);
                                break;

                            case R.id.unit3:
                                reactArea.setBackgroundColor(0xff8a6c5e);
                                break;
                        }


                        motherlayout.addView(localState);
                        break;
                }

                return true;
            }
        });

        reactArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reactArea.setBackgroundColor(0xffcd3457);
            }
        });


    }


    @Override
    public boolean onLongClick(View v) {

        ClipData data = ClipData.newPlainText("Label", "我是文本内容！");
        switch (v.getId())
        {
            case R.id.unit1:
                //设置震动反馈
                unit1.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);

                // 创建DragShadowBuilder，我把控件本身传进去
                View.DragShadowBuilder builder = new View.DragShadowBuilder(unit1);
                // 剪切板数据，可以在DragEvent.ACTION_DROP方法的时候获取。

                // 开始拖拽
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    unit1.startDragAndDrop(data, builder, unit1, 0);
                } else {
                    unit1.startDrag(data, builder, unit1, 0);
                }
                break;

            case R.id.unit2:
                //设置震动反馈
                unit2.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);

                View.DragShadowBuilder builder2 = new View.DragShadowBuilder(unit2);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    unit2.startDragAndDrop(data, builder2, unit2, 0);
                } else {
                    unit2.startDrag(data, builder2, unit2, 0);
                }
                break;

            case R.id.unit3:
                //设置震动反馈
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
}
