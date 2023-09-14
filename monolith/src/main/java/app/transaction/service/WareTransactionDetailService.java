package app.transaction.service;

import app.transaction.dto.WareTransactionDetailDto;

import java.util.List;

public interface WareTransactionDetailService {

    WareTransactionDetailDto findById(Long id);

    List<WareTransactionDetailDto> findByWareTransactionId(Long wareTransactionId);
}
