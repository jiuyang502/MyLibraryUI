package com.pannygirlstudio.necly.uilibrary.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.pannygirlstudio.necly.uilibrary.R;

public class Necly_Button extends AppCompatButton {

    String Tags = "Necly_Button：";

    private int normal_color;
    private int pressed_color;
    private int disabled_color;
    private int radius_size;
    private int gravity;

    public Necly_Button(Context context) {
        this(context, null);
    }
    public Necly_Button(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public Necly_Button(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);

        InitData();
        InitObj(attrs);
        init();
    }

    private void InitData(){
        normal_color        = Color.parseColor("#1DBF97");
        pressed_color       = Color.parseColor("#068043");
        disabled_color      = Color.parseColor("#464646");
    //  disabled_color      = Color.GRAY;
        radius_size         = 25;
        gravity              = Gravity.CENTER;
    }
    private void InitObj(AttributeSet attrs){
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.Necly_Button);
        if (ta != null) {
            normal_color    = ta.getColor(R.styleable.Necly_Button_normal_color_button     , Color.parseColor("#1DBF97"));
            pressed_color   = ta.getColor(R.styleable.Necly_Button_pressed_color_button    , Color.parseColor("#068043"));
            disabled_color  = ta.getColor(R.styleable.Necly_Button_disabled_color_button   , Color.parseColor("#464646"));

            radius_size = (int) ta.getDimension(R.styleable.Necly_Button_radius_size_button , dip2px(25));
            gravity      =       ta.getInt(R.styleable.Necly_Button_android_gravity   , Gravity.CENTER);
//        int textColor = attrs.getAttributeIntValue(
//                "http://schemas.android.com/apk/res/android", "textColor", Color.WHITE);
//        setTextColor(textColor);
            ta.recycle();
        }

        TypedArray tar = getContext().obtainStyledAttributes(attrs, new int[]{android.R.attr.textColor, android.R.attr.paddingTop, android.R.attr.paddingBottom});
        if (tar != null) {
            setTextColor(tar.getColor(0, Color.WHITE));
            setPadding(6, (int) tar.getDimension(1, 8), 6, (int) tar.getDimension(2, 8));
            setGravity(gravity);
            tar.recycle();
        }
    }
    private void init() {
        GradientDrawable pressedDrawable  = getSolidRectDrawable(radius_size, pressed_color );
        GradientDrawable normalDrawable   = getSolidRectDrawable(radius_size, normal_color  );
        GradientDrawable disabledDrawable = getSolidRectDrawable(radius_size, disabled_color);

        setBackgroundDrawable(getStateListDrawable(pressedDrawable,normalDrawable,disabledDrawable));

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {                                                      // 如果在调用按钮的程序中实现了 OnClickListener，则该处的 OnClickListener 就不会执行了！！！

                Log.i(Tags,"Necly_Button Clicked~!");
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

    /**
     * 背景选择器
     *
     * @param pressedDrawable 按下状态的Drawable
     * @param normalDrawable  正常状态的Drawable
     * @return 状态选择器
     */
    public StateListDrawable getStateListDrawable(Drawable pressedDrawable, Drawable normalDrawable,Drawable disabledDrawable) {

        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed}, pressedDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled                                }, normalDrawable);
        stateListDrawable.addState(new int[]{                                                              }, disabledDrawable);     //设置不能用的状态  //默认其他状态背景

        return stateListDrawable;
    }

    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
