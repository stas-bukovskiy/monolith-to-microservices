package app.transaction.service;

import app.transaction.dto.WareTransactionDto;

import java.util.List;

public interface WareTransactionService {

    WareTransactionDto findById(Long id);

    List<WareTransactionDto> findAll();

    WareTransactionDto save(WareTransactionDto wareTransactionDto);

    void deleteById(Long id);

}
