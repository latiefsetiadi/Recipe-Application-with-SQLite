package com.example.myrecipe2;

public class Constans {
    public static final String DB_NAME = "MY_RECIPE2";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "FOOD_INFO_TABLE";
    public static final String C_ID = "ID";
    public static final String C_NAMEFOOD = "NAMEFOOD";
    public static final String C_NAMECHEF = "NAMECHEF";
    public static final String C_BAHAN = "INGREDIENTS";
    public static final String C_STEP = "STEP";
    public static final String C_IMAGE = "IMAGE";
    public static final String C_ADD_TIMESTAMP = "ADD_TIMESTAMP";
    public static final String C_UPDATE_TIMESTAMP = "UPDATE_TIMESTAMP";

    public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME
            + " ("
            + C_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_NAMEFOOD +" TEXT,"
            + C_NAMECHEF+" TEXT,"
            + C_BAHAN + " TEXT,"
            + C_STEP + " TEXT,"
            + C_IMAGE+" TEXT,"
            + C_ADD_TIMESTAMP + " TEXT,"
            + C_UPDATE_TIMESTAMP + " TEXT"
            +");";
}
