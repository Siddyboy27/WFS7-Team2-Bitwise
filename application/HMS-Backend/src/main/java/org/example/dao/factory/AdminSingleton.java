package org.example.dao.factory;

import org.example.models.Admin;

public class AdminSingleton {
    //only one admin instance is created and used in the system overall
    private static Admin adminInstance;

    private AdminSingleton() {
    }

    // Public static method to provide the single instance of Admin
    public static Admin getAdminInstance() {
        if (adminInstance == null) {
                
            adminInstance = new Admin(1001, "Admin", "admin@gmail.com", "MyHospital#123", "ChurchStreet, Bangalore", "9876543210");
                
        }
        return adminInstance;
    }
}
