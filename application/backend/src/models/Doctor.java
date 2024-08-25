package models;

import java.time.LocalDate;
import java.util.Arrays;

public class Doctor extends User {
    private String specialization;
    private int experience;
    private double rating;
    // private final boolean isDoctor = true;
    private boolean[] isAvailable = new boolean[3];
    private int[][] appointments;
    private LocalDate lastUpdated;



    public Doctor(int id, String name, String email, String password, String address, String phoneNumber, String specialization, int experience, double rating) {
        super(id, name, email, password, address, phoneNumber);
        this.specialization = specialization;
        this.experience = experience;
        this.rating = rating;

        //3 days of availability
        //24 hours of availability
        appointments = new int[3][24];
    }
    
    public Doctor(int id, String name, String email, String password, String address, String phoneNumber, String specialization, int experience, double rating, String isAvailableStr, String appointments, LocalDate lastUpdated) {
        super(id, name, email, password, address, phoneNumber);
        this.specialization = specialization;
        this.experience = experience;
        this.rating = rating;
        //3 days of availability
        //24 hours of availability
        strToIsAvailable(isAvailableStr);
        this.appointments = stringToAppointment(appointments);
    }

    //GETTERS AND SETTERS FOR ALL
    public String getSpecialization() {return specialization;}
    public void setSpecialization(String specialization) {this.specialization = specialization;}

    public int getExperience() {return experience;}
    public void setExperience(int experience) {this.experience = experience;}

    public double getRating() {return rating;}
    public void setRating(double rating) {this.rating = rating;}

    public void setAvailable(int day, boolean availability){isAvailable[day] = availability;}
    public boolean getAvailable(int day){return isAvailable[day];}
    public String isAvailableStr(){
        String str = "";
        for(int i = 0; i < 3; i++){
            if(isAvailable[i])
                str += "1,";
            else
                str += "0,";
        }
        return str;
    }
    public void strToIsAvailable(String isAvailableStr){
        String[] str = isAvailableStr().split(",");
        for(int i = 0; i < 3; i++){
            if(str[i].equals("1"))
                isAvailable[i] = true;
            else
                isAvailable[i] = false;
        }
    }

    public int[][] getAllAppointments() {return appointments;}
    public void setAllAppointments(int[][] appointments) {this.appointments = appointments;}
    public void initAvailableTime(int startTime, int endTime){
        for(int i = 0; i < startTime; i++){
            if(startTime >= 0 && startTime < 24){
                if(isAvailable[0])
                    appointments[0][i] = -1;
                if(isAvailable[1])
                    appointments[1][i] = -1;
                if(isAvailable[2])
                    appointments[2][i] = -1;
            }
        }

        for(int day = 0; day < 3; day++){
            if(isAvailable[day]){
                for(int hr = startTime; hr <= endTime; hr++){
                    if(startTime >= 0 && startTime < 24){
                        appointments[day][hr] = 0;
                    }
                }
            }
        }

        for(int i = endTime+1; i < endTime; i++){
            if(endTime >= 0 && endTime < 24){
                if(isAvailable[0])
                    appointments[0][i] = -1;
                if(isAvailable[1])
                    appointments[1][i] = -1;
                if(isAvailable[2])
                    appointments[2][i] = -1;
            }
        }
    }
    public void clearAllAppointments(){appointments = new int[3][24];}

    public boolean checkSlotAvailability(int slotTime, int numDay){

        if(appointments[numDay-1][slotTime] != 0)
            return false;
        return true;
    }

//    public boolean checkSlotAvailability(int day, int hour) {
//        // Ensure day is within bounds (0-2 for 3 days)
//        if (day < 0 || day >= 3 || hour < 0 || hour >= 24) {
//            System.out.println("Invalid day or hour requested");
//            return false; // Return false if index is out of bounds
//        }
//        return this.appointments[day][hour] == 0; // Assuming 0 means available
//    }


    public void addAppointment(int slotTime, int numDay, int patientID){
        if(checkSlotAvailability(slotTime, numDay))
            appointments[numDay-1][slotTime] = patientID;
    }

//    public boolean addAppointment(int day, int hour) {
//        // Check for valid day and hour index
//        if (day < 0 || day >= 3 || hour < 0 || hour >= 24) {
//            System.out.println("Cannot add appointment: Invalid day or hour");
//            return false;
//        }
//
//        if (checkSlotAvailability(day, hour)) {
//            this.appointments[day][hour] = 1; // Assuming 1 means booked
//            return true;
//        } else {
//            System.out.println("Slot not available");
//            return false;
//        }
//    }

    public void removeAppointment(int slotTime, int numDay){
        appointments[numDay-1][slotTime] = 0;
    }
    public void updateAppointment(int numDay, int slotTime, int patientID){
        
        appointments[numDay-1][slotTime] = patientID;
    }

    public int getAppointment(int day, int time){
        return appointments[day][time];
    }

    
    public String appointmentString(int[][] appointments){
        String str = Arrays.toString(appointments[0]) + "\n" 
                    + Arrays.toString(appointments[1]) + "\n" 
                    + Arrays.toString(appointments[2]);
        
        return str;
    }

//    public int[][] stringToAppointment(String appointments){
//        String[] days = appointments.split("\n");
//        int[][] appts = new int[3][24];
//        for(int i = 0; i < 3; i++){
//            String[] day = days[i].split(",");
//            for(int j = 0; j < 24; j++){
//                System.out.println(day[j]);
//                appts[i][j] = Integer.parseInt(day[j]);
//            }
//        }
//        return appts;
//    }

    public int[][] stringToAppointment(String appointments) {
        // Check for invalid input such as "[]" or null/empty string
        if (appointments == null || appointments.trim().equals("[]")) {
            System.out.println("Empty or invalid appointment string detected");
            return new int[3][24]; // Return an empty appointment grid
        }

        try {
            String[] days = appointments.split("\n");
            int[][] appts = new int[3][24];

            for (int i = 0; i < days.length && i < 3; i++) { // Ensure we don't exceed bounds
                String[] day = days[i].split(",");

                for (int j = 0; j < day.length && j < 24; j++) { // Ensure we don't exceed bounds
                    appts[i][j] = Integer.parseInt(day[j].trim());
                }
            }

            return appts;
        } catch (Exception e) {
            System.out.println("Error parsing appointment data: " + e.getMessage());
            e.printStackTrace();
            return new int[3][24]; // Return an empty schedule on error
        }
    }




    public LocalDate getLastUpdated() {return lastUpdated;}
    public void setLastUpdated(LocalDate lastUpdated) {this.lastUpdated = lastUpdated;}

    //TO STRING
    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                // ", password='" + getPassword() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", specialization='" + specialization + '\'' +
                ", experience='" + experience + '\'' +
                ", rating=" + rating +
                ", appointments=[Day 1:" + Arrays.toString(appointments[0]) +
                ", Day 2:" + Arrays.toString(appointments[1]) +
                ", Day 3:" + Arrays.toString(appointments[2]) + "]" + '\'' +
                "}";
    }

    // public boolean isDoctor() {
    //     return isDoctor;
    // }

    // public void setDoctor(boolean doctor) {
    //     isDoctor = doctor;
    // }

    //EQUALS METHOD

    //HASH CODE: BASED ON SPECIALIZATION
    @Override
    public int hashCode() {
        return specialization != null ? specialization.hashCode() : 0;
    }
}
