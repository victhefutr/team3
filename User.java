package com.example.mvp;

import android.content.SharedPreferences;

import java.util.HashMap;

public class User{

    private HashMap<String, String> userNameAuth;

    public User(){
        userNameAuth = new HashMap<String, String>();
    }
    public void addUser(String fullName, String password) {
        if(!(fullName.isEmpty() && password.isEmpty())){
            userNameAuth.put(fullName, password);
        }
    }

    public boolean getUser(String fullName, String password){
        if(userNameAuth.isEmpty()){
            return false;
        }
        if(userNameAuth.get(fullName).equals(password)){
            return true;
        }
        return false;
    }

    public static void storeUserDataLocally(SharedPreferences.Editor editor, String user){
        editor.putString("appUserName", user);
        editor.commit();
    }

}