package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.Person;
import org.java.financespring.repository.pgrepo.PersonRepository;
import org.java.financespring.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public Person edit(Long id, Person person) throws NoContentException {
        Person existingPerson = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Person Was Found with id " + id + " To Update!")
                );
        existingPerson.setName(person.getName());
        existingPerson.setLastname(person.getLastname());
        existingPerson.setNationalId(person.getNationalId());
        existingPerson.setBirthdate(person.getBirthdate());
        existingPerson.setGender(person.getGender());
        existingPerson.setEditing(true);

        return repository.saveAndFlush(existingPerson);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Person logicalRemove(Long id) throws NoContentException {
        repository.findPersonByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Person Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
        return null;
    }

    @Override
    public Optional<Person> findPersonByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<Person> optional = repository.findPersonByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active Person Was Found with id : " + id);
        }
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public Person findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No person found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
