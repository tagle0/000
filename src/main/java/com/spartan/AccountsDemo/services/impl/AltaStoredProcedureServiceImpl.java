/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spartan.AccountsDemo.services.impl;

import com.spartan.AccountsDemo.dao.pojo.AltaStoredProcedureResponse;
import com.spartan.AccountsDemo.dao.procedures.AltaStoredProcedure;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.spartan.AccountsDemo.services.AltaStoredProcedureServiceI;

/**
 *
 * @author tagle
 */
@Service("altaStoredProcedureService")
@Repository
public class AltaStoredProcedureServiceImpl implements AltaStoredProcedureServiceI{

    @Autowired
    DataSource dataSource;
    
    @Override
    public AltaStoredProcedureResponse callAlta(String nombre, String telefono, String direccion, String correo, Double saldo_in, String ejecutivo) {
        AltaStoredProcedure a = new AltaStoredProcedure(dataSource);
        AltaStoredProcedureResponse response = new AltaStoredProcedureResponse();
        try {
              CallableStatement cs = dataSource.getConnection().prepareCall("call alta(?,?,?,?,?,?,?,?,?)");
            cs.setString(1, nombre);
            cs.setString(2, telefono);
            cs.setString(3, direccion);
            cs.setString(4, correo);
            cs.setDouble(5, saldo_in);
            cs.setString(6, ejecutivo);
            cs.registerOutParameter(7, java.sql.Types.INTEGER);
            cs.registerOutParameter(8, java.sql.Types.INTEGER);
            cs.registerOutParameter(9, java.sql.Types.DECIMAL);
            cs.executeUpdate();
            response.setClienteId(cs.getInt(7));
            response.setCuentaId(cs.getInt(8));
            response.setSaldo(cs.getDouble(9));
        } catch (SQLException ex) {
            Logger.getLogger(AltaStoredProcedureServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    
    
}
