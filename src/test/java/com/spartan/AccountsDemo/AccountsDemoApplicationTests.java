package com.spartan.AccountsDemo;

import com.spartan.AccountsDemo.dao.pojo.AltaStoredProcedureResponse;
import com.spartan.AccountsDemo.repositories.ClienteCrudRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.test.context.junit4.SpringRunner;
import com.spartan.AccountsDemo.services.AltaStoredProcedureServiceI;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountsDemoApplicationTests {

    @Autowired
    AltaStoredProcedureServiceI altaStoredProcedureService;
    
    @Autowired
    ClienteCrudRepository clienteCrudRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void storedProcedureTest() {
        String nombre = "Alejandro Tagle";
        String telefono = "2221981741";
        String direccion = "Came house";
        String correo = "mail@domain.com";
        Double saldo = 999.99d;
        String ejecutivo = "Anonymous test";
        AltaStoredProcedureResponse altaStoredProcedureResponse = altaStoredProcedureService.callAlta(nombre, telefono, direccion, correo, Double.NaN, ejecutivo);
    }

}
