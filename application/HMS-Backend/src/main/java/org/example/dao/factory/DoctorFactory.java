package org.example.dao.factory;

import org.example.dao.impl.*;
import org.example.dao.intf.DoctorIntf;
import org.example.dao.impl.DoctorJDBCImpl;
import org.example.dao.impl.DoctorListImpl;


import java.sql.SQLException;

public class DoctorFactory {
    //doctor factory class to select type of implementation
    public static DoctorIntf getDoctorFactory(String type) throws SQLException {
        if (type.equalsIgnoreCase("impl")) {
            return (DoctorIntf) new DoctorListImpl();
        }
        else if(type.equalsIgnoreCase("jdbc")) {
            return (DoctorIntf) new DoctorJDBCImpl();
        }
        return null;
    }
}
