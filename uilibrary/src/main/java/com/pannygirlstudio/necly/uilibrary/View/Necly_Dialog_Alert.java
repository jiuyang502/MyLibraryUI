package com.pannygirlstudio.necly.uilibrary.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import com.pannygirlstudio.necly.uilibrary.ViewInner.Necly_DialogView_Alert;


public class Necly_Dialog_Alert {
    String Tags = "Necly_Dialog_Alert：";

    private Context mContext;
    private String m_TitleStr;
    private int m_Visibility_Title;
    private int m_Visibility_Divider;

    private int m_TextSize_Msg;
    private int m_TextColor_Msg;

    private String m_Msg;
    private int m_AlertMsgType;

    private int m_ButtonType;
    private int m_AlertDialogType;

    private String m_TextOK;
    private String m_TextCancel;
    private String m_TextNeutral;


    private boolean m_IsCanceledOnTouchOutside;
    private float m_WidthFactor;

    private AlertDialog m_Necly_Dialog;

    public Necly_Dialog_Alert(Context context){
        mContext = context;
        InitData();
    }
    private void InitData(){
        m_TitleStr              = "提示";
        m_Visibility_Title     = View.VISIBLE;
        m_Visibility_Divider   = View.VISIBLE;

        m_TextSize_Msg      = 14;
        m_TextColor_Msg     = Color.parseColor("#FF36424A");

        m_Msg                 = "";
        m_AlertMsgType      = 0;                                    //AlertMessageType_Infor = 0

        m_ButtonType        = 2;                                   //ButtonType_OKCancel = 2
        m_AlertDialogType  = 0;                                    // AlertDialogType_Simple = 0;

        m_TextOK         = "确定";
        m_TextCancel    = "取消";
        m_TextNeutral   = "其他";


        m_IsCanceledOnTouchOutside = true;
        m_WidthFactor       = 0.8f;;

    }

    public void SetTitleStr(String TitleStr){
        m_TitleStr = TitleStr;
    }
    public void SetVisibility_Title(int visibility){
        m_Visibility_Title = visibility;
    }
    public void SetVisibility_Divider(int visibility){
        m_Visibility_Divider = visibility;
    }

    public void SetTextSize_Msg(int spSize){
        m_TextSize_Msg = spSize;
    }
    public void SetTextColor_Msg(int color){
        m_TextColor_Msg = color;
    }

    public void SetMsg(String MsgStr){
        m_Msg = MsgStr;
    }
    public void SetMsg(String MsgStr,int MsgType){
        m_Msg = MsgStr;
        SetAlertMessageType(MsgType);
    }
    public void SetAlertMessageType(int MsgType){
        m_AlertMsgType = MsgType;
    }

    public void SetButtonType(int ButtonType){
        m_ButtonType = ButtonType;
    }
    public void SetAlertDialogType(int AlertDialogType){
        m_AlertDialogType = AlertDialogType;
    }

    public void SetButtonText(String TextOK){
        SetButtonText_OK(TextOK);
    }
    public void SetButtonText(String TextOK,String TextCancel){
        SetButtonText_OK(TextOK);
        SetButtonText_Cancel(TextCancel);
    }
    public void SetButtonText(String TextOK,String TextCancel,String TextNeutral){
        SetButtonText_OK(TextOK);
        SetButtonText_Cancel(TextCancel);
        SetButtonText_Neutral(TextNeutral);
    }

    public void SetButtonText_OK(String ButtonText){
        if (!ButtonText.equals("")) {
            m_TextOK = ButtonText;
        }
    }
    public void SetButtonText_Cancel(String ButtonText){
        if (!ButtonText.equals("")) {
            m_TextCancel = ButtonText;
        }
    }
    public void SetButtonText_Neutral(String ButtonText){
        if (!ButtonText.equals("")) {
            m_TextNeutral = ButtonText;
        }
    }

    public void SetCanceledOnTouchOutside(boolean enabled){
        m_IsCanceledOnTouchOutside = enabled;
    }
    public void SetLayoutWidthFactor(float WidthFactor){
        m_WidthFactor = WidthFactor;
    }

    public void ShowDialog(){
        Necly_DialogView_Alert m_TempView = new Necly_DialogView_Alert(mContext,m_AlertDialogType);

        m_TempView.SetTitleStr(m_TitleStr);
        m_TempView.SetVisibility_Title(m_Visibility_Title);
        m_TempView.SetVisibility_Divider(m_Visibility_Divider);

        m_TempView.SetTextSize_Msg(m_TextSize_Msg);
        m_TempView.SetTextColor_Msg(m_TextColor_Msg);

        m_TempView.SetMsg(m_Msg, m_AlertMsgType);

        m_TempView.SetButtonType(m_ButtonType);
   //     m_TempView.SetAlertDialogType(m_AlertDialogType);
        m_TempView.SetButtonText(m_TextOK, m_TextCancel, m_TextNeutral);

        m_TempView.LoadData();

        m_TempView.setOnListener_Necly_DialogView_Alert(new Necly_DialogView_Alert.OnListener_Necly_DialogView_Alert() {
            @Override
            public void onBtnClick_BtnNeutral() {
                SendBtnNeutralClick_ToParent();
                m_Necly_Dialog.dismiss();
            }
            @Override
            public void onBtnClick_BtnCancel() {
                SendBtnCancelClick_ToParent();
                m_Necly_Dialog.dismiss();
            }
            @Override
            public void onBtnClick_BtnOK() {
                SendBtnOKClick_ToParent();
                m_Necly_Dialog.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(m_TempView);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                SendBtnCancelClick_ToParent();
                m_Necly_Dialog.dismiss();
            }
        });

        m_Necly_Dialog = builder.create();
        m_Necly_Dialog.setCanceledOnTouchOutside(m_IsCanceledOnTouchOutside);
        m_Necly_Dialog.show();

        m_Necly_Dialog.getWindow().setLayout( (int)(getScreenWidth(mContext)*m_WidthFactor), LinearLayout.LayoutParams.WRAP_CONTENT);
        m_Necly_Dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    private static int getScreenHeight(Context context) {                                          // 获取屏幕高度(px)
        return context.getResources().getDisplayMetrics().heightPixels;
    }
    private static int getScreenWidth(Context context) {                                           //获取屏幕宽度(px)
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public void SendBtnNeutralClick_ToParent() {
        if (onListener_Necly_Dialog_Alert == null)
            return;

        onListener_Necly_Dialog_Alert.onBtnClick_BtnNeutral();
    }
    public void SendBtnCancelClick_ToParent() {
        if (onListener_Necly_Dialog_Alert == null)
            return;

        onListener_Necly_Dialog_Alert.onBtnClick_BtnCancel();
    }
    public void SendBtnOKClick_ToParent() {
        if (onListener_Necly_Dialog_Alert == null)
            return;

        onListener_Necly_Dialog_Alert.onBtnClick_BtnOK();
    }

    public OnListener_Necly_Dialog_Alert onListener_Necly_Dialog_Alert = null;
    public interface OnListener_Necly_Dialog_Alert {
        void onBtnClick_BtnNeutral();
        void onBtnClick_BtnCancel();
        void onBtnClick_BtnOK();
    }
    public void setOnListener_Necly_Dialog_Alert(OnListener_Necly_Dialog_Alert Listener_Necly_Dialog_Alert) {
        onListener_Necly_Dialog_Alert = Listener_Necly_Dialog_Alert;
    }
}
