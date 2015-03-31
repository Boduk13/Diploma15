package com.example.bodik13.duplom15;

/**
 * Created by Bodik13 on 31.03.2015.
 */
public class Travel {
    private String TAG_ID;
    private String TAG_TIME_FIRST;
    private String TAG_FIRST_NAME;
    private String TAG_LAST_NAME;
    private String TAG_PRICE;
    private String TAG_SEATE;
    private String TAG_carBrand;
    private String TAG_carModel;

    public Travel(String TAG_ID, String TAG_TIME_FIRST, String TAG_FIRST_NAME, String TAG_LAST_NAME, String TAG_PRICE, String TAG_SEATE, String TAG_carBrand, String TAG_carModel) {
        this.TAG_ID = TAG_ID;
        this.TAG_TIME_FIRST = TAG_TIME_FIRST;
        this.TAG_FIRST_NAME = TAG_FIRST_NAME;
        this.TAG_LAST_NAME = TAG_LAST_NAME;
        this.TAG_PRICE = TAG_PRICE;
        this.TAG_SEATE = TAG_SEATE;
        this.TAG_carBrand = TAG_carBrand;
        this.TAG_carModel = TAG_carModel;
    }
}
