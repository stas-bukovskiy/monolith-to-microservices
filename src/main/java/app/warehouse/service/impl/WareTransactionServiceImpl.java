package app.warehouse.service.impl;

import app.common.exception.ResourceNotFoundException;
import app.product.model.Product;
import app.product.repo.ProductRepository;
import app.stock.model.Shelf;
import app.stock.model.StockClerk;
import app.stock.repo.ShelfRepository;
import app.stock.repo.StockClerkRepository;
import app.warehouse.dto.WareTransactionDetailDto;
import app.warehouse.dto.WareTransactionDto;
import app.warehouse.model.WareTransaction;
import app.warehouse.model.WareTransactionDetail;
import app.warehouse.repository.WareTransactionRepository;
import app.warehouse.service.WareTransactionService;
import app.warehouse.validation.WareTransactionDtoValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WareTransactionServiceImpl implements WareTransactionService {

    private final WareTransactionRepository wareTransactionRepository;
    private final StockClerkRepository stockClerkRepository;
    private final ProductRepository productRepository;
    private final ShelfRepository shelfRepository;
    private final WareTransactionDtoValidator wareTransactionDtoValidator;

    @Autowired
    public WareTransactionServiceImpl(
            WareTransactionRepository wareTransactionRepository,
            StockClerkRepository stockClerkRepository,
            ProductRepository productRepository,
            ShelfRepository shelfRepository,
            WareTransactionDtoValidator wareTransactionDtoValidator) {
        this.wareTransactionRepository = wareTransactionRepository;
        this.stockClerkRepository = stockClerkRepository;
        this.productRepository = productRepository;
        this.shelfRepository = shelfRepository;
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
                .map(wareTx -> new WareTransactionDto(wareTx))
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
        StockClerk stockClerk = this.stockClerkRepository
                .findById(stockClerkId)
                .orElse(null);
        wareTransaction.setStockClerk(stockClerk);

        List<WareTransactionDetailDto> wareTransactionDetailDtos = wareTransactionDto.getWareTransactionDetails();
        if(wareTransactionDetailDtos != null && wareTransactionDetailDtos.size() > 0) {
            for(WareTransactionDetailDto wTxDetailDto : wareTransactionDetailDtos) {
                WareTransactionDetail wTxDetail = new WareTransactionDetail();
                BeanUtils.copyProperties(wTxDetailDto, wTxDetail);

                Product product = this.productRepository
                        .findById(wTxDetailDto.getProductId())
                        .orElse(null);
                if (product != null) {
                    wTxDetail.setProduct(product);
                }

                Shelf shelf = this.shelfRepository
                        .findById(wTxDetailDto.getShelfId())
                        .orElse(null);
                if (shelf != null) {
                    wTxDetail.setShelf(shelf);
                }

                wTxDetail.setWareTransaction(wareTransaction);
                wareTransactionDetails.add(wTxDetail);
            }
        }
        wareTransaction.setWareTransactionDetails(wareTransactionDetails);
        return wareTransaction;
    }
}
