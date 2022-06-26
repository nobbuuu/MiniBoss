package com.dream.miniboss.bean;

/**
 * 创建日期：2022-06-21 on 14:09
 * 描述:衣带渐宽终不悔、为伊消得人憔悴
 * 作者:HeGuiCun Administrator
 */
public class PugongBean {

    String zhiwei;
    int gongzi;
    String xueli;
    int nianling;
    String jingyan;
    int renshu;
    int image;
    String name;
    String gerenzhaopin;
    String yirenzheng;
    String zaixian;
    String company;
    String adress;

    public String getZhiwei() {
        return zhiwei;
    }

    public void setZhiwei(String zhiwei) {
        this.zhiwei = zhiwei;
    }

    public int getGongzi() {
        return gongzi;
    }

    public void setGongzi(int gongzi) {
        this.gongzi = gongzi;
    }

    public String getXueli() {
        return xueli;
    }

    public void setXueli(String xueli) {
        this.xueli = xueli;
    }

    public int getNianling() {
        return nianling;
    }

    public void setNianling(int nianling) {
        this.nianling = nianling;
    }

    public String getJingyan() {
        return jingyan;
    }

    public void setJingyan(String jingyan) {
        this.jingyan = jingyan;
    }

    public int getRenshu() {
        return renshu;
    }

    public void setRenshu(int renshu) {
        this.renshu = renshu;
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

    public String getGerenzhaopin() {
        return gerenzhaopin;
    }

    public void setGerenzhaopin(String gerenzhaopin) {
        this.gerenzhaopin = gerenzhaopin;
    }

    public String getYirenzheng() {
        return yirenzheng;
    }

    public void setYirenzheng(String yirenzheng) {
        this.yirenzheng = yirenzheng;
    }

    public String getZaixian() {
        return zaixian;
    }

    public void setZaixian(String zaixian) {
        this.zaixian = zaixian;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public PugongBean(String zhiwei, int gongzi, String xueli, int nianling, String jingyan, int renshu, int image, String name, String gerenzhaopin, String yirenzheng, String zaixian, String company, String adress) {
        this.zhiwei = zhiwei;
        this.gongzi = gongzi;
        this.xueli = xueli;
        this.nianling = nianling;
        this.jingyan = jingyan;
        this.renshu = renshu;
        this.image = image;
        this.name = name;
        this.gerenzhaopin = gerenzhaopin;
        this.yirenzheng = yirenzheng;
        this.zaixian = zaixian;
        this.company = company;
        this.adress = adress;
    }

    public PugongBean() {

    }

    @Override
    public String toString() {
        return "PugongBean{" +
                "zhiwei='" + zhiwei + '\'' +
                ", gongzi=" + gongzi +
                ", xueli='" + xueli + '\'' +
                ", nianling=" + nianling +
                ", renshu=" + renshu +
                ", jingyan='" + jingyan + '\'' +
                ", image=" + image +
                ", name='" + name + '\'' +
                ", gerenzhaopin='" + gerenzhaopin + '\'' +
                ", yirenzheng='" + yirenzheng + '\'' +
                ", zaixian='" + zaixian + '\'' +
                ", company='" + company + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
