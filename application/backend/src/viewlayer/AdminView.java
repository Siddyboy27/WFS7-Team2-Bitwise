package viewlayer;

import bl.AdminBL;
import models.Doctor;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AdminView {
    private final AdminBL adminBL;

    public AdminView() throws SQLException {
        this.adminBL = new AdminBL();  // Initialize AdminBL
    }

    public void displayAdminMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Import Doctor by ID");
            System.out.println("2. Show All Doctors by Specialization");
            System.out.println("3. Update Doctor Schedule");
            System.out.println("4. Cancel Appointments");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> importDoctorById();
                case 2 -> showAllDoctorsBySpecialization();
                case 3 -> updateDoctorSchedule();
                case 4 -> cancelAppointment();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);
    }

    // Method to import doctor by ID
    private void importDoctorById() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Doctor ID: ");
        int doctorID = sc.nextInt();
        Doctor doctor = adminBL.getDoctorById(doctorID);

        if (doctor != null) {
            System.out.println("Doctor Found: " + doctor.getName() + " | Specialization: " + doctor.getSpecialization());
        } else {
            System.out.println("Doctor with ID " + doctorID + " not found.");
        }
    }

    // Method to show all doctors based on specialization
    private void showAllDoctorsBySpecialization() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Doctor Specialization: ");
        String specialization = sc.nextLine();

        List<Doctor> doctors = adminBL.getDoctorsBySpecialization(specialization);

        if (doctors.isEmpty()) {
            System.out.println("No doctors found with specialization: " + specialization);
        } else {
            System.out.println("Doctors with specialization " + specialization + ":");
            for (Doctor doctor : doctors) {
                System.out.println("ID: " + doctor.getId() + " | Name: " + doctor.getName());
            }
        }
    }

    // Method to update doctor's appointment schedule
    private void updateDoctorSchedule() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Doctor ID: ");
        int doctorID = sc.nextInt();
        System.out.print("Enter Day (0-6 for Monday-Sunday): ");
        int day = sc.nextInt();
        System.out.print("Enter Time Slot: ");
        int time = sc.nextInt();
        System.out.print("Enter Patient ID: ");
        int patientID = sc.nextInt();

        adminBL.updateDoctorAppointment(doctorID, day, time, patientID);
        System.out.println("Appointment Updated Successfully.");
    }

    // Method to cancel doctor's appointment
    private void cancelAppointment() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Doctor ID: ");
        int doctorID = sc.nextInt();
        System.out.print("Enter Day (0-6 for Monday-Sunday): ");
        int day = sc.nextInt();
        System.out.print("Enter Time Slot: ");
        int time = sc.nextInt();

        adminBL.cancelAppointment(doctorID, day, time);
        System.out.println("Appointment Canceled Successfully.");
    }
}
