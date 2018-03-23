/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spartan.AccountsDemo.repositories;

import com.spartan.AccountsDemo.pojo.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author tagle
 */
@RepositoryRestResource(collectionResourceRel = "cliente", path = "cliente")
public interface ClienteCrudRepository extends CrudRepository<Cliente, Integer>{
    
}
