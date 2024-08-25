package org.example.models;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    private int id;
    private String name;
    private String email;
    private String address;
    private String gender;
    private String phoneNumber;
    private List<String> prescriptions;
    private List<String> tests;
    
    public Patient(int id, String name, String email, String address, String gender, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        prescriptions = new ArrayList<String>();
        tests = new ArrayList<String>();
    }
    public Patient(int id, String name, String email, String address, String gender, String phoneNumber, String prescriptionStr, String testStr) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        prescriptions = stringToList(prescriptionStr);
        tests = stringToList(testStr);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public List<String> getAllPrescriptions() {
        return prescriptions;
    }
    public void setAllPrescriptions(List<String> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public void addPrescription(String description) {
        //add current date to description as string
        LocalDate date = LocalDate.now();
        String prescription = date.toString()+": "+description;
        prescriptions.add(prescription);
    }

    public void updatePrescription(LocalDate date, String description) {
        for (int i = 0; i < prescriptions.size(); i++) {
            if (prescriptions.get(i).contains(date.toString())) {
                prescriptions.set(i, date.toString()+": "+description);
            }
        }
    }

    public List<String> getPrescriptionByDate(LocalDate date) {
        List<String> prescriptionsByDate = new ArrayList<String>();
        for (String prescription : prescriptions) {
            if (prescription.contains(date.toString())) {
                prescriptionsByDate.add(prescription);
            }
        }
        return prescriptionsByDate; 
    }

    public void deletePrescription(LocalDate date) {
        for (int i = 0; i < prescriptions.size(); i++) {
            if (prescriptions.get(i).contains(date.toString())) {
                prescriptions.remove(i);
            }
        }
    }

    public String listString(List<String> list) {
        String str = "";
        for (String x: list) {
            str += x + "\n";
        }
        return str;
    }

    public List<String> stringToList(String listStr) {
        List<String> list = new ArrayList<>();
        String[] strArray = listStr.split("\n");
        for (String prescription : strArray) {
            list.add(prescription);
        }
        return list;
    }

    public List<String> getAllTests() {
        return tests;
    }
    public void setAllTests(List<String> tests) {
        this.tests = tests;
    }

    public void addTest(String description) {
        //add current date to description as string
        LocalDate date = LocalDate.now();
        String test = date.toString()+": "+description;
        tests.add(test);
    }

    public void updateTest(LocalDate date, String description) {
        for (int i = 0; i < prescriptions.size(); i++) {
            if (tests.get(i).contains(date.toString())) {
                tests.set(i, date.toString()+": "+description);
            }
        }
    }

    public List<String> getTestsByDate(LocalDate date) {
        List<String> testByDate = new ArrayList<String>();
        for (String test : tests) {
            if (test.contains(date.toString())) {
                testByDate.add(test);
            }
        }
        return testByDate; 
    }

    public void deleteTest(LocalDate date) {
        for (int i = 0; i < prescriptions.size(); i++) {
            if (prescriptions.get(i).contains(date.toString())) {
                prescriptions.remove(i);
            }
        }
    }

    //TO STRING
    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                // ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
    
    //EQUALS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return getId() == patient.getId() &&
                getName().equals(patient.getName()) &&
                getEmail().equals(patient.getEmail()) &&
                getAddress().equals(patient.getAddress()) &&
                getPhoneNumber().equals(patient.getPhoneNumber());
    }

    
    // //COMPARE TO
    // public int compareTo(Patient p) {
    //     return this.getName().compareTo(p.getName());

}
