package dao.factory;

import dao.impl.PatientJDBCImpl;
import dao.impl.PatientListImpl;
import dao.intf.PatientIntf;

import java.sql.SQLException;

public class PatientFactory {
    //doctor factory class to select type of implementation
    public static PatientIntf getDoctorFactory(String type) throws SQLException {
        if (type.equalsIgnoreCase("impl")) {
            return new PatientListImpl();
        }
        else if(type.equalsIgnoreCase("jdbc")) {
            return new PatientJDBCImpl();
        }
        return null;
    }
}
