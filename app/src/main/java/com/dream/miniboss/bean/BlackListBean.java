package com.dream.miniboss.bean;

/**
 * 创建日期：2022-06-28 on 13:03
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class BlackListBean {
    int image;
    String name;
    String introduce;

    public BlackListBean(int image, String name, String introduce) {
        this.image = image;
        this.name = name;
        this.introduce = introduce;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
