package com.example.MealMinder.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    public static final String LOGIN_PREF_NAME = "session";
    public static final String SESSION_KEY = "session_user";
    private static final String PREF_NAME = "meal_minder";

    private static final String PREF_ID_USER = "idUser";

    private static final String PREF_ID_NUMBER = "idNumber";

    private static final String IS_LOGIN = "isLogin";

    private static final String NAMA_USER = "nama";
    private static final String NO_USER = "no_telepon";
    private static final String TANGGAL_LAHIR = "tgl_lahir";

    private static final String EMAIL_USER = "email";
    private static final String PASS_USER = "pass";

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    // Constructior

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public SessionManager(Context context, String PREF_NAME_PARAMS) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME_PARAMS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public int getSession() {
        return sharedPreferences.getInt(SESSION_KEY, -1);
    }

    public void unsetSession() {
        editor.putInt(SESSION_KEY, -1).commit();
    }

    public void saveSPBool(String keySP, boolean value) {
        editor.putBoolean(keySP, value);
        editor.commit();
    }

    public void setNameUser(String nama) {
        if (!nama.equals(""))
            saveSPString(NAMA_USER, nama);
    }

    public void setEmailUser(String email) {
        if (!email.equals(""))
            saveSPString(EMAIL_USER, email);
    }

    public void setNoUser(String no) {
        if (!no.equals(""))
            saveSPString(NO_USER, no);
    }

    public void setPassUser(String pass) {
        if (!pass.equals(""))
            saveSPString(PASS_USER, pass);
    }

    public void setTanggalLahir(String tanggalLahir) {
        if (!tanggalLahir.equals(""))
            saveSPString(TANGGAL_LAHIR, tanggalLahir);
    }

    public String getNamaUser(){
        return sharedPreferences.getString(NAMA_USER, "");
    }
    public String getEmailUser(){
        return sharedPreferences.getString(EMAIL_USER, "");
    }
    public String getNoUser(){
        return sharedPreferences.getString(NO_USER, "");
    }
    public String getPassUser(){
        return sharedPreferences.getString(PASS_USER, "");
    }

    public String getTanggalLahir(){
        return sharedPreferences.getString(TANGGAL_LAHIR, "");
    }

    public void saveSPString(String keySP, String value){
        editor.putString(keySP, value);
        editor.commit();
    }

    public void isLogin(boolean value) {
        editor.putBoolean(IS_LOGIN, value);
        editor.commit();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void idNumber(String value) {
        editor.putString(PREF_ID_NUMBER, value);
        editor.commit();
    }

    public String idNumber() {
        return sharedPreferences.getString(PREF_ID_NUMBER, "");
    }

    public void idUser(String value) {
        editor.putString(PREF_ID_USER, value);
        editor.commit();
    }

    public String idUser() {
        return sharedPreferences.getString(PREF_ID_USER, "");
    }
}
