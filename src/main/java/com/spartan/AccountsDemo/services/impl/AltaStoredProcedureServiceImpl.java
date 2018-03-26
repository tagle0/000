/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spartan.AccountsDemo.services.impl;

import com.spartan.AccountsDemo.pojo.Cliente;
import com.spartan.AccountsDemo.repositories.ClienteCrudRepository;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.spartan.AccountsDemo.services.AltaStoredProcedureServiceI;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author tagle
 */
@Service("altaStoredProcedureService")
@Repository
public class AltaStoredProcedureServiceImpl implements AltaStoredProcedureServiceI{

    @Autowired
    EntityManager entityManager;
    
    @Autowired
    ClienteCrudRepository clienteCrudRepository;
    
    @Override
    public Cliente callAlta(String nombre, String telefono, String direccion, String correo, Double saldo_in, String ejecutivo) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("alta");
        storedProcedureQuery.registerStoredProcedureParameter("param_nombre", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("param_nombre", nombre);
        storedProcedureQuery.registerStoredProcedureParameter("param_telefono", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("param_telefono", telefono);
        storedProcedureQuery.registerStoredProcedureParameter("param_direccion", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("param_direccion", direccion);
        storedProcedureQuery.registerStoredProcedureParameter("param_correo", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("param_correo", correo);
        storedProcedureQuery.registerStoredProcedureParameter("param_saldo_in", Double.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("param_saldo_in", saldo_in);
        storedProcedureQuery.registerStoredProcedureParameter("param_ejecutivo", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("param_ejecutivo", ejecutivo);
        storedProcedureQuery.registerStoredProcedureParameter("param_cliente_id", Integer.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("param_cuenta_id", Integer.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter("param_saldo", Double.class, ParameterMode.OUT);
        storedProcedureQuery.execute();
        int clienteId = (int) storedProcedureQuery.getOutputParameterValue("param_cliente_id");
        return clienteCrudRepository.findById(clienteId).get();
    }

    
    
}
