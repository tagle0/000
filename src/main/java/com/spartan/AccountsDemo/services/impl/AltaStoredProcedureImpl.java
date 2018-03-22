/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spartan.AccountsDemo.services.impl;

import com.spartan.AccountsDemo.dao.pojo.AltaStoredProcedureResponse;
import com.spartan.AccountsDemo.dao.procedures.AltaStoredProcedure;
import com.spartan.AccountsDemo.services.AltaStoredProcedureI;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author tagle
 */
@Service("altaStoredProcedure")
@Repository
public class AltaStoredProcedureImpl implements AltaStoredProcedureI{

    @Autowired
    DataSource dataSource;
    
    @Override
    public AltaStoredProcedureResponse callAlta(String nombre, String telefono, String direccion, String correo, Double saldo_in, String ejecutivo) {
        AltaStoredProcedure a = new AltaStoredProcedure(dataSource);
        return a.execute(nombre, telefono, direccion, correo, saldo_in, ejecutivo);
    }

    
    
}
