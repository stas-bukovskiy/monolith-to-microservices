package app.warehouse.repo;

import app.common.search.PageSearchResult;
import app.warehouse.model.Stock;
import app.warehouse.search.StockSearchCriteria;


public interface StockRepositoryCustom {

    PageSearchResult<Stock> search(StockSearchCriteria criteria);
}