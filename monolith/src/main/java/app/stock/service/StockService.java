package app.stock.service;

import app.common.search.PageSearchResult;
import app.stock.dto.StockDto;
import app.stock.projection.StockProjection;
import app.stock.search.StockSearchCriteria;

import java.time.LocalDateTime;
import java.util.List;

public interface StockService {

    List<StockProjection> findStockByProductAndDate(String productCode, LocalDateTime date);

    List<StockDto> findAll();

    PageSearchResult<StockDto> search(StockSearchCriteria criteria);

}
