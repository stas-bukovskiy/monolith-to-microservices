package warehouse.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warehouse.common.exception.ResourceNotFoundException;
import warehouse.dto.ShelfDto;
import warehouse.dto.WarehouseDto;
import warehouse.model.Shelf;
import warehouse.model.Warehouse;
import warehouse.repo.WarehouseRepository;
import warehouse.validation.WarehouseDtoValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseDtoValidator warehouseValidator;

    @Autowired
    public WarehouseServiceImpl(
            WarehouseRepository warehouseRepository,
            WarehouseDtoValidator warehouseValidator) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseValidator = warehouseValidator;
    }

    @Override
    public WarehouseDto findById(Long id) {
        Warehouse warehouse = this.warehouseRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return new WarehouseDto(warehouse);
    }

    @Override
    public List<WarehouseDto> findAll() {
        return this.warehouseRepository
                .findAll()
                .stream()
                .map(warehouse -> new WarehouseDto(warehouse))
                .collect(Collectors.toList());
    }

    @Override
    public WarehouseDto save(WarehouseDto warehouseDto) {
        this.warehouseValidator.validate(warehouseDto);
        Warehouse warehouse = this.dtoToEntity(warehouseDto);
        Warehouse savedWarehouse = this.warehouseRepository.save(warehouse);
        return new WarehouseDto(savedWarehouse);
    }

    @Override
    public void deleteById(Long id) {
        this.warehouseRepository.deleteById(id);
    }

    private Warehouse dtoToEntity(WarehouseDto warehouseDto) {
        Warehouse warehouse = new Warehouse();
        List<Shelf> shelves = new ArrayList<>();

        BeanUtils.copyProperties(warehouseDto, warehouse, "shelves");

        List<ShelfDto> shelfDtos = warehouseDto.getShelves();
        if (shelfDtos != null && shelfDtos.size() > 0) {
            shelfDtos.forEach(shelfDto -> {
                Shelf shelf = new Shelf();
                BeanUtils.copyProperties(shelfDto, shelf);
                shelf.setWarehouse(warehouse);
                shelves.add(shelf);
            });
        }
        warehouse.setShelves(shelves);
        return warehouse;
    }
}
