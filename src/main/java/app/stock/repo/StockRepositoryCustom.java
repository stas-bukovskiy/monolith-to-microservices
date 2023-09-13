package app.stock.repo;

import app.common.search.PageSearchResult;
import app.stock.model.Stock;
import app.stock.search.StockSearchCriteria;


public interface StockRepositoryCustom {

    PageSearchResult<Stock> search(StockSearchCriteria criteria);
}