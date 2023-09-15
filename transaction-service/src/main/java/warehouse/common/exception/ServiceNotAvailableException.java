package warehouse.common.exception;

import org.springframework.http.HttpStatus;

public class ServiceNotAvailableException extends BaseRuntimeException {

    public ServiceNotAvailableException(String serviceName) {
        super("Service " + serviceName + " is not available", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
