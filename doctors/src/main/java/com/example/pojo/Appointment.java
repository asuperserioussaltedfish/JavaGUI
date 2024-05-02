package com.example.pojo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import lombok.Data;

/**
 * @author SaltedFish
 */
@Data
public class Appointment {
    private LongProperty id;
    private LongProperty doctorId;
    private LongProperty patientId;
    private LongProperty appointmentTime;
    private IntegerProperty appointment_status;

//    public Appointment( long patient_id, long doctor_id, long appointment_time, int appointment_status) {
//        this.appointment_status=appointment_status;
//        this.appointmentTime=appointment_time;
//        this.doctorId=doctor_id;
//        this.patientId=patient_id;
//    }
public Appointment() {
    this.id = new SimpleLongProperty();
    this.doctorId = new SimpleLongProperty();
    this.patientId = new SimpleLongProperty();
    this.appointmentTime = new SimpleLongProperty();
    this.appointment_status = new SimpleIntegerProperty();
}

    public Appointment(long id, long doctorId, long patientId, long appointmentTime, int appointment_status) {
        this.id = new SimpleLongProperty(id);
        this.doctorId = new SimpleLongProperty(doctorId);
        this.patientId = new SimpleLongProperty(patientId);
        this.appointmentTime = new SimpleLongProperty(appointmentTime);
        this.appointment_status = new SimpleIntegerProperty(appointment_status);
    }
    public Appointment( long doctorId, long patientId, long appointmentTime, int appointment_status) {
        this.doctorId = new SimpleLongProperty(doctorId);
        this.patientId = new SimpleLongProperty(patientId);
        this.appointmentTime = new SimpleLongProperty(appointmentTime);
        this.appointment_status = new SimpleIntegerProperty(appointment_status);
    }

public LongProperty idProperty() {
    return id;
}

    public  Long getId() {
        return idProperty().get();
    }

    public  void setId(Long id) {
        idProperty().set(id);
    }

    public LongProperty doctorIdProperty() {
        return doctorId;
    }

    public  Long getDoctorId() {
        return doctorIdProperty().get();
    }

    public  void setDoctorId(Long doctorId) {
        doctorIdProperty().set(doctorId);
    }

    public LongProperty patientIdProperty() {
        return patientId;
    }

    public  Long getPatientId() {
        return patientIdProperty().get();
    }

    public  void setPatientId(Long patientId) {
        patientIdProperty().set(patientId);
    }

    public LongProperty appointmentTimeProperty() {
        return appointmentTime;
    }

    public  Long getAppointmentTime() {
        return appointmentTimeProperty().get();
    }

    public final void setAppointmentTime(Long appointmentTime) {
        appointmentTimeProperty().set(appointmentTime);
    }

    public IntegerProperty appointment_statusProperty() {
        return appointment_status;
    }

    public  Integer getAppointment_status() {
        return appointment_statusProperty().get();
    }

    public  void setAppointment_status(Integer appointment_status) {
        appointment_statusProperty().set(appointment_status);
    }
}
