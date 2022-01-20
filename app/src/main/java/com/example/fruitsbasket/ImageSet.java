package com.example.fruitsbasket;

import android.graphics.Bitmap;

public class ImageSet {
    private Bitmap image1;
    private Bitmap image2;
    private Bitmap image3;
    private Bitmap image4;
    private String[] check;

    public String[] getCheck() {
        return check;
    }

    public void setCheck(String[] check) {
        this.check = check;
    }

    public Bitmap getImage1() {
        return image1;
    }

    public void setImage(Bitmap image, int index) {
        switch (index){
            case 0: this.image1 = image;break;
            case 1: this.image2 = image;break;
            case 2: this.image3 = image;break;
            case 3: this.image4 = image;
        }
    }

    public Bitmap getImage(int index) {
        switch (index){
            case 0: return this.image1;
            case 1: return this.image2;
            case 2: return this.image3;
            case 3: return this.image4;
        }
        return null;
    }

    public void setImage1(Bitmap image1) {
        this.image1 = image1;
    }

    public Bitmap getImage2() {
        return image2;
    }

    public void setImage2(Bitmap image2) {
        this.image2 = image2;
    }

    public Bitmap getImage3() {
        return image3;
    }

    public void setImage3(Bitmap image3) {
        this.image3 = image3;
    }

    public Bitmap getImage4() {
        return image4;
    }

    public void setImage4(Bitmap image4) {
        this.image4 = image4;
    }
}
