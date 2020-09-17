package com.pannygirlstudio.necly.uilibrary.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pannygirlstudio.necly.uitest001.R;

public class Necly_Toast extends Toast {

    public static final int ToastShowType_None      = -1;
    public static final int ToastShowType_Infor     = 0;
    public static final int ToastShowType_Error     = 1;
    public static final int ToastShowType_warning   = 2;

    private TextView TxtMessage ;

    private Toast mToast         ;
    private Context mContext     ;
    private int m_ShowTime      ;
    private int m_ToastShowType ;

    public Necly_Toast(Context context){
        super(context);
        mContext         = context;
        m_ShowTime       = Toast.LENGTH_SHORT;
        m_ToastShowType = ToastShowType_None;

        InitView(ToastShowType_Infor);
    }
    private void InitView(int ToastShowType){
//        //获取系统的LayoutInflater
//        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.necly_toast_layout,null);
        if (m_ToastShowType == ToastShowType)
            return;

        m_ToastShowType = ToastShowType;
        View view = LayoutInflater.from(mContext).inflate(R.layout.necly_toast_layout, null);
        TxtMessage = view.findViewById(R.id.TxtMessage);

        if (m_ToastShowType == ToastShowType_Error){
            TxtMessage.setBackgroundResource(R.drawable.necly_toast_bg_error);
        }
        else if (m_ToastShowType == ToastShowType_warning){
            TxtMessage.setBackgroundResource(R.drawable.necly_toast_bg_warning);
        }
        else {
            TxtMessage.setBackgroundResource(R.drawable.necly_toast_bg_infor);
        }

        InitToast(view);
    }
    private void InitToast(View view){
        //实例化toast
        mToast = new Toast(mContext);
        mToast.setView(view);
        mToast.setDuration(m_ShowTime);
    //    mToast.setGravity(Gravity.CENTER,0,0);
    //    mToast.show();
    }

    public void setDuration(int Duration){
        m_ShowTime = Duration;
    }

    public void ShowMsg(String ShowMsg) {
        ShowMsg(ShowMsg,m_ShowTime,m_ToastShowType);
    }
    public void ShowMsg(String ShowMsg,int ToastShowType) {
        ShowMsg(ShowMsg,m_ShowTime,ToastShowType);
    }
    public void ShowMsg(String ShowMsg,int ShowTime,int ToastShowType) {
        setDuration(ShowTime);

        InitView(ToastShowType);
        TxtMessage.setText(ShowMsg);

        mToast.setDuration(m_ShowTime);
        mToast.show();
    }

}
