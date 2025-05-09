package org.java.financespring.service.impl;

import org.java.financespring.exception.NoContentException;
import org.java.financespring.model.CardPayment;
import org.java.financespring.repository.CardPaymentRepository;
import org.java.financespring.service.CardPaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardPaymentServiceImpl implements CardPaymentService {

    private final CardPaymentRepository repository;

    public CardPaymentServiceImpl(CardPaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public CardPayment save(CardPayment cardPayment) {
        return repository.save(cardPayment);
    }

    @Override
    public CardPayment edit(Long id, CardPayment cardPayment) throws NoContentException {
        CardPayment existingCardPayment = repository.findById(id)
                .orElseThrow(
                        () -> new NoContentException("No Active Card Payment Found with id " + id + " To Update!")
                );

        existingCardPayment.setCardNumber(cardPayment.getCardNumber());
        existingCardPayment.setCardHolderName(cardPayment.getCardHolderName());
        existingCardPayment.setExpiryDate(cardPayment.getExpiryDate());
        existingCardPayment.setCvv(cardPayment.getCvv());
        existingCardPayment.setAmount(cardPayment.getAmount());
        existingCardPayment.setDate(cardPayment.getDate());
        existingCardPayment.setTime(cardPayment.getTime());
        existingCardPayment.setPaymentMethodDoc(cardPayment.getPaymentMethodDoc());
        existingCardPayment.setDescription(cardPayment.getDescription());
        existingCardPayment.setIsActive(cardPayment.getIsActive());

        return repository.saveAndFlush(existingCardPayment);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CardPayment> findAll() {
        return repository.findAll();
    }

    @Override
    public CardPayment findById(Long id) throws NoContentException {
        return repository.findById(id)
                .orElseThrow(() -> new NoContentException("No Card Payment found with id " + id));
    }
}
