package com.pannygirlstudio.necly.uilibrary.ViewInner;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pannygirlstudio.necly.uilibrary.R;
import com.pannygirlstudio.necly.uilibrary.View.Necly_Button;

import static com.pannygirlstudio.necly.uilibrary.Utils.DataType.AlertDialogType_Dialog;
import static com.pannygirlstudio.necly.uilibrary.Utils.DataType.AlertDialogType_Simple;
import static com.pannygirlstudio.necly.uilibrary.Utils.DataType.AlertMessageType_Infor;
import static com.pannygirlstudio.necly.uilibrary.Utils.DataType.ButtonType_All;
import static com.pannygirlstudio.necly.uilibrary.Utils.DataType.ButtonType_None;
import static com.pannygirlstudio.necly.uilibrary.Utils.DataType.ButtonType_OK;
import static com.pannygirlstudio.necly.uilibrary.Utils.DataType.ButtonType_OKCancel;
import static com.pannygirlstudio.necly.uilibrary.Utils.DataType.ToastShowType_Error;
import static com.pannygirlstudio.necly.uilibrary.Utils.DataType.ToastShowType_warning;


public class Necly_DialogView_Alert extends LinearLayout implements View.OnClickListener {

    String Tags = "Necly_DialogView_Alert：";

    private static final int MessageType_ReloadData       = 20001;


    private LinearLayout MyLLayout_Title;
    private TextView Label_Title;

    private TextView TxtMsg;
    private View MyDivider1;

    private LinearLayout MyLLayout_Button;
    private Necly_Button BtnNeutral;
    private Necly_Button BtnCancel;
    private Necly_Button BtnOK;


    private Context mContext;
    private String m_Msg;
    private int m_AlertMsgType;
    private int m_ButtonType ;
    private int m_AlertDialogType ;

    private MyHandler myHandler = new MyHandler();


