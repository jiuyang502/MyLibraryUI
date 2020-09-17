package com.pannygirlstudio.necly.uilibrary.DataModel;


public class Necly_ContentItem_String {

    private String m_ItemString;

    public Necly_ContentItem_String() {
        m_ItemString = "";
    }
    public Necly_ContentItem_String(String ItemString) {
        m_ItemString = ItemString;
    }


    public void  SetItemString(String ItemString) {
        m_ItemString = ItemString;
    }
    public String GetItemString()
    {
        return m_ItemString;
    }
}
