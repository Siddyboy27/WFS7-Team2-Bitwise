package dao.impl;
import models.Patient;
import dao.intf.PatientIntf;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

public class PatientListImpl implements PatientIntf {
    List<Patient> patients = new ArrayList<>();

    @Override
    public Patient getPatientById(int patientID) {
        return patients.stream()
                .filter(patient -> patient.getId() == patientID)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Patient> getPatientsByName(String patientName) {
        return patients.stream()
                .filter(patient -> patient.getName().equals(patientName))
                .toList();
    }

    @Override
    public void registerPatient(Patient patient) {
        patients.add(patient);
    }

    @Override
    public boolean updatePatient(Patient patient) {
        for (Patient p : patients) {
            if (p.getId() == patient.getId()) {
                p.setName(patient.getName());
                p.setEmail(patient.getEmail());
                p.setAddress(patient.getAddress());
                p.setGender(patient.getGender());
                p.setPhoneNumber(patient.getPhoneNumber());
                p.setAllPrescriptions(patient.getAllPrescriptions());
                p.setAllTests(patient.getAllTests());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletePatient(int patientID) {
        patients.removeIf(patient -> patient.getId() == patientID);
        return true;
    }

    @Override
    public List<String> viewAllPrescriptionsByPatientID(int patientID) {
        return patients.stream()
                .filter(patient -> patient.getId() == patientID)
                .findFirst()
                .map(Patient::getAllPrescriptions)
                .orElse(null);
    }

    @Override
    public void addPrescription(int patientID, String description) {
        patients.stream()
                .filter(patient -> patient.getId() == patientID)
                .findFirst()
                .ifPresent(patient -> patient.addPrescription(description));
    }

    @Override
    public boolean updatePrescription(int patientID, String Date, String description) {
        //update without stream
        for (Patient p : patients) {
            if (p.getId() == patientID) {
                p.updatePrescription(LocalDate.parse(Date), description);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deletePrescription(int patientID, String Date) {
        //delete without stream
        for (Patient p : patients) {
            if (p.getId() == patientID) {
                p.deletePrescription(LocalDate.parse(Date));
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> viewAllTestsByPatientID(int patientID) {
        return patients.stream()
                .filter(patient -> patient.getId() == patientID)
                .findFirst()
                .map(Patient::getAllTests)
                .orElse(null);
    }

    @Override
    public void addTest(int patientID, String description) {
        patients.stream()
                .filter(patient -> patient.getId() == patientID)
                .findFirst()
                .ifPresent(patient -> patient.addTest(description));
    }

    @Override
    public boolean updateTest(int patientID, String date, String description) {
        //update without stream
        for (Patient p : patients) {
            if (p.getId() == patientID) {
                p.updateTest(LocalDate.parse(date), description);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteTest(int patientID, String date) {
        //delete without stream
        for (Patient p : patients) {
            if (p.getId() == patientID) {
                p.deleteTest(LocalDate.parse(date));
                return true;
            }
        }
        return  false;
    }
}
