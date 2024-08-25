package dao.factory;

import dao.impl.*;
import dao.intf.DoctorIntf;

import java.sql.SQLException;

public class DoctorFactory {
    //doctor factory class to select type of implementation
    public static DoctorIntf getDoctorFactory(String type) throws SQLException {
        if (type.equalsIgnoreCase("impl")) {
            return new DoctorListImpl();
        }
        else if(type.equalsIgnoreCase("jdbc")) {
            return new DoctorJDBCImpl();
        }
        return null;
    }
}
