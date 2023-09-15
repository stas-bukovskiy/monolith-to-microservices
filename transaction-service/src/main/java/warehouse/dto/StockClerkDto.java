package warehouse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockClerkDto {
    private Long id;
    private String registryNumber;
    private String firstName;
    private String lastName;
}
