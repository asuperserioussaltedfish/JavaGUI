package com.example.utils;


import com.example.pojo.Doctor;
import com.example.pojo.Patient;

/**
 * @author 14158
 */
public class UserThreadLocal {

    private UserThreadLocal(){}

    private static final ThreadLocal<Patient> LOCAL = new ThreadLocal<>();
    public static Patient get(){
        return LOCAL.get();
    }

    public static void put(Patient patient){
        LOCAL.set(patient);
    }
    public static void remove(){
        LOCAL.remove();
    }
}
