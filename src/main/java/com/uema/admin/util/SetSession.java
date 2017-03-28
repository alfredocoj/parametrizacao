package com.uema.admin.util;

/**
 * Created by alfredo on 27/03/17.
 */
public class SetSession {
    private static String erro = "Texto de erro";

    public static void setErrorSession(String erro){
        System.out.println("setDebug");
        SetSession.erro = erro;

    }

    public static String getErrorSession(){
        System.out.println("getDebug");
        return SetSession.erro;
    }
}
