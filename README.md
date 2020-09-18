# MyLibraryUI
Necly
[![](https://jitpack.io/v/jiuyang502/MyLibraryUI.svg)](https://jitpack.io/#jiuyang502/MyLibraryUI)

4个常用控件

1、Necly_Button 四个属性可以设置

    "normal_color_button" format="color" 
    
    "pressed_color_button" format="color" 
    
    "disabled_color_button" format="color" 
    
    "radius_size_button" format="dimension" 
    
    "android:gravity" 
    
    
2、Necly_Toast 调用如下：
    public void ShowMsg(String ShowMsg,int ToastShowType);
    
    public void ShowMsg(String ShowMsg,int ShowTime,int ToastShowType) ;
  
3、Necly_Dialog_ListItem_Single  调用如下：

    ArrayList<String> ListStr_Item = new ArrayList<>();
    
    ListStr_Item.add("中国");
    
    ListStr_Item.add("马来西亚");
    
    ListStr_Item.add("澳大利亚");
    
    ListStr_Item.add("泰国");
    
    ListStr_Item.add("俄罗斯");
    
    ListStr_Item.add("日本");

    Necly_Dialog_ListItem_Single TempDialog = new Necly_Dialog_ListItem_Single(this);
    
    TempDialog.SetListItem(2,ListStr_Item);
    
    TempDialog.SetTitleStr("目的地选择");
    
    TempDialog.SetVisibility_Title(View.VISIBLE);
    
    TempDialog.SetVisibility_Divider(View.INVISIBLE);
    
    TempDialog.SetButtonType(ButtonType_OKCancel);
    
    TempDialog.SetCanceledOnTouchOutside(false);
    
    TempDialog.SetLayoutWidthFactor(0.75f);
    
    TempDialog.SetImageResID(R.drawable.ic_place,R.drawable.ic_hole_dark_24dp);
    
    ColorStateList TempColorSL_TextView = CreateColorStateList2(Color.parseColor("#36424A"),Color.parseColor("#1DBF97"),Color.GRAY);
    
    TempDialog.SetColorStateList_TextView(TempColorSL_TextView);
    
    TempDialog.SetShowRadioButton(false);
        
    TempDialog.setOnListener_Necly_Dialog_ListItem_Single(new Necly_Dialog_ListItem_Single.OnListener_Necly_Dialog_ListItem_Single() {
    
        @Override
        public void onBtnClick_BtnNeutral() {
        }
        
        @Override
        public void onBtnClick_BtnCancel() {
        }
        
        @Override
        public void onBtnClick_BtnOK(int Index_Selected, String ItemName_Selected) {
        }
        
    });

    TempDialog.ShowDialog();
    
3、Necly_ShowView_ListItem_Single 调用如下：

    ArrayList<String> ListStr_Item = new ArrayList<>();
    
    ListStr_Item.add("中国");
    
    ListStr_Item.add("马来西亚");
    
    ListStr_Item.add("澳大利亚");
    
    ListStr_Item.add("泰国");
    
    ListStr_Item.add("俄罗斯");
    
    ListStr_Item.add("日本");

    Necly_ShowView_ListItem_Single1.SetTextSize_DataName(14);
    
    Necly_ShowView_ListItem_Single1.SetTextSize_DataValue(12);
    
    Necly_ShowView_ListItem_Single1.SetDataName("目的地选择");
    
    Necly_ShowView_ListItem_Single1.SetListItem(2,ListStr_Item);
    
    Necly_ShowView_ListItem_Single1.SetImageResID_IvIcon(R.drawable.ic_place_blue_24dp);
    
    Necly_ShowView_ListItem_Single3.SetDataShowType(DataShowType_NameValue);
    
    Necly_ShowView_ListItem_Single1.LoadData();

    Necly_ShowView_ListItem_Single1.SetVisibility_Title(View.VISIBLE);
    
    Necly_ShowView_ListItem_Single1.SetVisibility_Divider(View.INVISIBLE);
    
    Necly_ShowView_ListItem_Single1.SetButtonType(ButtonType_OKCancel );
    
    Necly_ShowView_ListItem_Single1.SetCanceledOnTouchOutside(false);
    
    Necly_ShowView_ListItem_Single1.SetLayoutWidthFactor(0.75f);

    Necly_ShowView_ListItem_Single3.SetImageResID(R.drawable.ic_place,R.drawable.ic_hole_dark_24dp);
    
    ColorStateList TempColorSL_TextView = CreateColorStateList2(Color.parseColor("#36424A"),Color.parseColor("#1DBF97"),Color.GRAY);
    
    Necly_ShowView_ListItem_Single2.SetColorStateList_TextView(TempColorSL_TextView);
    
    Necly_ShowView_ListItem_Single2.SetShowRadioButton(false);

    Necly_ShowView_ListItem_Single1.setOnListener_Necly_ShowView_ListItem_Single(new Necly_ShowView_ListItem_Single.OnListener_Necly_ShowView_ListItem_Single() {
    
        @Override
        public void onBtnClick_BtnNeutral() {
        }
        
        @Override
        public void onBtnClick_BtnCancel() {
        }
        
        @Override
        public void onBtnClick_BtnOK(int Index_Selected, String ItemName_Selected) {
        }
        
    });
