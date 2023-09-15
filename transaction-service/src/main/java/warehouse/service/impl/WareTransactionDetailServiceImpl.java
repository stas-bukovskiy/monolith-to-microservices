package warehouse.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.common.exception.ResourceNotFoundException;
import warehouse.dto.ProductDto;
import warehouse.dto.ShelfDto;
import warehouse.dto.WareTransactionDetailDto;
import warehouse.model.WareTransactionDetail;
import warehouse.repository.WareTransactionDetailRepository;
import warehouse.repository.WareTransactionRepository;
import warehouse.service.ProductService;
import warehouse.service.ShelfService;
import warehouse.service.WareTransactionDetailService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WareTransactionDetailServiceImpl implements WareTransactionDetailService {

    private final WareTransactionDetailRepository wareTransactionDetailRepository;
    private final WareTransactionRepository wareTransactionRepository;
    private final ProductService productService;
    private final ShelfService shelfService;

    @Autowired
    public WareTransactionDetailServiceImpl(
            WareTransactionDetailRepository wareTransactionDetailRepository,
            WareTransactionRepository wareTransactionRepository,
            ProductService productService,
            ShelfService shelfService) {
        this.wareTransactionDetailRepository = wareTransactionDetailRepository;
        this.wareTransactionRepository = wareTransactionRepository;
        this.productService = productService;
        this.shelfService = shelfService;
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
                .map(WareTransactionDetailDto::new)
                .collect(Collectors.toList());
    }

    private WareTransactionDetail dtoToEntity(WareTransactionDetailDto wareTransactionDetailDto) {
        WareTransactionDetail wTxDetail = new WareTransactionDetail();
        BeanUtils.copyProperties(wareTransactionDetailDto, wTxDetail);

        Long wareTransactionId = wareTransactionDetailDto.getWareTransactionId();
        if (wareTransactionId != null) {
            this.wareTransactionRepository.findById(wareTransactionId).ifPresent(wTxDetail::setWareTransaction);
        }

        // todo: check for exception
        Long productId = wareTransactionDetailDto.getProductId();
        if (productId != null) {
            ProductDto product = this.productService.findById(productId);

            if (product != null) {
                wTxDetail.setProductId(product.getId());
            }
        }

        // todo: check for exception
        Long shelfId = wareTransactionDetailDto.getShelfId();
        if (shelfId != null) {
            ShelfDto shelf = this.shelfService.findById(shelfId);
            wTxDetail.setShelfId(shelf.getId());
        }
        return wTxDetail;
    }
}