    public Necly_DialogView_Alert(Context context, int AlertDialogType) {
        super(context);

        mContext = context;
        m_AlertDialogType = AlertDialogType;
        InitView();
        InitData();
        InitEvent();
    }
    public Necly_DialogView_Alert(Context context) {
        this(context, null);
    }
    public Necly_DialogView_Alert(Context context, AttributeSet attrs) {
        super(context);

        mContext = context;
        m_AlertDialogType = AlertDialogType_Simple;
        InitView();
        InitData();
        InitEvent();
    }
    private void InitView() {
        View v ;
        if (m_AlertDialogType == AlertDialogType_Simple) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.necly_dialog_alert_simple_layout, null);
        }
        else {
            m_AlertDialogType = AlertDialogType_Dialog;
            v = LayoutInflater.from(getContext()).inflate(R.layout.necly_dialog_alert_dialog_layout, null);
        }

        MyLLayout_Title    = v.findViewById(R.id.MyLLayout_Title);
        Label_Title         = v.findViewById(R.id.Label_Title);

        TxtMsg               = v.findViewById(R.id.TxtMsg);
        MyDivider1          = v.findViewById(R.id.MyDivider1);

        MyLLayout_Button   = v.findViewById(R.id.MyLLayout_Button);
        BtnNeutral          = v.findViewById(R.id.BtnNeutral);
        BtnCancel           = v.findViewById(R.id.BtnCancel);
        BtnOK                = v.findViewById(R.id.BtnOK);

        addView(v, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }
    private void InitData(){
        m_Msg           = "";
        m_AlertMsgType = AlertMessageType_Infor;
        m_ButtonType   = ButtonType_OKCancel;
    }
    private void InitEvent(){
        BtnNeutral .setOnClickListener(this);
        BtnCancel  .setOnClickListener(this);
        BtnOK       .setOnClickListener(this);
    }


    public void LoadData(){
        DoHandle_ReloadData();
    }

    public void SetTitleStr(String TitleStr){
        Label_Title.setText(TitleStr);
    }
    public void SetVisibility_Title(int visibility){

        int TempHeightDp = 45;

        if (visibility != View.VISIBLE) {
            visibility = View.INVISIBLE;
            TempHeightDp = 10;
        }

        MyLLayout_Title.setVisibility(visibility);

        LayoutParams TempLP = (LayoutParams) MyLLayout_Title.getLayoutParams();
        TempLP.height = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, TempHeightDp, getResources().getDisplayMetrics()));
        MyLLayout_Title.setLayoutParams(TempLP);
        MyLLayout_Title.invalidate();

    }
    public void SetVisibility_Divider(int visibility){
        MyDivider1.setVisibility(visibility);
    }

    public void SetTextSize_Msg(int spSize){
        TxtMsg.setTextSize(spSize);
    }
    public void SetTextColor_Msg(int color){
        TxtMsg.setTextColor(color);
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
            BtnOK.setText(ButtonText);
        }
    }
    public void SetButtonText_Cancel(String ButtonText){
        if (!ButtonText.equals("")) {
            BtnCancel.setText(ButtonText);
        }
    }
    public void SetButtonText_Neutral(String ButtonText){
        if (!ButtonText.equals("")) {
            BtnNeutral.setText(ButtonText);
        }
    }


    private void DoHandle_ReloadData(){
        Message msg = new Message();
        msg.what = MessageType_ReloadData;

        Bundle bundle = new Bundle();
        bundle.clear();

        msg.setData(bundle);
        myHandler.sendMessage(msg);
    }

    private void Handle_ReloadData(){
        TxtMsg.setText(m_Msg);

        if (m_AlertDialogType == AlertDialogType_Simple){                                    // 简单对话框界面
            if (m_AlertMsgType == ToastShowType_Error){                                       // 根据消息类型设置按钮背景颜色
                BtnOK.setNormal_color (Color.parseColor("#FF584f"));
                BtnOK.setPressed_color(Color.parseColor("#BA2835"));
            }
            else if (m_AlertMsgType == ToastShowType_warning){
                BtnOK.setNormal_color (Color.parseColor("#536DFE"));
                BtnOK.setPressed_color(Color.parseColor("#3F51B5"));
            }
            else {
                BtnOK.setNormal_color (Color.parseColor("#1DBF97"));
                BtnOK.setPressed_color(Color.parseColor("#068043"));
            }

            if (m_ButtonType == ButtonType_None) {                                             // 根据按钮类型设置按钮背景圆角
            }
            else if (m_ButtonType == ButtonType_OK) {
                BtnOK.setRadius_size_left_bottom(15);
                BtnOK.setRadius_size_right_bottom(15);
            }
            else if (m_ButtonType == ButtonType_OKCancel) {
                BtnCancel.setRadius_size_left_bottom(15);
                BtnOK.setRadius_size_right_bottom(15);
            }
            else  {
                BtnNeutral.setRadius_size_left_bottom(15);
                BtnCancel.SetRadii(0,0,0,0);
                BtnOK.setRadius_size_right_bottom(15);
            }

            BtnOK.ReLoadView();
            BtnCancel.ReLoadView();
            BtnNeutral.ReLoadView();
        }
        else {                                                                                     // 普通对话框界面
//            if (m_AlertMsgType == ToastShowType_Error){                                       // 根据消息类型设置按钮背景颜色
//                BtnOK.setNormal_color (Color.parseColor("#FF584f"));
//                BtnOK.setPressed_color(Color.parseColor("#BA2835"));
//            }
//            else if (m_AlertMsgType == ToastShowType_warning){
//                BtnOK.setNormal_color (Color.parseColor("#536DFE"));
//                BtnOK.setPressed_color(Color.parseColor("#3F51B5"));
//            }
//            else {
//                BtnOK.setNormal_color (Color.parseColor("#1DBF97"));
//                BtnOK.setPressed_color(Color.parseColor("#068043"));
//            }
//
//            BtnOK.ReLoadView();
        }

 //region 设置按钮的可见性
        int visibility = View.VISIBLE;
        int TempHeightDp = 38;
        if (m_AlertDialogType == AlertDialogType_Simple){
            TempHeightDp = 45;
        }
        if (m_ButtonType == ButtonType_None){
            visibility = View.INVISIBLE;
            TempHeightDp = 10;
        }

        MyLLayout_Button.setVisibility(visibility);
        LayoutParams TempLP = (LayoutParams) MyLLayout_Button.getLayoutParams();
        TempLP.height = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, TempHeightDp, getResources().getDisplayMetrics()));
        MyLLayout_Button.setLayoutParams(TempLP);
        MyLLayout_Button.invalidate();

        if (m_ButtonType == ButtonType_None) {
            MyDivider1.setVisibility(View.INVISIBLE);
            BtnNeutral.setVisibility(View.GONE);
            BtnCancel .setVisibility(View.GONE);
            BtnOK      .setVisibility(View.GONE);
        }
        else if (m_ButtonType == ButtonType_OK) {
            BtnNeutral.setVisibility(View.GONE);
            BtnCancel .setVisibility(View.GONE);
            BtnOK      .setVisibility(View.VISIBLE);
        }
        else if (m_ButtonType == ButtonType_OKCancel) {
            BtnNeutral.setVisibility(View.GONE);
            BtnCancel .setVisibility(View.VISIBLE);
            BtnOK      .setVisibility(View.VISIBLE);
        }
        else  {
            m_ButtonType = ButtonType_All;
            BtnNeutral.setVisibility(View.VISIBLE);
            BtnCancel .setVisibility(View.VISIBLE);
            BtnOK      .setVisibility(View.VISIBLE);
        }
