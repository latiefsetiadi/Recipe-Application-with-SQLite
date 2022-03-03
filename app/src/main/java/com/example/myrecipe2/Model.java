package com.example.myrecipe2;

public class Model {
    String id,image, namefood, namechef, bahan,langkah, addTimeStamp,updateTimeStamp;

    public Model(String id, String image, String namefood, String namechef, String bahan, String langkah, String addTimeStamp, String updateTimeStamp) {
        this.id = id;
        this.image = image;
        this.namefood = namefood;
        this.namechef = namechef;
        this.bahan = bahan;
        this.langkah = langkah;
        this.addTimeStamp = addTimeStamp;
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNamefood() {
        return namefood;
    }

    public void setNamefood(String namefood) {
        this.namefood = namefood;
    }

    public String getNamechef() {
        return namechef;
    }

    public void setNamechef(String namechef) {
        this.namechef = namechef;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getLangkah() {
        return langkah;
    }

    public void setLangkah(String langkah) {
        this.langkah = langkah;
    }

    public String getAddTimeStamp() {
        return addTimeStamp;
    }

    public void setAddTimeStamp(String addTimeStamp) {
        this.addTimeStamp = addTimeStamp;
    }

    public String getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(String updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }
}
