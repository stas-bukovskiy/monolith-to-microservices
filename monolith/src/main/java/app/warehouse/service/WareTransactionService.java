package app.warehouse.service;

import app.warehouse.dto.WareTransactionDto;

import java.util.List;

public interface WareTransactionService {

    WareTransactionDto findById(Long id);

    List<WareTransactionDto> findAll();

    WareTransactionDto save(WareTransactionDto wareTransactionDto);

    void deleteById(Long id);

}