// endregion

        if (m_AlertMsgType == ToastShowType_Error){                                           // 根据消息类型设置标题背景颜色
            Label_Title.setBackgroundResource(R.drawable.necly_shape_corner_textview_red);
        }
        else if (m_AlertMsgType == ToastShowType_warning){
            Label_Title.setBackgroundResource(R.drawable.necly_shape_corner_textview_blue);
        }
        else {
            Label_Title.setBackgroundResource(R.drawable.necly_shape_corner_textview_green);
            m_AlertMsgType = AlertMessageType_Infor;
        }
    }


    private void OnBtnNeutral(){
        SendBtnNeutralClick_ToParent();
    }
    private void OnBtnCancel(){
        SendBtnCancelClick_ToParent();
    }
    private void OnBtnOK(){
        SendBtnOKClick_ToParent();
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.BtnNeutral) {
            OnBtnNeutral();
        } else if (i == R.id.BtnCancel) {
            OnBtnCancel();
        } else if (i == R.id.BtnOK) {
            OnBtnOK();
        } else {
        }
    }


    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            switch(msg.what) {
                case MessageType_ReloadData:{
                    Handle_ReloadData();
                    break;
                }
                default:
                    break;
            }
        }
    }

    public void SendBtnNeutralClick_ToParent() {
        if (onListener_Necly_DialogView_Alert == null)
            return;

        onListener_Necly_DialogView_Alert.onBtnClick_BtnNeutral();
    }
    public void SendBtnCancelClick_ToParent() {
        if (onListener_Necly_DialogView_Alert == null)
            return;

        onListener_Necly_DialogView_Alert.onBtnClick_BtnCancel();
    }
    public void SendBtnOKClick_ToParent() {
        if (onListener_Necly_DialogView_Alert == null)
            return;

        onListener_Necly_DialogView_Alert.onBtnClick_BtnOK();
    }

    public OnListener_Necly_DialogView_Alert onListener_Necly_DialogView_Alert = null;
    public interface OnListener_Necly_DialogView_Alert {
        void onBtnClick_BtnNeutral();
        void onBtnClick_BtnCancel();
        void onBtnClick_BtnOK();
    }
    public void setOnListener_Necly_DialogView_Alert(OnListener_Necly_DialogView_Alert Listener_Necly_DialogView_Alert) {
        onListener_Necly_DialogView_Alert = Listener_Necly_DialogView_Alert;
    }
}
