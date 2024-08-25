//import viewlayer.AdminView;
import viewlayer.AdminView;
import viewlayer.DoctorView;
//import viewlayer.UserView;
//import viewlayer.UserView;

import java.sql.SQLException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Hospital Management System");
            System.out.println("1. Admin Login");
            System.out.println("2. Doctor Login");
            System.out.println("3. User Login");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleAdminLogin();
                    break;
                case 2:
                    handleDoctorLogin();
                    break;
                case 3:
                  //  handleUserLogin();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void handleAdminLogin() {
        // Simulate Admin login
        System.out.println("Admin logged in successfully.");
        AdminView adminView = null;
        try {
            adminView = new AdminView();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        adminView.displayAdminMenu();
    }


    private static void handleDoctorLogin() {
        // Simulate Doctor login
        System.out.println("Doctor logged in successfully.");
        try {
            DoctorView view = new DoctorView();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your doctor ID to log in: ");
            int doctorId = scanner.nextInt();
            view.displayMenu(doctorId);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
//        private static void handleUserLogin() {
//            // Simulate User login
//            System.out.println("User logged in successfully.");
//            UserView userView = new UserView();
//            userView.displayMenu();
//        }
    }


