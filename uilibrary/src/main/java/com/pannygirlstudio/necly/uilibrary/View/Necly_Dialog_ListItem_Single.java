package com.pannygirlstudio.necly.uilibrary.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.LinearLayout;

import com.pannygirlstudio.necly.uitest001.ViewInner.Necly_DialogView_ListItem_Single;

import java.util.ArrayList;


public class Necly_Dialog_ListItem_Single{

    String Tags = "Necly_Dialog_ListItem_Single：";

    private Context mContext;
    private int m_Index_Selected;
    private ArrayList<String> m_ListStr_Item;
    private String m_TitleStr;
    private int m_Visibility_Title;
    private int m_Visibility_Divider;
    private int m_ButtonType;
    private boolean m_IsCanceledOnTouchOutside;
    private float m_WidthFactor;
    private int m_ImageResID_Selected   ;
    private int m_ImageResID_UnSelected ;
    private ColorStateList m_ColorSL_TextView;
    private boolean m_ShowRadioButton;

    private AlertDialog m_Necly_Dialog;


    public Necly_Dialog_ListItem_Single(Context context){
        mContext = context;
        InitData();
    }
    private void InitData(){
        m_Index_Selected     = -1;
        m_ListStr_Item       = new ArrayList<>();
        m_TitleStr            = "数据选择";
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

    public void SetListItem(int CurIndex,ArrayList<String> ListStr_Item){
        m_Index_Selected = CurIndex;
        m_ListStr_Item   = ListStr_Item;

        if (m_Index_Selected<0 ||m_Index_Selected >= m_ListStr_Item.size()){
            m_Index_Selected = 0;
        }
    }
//    public void LoadData(){
//        DoHandle_ReloadData();
//    }

    public void SetTitleStr(String TitleStr){
        m_TitleStr = TitleStr;
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

    public void ShowDialog(){
        Necly_DialogView_ListItem_Single m_TempView = new Necly_DialogView_ListItem_Single(mContext);

        m_TempView.SetListItem(m_Index_Selected,m_ListStr_Item);
        m_TempView.SetTitleStr(m_TitleStr);
        m_TempView.SetVisibility_Title(m_Visibility_Title);
        m_TempView.SetVisibility_Divider(m_Visibility_Divider);
        m_TempView.SetButtonType(m_ButtonType);
        m_TempView.SetImageResID(m_ImageResID_Selected,m_ImageResID_UnSelected);
        m_TempView.SetColorStateList_TextView(m_ColorSL_TextView);
        m_TempView.SetShowRadioButton(m_ShowRadioButton);

        m_TempView.LoadData();

        m_TempView.setOnListener_Necly_DialogView_ListItem_Single(new Necly_DialogView_ListItem_Single.OnListener_Necly_DialogView_ListItem_Single() {
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
            public void onBtnClick_BtnOK(int Index_Selected, String ItemName_Selected) {
                SendBtnOKClick_ToParent(Index_Selected,ItemName_Selected);
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
        if (onListener_Necly_Dialog_ListItem_Single == null)
            return;

        onListener_Necly_Dialog_ListItem_Single.onBtnClick_BtnNeutral();
    }
    public void SendBtnCancelClick_ToParent() {
        if (onListener_Necly_Dialog_ListItem_Single == null)
            return;

        onListener_Necly_Dialog_ListItem_Single.onBtnClick_BtnCancel();
    }
    public void SendBtnOKClick_ToParent(int Index_Selected,String ItemName_Selected) {
        if (onListener_Necly_Dialog_ListItem_Single == null)
            return;

        onListener_Necly_Dialog_ListItem_Single.onBtnClick_BtnOK(Index_Selected,ItemName_Selected);
    }

    public OnListener_Necly_Dialog_ListItem_Single onListener_Necly_Dialog_ListItem_Single = null;
    public interface OnListener_Necly_Dialog_ListItem_Single {
        void onBtnClick_BtnNeutral();
        void onBtnClick_BtnCancel();
        void onBtnClick_BtnOK(int Index_Selected, String ItemName_Selected);
    }
    public void setOnListener_Necly_Dialog_ListItem_Single(OnListener_Necly_Dialog_ListItem_Single Listener_Necly_Dialog_ListItem_Single) {
        onListener_Necly_Dialog_ListItem_Single = Listener_Necly_Dialog_ListItem_Single;
    }

}
