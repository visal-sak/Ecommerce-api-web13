package co.istad.visal.ecommerce.execption;

import lombok.Builder;

import java.time.Instant;

@Builder
public record ApiErrorResponse<T>(
        Integer code,
        Boolean isSucess,
        String message,
        Instant timeStamp,
        T errorDetail
) {

}
