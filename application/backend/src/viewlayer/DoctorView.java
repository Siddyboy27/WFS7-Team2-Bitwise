package viewlayer;

import dao.impl.DoctorJDBCImpl;
import dao.impl.PatientJDBCImpl;
import models.Doctor;
import java.sql.SQLException;
import java.util.Scanner;

public class DoctorView {
    private DoctorJDBCImpl doctorDAO;
    private PatientJDBCImpl patientDAO;
    private Scanner scanner;

    public DoctorView() throws SQLException {
        this.doctorDAO = new DoctorJDBCImpl();
        this.patientDAO = new PatientJDBCImpl();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu(int doctorId) {
        int option = -1;
        while (option != 6) {
            System.out.println("\nDoctor Menu:");
            System.out.println("1. Add Schedule");
            System.out.println("2. Update Schedule");
            System.out.println("3. View Appointments");
            System.out.println("4. Suggest Medical Test");
            System.out.println("5. Suggest Medicines");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addSchedule(doctorId);
                    break;
                case 2:
                    updateSchedule(doctorId);
                    break;
                case 3:
                    viewAppointments(doctorId);
                    break;
                case 4:
                    suggestMedicalTest();
                    break;
                case 5:
                    suggestMedicines();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addSchedule(int doctorId) {
        System.out.println("Enter the day of the appointment (0-6): ");
        int day = scanner.nextInt();
        System.out.println("Enter the time of the appointment (hour): ");
        int time = scanner.nextInt();
        System.out.println("Enter the patient ID: ");
        int patientId = scanner.nextInt();

        doctorDAO.addAppointment(doctorId, day, time, patientId);
        System.out.println("Appointment added successfully!");
    }

    private void updateSchedule(int doctorId) {
        System.out.println("Enter the day of the appointment to update (0-6): ");
        int day = scanner.nextInt();
        System.out.println("Enter the time of the appointment to update (hour): ");
        int time = scanner.nextInt();
        System.out.println("Enter the new patient ID: ");
        int patientId = scanner.nextInt();

        if (doctorDAO.updateAppointment(doctorId, day, time, patientId)) {
            System.out.println("Appointment updated successfully!");
        } else {
            System.out.println("Failed to update appointment.");
        }
    }

    private void viewAppointments(int doctorId) {
        System.out.println("Enter the day to view appointments (0-6): ");
        int day = scanner.nextInt();

        int[] appointments = doctorDAO.viewAppointments(doctorId, day);
        if (appointments.length > 0) {
            System.out.println("Appointments for day " + day + ": ");
            for (int appointment : appointments) {
                System.out.println("Patient ID: " + appointment);
            }
        } else {
            System.out.println("No appointments found for this day.");
        }
    }

    private void suggestMedicalTest() {
        System.out.println("Enter the patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter the medical test description: ");
        String testDescription = scanner.nextLine();

        patientDAO.addTest(patientId, testDescription);
        System.out.println("Medical test added successfully!");
    }

    private void suggestMedicines() {
        System.out.println("Enter the patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter the medicine description: ");
        String prescription = scanner.nextLine();

        patientDAO.addPrescription(patientId, prescription);
        System.out.println("Prescription added successfully!");
    }


    }

