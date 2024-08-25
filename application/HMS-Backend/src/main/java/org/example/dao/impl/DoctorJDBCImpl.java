package org.example.dao.impl;

import org.example.dao.intf.DoctorIntf;
import org.example.models.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
// import models.Prescription;

public class DoctorJDBCImpl implements DoctorIntf {

    public DoctorJDBCImpl() throws SQLException{
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
    public Doctor getDoctorById(int id) {
        try(Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM doctor WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Doctor(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("address"), rs.getString("phoneNumber"), rs.getString("specialization"), rs.getInt("experience"), rs.getDouble("rating"), rs.getString("isAvailable"), rs.getString("appointments"), rs.getDate("lastUpdated").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        List<Doctor> doctors = new ArrayList<>();
        try(Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM doctor");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                doctors.add(new Doctor(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getString("address"), rs.getString("phoneNumber"), rs.getString("specialization"), rs.getInt("experience"), rs.getDouble("rating"), rs.getString("isAvailable"), rs.getString("appointments"), rs.getDate("lastUpdated").toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @Override
    public void addDoctor(Doctor doctor) throws SQLException{
        try(Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement("INSERT INTO doctor (id, name, email, password, address, phoneNumber, specialization, experience, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, doctor.getId());
            ps.setString(2, doctor.getName());
            ps.setString(3, doctor.getEmail());
            ps.setString(4, doctor.getPassword());
            ps.setString(5, doctor.getAddress());
            ps.setString(6, doctor.getPhoneNumber());
            ps.setString(7, doctor.getSpecialization());
            ps.setInt(8, doctor.getExperience());
            ps.setDouble(9, doctor.getRating());
            ps.setString(10, doctor.isAvailableStr());
            ps.setString(11, doctor.appointmentString(doctor.getAllAppointments()));
            ps.setDate(12, java.sql.Date.valueOf(doctor.getLastUpdated()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {
        try(Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement("UPDATE doctor SET name = ?, email = ?, password = ?, address = ?, phoneNumber = ?, specialization = ?, experience = ?, rating = ? isAvailable = ?, appointments = ?, lastUpdated = ? WHERE id = ?");
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getEmail());
            ps.setString(3, doctor.getPassword());
            ps.setString(4, doctor.getAddress());
            ps.setString(5, doctor.getPhoneNumber());
            ps.setString(6, doctor.getSpecialization());
            ps.setInt(7, doctor.getExperience());
            ps.setDouble(8, doctor.getRating());
            ps.setString(9, doctor.isAvailableStr());
            ps.setString(10, doctor.appointmentString(doctor.getAllAppointments()));
            ps.setDate(11, java.sql.Date.valueOf(doctor.getLastUpdated()));
            ps.setInt(12, doctor.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteDoctor(int id) {
        try(Connection conn = getConnection()){
            PreparedStatement ps = conn.prepareStatement("DELETE FROM doctor WHERE id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int[] viewAppointments(int doctorID, int day) {
        return getDoctorById(doctorID).getAllAppointments()[day];
    }

    @Override
    public int[][] viewAllAppointments(int doctorID){
        return getDoctorById(doctorID).getAllAppointments();
    }

    @Override
    public void addAppointment(int doctorID, int day, int time, int patientID){
        Doctor doctor = getDoctorById(doctorID);
        doctor.addAppointment(day, time, patientID);
        updateDoctor(doctor);
    }

    @Override
    public boolean updateAppointment(int doctorID, int day, int time, int patientID){
        Doctor doctor = getDoctorById(doctorID);
        doctor.updateAppointment(day, time, patientID);
        return updateDoctor(doctor);
    }

    @Override
    public boolean deleteAppointment(int doctorID, int day, int time){
        Doctor doctor = getDoctorById(doctorID);
        doctor.removeAppointment(day, time);
        return updateDoctor(doctor);
    }

}