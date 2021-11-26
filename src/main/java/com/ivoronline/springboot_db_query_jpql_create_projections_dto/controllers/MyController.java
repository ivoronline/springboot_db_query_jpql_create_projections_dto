package com.ivoronline.springboot_db_query_jpql_create_projections_dto.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivoronline.springboot_db_query_jpql_create_projections_dto.dto.PersonDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@RestController
public class MyController {

  @PersistenceContext EntityManager entityManager;

  //================================================================
  // RETURN PERSON DTO
  //================================================================
  @RequestMapping("ReturnPersonDTO")
  PersonDTO returnPersonDTO() throws JsonProcessingException {

    //CREATE QUERY
    String select = "SELECT person.name, person.age FROM Person person WHERE person.name = :name";
    Query  query  = entityManager.createQuery(select);
           query.setParameter("name", "John");

    //SELECT OBJECT ARRAY
    Object[] columns = (Object[]) query.getSingleResult(); //["John",20]

    //DISPLAY COLUMNS
    String columnsJSON = new ObjectMapper().writeValueAsString(columns);
    System.out.println(columnsJSON);

    //MAP COLUMNS INTO DTO
    PersonDTO personDTO      = new PersonDTO();
              personDTO.name = (String ) columns[0];
              personDTO.age  = (Integer) columns[1];

    //RETURN OBJECT ARRAY
    return personDTO;

  }

}


