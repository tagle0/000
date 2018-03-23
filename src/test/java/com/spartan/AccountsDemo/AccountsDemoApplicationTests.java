package com.spartan.AccountsDemo;

import com.spartan.AccountsDemo.dao.pojo.AltaStoredProcedureResponse;
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
        AltaStoredProcedureResponse altaStoredProcedureResponse = altaStoredProcedureService.callAlta(nombre, telefono, direccion, correo, saldo, ejecutivo);
        Optional<Cliente> optionalClient = clienteCrudRepository.findById(altaStoredProcedureResponse.getClienteId());
        Assert.assertTrue("Client " + altaStoredProcedureResponse.getClienteId() + " was not recovered from DB", optionalClient.isPresent());
        if (optionalClient.isPresent()) {
            Cliente cliente = optionalClient.get();
            Assert.assertEquals("Nombre was not recovered correctly (" + cliente.getNombre() + ")", nombre, cliente.getNombre());
            Assert.assertEquals("Nombre was not recovered correctly (" + cliente.getCorreo()+ ")", correo, cliente.getCorreo());
            Assert.assertEquals("Nombre was not recovered correctly (" + cliente.getTelefono()+ ")", telefono, cliente.getTelefono());
            Assert.assertEquals("Nombre was not recovered correctly (" + cliente.getDireccion()+ ")", direccion, cliente.getDireccion());
            Assert.assertNotEquals("Nombre was not recovered correctly (" + cliente.getId()+ ")", 0, cliente.getId());
        }
    }

}
