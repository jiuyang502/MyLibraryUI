package com.pannygirlstudio.necly.uilibrary.View;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pannygirlstudio.necly.uilibrary.R;

import java.util.ArrayList;

public class Necly_ShowView_ListItem_Single extends LinearLayout implements View.OnClickListener {

    String Tags = "Necly_ShowView_ListItem_Single：";

    private static final int MessageType_ReloadData       = 20001;

    public static final int DataShowType_NameValue        = 0;
    public static final int DataShowType_OnlyValue        = 1;

    private LinearLayout My_LLayout_Data;
    private TextView TxtDataName;
    private TextView TxtDataValue;
    private ImageView IvIcon;

    private Context mContext;
    private String m_DataNameStr;
    private String m_DataValueStr;
    private int m_DataShowType;
    private int m_ImageResID_IvIcon ;

    private int m_Index_Selected;
    private ArrayList<String> m_ListStr_Item;

    private int m_Visibility_Title;
    private int m_Visibility_Divider;
    private int m_ButtonType;
    private boolean m_IsCanceledOnTouchOutside;
    private float m_WidthFactor;

    private int m_ImageResID_Selected   ;
    private int m_ImageResID_UnSelected ;
    private ColorStateList m_ColorSL_TextView;
    private boolean m_ShowRadioButton;



    private MyHandler myHandler = new MyHandler();

    public Necly_ShowView_ListItem_Single(Context context) {
        this(context, null);
    }
    public Necly_ShowView_ListItem_Single(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public Necly_ShowView_ListItem_Single(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        InitView();
        InitEvent();
        InitData();
    }

    private void InitView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.necly_show_view_list_item_single_layout,null);

        My_LLayout_Data   = v.findViewById(R.id.My_LLayout_Data);
        TxtDataName        = v.findViewById(R.id.TxtDataName);
        TxtDataValue       = v.findViewById(R.id.TxtDataValue);
        IvIcon              = v.findViewById(R.id.IvIcon);

        addView(v, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }
    private void InitEvent(){
        My_LLayout_Data.setOnClickListener(this);
        TxtDataName .setOnClickListener(this);
        TxtDataValue.setOnClickListener(this);
        IvIcon       .setOnClickListener(this);
    }
    private void InitData(){
        m_DataNameStr       = "数据名称";
        m_DataValueStr      = "数据值";
        m_DataShowType      = DataShowType_NameValue;
        m_ImageResID_IvIcon = R.drawable.ic_arrow_right_gray_24dp;

        m_Index_Selected     = -1;
        m_ListStr_Item       = new ArrayList<>();

        m_Visibility_Title      = View.VISIBLE;
        m_Visibility_Divider    = View.VISIBLE;
        m_ButtonType             = 2;                                   //ButtonType_OKCancel = 2

        m_IsCanceledOnTouchOutside = true;
        m_WidthFactor   = 0.8f;

        m_ImageResID_Selected   = -1;
        m_ImageResID_UnSelected = -1;
        //m_ColorSL_TextView;

        m_ShowRadioButton = true;
    }

    public void SetTextSize_DataName(int spSize){
        TxtDataName.setTextSize((spSize));
    }
    public void SetTextSize_DataValue(int spSize){
        TxtDataValue.setTextSize((spSize));
    }
    public void SetDataShowType(int DataShowType){
        m_DataShowType = DataShowType;

        if (m_DataShowType != DataShowType_NameValue)
            m_DataShowType = DataShowType_OnlyValue;
    }
    public void SetDataName(String DataName){
        m_DataNameStr = DataName;
    }
    public void SetImageResID_IvIcon(int ResID){
        m_ImageResID_IvIcon = ResID;
    }
    public void SetListItem(int CurIndex,ArrayList<String> ListStr_Item){
        if (ListStr_Item == null ) {
            Log.i(Tags,"SetListItem：ListStr_Item is null ~!");
            return;
        }
        if ( ListStr_Item.size()<1) {
            Log.i(Tags,"SetListItem：ListStr_Item: size is 0 ~!");
            return;
        }

        m_Index_Selected = CurIndex;
        m_ListStr_Item   = ListStr_Item;

        if (m_Index_Selected<0 ||m_Index_Selected >= m_ListStr_Item.size()){
            m_Index_Selected = 0;
        }
        try {
            m_DataValueStr = m_ListStr_Item.get(m_Index_Selected);
        }
        catch (Exception ee){
            m_DataValueStr = "Null";
            Log.i(Tags,"SetListItem：DataStr Not Found! index: " + m_Index_Selected);
        }
    }
    public void LoadData(){
        DoHandle_ReloadData();
    }

    public void SetVisibility_Title(int visibility){
        m_Visibility_Title = visibility;
    }
    public void SetVisibility_Divider(int visibility){
        m_Visibility_Divider = visibility;
    }
    public void SetButtonType(int ButtonType){
        m_ButtonType = ButtonType;
    }

