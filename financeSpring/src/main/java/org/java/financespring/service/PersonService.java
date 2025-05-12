package org.java.financespring.service;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Person save(Person person);

    Person edit(Long id, Person person) throws NoContentException;

    void remove(Long id);

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    Optional<Person> findPersonByIdAndDeletedFalse(Long id) throws NoContentException;

    List<Person> findAll();

    Person findById(Long id);

}
