package app.warehouse.service;

import app.warehouse.dto.WareTransactionDetailDto;

import java.util.List;

public interface WareTransactionDetailService {

    WareTransactionDetailDto findById(Long id);

    List<WareTransactionDetailDto> findByWareTransactionId(Long wareTransactionId);
}