    public void SetCanceledOnTouchOutside(boolean enabled){
        m_IsCanceledOnTouchOutside = enabled;
    }
    public void SetLayoutWidthFactor(float WidthFactor){
        m_WidthFactor = WidthFactor;
    }

    public void SetImageResID(int ResID_Selected,int ResID_UnSelected) {
        m_ImageResID_Selected      = ResID_Selected;
        m_ImageResID_UnSelected    = ResID_UnSelected;
    }
    public void SetColorStateList_TextView(ColorStateList colorStateList) {
        if (colorStateList == null)
            return;

        m_ColorSL_TextView = colorStateList;
    }
    public void SetShowRadioButton(boolean IsShow){
        m_ShowRadioButton = IsShow;
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

        if (m_DataShowType == DataShowType_NameValue) {
            TxtDataValue.setVisibility(View.VISIBLE);
            TxtDataName.setText(m_DataNameStr);
            TxtDataValue.setText(m_DataValueStr);
        }
        else {
            TxtDataValue.setVisibility(View.GONE);
            TxtDataName.setText(m_DataValueStr);
        }

        if (m_ImageResID_IvIcon != -1){
            IvIcon.setVisibility(View.VISIBLE);
            IvIcon.setImageResource(m_ImageResID_IvIcon);
        }
        else {
            IvIcon.setVisibility(View.GONE);
        }
    }

    private void OnDataSelected(){
        Necly_Dialog_ListItem_Single TempDialog = new Necly_Dialog_ListItem_Single(mContext);
        TempDialog.SetListItem(m_Index_Selected,m_ListStr_Item);
        TempDialog.SetTitleStr(m_DataNameStr);

        TempDialog.SetVisibility_Title(m_Visibility_Title);
        TempDialog.SetVisibility_Divider(m_Visibility_Divider);
        TempDialog.SetButtonType(m_ButtonType);

        TempDialog.SetCanceledOnTouchOutside(m_IsCanceledOnTouchOutside);
        TempDialog.SetLayoutWidthFactor(m_WidthFactor);

        TempDialog.SetImageResID(m_ImageResID_Selected,m_ImageResID_UnSelected);
        TempDialog.SetColorStateList_TextView(m_ColorSL_TextView);
        TempDialog.SetShowRadioButton(m_ShowRadioButton);

        TempDialog.setOnListener_Necly_Dialog_ListItem_Single(new Necly_Dialog_ListItem_Single.OnListener_Necly_Dialog_ListItem_Single() {
            @Override
            public void onBtnClick_BtnNeutral() {
                SendBtnNeutralClick_ToParent();
            }
            @Override
            public void onBtnClick_BtnCancel() {
                SendBtnCancelClick_ToParent();
            }
            @Override
            public void onBtnClick_BtnOK(int Index_Selected, String ItemName_Selected) {
                m_Index_Selected = Index_Selected;
                try {
                    m_DataValueStr = m_ListStr_Item.get(m_Index_Selected);
                }
                catch (Exception ee){
                    m_DataValueStr = "Null";
                    Log.i(Tags,"SetListItem：DataStr Not Found! index: " + m_Index_Selected);
                }
                DoHandle_ReloadData();

                SendBtnOKClick_ToParent(Index_Selected,ItemName_Selected);
            }
        });
        TempDialog.ShowDialog();
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

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.My_LLayout_Data || i == R.id.TxtDataName || i == R.id.TxtDataValue || i == R.id.IvIcon) {
            OnDataSelected();
        }
    }


    public void SendBtnNeutralClick_ToParent() {
        if (onListener_Necly_ShowView_ListItem_Single == null)
            return;

        onListener_Necly_ShowView_ListItem_Single.onBtnClick_BtnNeutral();
    }
    public void SendBtnCancelClick_ToParent() {
        if (onListener_Necly_ShowView_ListItem_Single == null)
            return;

        onListener_Necly_ShowView_ListItem_Single.onBtnClick_BtnCancel();
    }
    public void SendBtnOKClick_ToParent(int Index_Selected,String ItemName_Selected) {
        if (onListener_Necly_ShowView_ListItem_Single == null)
            return;

        onListener_Necly_ShowView_ListItem_Single.onBtnClick_BtnOK(Index_Selected,ItemName_Selected);
    }

    public OnListener_Necly_ShowView_ListItem_Single onListener_Necly_ShowView_ListItem_Single = null;
    public interface OnListener_Necly_ShowView_ListItem_Single {
        void onBtnClick_BtnNeutral();
        void onBtnClick_BtnCancel();
        void onBtnClick_BtnOK(int Index_Selected, String ItemName_Selected);
    }
    public void setOnListener_Necly_ShowView_ListItem_Single(OnListener_Necly_ShowView_ListItem_Single Listener_Necly_ShowView_ListItem_Single) {
        onListener_Necly_ShowView_ListItem_Single = Listener_Necly_ShowView_ListItem_Single;
    }
}
