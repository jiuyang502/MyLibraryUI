package com.pannygirlstudio.necly.uilibrary.View;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import com.pannygirlstudio.necly.uitest001.R;

@Deprecated
//it is usefulless ,Cause it is just a test
public class Necly_TextView extends android.support.v7.widget.AppCompatTextView {

    String Tags = "Necly_TextView：";

    private int normal_bgcolor;
    private int normal_color;
    private int selected_color;
    private int disabled_color;
    private int radius_size;
    private int gravity;

    private int mTextColor  = 0xFF000000;

    private String mCustomText;                                                           // 存储要显示的文本
    private int mCustomColor    = 0xFF000000;                                            // 存储文本的显示颜色
    private TextPaint mTextPaint;               // 画笔
    private int fontSize = 12;                // 字体大小
  //  private float fontSize = getResources().getDimension(R.dimen.fontSize);                // 字体大小

    public Necly_TextView(Context context) {
        this(context, null);
    }
    public Necly_TextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public Necly_TextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        InitData();
        InitObj(attrs);
        init(attrs, defStyle);
    }
    private void InitData(){
        normal_bgcolor      = Color.parseColor("#000000");
        normal_color        = Color.parseColor("#36424A");
        selected_color      = Color.parseColor("#536DFE");
        // disabled_color      = Color.parseColor("#464646");
        disabled_color      = Color.GRAY;
        radius_size         = 0;
        gravity              = Gravity.CENTER;
    }
    private void InitObj(AttributeSet attrs){
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.Necly_TextView);
        if (ta != null) {
            normal_bgcolor  = ta.getColor(R.styleable.Necly_TextView_normal_bgcolor_textview, Color.parseColor("#000000"));
            normal_color    = ta.getColor(R.styleable.Necly_TextView_normal_color_textview   , Color.parseColor("#36424A"));
            selected_color  = ta.getColor(R.styleable.Necly_TextView_selected_color_textview, Color.parseColor("#536DFE"));
            disabled_color  = ta.getColor(R.styleable.Necly_TextView_disabled_color_textview, Color.GRAY);

            radius_size = (int) ta.getDimension(R.styleable.Necly_TextView_radius_size_textview, dip2px(0));
            gravity      =       ta.getInt(R.styleable.Necly_TextView_android_gravity   , Gravity.CENTER);

            ta.recycle();
        }

        TypedArray tar = getContext().obtainStyledAttributes(attrs, new int[]{android.R.attr.textSize, android.R.attr.paddingTop, android.R.attr.paddingBottom});
        if (tar != null) {
            fontSize = (int)tar.getDimension(0, 12);
            setPadding(6, (int) tar.getDimension(1, 8), 6, (int) tar.getDimension(2, 8));
           // setGravity(gravity);
            tar.recycle();
        }
    }

    private void init(AttributeSet attrs, int defStyle) {
        mTextColor = normal_color;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        if (enabled){
            mTextColor = (isSelected()?selected_color:normal_color);
        }
        else {
            mTextColor = disabled_color;
        }
        invalidate();
    }
    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);

        mTextColor = (selected?selected_color:normal_color);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mTextPaint == null){
            mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        }

        if(getText() != null && !getText().toString().equals("")){
            mTextPaint.setTextSize(sp2px(fontSize));
            mTextPaint.setColor(mTextColor);

            String TempTextStr = getText().toString();
            Rect mTempRect = new Rect();
            mTextPaint.getTextBounds(TempTextStr, 0, TempTextStr.length(), mTempRect);
            canvas.drawText(TempTextStr, 0, 0, mTextPaint);                          //将文本绘制显示出来
        }
    }

    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

}