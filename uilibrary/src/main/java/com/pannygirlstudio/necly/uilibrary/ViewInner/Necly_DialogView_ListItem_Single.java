package com.pannygirlstudio.necly.uilibrary.ViewInner;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pannygirlstudio.necly.uilibrary.Adapter.Necly_Adapter_ListItem_Single;
import com.pannygirlstudio.necly.uilibrary.DataModel.Necly_ContentItem_String;
import com.pannygirlstudio.necly.uilibrary.R;

import java.util.ArrayList;


public class Necly_DialogView_ListItem_Single extends LinearLayout implements View.OnClickListener, AdapterView.OnItemClickListener {

    String Tags = "Necly_DialogView_ListItem_Single：";

    private static final int MessageType_ReloadData       = 20001;

    public static final int ButtonType_None               = 0;
    public static final int ButtonType_OK                 = 1;
    public static final int ButtonType_OKCancel          = 2;
    public static final int ButtonType_All                = 3;

    private LinearLayout MyLLayout_Title;
    private TextView Label_Title;

    private ListView LvItemList;
    private View MyDivider1;

    private LinearLayout MyLLayout_Button;
    private Button BtnNeutral;
    private Button BtnCancel;
    private Button BtnOK;

    private int m_ButtonType = ButtonType_OKCancel;

    private Context mContext;
    private int m_Index_Selected = -1;
    private ArrayList<String> m_ListStr_Item = new ArrayList<>();

    private int m_ImageResID_Selected   = -1;
    private int m_ImageResID_UnSelected = -1;
    private ColorStateList m_ColorSL_TextView;
    private boolean m_ShowRadioButton = true;

    private Necly_Adapter_ListItem_Single MyAdapter;
    private ArrayList<Necly_ContentItem_String> m_List_ContentItem_String = new ArrayList<>();

    private MyHandler myHandler = new MyHandler();


