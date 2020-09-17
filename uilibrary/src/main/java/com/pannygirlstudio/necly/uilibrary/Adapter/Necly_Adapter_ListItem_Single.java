package com.pannygirlstudio.necly.uilibrary.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.pannygirlstudio.necly.uilibrary.DataModel.Necly_ContentItem_String;
import com.pannygirlstudio.necly.uilibrary.R;

import java.util.List;

//import androidx.annotation.NonNull;

public class Necly_Adapter_ListItem_Single extends ArrayAdapter<Necly_ContentItem_String> {

    private final Typeface mTypeFaceLight;
    private final Typeface mTypeFaceRegular;
    private final Typeface mTypeFaceBold;

    private int m_Index_CurrentItem = -1;                                       //当前Item被点击的位置
    private int m_ImageResID_Selected   = -1;
    private int m_ImageResID_UnSelected = -1;
    private ColorStateList m_ColorSL_TextView;
    private boolean m_ShowRadioButton = true;

    public Necly_Adapter_ListItem_Single(Context context, List<Necly_ContentItem_String> objects) {
        super(context, 0, objects);

        mTypeFaceLight   = Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf");
        mTypeFaceRegular = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
        mTypeFaceBold    = Typeface.createFromAsset(context.getAssets(), "OpenSans-Bold.ttf");

        m_ColorSL_TextView = CreateColorStateList_TextView(Color.parseColor("#36424A"),Color.parseColor("#536DFE"),Color.GRAY);
    }

    public void setIndex_CurrentItem(int currentItem) {
        this.m_Index_CurrentItem = currentItem;
    }
    public void setImageResID(int ResID_Selected,int ResID_UnSelected) {
        this.m_ImageResID_Selected      = ResID_Selected;
        this.m_ImageResID_UnSelected    = ResID_UnSelected;
    }
    public void setColorStateList_TextView(ColorStateList colorStateList) {
        if (colorStateList == null)
            return;

        m_ColorSL_TextView = colorStateList;
    }
    public void setShowRadioButton(boolean IsShow){
        m_ShowRadioButton = IsShow;
    }

    private ColorStateList CreateColorStateList_TextView(int normalColor, int selectedColor, int disabledColor) {

        int[][] states = new int[3][];                                                             // 状态数组
        states[0] = new int[] {android.R.attr.state_enabled ,android.R.attr.state_selected};    // 选择状态
        states[1] = new int[] {android.R.attr.state_enabled };                                    // 未选择状态
        states[2] = new int[] {-android.R.attr.state_enabled};                                   // 不可用状态

        //状态对应颜色值（按下，默认）
        int[] colors = new int[] { selectedColor, normalColor,disabledColor};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Necly_ContentItem_String Item = getItem(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.necly_list_item_single, null);
        ViewHolder holder = new ViewHolder();
        convertView.setTag(holder);

        holder.ivIcon        = convertView.findViewById(R.id.ivIcon);
        holder.tvItemName   = convertView.findViewById(R.id.tvItemName);
        holder.ChkSelected  = convertView.findViewById(R.id.ChkSelected);

        if (m_ShowRadioButton)
            holder.ChkSelected.setVisibility(View.VISIBLE);
        else
            holder.ChkSelected.setVisibility(View.GONE);

        holder.tvItemName.setTextColor(m_ColorSL_TextView);

        holder.tvItemName  .setTypeface(mTypeFaceLight);
        holder.ChkSelected .setTypeface(mTypeFaceLight);

        if (Item != null) {
            holder.tvItemName.setText(Item.GetItemString());

            if (m_Index_CurrentItem == position) {
                holder.tvItemName  .setTypeface(mTypeFaceBold);
                holder.tvItemName .setSelected(true);                                  //如果被点击，设置当前TextView被选中
                holder.ChkSelected.setChecked(true);

                if (m_ImageResID_Selected != -1)
                    holder.ivIcon.setImageResource(m_ImageResID_Selected);
                else
                    holder.ivIcon.setVisibility(View.GONE);
            }
            else {
                holder.tvItemName .setSelected(false);
                holder.ChkSelected.setChecked(false);

                if (m_ImageResID_UnSelected != -1)
                    holder.ivIcon.setImageResource(m_ImageResID_UnSelected);
                else
                    holder.ivIcon.setVisibility(View.GONE);
            }
        }
        else {
            holder.tvItemName .setText("Null");
            holder.ChkSelected.setChecked(false);

            if (m_ImageResID_UnSelected != -1)
                holder.ivIcon.setImageResource(m_ImageResID_UnSelected);
            else
                holder.ivIcon.setVisibility(View.GONE);
        }

        return convertView;
    }

    private class ViewHolder {                                         //可以加Static改为静态内部类,保证不一直查找此对象(优化)？？？？

        ImageView ivIcon;
        TextView  tvItemName;
        RadioButton ChkSelected;
    }
}
