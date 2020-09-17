package com.pannygirlstudio.necly.uilibrary.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.pannygirlstudio.necly.uitest001.R;

@Deprecated
//it is usefulless ,Cause it is just a test
public class Necly_RadioButton extends android.support.v7.widget.AppCompatRadioButton {

    String Tags = "Necly_RadioButton：";

    private int normal_color;
    private int checked_color;
    private int disabled_color;
    private int radius_size;
    private int gravity;

    public Necly_RadioButton(Context context) {
        this(context, null);
    }
    public Necly_RadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public Necly_RadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        InitData();
        InitObj(attrs);
        init();
    }

    private void InitData(){
        normal_color        = Color.parseColor("#36424A");
        checked_color       = Color.parseColor("#536DFE");
       // disabled_color      = Color.parseColor("#464646");
        disabled_color      = Color.GRAY;
        radius_size         = 0;
        gravity              = Gravity.CENTER;
    }
    private void InitObj(AttributeSet attrs){
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.Necly_RadioButton);
        if (ta != null) {
            normal_color    = ta.getColor(R.styleable.Necly_RadioButton_normal_color_radiobutton     , Color.parseColor("#36424A"));
            checked_color   = ta.getColor(R.styleable.Necly_RadioButton_checked_color_radiobutton    , Color.parseColor("#536DFE"));
            disabled_color  = ta.getColor(R.styleable.Necly_RadioButton_disabled_color_radiobutton   , Color.GRAY);

            radius_size = (int) ta.getDimension(R.styleable.Necly_RadioButton_radius_size_radiobutton , dip2px(0));
            gravity      =       ta.getInt(R.styleable.Necly_RadioButton_android_gravity   , Gravity.CENTER);
//        int textColor = attrs.getAttributeIntValue(
//                "http://schemas.android.com/apk/res/android", "textColor", Color.WHITE);
//        setTextColor(textColor);
            ta.recycle();
        }

//        TypedArray tar = getContext().obtainStyledAttributes(attrs, new int[]{android.R.attr.textColor, android.R.attr.paddingTop, android.R.attr.paddingBottom});
//        if (tar != null) {
//            setTextColor(tar.getColor(0, Color.WHITE));
//            setPadding(6, (int) tar.getDimension(1, 8), 6, (int) tar.getDimension(2, 8));
//            setGravity(gravity);
//            tar.recycle();
//        }
    }
    private void init() {
        ColorDrawable checkedDrawable  = getColorDrawable(radius_size, checked_color );
        ColorDrawable normalDrawable   = getColorDrawable(radius_size, normal_color  );
        ColorDrawable disabledDrawable = getColorDrawable(radius_size, disabled_color);

        setBackground(getStateListDrawable(checkedDrawable,normalDrawable,disabledDrawable));

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {                                                      // 如果在调用按钮的程序中实现了 OnClickListener，则该处的 OnClickListener 就不会执行了！！！

                Log.i(Tags,"Necly_RadioButton Clicked~!");
            }
        });
    }

    @Override
    public void setTextColor(@ColorInt int color) {
        super.setTextColor(color);
    }
    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(dip2px(left), dip2px(top), dip2px(right), dip2px(bottom));
    }

    /**
     * 得到实心的drawable, 一般作为选中，点中的效果
     *
     * @param cornerRadius 圆角半径
     * @param solidColor   实心颜色
     * @return 得到实心效果
     */
    public static GradientDrawable getSolidRectDrawable(int cornerRadius, int solidColor) {

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(cornerRadius);                                             // 设置矩形的圆角半径
        gradientDrawable.setColor(solidColor);                                                      // 设置绘画图片色值
        gradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);                       // 绘画的是矩形

        return gradientDrawable;
    }
    public static ColorDrawable getColorDrawable(int cornerRadius, int solidColor) {

        ColorDrawable colorDrawable = new ColorDrawable();
        colorDrawable.setColor(solidColor);                                                      // 设置绘画图片色值

        return colorDrawable;
    }

    /**
     * 背景选择器
     *
     * @param checkedDrawable 选择状态的Drawable
     * @param normalDrawable  正常状态的Drawable
     * @return 状态选择器
     */
    public StateListDrawable getStateListDrawable(Drawable checkedDrawable, Drawable normalDrawable,Drawable disabledDrawable) {

        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_checked}, checkedDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled                                }, normalDrawable);
        stateListDrawable.addState(new int[]{                                                              }, disabledDrawable);     //设置不能用的状态  //默认其他状态背景

        return stateListDrawable;
    }

    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
