package com.spartan.AccountsDemo;

import com.spartan.AccountsDemo.pojo.Cliente;
import com.spartan.AccountsDemo.repositories.ClienteCrudRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.spartan.AccountsDemo.services.AltaStoredProcedureServiceI;
import java.util.Optional;
import org.junit.Assert;

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
        Cliente cliente = altaStoredProcedureService.callAlta(nombre, telefono, direccion, correo, saldo, ejecutivo);
        Assert.assertNotEquals("Client not created", cliente);
        if (cliente != null) {
            Assert.assertEquals("Nombre was not recovered correctly", nombre, cliente.getNombre());
            Assert.assertEquals("Correo was not recovered correctly", correo, cliente.getCorreo());
            Assert.assertEquals("Telefono was not recovered correctly", telefono, cliente.getTelefono());
            Assert.assertEquals("Direccion was not recovered correctly", direccion, cliente.getDireccion());
            Assert.assertNotEquals("Id was not recovered correctly", 0, cliente.getId());
            Assert.assertNotNull("Account not created correctly", cliente.getCuentas());
            if (cliente.getCuentas() != null) {
                Assert.assertFalse("No accounts created", cliente.getCuentas().isEmpty());
                if(!cliente.getCuentas().isEmpty()){
                    Assert.assertEquals(saldo, cliente.getCuentas().get(0).getSaldo());
                    Assert.assertEquals(ejecutivo, cliente.getCuentas().get(0).getEjecutivo());
                    
                }
            }
        }
    }

}
