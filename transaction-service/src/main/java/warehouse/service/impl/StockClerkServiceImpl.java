package warehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import warehouse.common.exception.ResourceNotFoundException;
import warehouse.common.exception.ServiceNotAvailableException;
import warehouse.dto.StockClerkDto;
import warehouse.service.StockClerkService;

@Service
@RequiredArgsConstructor
public class StockClerkServiceImpl implements StockClerkService {
    private static final int MAX_RETRIES = 3;
    private static final long RETRY_BACKOFF_MS = 1000; // 1 second
    private final RestTemplate restTemplate;
    @Value("${services.clerk.url}")
    private String url;

    @Override
    @Retryable(
            value = {ServiceNotAvailableException.class},
            maxAttempts = MAX_RETRIES,
            backoff = @Backoff(delay = RETRY_BACKOFF_MS)
    )
    public StockClerkDto findById(Long stockClerkId) {
        try {
            return restTemplate.getForObject(url + "/{id}", StockClerkDto.class, stockClerkId);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ResourceNotFoundException(stockClerkId);
        } catch (HttpClientErrorException ex) {
            throw new ServiceNotAvailableException("clerk");
        }
    }
}

