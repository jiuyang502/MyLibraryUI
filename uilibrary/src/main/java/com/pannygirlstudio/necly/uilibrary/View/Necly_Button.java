package com.pannygirlstudio.necly.uilibrary.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
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
    private int radius_size_left_top;
    private int radius_size_left_bottom;
    private int radius_size_right_top;
    private int radius_size_right_bottom;
    private int gravity;

    private Typeface mTypeFaceLight;
    private Typeface mTypeFaceRegular;
    private Typeface mTypeFaceBold;

    public Necly_Button(Context context) {
        this(context, null);
    }
    public Necly_Button(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public Necly_Button(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);

        mTypeFaceLight   = Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf");
        mTypeFaceRegular = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
        mTypeFaceBold    = Typeface.createFromAsset(context.getAssets(), "OpenSans-Bold.ttf");

        InitData();
        InitObj(attrs);
        init();
    }

    private void InitData(){
        normal_color        = Color.parseColor("#1DBF97");
        pressed_color       = Color.parseColor("#068043");
        disabled_color      = Color.parseColor("#464646");
    //  disabled_color      = Color.GRAY;
        radius_size                   = 25;
        radius_size_left_top         = 25;
        radius_size_left_bottom     = 25;
        radius_size_right_top       = 25;
        radius_size_right_bottom    = 25;
        gravity              = Gravity.CENTER;
    }
    private void InitObj(AttributeSet attrs){
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.Necly_Button);
        if (ta != null) {
            normal_color    = ta.getColor(R.styleable.Necly_Button_normal_color_button     , Color.parseColor("#1DBF97"));
            pressed_color   = ta.getColor(R.styleable.Necly_Button_pressed_color_button    , Color.parseColor("#068043"));
            disabled_color  = ta.getColor(R.styleable.Necly_Button_disabled_color_button   , Color.parseColor("#464646"));

            radius_size               = (int) ta.getDimension(R.styleable.Necly_Button_radius_size_button , dip2px(-66));
            radius_size_left_top     = (int) ta.getDimension(R.styleable.Necly_Button_radius_size_left_top_button , dip2px(-66));
            radius_size_left_bottom  = (int) ta.getDimension(R.styleable.Necly_Button_radius_size_left_bottom_button , dip2px(-66));
            radius_size_right_top    = (int) ta.getDimension(R.styleable.Necly_Button_radius_size_right_top_button , dip2px(-66));
            radius_size_right_bottom = (int) ta.getDimension(R.styleable.Necly_Button_radius_size_right_bottom_button , dip2px(-66));
            gravity      =       ta.getInt(R.styleable.Necly_Button_android_gravity   , Gravity.CENTER);

            int TempValue = dip2px(-66);
            if (radius_size == TempValue){                                            //未定义 radius_size
                if( radius_size_left_top  != TempValue ||radius_size_left_bottom != TempValue ||
                    radius_size_right_top != TempValue || radius_size_right_bottom != TempValue ){
                    if (radius_size_left_top  == TempValue){
                        radius_size_left_top = 0;
                    }
                    if (radius_size_left_bottom  == TempValue){
                        radius_size_left_bottom = 0;
                    }
                    if (radius_size_right_top  == TempValue){
                        radius_size_right_top = 0;
                    }
                    if (radius_size_right_bottom  == TempValue){
                        radius_size_right_bottom = 0;
                    }
                }
                else {
                    radius_size                   = dip2px(25);
                    radius_size_left_top         = dip2px(25);
                    radius_size_left_bottom     = dip2px(25);
                    radius_size_right_top       = dip2px(25);
                    radius_size_right_bottom    = dip2px(25);
                }
            }
            else if(radius_size<0){                                             //定义了 radius_size，但是是小于0 的数，全部置 0
                radius_size                   = 0;
                radius_size_left_top        = 0;
                radius_size_left_bottom     = 0;
                radius_size_right_top       = 0;
                radius_size_right_bottom    = 0;
            }
            else {                                                               //定义了 radius_size，全部置为 radius_size，
                radius_size_left_top        = radius_size;
                radius_size_left_bottom     = radius_size;
                radius_size_right_top       = radius_size;
                radius_size_right_bottom    = radius_size;
            }
//        int textColor = attrs.getAttributeIntValue(
//                "http://schemas.android.com/apk/res/android", "textColor", Color.WHITE);
//        setTextColor(textColor);
            ta.recycle();
        }

        TypedArray tar = getContext().obtainStyledAttributes(attrs, new int[]{android.R.attr.textColor, android.R.attr.paddingTop, android.R.attr.paddingBottom});
        if (tar != null) {
            setTextColor(tar.getColor(0, Color.WHITE));
            setPadding(6, (int) tar.getDimension(1, 6), 6, (int) tar.getDimension(2, 6));
            setGravity(gravity);
            tar.recycle();
        }
    }
    private void init() {
        float[] radii = new float[]{                                                            //注意顺序！！！
                radius_size_left_top, radius_size_left_top,
                radius_size_right_top, radius_size_right_top,
                radius_size_right_bottom, radius_size_right_bottom,
                radius_size_left_bottom, radius_size_left_bottom
        };

        GradientDrawable pressedDrawable  = getSolidRectDrawable(radii, pressed_color );
        GradientDrawable normalDrawable   = getSolidRectDrawable(radii, normal_color  );
        GradientDrawable disabledDrawable = getSolidRectDrawable(radii, disabled_color);

        setBackgroundDrawable(getStateListDrawable(pressedDrawable,normalDrawable,disabledDrawable));

        //this.setTypeface(mTypeFaceRegular);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {                                                      // 如果在调用按钮的程序中实现了 OnClickListener，则该处的 OnClickListener 就不会执行了！！！

                Log.i(Tags,"Necly_Button Clicked~!");
            }
        });
    }
    public void ReLoadView() {
        float[] radii = new float[]{                                                            //注意顺序！！！
                radius_size_left_top, radius_size_left_top,
                radius_size_right_top, radius_size_right_top,
                radius_size_right_bottom, radius_size_right_bottom,
                radius_size_left_bottom, radius_size_left_bottom
        };

        GradientDrawable pressedDrawable  = getSolidRectDrawable(radii, pressed_color );
        GradientDrawable normalDrawable   = getSolidRectDrawable(radii, normal_color  );
        GradientDrawable disabledDrawable = getSolidRectDrawable(radii, disabled_color);

        setBackgroundDrawable(getStateListDrawable(pressedDrawable,normalDrawable,disabledDrawable));
    }

    public void setNormal_color(int color) {
        normal_color = color;
    }
    public void setPressed_color(int color) {
        pressed_color = color;
    }
    public void setDisabled_color(int color) {
        disabled_color = color;
    }

    public void SetRadii(int left_top, int left_bottom, int right_top, int right_bottom){
        setRadius_size_left_top(left_top);
        setRadius_size_left_bottom(left_bottom);
        setRadius_size_right_top(right_top);
        setRadius_size_right_bottom(right_bottom);
    }
    public void setRadius_size_left_top(int left_top) {
        radius_size_left_top = dip2px(left_top);
    }
    public void setRadius_size_left_bottom(int left_bottom) {
        radius_size_left_bottom = dip2px(left_bottom);
    }
    public void setRadius_size_right_top(int right_top) {
        radius_size_right_top = dip2px(right_top);
    }
    public void setRadius_size_right_bottom(int right_bottom) {
        radius_size_right_bottom = dip2px(right_bottom);
    }

    @Override
    public void setTypeface(Typeface tf){
        super.setTypeface(tf);
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
     * 得到实心的drawable, 一般作为选中，点中的效果
     *
     * @param radii 圆角半径数组
     * @param solidColor   实心颜色
     * @return 得到实心效果
     */
    public static GradientDrawable getSolidRectDrawable(float[] radii, int solidColor) {

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii (radii);                                                    // 设置矩形的圆角半径

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