    public Necly_DialogView_ListItem_Single(Context context) {
        this(context, null);
    }
    public Necly_DialogView_ListItem_Single(Context context, AttributeSet attrs) {
        super(context);

        mContext = context;
        InitView();
        InitEvent();
    }
    private void InitView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.necly_dialog_list_item_single_layout,null);

        MyLLayout_Title    = v.findViewById(R.id.MyLLayout_Title);
        Label_Title         = v.findViewById(R.id.Label_Title);

        LvItemList          = v.findViewById(R.id.LvItemList);
        MyDivider1          = v.findViewById(R.id.MyDivider1);

        MyLLayout_Button   = v.findViewById(R.id.MyLLayout_Button);
        BtnNeutral          = v.findViewById(R.id.BtnNeutral);
        BtnCancel           = v.findViewById(R.id.BtnCancel);
        BtnOK                = v.findViewById(R.id.BtnOK);

        addView(v, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }
    private void InitEvent(){
        BtnNeutral .setOnClickListener(this);
        BtnCancel  .setOnClickListener(this);
        BtnOK       .setOnClickListener(this);
        LvItemList .setOnItemClickListener(this);
    }

    public void SetListItem(int CurIndex,ArrayList<String> ListStr_Item){
        m_Index_Selected = CurIndex;
        m_ListStr_Item   = ListStr_Item;
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

    public void SetButtonType(int ButtonType){

        int visibility = View.VISIBLE;
        int TempHeightDp = 38;
        if (ButtonType == ButtonType_None){
            visibility = View.INVISIBLE;
            TempHeightDp = 10;
        }

        MyLLayout_Button.setVisibility(visibility);
        LayoutParams TempLP = (LayoutParams) MyLLayout_Button.getLayoutParams();
        TempLP.height = ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, TempHeightDp, getResources().getDisplayMetrics()));
        MyLLayout_Button.setLayoutParams(TempLP);
        MyLLayout_Button.invalidate();

        if (ButtonType == ButtonType_None) {
            m_ButtonType = ButtonType;
            MyDivider1.setVisibility(View.INVISIBLE);
            BtnNeutral.setVisibility(View.GONE);
            BtnCancel .setVisibility(View.GONE);
            BtnOK      .setVisibility(View.GONE);
        }
        else if (ButtonType == ButtonType_OK) {
            m_ButtonType = ButtonType;
            BtnNeutral.setVisibility(View.GONE);
            BtnCancel .setVisibility(View.GONE);
            BtnOK      .setVisibility(View.VISIBLE);
        }
        else if (ButtonType == ButtonType_OKCancel) {
            m_ButtonType = ButtonType;
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
    }
    @Deprecated
    public void SetButtonType2(int ButtonType){
        if (ButtonType == ButtonType_None) {
            m_ButtonType = ButtonType;
            MyDivider1.setVisibility(View.INVISIBLE);
            BtnNeutral.setVisibility(View.INVISIBLE);
            BtnCancel .setVisibility(View.INVISIBLE);
            BtnOK      .setVisibility(View.INVISIBLE);
        }
        else if (ButtonType == ButtonType_OK) {
            m_ButtonType = ButtonType;
            BtnNeutral.setVisibility(View.INVISIBLE);
            BtnCancel .setVisibility(View.INVISIBLE);
            BtnOK      .setVisibility(View.VISIBLE);
        }
        else if (ButtonType == ButtonType_OKCancel) {
            m_ButtonType = ButtonType;
            BtnNeutral.setVisibility(View.INVISIBLE);
            BtnCancel .setVisibility(View.VISIBLE);
            BtnOK      .setVisibility(View.VISIBLE);
        }
        else  {
            m_ButtonType = ButtonType_All;
            BtnNeutral.setVisibility(View.VISIBLE);
            BtnCancel .setVisibility(View.VISIBLE);
            BtnOK      .setVisibility(View.VISIBLE);
        }
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
        if (m_ListStr_Item == null ) {
            Log.i(Tags,"ReloadData：ListStr_Item is null ~!");
            return;
        }
        if ( m_ListStr_Item.size()<1) {
            Log.i(Tags,"ReloadData：ListStr_Item: size is 0 ~!");
            return;
        }

        for (int i=0;i<m_ListStr_Item.size();i++) {
            Necly_ContentItem_String m_TempItem = new Necly_ContentItem_String(m_ListStr_Item.get(i));
            m_List_ContentItem_String.add(m_TempItem);
        }

        MyAdapter = new Necly_Adapter_ListItem_Single(mContext, m_List_ContentItem_String);
        MyAdapter.setImageResID(m_ImageResID_Selected,m_ImageResID_UnSelected);
        MyAdapter.setColorStateList_TextView(m_ColorSL_TextView);
        MyAdapter.setShowRadioButton(m_ShowRadioButton);
        LvItemList.setAdapter(MyAdapter);

        if (m_Index_Selected<0 || m_Index_Selected>= m_List_ContentItem_String.size()) {
            m_Index_Selected = 0;
        }

        MyAdapter.setIndex_CurrentItem(m_Index_Selected);									        //改变选中状态
        MyAdapter.notifyDataSetChanged();
        LvItemList.setSelection(m_Index_Selected);
    }


    private void OnBtnNeutral(){
        SendBtnNeutralClick_ToParent();
    }
    private void OnBtnCancel(){
        SendBtnCancelClick_ToParent();
    }
    private void OnBtnOK(){
        if (m_ListStr_Item == null ) {
            Log.i(Tags,"OnBtnOK：ListStr_Item is null ~!");
            SendBtnCancelClick_ToParent();
            return;
        }
        if (m_ListStr_Item.size()<1){                                                            // 名称列表为空，返回Cancel
            Log.i(Tags,"OnBtnOK：ListStr_Item: size is 0 ~!");
            SendBtnCancelClick_ToParent();
            return;
        }

        if (m_Index_Selected<0 || m_Index_Selected>= m_ListStr_Item.size()) {              //名称列表不为空，但是索引超出范围，发送第一项；
            m_Index_Selected = 0;
        }

        SendBtnOKClick_ToParent(m_Index_Selected,m_ListStr_Item.get(m_Index_Selected));
    }
    private void OnItemSelected(int Index){
        CalculateIndex_Selected(Index);
        if (MyAdapter != null){
            if (m_Index_Selected>= 0) {
                MyAdapter.setIndex_CurrentItem(m_Index_Selected);                               //改变选中状态
                MyAdapter.notifyDataSetChanged();

                if (m_ButtonType == ButtonType_None){
                    SendBtnOKClick_ToParent(m_Index_Selected,m_ListStr_Item.get(m_Index_Selected));
                }
            }
            //LvItemList.setSelection(position);
        }
    }

    private void CalculateIndex_Selected(int Index) {
        m_Index_Selected = Index;

        if (Index<0 || Index >= m_ListStr_Item.size()) {
            m_Index_Selected = 0;
            Log.i(Tags, "m_Index_Selected: Out of Range ~! ");
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.BtnNeutral) {
            OnBtnNeutral();
        }
        else if (i == R.id.BtnCancel) {
            OnBtnCancel();
        }
        else if (i == R.id.BtnOK) {
            OnBtnOK();
        }
        else {
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int i = parent.getId();
        if (i == R.id.LvItemList) {
            OnItemSelected(position);
        }
        else {
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
        if (onListener_Necly_DialogView_ListItem_Single == null)
            return;

        onListener_Necly_DialogView_ListItem_Single.onBtnClick_BtnNeutral();
    }
    public void SendBtnCancelClick_ToParent() {
        if (onListener_Necly_DialogView_ListItem_Single == null)
            return;

        onListener_Necly_DialogView_ListItem_Single.onBtnClick_BtnCancel();
    }
    public void SendBtnOKClick_ToParent(int Index_Selected,String ItemName_Selected) {
        if (onListener_Necly_DialogView_ListItem_Single == null)
            return;

        onListener_Necly_DialogView_ListItem_Single.onBtnClick_BtnOK(Index_Selected,ItemName_Selected);
    }

    public OnListener_Necly_DialogView_ListItem_Single onListener_Necly_DialogView_ListItem_Single = null;
    public interface OnListener_Necly_DialogView_ListItem_Single {
        void onBtnClick_BtnNeutral();
        void onBtnClick_BtnCancel();
        void onBtnClick_BtnOK(int Index_Selected, String ItemName_Selected);
    }
    public void setOnListener_Necly_DialogView_ListItem_Single(OnListener_Necly_DialogView_ListItem_Single Listener_Necly_DialogView_ListItem_Single) {
        onListener_Necly_DialogView_ListItem_Single = Listener_Necly_DialogView_ListItem_Single;
    }
}
