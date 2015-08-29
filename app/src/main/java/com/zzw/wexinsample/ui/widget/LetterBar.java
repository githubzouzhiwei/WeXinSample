package com.zzw.wexinsample.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * 字母索引栏
 * Created by zouzhiwei on 2015/8/28.
 */
public class LetterBar extends View {

    //用于Log的tag
    private final String TAG = getClass().getSimpleName();
    //26个英文字母数组
    private String[] letters;
    //用于在canvas画出字母
    private Paint paint;
    //点击的字母的下标
    private int chooseIndex = -1;
    //字母改变回调接口
    private OnLetterChangeListener onLetterChangeListener;
    //用于在界面显示点击的字母
    private TextView tvTip;

    public LetterBar(Context context) {
        this(context, null);
    }

    public LetterBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //初始化画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTextSize(28);
        //初始化字母数组
        letters = new String[]{"↑", "☆", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    }

    /**
     * 设置提示字母的TextView
     *
     * @param tvTip
     */
    public void setTvTip(TextView tvTip) {
        this.tvTip = tvTip;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取布局的宽和高
        int width = getWidth();
        int height = getHeight();
        //计算每个字母的高度
        int letterHeight = height / letters.length;
        //画出每个字母
        for (int i = 0; i < letters.length; i++) {
            //计算每个字母的坐标
            float xPos = (width - paint.measureText(letters[i])) / 2;
            float yPos = letterHeight * i + letterHeight;
            //在画布上画出字母
            canvas.drawText(letters[i], xPos, yPos, paint);
        }
    }

    /**
     * 还原字母侧边栏
     */
    public void reSet() {
        setBackgroundDrawable(new ColorDrawable(0x0000));
        if (tvTip != null) {
            tvTip.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int oldChooseIndex = chooseIndex;
        chooseIndex = (int) ((event.getY() / getHeight()) * letters.length);
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                setBackgroundDrawable(new ColorDrawable(0x0000));
                if (tvTip != null) {
                    tvTip.setVisibility(View.GONE);
                }
                break;
            default:
                setBackgroundColor(Color.GRAY);
                if (oldChooseIndex != chooseIndex) {
                    if (chooseIndex >= 0 && chooseIndex < letters.length) {
                        Log.i(TAG, "letter:" + letters[chooseIndex]);
                        if (onLetterChangeListener != null) {
                            //当设置了回调接口后，返回点击的字母
                            onLetterChangeListener.onLetterChange(letters[chooseIndex]);
                        }
                        if (tvTip != null) {
                            tvTip.setVisibility(View.VISIBLE);
                            tvTip.setText(letters[chooseIndex]);
                        }
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }

    /**
     * 设置回调接口
     *
     * @param onLetterChangeListener
     */
    public void setOnLetterChangeListener(OnLetterChangeListener onLetterChangeListener) {
        this.onLetterChangeListener = onLetterChangeListener;
    }

    /**
     * 字母回调接口
     */
    public interface OnLetterChangeListener {
        public void onLetterChange(String s);
    }
}
