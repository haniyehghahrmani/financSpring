package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.pgmodel.Bank;
import org.java.financespring.repository.pgrepo.BankRepository;
import org.java.financespring.service.BankService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    private final BankRepository repository;

    public BankServiceImpl(BankRepository repository) {
        this.repository = repository;
    }

    @Override
    public Bank save(Bank bank) {
        return repository.save(bank);
    }

    @Override
    public Bank edit(Long id, Bank bank) throws NoContentException {
        Bank existingBank = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Bank Found with id " + id + " To Update!")
                );

        existingBank.setBankName(bank.getBankName());
        existingBank.setSwiftCode(bank.getSwiftCode());
        existingBank.setIban(bank.getIban());
        existingBank.setRoutingNumber(bank.getRoutingNumber());
        existingBank.setAddress(bank.getAddress());
        existingBank.setContactNumber(bank.getContactNumber());
        existingBank.setWebsite(bank.getWebsite());
        existingBank.setCountry(bank.getCountry());
        existingBank.setIsActive(bank.getIsActive());

        return repository.saveAndFlush(existingBank);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void logicalRemove(Long id) throws NoContentException {
        repository.findBankByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Bank Was Found with id " + id + " To Remove !")
        );
        repository.logicalRemove(id);
    }

    @Override
    public List<Bank> findAll() {
        return repository.findAll();
    }

    @Override
    public Bank findById(Long id) {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new NoContentException("No bank found with id " + id));
        } catch (NoContentException e) {
            throw new RuntimeException(e);
        }
    }
}
