package com.dream.miniboss.message.bean;

/**
 * 创建日期：2022-07-17 on 1:38
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class MessageChatBean {
    int icon;
    String name;
    String company;
    String date;
    String content;

    public MessageChatBean(int icon, String name, String company, String date, String content) {
        this.icon = icon;
        this.name = name;
        this.company = company;
        this.date = date;
        this.content = content;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
