package app.warehouse.service;

import app.common.search.PageSearchResult;
import app.warehouse.dto.StockDto;
import app.warehouse.projection.StockProjection;
import app.warehouse.search.StockSearchCriteria;

import java.time.LocalDateTime;
import java.util.List;

public interface StockService {

    List<StockProjection> findStockByProductAndDate(String productCode, LocalDateTime date);

    List<StockDto> findAll();

    PageSearchResult<StockDto> search(StockSearchCriteria criteria);

}
