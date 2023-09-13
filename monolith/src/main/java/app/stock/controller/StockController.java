package app.stock.controller;

import app.common.search.PageSearchResult;
import app.common.search.SearchRequest;
import app.common.utils.SearchUtils;
import app.stock.dto.StockDto;
import app.stock.projection.StockProjection;
import app.stock.search.StockSearchCriteria;
import app.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("stock")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("snapshot")
    public List<StockProjection> findStockByProductAndDateTime(@RequestParam String productCode, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date) {
        return this.stockService.findStockByProductAndDate(productCode, date);
    }

    @GetMapping
    public List<StockDto> findAll() {
        return this.stockService.findAll();
    }

    @PostMapping("search")
    public Page<StockDto> search(@RequestBody SearchRequest searchRequest) {
        StockSearchCriteria criteria = SearchUtils.createSearchCriteria(searchRequest, StockSearchCriteria.class);
        PageSearchResult<StockDto> pageSearchResult = this.stockService.search(criteria);
        return SearchUtils.pageOf(searchRequest, pageSearchResult);
    }
}
