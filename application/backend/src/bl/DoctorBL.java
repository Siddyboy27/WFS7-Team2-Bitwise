package bl;

import dao.intf.DoctorIntf;
import dao.impl.DoctorListImpl;
import models.Doctor;
import java.util.List;

public class DoctorBL {
    // Correcting the interface to DoctorIntf
    private final DoctorIntf doctorDAO = new DoctorListImpl();

    public void addDoctor(Doctor doctor) {
        doctorDAO.addDoctor(doctor);
    }

    public void updateDoctor(Doctor doctor) {
        doctorDAO.updateDoctor(doctor);
    }

    public void deleteDoctor(int doctorID) {
        doctorDAO.deleteDoctor(doctorID);
    }

    public Doctor getDoctorById(int doctorID) {
        return doctorDAO.getDoctorById(doctorID);
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorDAO.getDoctorsBySpecialization(specialization);
    }

    // Method for viewing doctor's appointments by day
    public int[] viewAppointments(int doctorID, int day) {
        return doctorDAO.viewAppointments(doctorID, day);
    }

    // Method for viewing all doctor's appointments
    public int[][] viewAllAppointments(int doctorID) {
        return doctorDAO.viewAllAppointments(doctorID);
    }

    // Method to add appointment
    public void addAppointment(int doctorID, int day, int time, int patientID) {
        doctorDAO.addAppointment(doctorID, day, time, patientID);
    }

    // Method to update appointment
    public void updateAppointment(int doctorID, int day, int time, int patientID) {
        doctorDAO.updateAppointment(doctorID, day, time, patientID);
    }

    // Method to delete appointment
    public void deleteAppointment(int doctorID, int day, int time) {
        doctorDAO.deleteAppointment(doctorID, day, time);
    }


}
