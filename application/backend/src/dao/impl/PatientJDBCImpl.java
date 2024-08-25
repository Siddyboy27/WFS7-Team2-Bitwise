package dao.impl;
import dao.intf.PatientIntf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.Patient;

public class PatientJDBCImpl implements PatientIntf {
     public PatientJDBCImpl() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            //connect to database
            throw new RuntimeException("Driver not found");
        }
    }

    Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb3?useSSL=false",
                                                    "root", "Sakshi@123");
        
        System.out.println("Connected to database!!");
        return conn;
    }

    @Override
    public Patient getPatientById(int id) {
        try(Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM patient WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("address"), rs.getString("gender"), rs.getString("phoneNumber"), rs.getString("prescriptions"), rs.getString("tests"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Patient> getPatientsByName(String patientName) {
        List<Patient> patients = new ArrayList<>();
        try(Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM patient WHERE name = ?");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                patients.add(new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("address"), rs.getString("gender"), rs.getString("phoneNumber"), rs.getString("prescriptions"), rs.getString("tests")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public void registerPatient(Patient patient) {
        try(Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement("INSERT INTO patient (id, name, email, address, gender, phoneNumber, prescriptions, tests) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, patient.getId());
            ps.setString(2, patient.getName());
            ps.setString(3, patient.getEmail());
            ps.setString(5, patient.getAddress());
            ps.setString(6, patient.getGender());
            ps.setString(6, patient.getPhoneNumber());
            ps.setString(7, patient.listString(patient.getAllPrescriptions()));
            ps.setString(8, patient.listString(patient.getAllTests()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updatePatient(Patient patient) {
        try(Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement("UPDATE patient SET name = ?, email = ?, address = ?, gender = ?, phoneNumber = ?, prescriptions = ?, tests = ? WHERE id = ?");
            ps.setInt(1, patient.getId());
            ps.setString(2, patient.getName());
            ps.setString(3, patient.getEmail());
            ps.setString(5, patient.getAddress());
            ps.setString(6, patient.getGender());
            ps.setString(6, patient.getPhoneNumber());
            ps.setString(7, patient.listString(patient.getAllPrescriptions()));
            ps.setString(8, patient.listString(patient.getAllTests()));
            ps.setInt(9, patient.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePatient(int id) {
        try(Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement("DELETE FROM patient WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<String> viewAllPrescriptionsByPatientID(int patientID){
        return getPatientById(patientID).getAllPrescriptions();
    }

    @Override
    public void addPrescription(int patientID, String description){
        //get current date
        getPatientById(patientID).addPrescription(description);
    }

    @Override
    public boolean updatePrescription(int patientID, String date, String description){
        //get current date
        getPatientById(patientID).updatePrescription(LocalDate.parse(date), description);
        return true;
    }    

    @Override
    public boolean deletePrescription(int patientID, String date){
        //get current date
        getPatientById(patientID).deletePrescription(LocalDate.parse(date));
        return true;
    }

    @Override
    public List<String> viewAllTestsByPatientID(int patientID){
        return getPatientById(patientID).getAllTests();
    }

    @Override
    public void addTest(int patientID, String description){
        //get current date
        getPatientById(patientID).addTest(description);
    }

    @Override
    public boolean updateTest(int patientID, String date, String description){
        //get current date
        getPatientById(patientID).updateTest(LocalDate.parse(date), description);
        return true;
    }

    @Override
    public boolean deleteTest(int patientID, String date){
        //get current date
        getPatientById(patientID).deleteTest(LocalDate.parse(date));
        return true;
    }
}
