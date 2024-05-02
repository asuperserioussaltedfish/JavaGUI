package com.example.pojo;

import lombok.Data;

/**
 * @author SaltedFish
 */
@Data
public class PatientIllness {
    Long id;
    Long patientId;
    Long illnessTime;
    String illness;
    public PatientIllness(){

    }
    public PatientIllness(Long patientId,Long illnessTime,String illness){
        this.patientId=patientId;
        this.illnessTime=illnessTime;
        this.illness=illness;
    }
}
