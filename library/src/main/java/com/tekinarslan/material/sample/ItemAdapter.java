package com.tekinarslan.material.sample;

public class ItemAdapter {

    String ImageUrl;
    String month;
    String date;
    String number1;
    String number2;
    String number3;
    String number4;
    String messen;
    String view;
    String image_messen;

    public ItemAdapter() {

    }

    public ItemAdapter(String ImageUrl, String month, String date, String number1, String number2, String number3, String number4,
                       String messen, String view, String image_messen) {

        this.ImageUrl = ImageUrl;
        this.month = month;
        this.date = date;
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
        this.number4 = number4;
        this.messen = messen;
        this.view = view;
        this.image_messen = image_messen;

    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getMonth() {
        return month;
    }

    public String getDate() {
        return date;
    }

    public String getNumber1() {
        return number1;
    }

    public String getNumber2() {
        return number2;
    }

    public String getNumber3() {
        return number3;
    }

    public String getNumber4() {
        return number4;
    }

    public String getMessen() {
        return messen;
    }

    public String getView() {
        return view;
    }

    public String getImage_messen() {
        return image_messen;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public void setNumber3(String number3) {
        this.number3 = number3;
    }

    public void setNumber4(String number4) {
        this.number4 = number4;
    }

    public void setMessen(String messen) {
        this.messen = messen;
    }

    public void setView(String view) {
        this.view = view;
    }

    public void setImage_messen(String image_messen) {
        this.image_messen = image_messen;
    }


}
