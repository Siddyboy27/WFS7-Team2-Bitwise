package org.example.dao.intf;

import org.example.models.Doctor;

import java.sql.SQLException;
import java.util.List;

public interface DoctorIntf {
    Doctor getDoctorById(int doctorID);
    List<Doctor> getDoctorsBySpecialization(String specialization);
    void addDoctor(Doctor doctor) throws SQLException;
    boolean updateDoctor(Doctor doctor);
    boolean deleteDoctor(int doctorID);

    int[] viewAppointments(int doctorID, int day);
    int[][] viewAllAppointments(int doctorID);
    void addAppointment(int doctorID, int day, int time, int patientID);
    boolean updateAppointment(int doctorID, int day, int time, int patientID);
    boolean deleteAppointment(int doctorID, int day, int time);
}