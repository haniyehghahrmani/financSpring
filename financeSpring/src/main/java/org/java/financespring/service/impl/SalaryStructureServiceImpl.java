package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.SalaryStructure;
import org.java.financespring.repository.SalaryStructureRepository;
import org.java.financespring.service.SalaryStructureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryStructureServiceImpl implements SalaryStructureService {

    private final SalaryStructureRepository repository;

    public SalaryStructureServiceImpl(SalaryStructureRepository repository) {
        this.repository = repository;
    }

    @Override
    public SalaryStructure save(SalaryStructure salaryStructure) {
        return repository.save(salaryStructure);
    }

    @Override
    public SalaryStructure edit(Long id, SalaryStructure salaryStructure) throws NoContentException {
        SalaryStructure existingSalaryStructure = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active SalaryStructure Was Found with id " + id + " To Update!")
                );
        existingSalaryStructure.setEmployee(salaryStructure.getEmployee());
        existingSalaryStructure.setBaseSalary(salaryStructure.getBaseSalary());
        existingSalaryStructure.setHousingAllowance(salaryStructure.getHousingAllowance());
        existingSalaryStructure.setChildrenAllowance(salaryStructure.getChildrenAllowance());
        existingSalaryStructure.setTransportationAllowance(salaryStructure.getTransportationAllowance());
        existingSalaryStructure.setMealAllowance(salaryStructure.getMealAllowance());
        existingSalaryStructure.setInsuranceDeduction(salaryStructure.getInsuranceDeduction());
        existingSalaryStructure.setTaxDeduction(salaryStructure.getTaxDeduction());
        existingSalaryStructure.setOtherDeductions(salaryStructure.getOtherDeductions());

        return repository.saveAndFlush(existingSalaryStructure);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        repository.findSalaryStructureByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active SalaryStructure Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public Optional<SalaryStructure> findSalaryStructureByIdAndDeletedFalse(Long id) throws NoContentException {
        Optional<SalaryStructure> optional = repository.findSalaryStructureByIdAndDeletedFalse(id);
        if (optional.isPresent()) {
            return optional;
        } else {
            throw new NoContentException("No Active SalaryStructure Was Found with id : " + id);
        }
    }

    @Override
    public List<SalaryStructure> findAll() {
        return repository.findAll();
    }

    @Override
    public SalaryStructure findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No salaryStructure found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
