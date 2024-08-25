package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.sql.SQLException;
import org.example.dao.factory.DoctorFactory;
import org.example.models.Doctor;
import org.example.dao.impl.DoctorJDBCImpl;

import org.junit.Test;

public class DoctorJdbcDaoTest {
    private DoctorJDBCImpl doctorDAO;

    {
        try {
            doctorDAO = (DoctorJDBCImpl) DoctorFactory.getDoctorFactory("jdbc");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGetDoctorById_ValidId() throws SQLException {
        int doctorId = 1; // Replace with a known valid ID
        Doctor expectedDoctor = new Doctor(doctorId, "John Doe", "johndoe@example.com", "password", "123 Main St", "555-123-4567", "Cardiology", 5, 4.8); // Replace with expected values

        // Act
        Doctor actualDoctor = doctorDAO.getDoctorById(doctorId);

        // Assert
//        assertNotNull(actualDoctor);
        assertEquals(expectedDoctor, actualDoctor, "Not the same doctor");
    }

    @Test
    public void testGetDoctorById_InvalidId() throws SQLException {
        // Arrange
        int invalidDoctorId = -1;

        // Act
        Doctor actualDoctor = doctorDAO.getDoctorById(invalidDoctorId);

        // Assert
        assertNull(actualDoctor);
    }

    @Test
    public void testAddDoctor_ValidDoctor() throws SQLException {
        // Arrange
        Doctor newDoctor = new Doctor(100, "New Doctor", "newdoctor@example.com", "password", "456 Main St", "555-456-7890", "General Surgery", 3, 0.0);

        // Act
        doctorDAO.addDoctor(newDoctor);

        // Assert
        // Verify the doctor was added to the database (e.g., using a separate query or assertion mechanism)
    }

    @Test
    public void testAddDoctor_DuplicateId() throws SQLException {
        // Arrange
        Doctor existingDoctor = doctorDAO.getDoctorById(1); // Replace with an existing ID
        Doctor duplicateDoctor = new Doctor(existingDoctor.getId(), "Duplicate Name", "duplicatename@example.com", "password", "789 Main St", "555-789-0123", "Pediatrics", 7, 0.0);

        // Act
        try {
            doctorDAO.addDoctor(duplicateDoctor);
            fail("Expected SQLException due to duplicate ID");
        } catch (SQLException expectedException) {
            // Expected exception
        }
    }

    @Test
    public void testUpdateDoctor_ValidChanges() throws SQLException {
        // Arrange
        int existingDoctorId = 2; // Replace with an existing ID
        Doctor existingDoctor = doctorDAO.getDoctorById(existingDoctorId);
        existingDoctor.setName("Updated Name");
        existingDoctor.setExperience(existingDoctor.getExperience() + 1);

        // Act
        boolean updated = doctorDAO.updateDoctor(existingDoctor);

        // Assert
        assertTrue(updated);

        // Verify the updated information in the database (e.g., using a separate query or assertion mechanism)
    }

    @Test
    public void testUpdateDoctor_InvalidId() throws SQLException {
        // Arrange
        int invalidDoctorId = -1;
        Doctor invalidDoctor = new Doctor(invalidDoctorId, "Nonexistent Doctor", "nonexistent@example.com", "password", "000 Main St", "555-000-0000", "Unknown", 0, 0.0);

        // Act
        boolean updated = doctorDAO.updateDoctor(invalidDoctor);

        // Assert
        assertFalse(updated);
    }
}
