package com.pannygirlstudio.necly.uilibrary.View;


import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.pannygirlstudio.necly.uilibrary.R;

@Deprecated
//it is usefulless ,Cause it is just a test
public class Necly_TextView_Test extends View {

    String Tags = "Necly_TextView：";


    private String mCustomText;                                                          // 存储要显示的文本
    private int mCustomColor = 0xFF000000;                                              // 存储文本的显示颜色
    private TextPaint mTextPaint;                                                        // 画笔
    private float fontSize = 12;             // 字体大小
  //  private float fontSize = getResources().getDimension(R.dimen.fontSize);             // 字体大小

    public Necly_TextView_Test(Context context) {
        this(context, null);
    }
    public Necly_TextView_Test(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public Necly_TextView_Test(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);


        init(attrs, defStyle);
    }
    private void init(AttributeSet attrs, int defStyle) {

        if(attrs != null){                                                          // 首先判断attrs是否为null
            int count = attrs.getAttributeCount();                                  // 获取AttributeSet中所有的XML属性的数量
            for(int i = 0; i < count; i++){                                         // 遍历AttributeSet中的XML属性

                int attrResId = attrs.getAttributeNameResource(i);                  // 获取attr的资源ID
//                switch (attrResId){
//                    case R.attr.customText:
//                        mCustomText = attrs.getAttributeValue(i);                   //customText属性
//                        break;
//                    case R.attr.customColor:
//                        mCustomColor = attrs.getAttributeIntValue(i, 0xFF000000);   //customColor属性   //如果读取不到对应的颜色值，那么就用黑色作为默认颜色
//                        break;
//                }
            }
        }

        mTextPaint = new TextPaint();                                                        //初始化画笔
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(fontSize);
    }
    private void init2(AttributeSet attributeSet, int defStyle) {

//        if(attributeSet != null){                                                                  // 首先判断attributeSet是否为null
//            Resources.Theme theme = getContext().getTheme();                                        // 获取当前MyView所在的Activity的theme
//            TypedArray typedArray = theme.obtainStyledAttributes(attributeSet, R.styleable.MyView, 0, 0);//通过theme的obtainStyledAttributes方法获取TypedArray对象
//
//            int count = typedArray.getIndexCount();                                                 // 获取typedArray的长度
//            for(int i = 0; i < count; i++){                                                        // 通过for循环遍历typedArray
//                int styledAttr = typedArray.getIndex(i);                                            // 通过typedArray的getIndex方法获取指向R.styleable中对应的属性ID
//                if (styledAttr == R.styleable.MyView_customText) {
//                    mCustomText = typedArray.getString(i);                                    // 通过typedArray的getString方法获取字符串值
//
//                } else if (styledAttr == R.styleable.MyView_customColor) {
//                    mCustomColor = typedArray.getColor(i, 0xFF000000);              // 通过typedArray的getColor方法获取整数类型的颜色值
//
//                }
//            }
//            typedArray.recycle();                                                                   //在使用完typedArray之后，要调用recycle方法回收资源
//        }

        mTextPaint = new TextPaint();                                                        //初始化画笔
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(fontSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mCustomText != null && !mCustomText.equals("")){
            mTextPaint.setColor(mCustomColor);
            canvas.drawText(mCustomText, 0, fontSize, mTextPaint);                          //将文本绘制显示出来
        }
    }

}