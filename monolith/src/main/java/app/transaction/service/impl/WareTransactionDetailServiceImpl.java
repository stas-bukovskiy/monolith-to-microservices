package app.transaction.service.impl;

import app.common.exception.ResourceNotFoundException;
import app.product.model.Product;
import app.product.repo.ProductRepository;
import app.transaction.dto.WareTransactionDetailDto;
import app.transaction.model.WareTransaction;
import app.transaction.model.WareTransactionDetail;
import app.transaction.repository.WareTransactionDetailRepository;
import app.transaction.repository.WareTransactionRepository;
import app.transaction.service.WareTransactionDetailService;
import app.warehouse.model.Shelf;
import app.warehouse.repo.ShelfRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WareTransactionDetailServiceImpl implements WareTransactionDetailService {

    private final WareTransactionDetailRepository wareTransactionDetailRepository;
    private final WareTransactionRepository wareTransactionRepository;
    private final ProductRepository productRepository;
    private final ShelfRepository shelfRepository;

    @Autowired
    public WareTransactionDetailServiceImpl(
            WareTransactionDetailRepository wareTransactionDetailRepository,
            WareTransactionRepository wareTransactionRepository,
            ProductRepository productRepository,
            ShelfRepository shelfRepository) {
        this.wareTransactionDetailRepository = wareTransactionDetailRepository;
        this.wareTransactionRepository = wareTransactionRepository;
        this.productRepository = productRepository;
        this.shelfRepository = shelfRepository;
    }

    @Override
    public WareTransactionDetailDto findById(Long id) {
        WareTransactionDetail wTxDetail = this.wareTransactionDetailRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return new WareTransactionDetailDto(wTxDetail);
    }

    @Override
    public List<WareTransactionDetailDto> findByWareTransactionId(Long wareTransactionId) {
        return this.wareTransactionDetailRepository
                .findByWareTransactionId(wareTransactionId)
                .stream()
                .map(wTxDetail -> new WareTransactionDetailDto(wTxDetail))
                .collect(Collectors.toList());
    }

    private WareTransactionDetail dtoToEntity(WareTransactionDetailDto wareTransactionDetailDto) {
        WareTransactionDetail wTxDetail = new WareTransactionDetail();
        BeanUtils.copyProperties(wareTransactionDetailDto, wTxDetail);

        Long wareTransactionId = wareTransactionDetailDto.getWareTransactionId();
        if (wareTransactionId != null) {
            WareTransaction wareTransaction = this.wareTransactionRepository
                    .findById(wareTransactionId)
                    .orElse(null);

            if (wareTransaction != null) {
                wTxDetail.setWareTransaction(wareTransaction);
            }
        }

        Long productId = wareTransactionDetailDto.getProductId();
        if (productId != null) {
            Product product = this.productRepository
                    .findById(productId)
                    .orElse(null);

            if (product != null) {
                wTxDetail.setProduct(product);
            }
        }

        Long shelfId = wareTransactionDetailDto.getShelfId();
        if (shelfId != null) {
            Shelf shelf = this.shelfRepository
                    .findById(shelfId)
                    .orElse(null);

            if (shelf != null) {
                wTxDetail.setShelf(shelf);
            }
        }
        return wTxDetail;
    }
}
