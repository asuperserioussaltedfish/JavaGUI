package com.example.utils;


import com.example.pojo.Doctor;

/**
 * @author 14158
 */
public class UserThreadLocal {

    private UserThreadLocal(){}

    private static final ThreadLocal<Doctor> LOCAL = new ThreadLocal<>();
    public static Doctor get(){
        return LOCAL.get();
    }

    public static void put(Doctor doctor){
        LOCAL.set(doctor);
    }
    public static void remove(){
        LOCAL.remove();
    }
}
