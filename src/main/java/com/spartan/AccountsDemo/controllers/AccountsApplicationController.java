/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spartan.AccountsDemo.controllers;

import com.spartan.AccountsDemo.services.AltaStoredProcedureI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author tagle
 */
@Controller
public class AccountsApplicationController {

    @Autowired
    private AltaStoredProcedureI altaStoredProcedure;
    
    @RequestMapping(path = "/ClientsApplication")
    public String index() {
        return "accounts_application/index";
    }

    @RequestMapping(path = "/altaStoredProcedure", produces = "application/json")
    @ResponseBody
    public Object altaStoredProcedure(
            @RequestParam (name = "nombre")String nombre,
            @RequestParam (name = "telefono")String telefono,
            @RequestParam (name = "direccion")String direccion,
            @RequestParam (name = "correo")String correo,
            @RequestParam (name = "saldo_in")Double saldo_in
    ) {
        String ejecutivo = "executed by anonymous";
        return altaStoredProcedure.callAlta(nombre, telefono, direccion, correo, saldo_in, ejecutivo);
    }

}

