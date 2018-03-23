/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spartan.AccountsDemo.controllers;

import com.spartan.AccountsDemo.pojo.Cliente;
import com.spartan.AccountsDemo.pojo.Cuenta;
import com.spartan.AccountsDemo.repositories.ClienteCrudRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.spartan.AccountsDemo.services.AltaStoredProcedureServiceI;

/**
 *
 * @author tagle
 */
@Controller
public class AccountsApplicationController {

    @Autowired
    private AltaStoredProcedureServiceI altaStoredProcedure;
    
    @Autowired
    private ClienteCrudRepository clienteCrudRepository;
    
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
    
    
    
    @RequestMapping(path = "/ClientsApplication/fullClientsTable")
    public String fullClientsTable(Model model){
        model.addAttribute("clientes", clienteCrudRepository.findAll());
        return "accounts_application/fullClientsTable";
    }
    
    @RequestMapping(path = "/ClientesApplication/addClient", produces="application/json", method = RequestMethod.POST)
    @ResponseBody
    public String addClient(
            @RequestParam String correo, 
            @RequestParam String direccion, 
            @RequestParam int id, 
            @RequestParam String nombre, 
            @RequestParam String telefono){
        Cliente cliente = new Cliente();
        cliente.setCorreo(correo);
        cliente.setDireccion(direccion);
        cliente.setId(id);
        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);
        clienteCrudRepository.save(cliente);
        return "accounts_application/index";
    }
    
    
    @RequestMapping(path = "/ClientesApplication/addAccount", produces="application/json")
    public String addAccount(
            @RequestParam Double saldo, 
            @RequestParam int cliente_id){
        Cuenta cuenta = new Cuenta();
        cuenta.setSaldo(saldo);
        cuenta.setEjecutivo("anonymous");
        Optional<Cliente> cliente = clienteCrudRepository.findById(cliente_id);
        if(cliente.isPresent()){
            cuenta.setCliente(cliente.get());
        }
        return "accounts_application/index";
    }
}

