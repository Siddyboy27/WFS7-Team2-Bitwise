package org.example.dao.impl;

import org.example.dao.intf.DoctorIntf;
import org.example.models.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorListImpl implements DoctorIntf {
    private final List<Doctor> doctors = new ArrayList<>();

    @Override
    public Doctor getDoctorById(int doctorID) {
        return doctors.stream()
                .filter(doctor -> doctor.getId() == doctorID)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        List<Doctor> specializedDoctors = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getSpecialization().equalsIgnoreCase(specialization)) {
                specializedDoctors.add(doctor);
            }
        }
        return specializedDoctors;
    }

    @Override
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId() == doctor.getId()) {
                doctors.set(i, doctor);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteDoctor(int doctorID) {
        return doctors.removeIf(doctor -> doctor.getId() == doctorID);
    }

    @Override
    public int[] viewAppointments(int doctorID, int day) {
        Doctor doctor = getDoctorById(doctorID);
        return doctor != null ? doctor.getAllAppointments()[day] : new int[0];
    }

    @Override
    public int[][] viewAllAppointments(int doctorID) {
        Doctor doctor = getDoctorById(doctorID);
        return doctor != null ? doctor.getAllAppointments() : new int[0][0];
    }

    @Override
    public void addAppointment(int doctorID, int day, int time, int patientID) {
        Doctor doctor = getDoctorById(doctorID);
        if (doctor != null) {
            doctor.addAppointment(time, day, patientID);
        }
    }

    @Override
    public boolean updateAppointment(int doctorID, int day, int time, int patientID) {
        Doctor doctor = getDoctorById(doctorID);
        if (doctor != null) {
            doctor.updateAppointment(day, time, patientID);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAppointment(int doctorID, int day, int time) {
        Doctor doctor = getDoctorById(doctorID);
        if (doctor != null) {
            doctor.removeAppointment(time, day);
            return true;
        }
        return false;
    }


}
