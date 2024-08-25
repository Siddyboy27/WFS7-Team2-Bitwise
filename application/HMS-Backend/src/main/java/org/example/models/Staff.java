package org.example.models;

import java.util.Objects;

public class Staff extends User{

    public Staff(int id, String name, String email, String password, String address, String phoneNumber) {
        super(id, name, email, password, address, phoneNumber);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + this.getId() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", address='" + this.getAddress() + '\'' +
                ", phoneNumber='" + this.getPhoneNumber() + '\'' +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff staff)) return false;

        return this.getId() == staff.getId() && Objects.equals(this.getPhoneNumber(), staff.getPhoneNumber());
    }
}
