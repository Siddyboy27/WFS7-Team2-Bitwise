package org.example.models;

public class Admin extends Staff {
    // private final boolean isAdmin = true;
    public Admin(int id, String name, String email, String password, String address, String phoneNumber) {
        super(id, name, email, password, address, phoneNumber);
    }

    
    //TO STRING
    @Override
    public String toString() {
        return "Admin{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                // ", password='" + getPassword() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                '}';
    }
}
