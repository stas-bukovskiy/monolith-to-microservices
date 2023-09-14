package warehouse.search;

import lombok.Getter;
import lombok.Setter;
import warehouse.common.search.BaseSearchCriteria;
import warehouse.enums.MeasurementUnit;

@Getter
@Setter
public class ProductSearchCriteria extends BaseSearchCriteria {

    public static final String ORDER_BY_ID = "id";
    public static final String ORDER_BY_CODE = "code";
    public static final String ORDER_BY_MEASUREMENT_UNIT = "measurementUnit";

    public static final String DEFAULT_ORDER_BY = ORDER_BY_ID;

    private Long id;
    private String code;
    private String description;
    private MeasurementUnit measurementUnit;
}
