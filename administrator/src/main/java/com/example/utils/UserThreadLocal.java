package com.example.utils;


import com.example.pojo.Admin;

/**
 * @author 14158
 */
public class UserThreadLocal {

    private UserThreadLocal(){}

    private static final ThreadLocal<Admin> LOCAL = new ThreadLocal<>();
    public static Admin get(){
        return LOCAL.get();
    }

    public static void put(Admin sysAdmin){
        LOCAL.set(sysAdmin);
    }
    public static void remove(){
        LOCAL.remove();
    }
}
