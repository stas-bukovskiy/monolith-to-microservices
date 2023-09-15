package warehouse.service;

import warehouse.dto.StockClerkDto;

public interface StockClerkService {
    StockClerkDto findById(Long stockClerkId);
}
