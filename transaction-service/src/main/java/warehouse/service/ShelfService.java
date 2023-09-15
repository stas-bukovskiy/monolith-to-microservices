package warehouse.service;

import warehouse.dto.ShelfDto;

public interface ShelfService {
    ShelfDto findById(Long shelfId);
}
