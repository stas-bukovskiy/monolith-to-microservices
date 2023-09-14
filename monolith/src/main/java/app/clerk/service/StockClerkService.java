package app.clerk.service;

import app.clerk.dto.StockClerkDto;

import java.util.List;

public interface StockClerkService {

    StockClerkDto findById(Long id);

    List<StockClerkDto> findAll();

    StockClerkDto save(StockClerkDto stockClerkDto);

    void deleteById(Long id);
}
