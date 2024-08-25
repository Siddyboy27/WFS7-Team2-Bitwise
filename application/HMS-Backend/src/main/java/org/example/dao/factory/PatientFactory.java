package org.example.dao.factory;

import org.example.dao.impl.PatientJDBCImpl;
import org.example.dao.impl.PatientListImpl;
import org.example.dao.intf.PatientIntf;

import java.sql.SQLException;

public class PatientFactory {
    //doctor factory class to select type of implementation
    public static PatientIntf getDoctorFactory(String type) throws SQLException {
        if (type.equalsIgnoreCase("impl")) {
            return (PatientIntf) new PatientListImpl();
        }
        else if(type.equalsIgnoreCase("jdbc")) {
            return (PatientIntf) new PatientJDBCImpl();
        }
        return null;
    }
}
