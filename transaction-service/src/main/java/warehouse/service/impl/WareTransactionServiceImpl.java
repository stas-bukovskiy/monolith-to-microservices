package warehouse.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.common.exception.ResourceNotFoundException;
import warehouse.dto.*;
import warehouse.model.WareTransaction;
import warehouse.model.WareTransactionDetail;
import warehouse.repository.WareTransactionRepository;
import warehouse.service.ProductService;
import warehouse.service.ShelfService;
import warehouse.service.StockClerkService;
import warehouse.service.WareTransactionService;
import warehouse.validation.WareTransactionDtoValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WareTransactionServiceImpl implements WareTransactionService {

    private final WareTransactionRepository wareTransactionRepository;
    private final StockClerkService stockClerkService;
    private final ProductService productService;
    private final ShelfService shelfService;
    private final WareTransactionDtoValidator wareTransactionDtoValidator;

    @Autowired
    public WareTransactionServiceImpl(
            WareTransactionRepository wareTransactionRepository,
            StockClerkService stockClerkService,
            ProductService productService,
            ShelfService shelfService,
            WareTransactionDtoValidator wareTransactionDtoValidator) {
        this.wareTransactionRepository = wareTransactionRepository;
        this.stockClerkService = stockClerkService;
        this.productService = productService;
        this.shelfService = shelfService;
        this.wareTransactionDtoValidator = wareTransactionDtoValidator;
    }

    @Override
    public WareTransactionDto findById(Long id) {
        WareTransaction wareTransaction = this.wareTransactionRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return new WareTransactionDto(wareTransaction);
    }

    @Override
    public List<WareTransactionDto> findAll() {
        return this.wareTransactionRepository
                .findAll()
                .stream()
                .map(WareTransactionDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public WareTransactionDto save(WareTransactionDto wareTransactionDto) {
        this.wareTransactionDtoValidator.validate(wareTransactionDto);
        WareTransaction wareTransaction = this.dtoToEntity(wareTransactionDto);
        return new WareTransactionDto(this.wareTransactionRepository.save(wareTransaction));
    }

    @Override
    public void deleteById(Long id) {
        this.wareTransactionRepository.deleteById(id);
    }

    private WareTransaction dtoToEntity(WareTransactionDto wareTransactionDto) {
        WareTransaction wareTransaction = new WareTransaction();
        List<WareTransactionDetail> wareTransactionDetails = new ArrayList<>();

        BeanUtils.copyProperties(wareTransactionDto, wareTransaction, "wareTransactionDetails");

        Long stockClerkId = wareTransactionDto.getStockClerkId();
        StockClerkDto stockClerk = this.stockClerkService.findById(stockClerkId);
        wareTransaction.setStockClerkId(stockClerk.getId());

        List<WareTransactionDetailDto> wareTransactionDetailDtos = wareTransactionDto.getWareTransactionDetails();
        if (wareTransactionDetailDtos != null && wareTransactionDetailDtos.size() > 0) {
            for (WareTransactionDetailDto wTxDetailDto : wareTransactionDetailDtos) {
                WareTransactionDetail wTxDetail = new WareTransactionDetail();
                BeanUtils.copyProperties(wTxDetailDto, wTxDetail);

                ProductDto product = this.productService.findById(wTxDetailDto.getProductId());
                wTxDetail.setProductId(product.getId());

                ShelfDto shelf = this.shelfService.findById(wTxDetailDto.getShelfId());
                wTxDetail.setShelfId(shelf.getId());

                wTxDetail.setWareTransaction(wareTransaction);
                wareTransactionDetails.add(wTxDetail);
            }
        }
        wareTransaction.setWareTransactionDetails(wareTransactionDetails);
        return wareTransaction;
    }
}
