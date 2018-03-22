/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spartan.AccountsDemo.services;

import com.spartan.AccountsDemo.dao.pojo.AltaStoredProcedureResponse;

/**
 *
 * @author tagle
 */
public interface AltaStoredProcedureI {

    public AltaStoredProcedureResponse callAlta(String nombre,
            String telefono,
            String direccion,
            String correo,
            Double saldo_in,
            String ejecutivo);

}
