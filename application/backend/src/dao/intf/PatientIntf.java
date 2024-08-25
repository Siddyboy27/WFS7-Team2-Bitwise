package dao.intf;

import java.util.List;
import models.Patient;

public interface PatientIntf {
    Patient getPatientById(int patientID);
    List<Patient> getPatientsByName(String patientName);
    void registerPatient(Patient patient);
    boolean updatePatient(Patient patient);
    boolean deletePatient(int patientID);

    List<String> viewAllPrescriptionsByPatientID(int patientID);
    void addPrescription(int patientID, String description);
    boolean updatePrescription(int patientID, String Date, String description);
    boolean deletePrescription(int patientID, String Date);

    List<String> viewAllTestsByPatientID(int patientID);
    void addTest(int patientID, String description);
    boolean updateTest(int patientID, String date, String description);
    boolean deleteTest(int patientID, String date);


}
