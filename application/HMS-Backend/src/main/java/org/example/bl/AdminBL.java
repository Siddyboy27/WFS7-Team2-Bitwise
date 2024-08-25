
package org.example.bl;

import org.example.dao.impl.PatientJDBCImpl;
import org.example.models.Doctor;
import org.example.models.Patient;

import java.sql.SQLException;
import java.util.List;

public class AdminBL {
    private final DoctorBL doctorBL = new DoctorBL();
    private final PatientJDBCImpl patientJDBC;

    public AdminBL() throws SQLException {
        this.patientJDBC = new PatientJDBCImpl();  // Initialize PatientJDBCImpl
    }

    // Fetch doctor by ID
    public Doctor getDoctorById(int doctorID) {
        return doctorBL.getDoctorById(doctorID);
    }

    // Show all doctors based on specialization
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorBL.getDoctorsBySpecialization(specialization);
    }

    // Update doctor's appointment schedule
    public void updateDoctorAppointment(int doctorID, int day, int time, int patientID) {
        doctorBL.updateAppointment(doctorID, day, time, patientID);
    }

    // Cancel doctor appointment
    public void cancelAppointment(int doctorID, int day, int time) {
        doctorBL.deleteAppointment(doctorID, day, time);
    }

    // Register a patient (example additional functionality if needed)
    public void registerPatient(Patient patient) {
        patientJDBC.registerPatient(patient);
    }
}
