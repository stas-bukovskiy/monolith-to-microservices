package warehouse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import warehouse.model.StockClerk;

@Data
@NoArgsConstructor
public class StockClerkDto {

    private Long id;
    private String registryNumber;
    private String firstName;
    private String lastName;

    public StockClerkDto(StockClerk stockClerk) {
        BeanUtils.copyProperties(stockClerk, this);
    }
}
