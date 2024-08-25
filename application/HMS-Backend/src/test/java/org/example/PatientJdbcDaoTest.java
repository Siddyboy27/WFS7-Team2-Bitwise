package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.models.Patient;
import org.example.dao.impl.PatientJDBCImpl;

import org.junit.jupiter.api.Test;

public class PatientJdbcDaoTest {

    @Test
    public void testConnection() throws SQLException {
        PatientJDBCImpl patientDAO = new PatientJDBCImpl();
        Connection connection = patientDAO.getConnection();
        assertNotNull(connection);
        assertTrue(connection.isValid(10)); // Check connection validity for 10 seconds
    }

    @Test
    public void testGetPatientById_ValidId() throws SQLException {
        // Arrange
        int validPatientId = 1; // Replace with an existing patient ID

        // Act
        PatientJDBCImpl patientDAO = new PatientJDBCImpl();
        Patient retrievedPatient = patientDAO.getPatientById(validPatientId);

        // Assert
        assertNotNull(retrievedPatient);
        assertEquals(validPatientId, retrievedPatient.getId());
        // You can further assert other patient details based on your data
    }

    @Test
    public void testGetPatientById_InvalidId() throws SQLException {
        // Arrange
        int invalidPatientId = -1;

        // Act
        PatientJDBCImpl patientDAO = new PatientJDBCImpl();
        Patient retrievedPatient = patientDAO.getPatientById(invalidPatientId);

        // Assert
        assertNull(retrievedPatient);
    }

    @Test
    public void testRegisterPatient_ValidPatient() throws SQLException {
        // Arrange
        String testName = "John Doe";
        String testEmail = "johndoe@example.com";
        String testAddress = "123 Main St";
        String testGender = "Male";
        String testPhoneNumber = "555-123-4567";
        List<String> testPrescriptions = new ArrayList<>();
        testPrescriptions.add("Medication X (2024-08-25)");
        List<String> testTests = new ArrayList<>();
        testTests.add("Blood Test (2024-08-25)");
        Patient newPatient = new Patient(0, testName, testEmail, testAddress, testGender, testPhoneNumber, testPrescriptions.toString(), testTests.toString()); // Note: using toString() for tests list

        // Act
        PatientJDBCImpl patientDAO = new PatientJDBCImpl();
        patientDAO.registerPatient(newPatient);

        // Assert
        // Consider using a separate method to verify the patient exists in the database
        // with the expected details (e.g., by retrieving patient by email or name)
    }

    @Test
    public void testUpdatePatient_ValidChanges() throws SQLException {
        // Arrange
        int existingPatientId = 2; // Replace with an existing patient ID

        // Act
        PatientJDBCImpl patientDAO = new PatientJDBCImpl();
        Patient existingPatient = patientDAO.getPatientById(existingPatientId);
        existingPatient.setName("Updated Name");
        existingPatient.setEmail("updated@example.com");
        patientDAO.updatePatient(existingPatient);

        // Assert
        // Consider using a separate method to verify the patient information is updated in the database
        // (e.g., by retrieving patient by ID and comparing details)
    }
}
