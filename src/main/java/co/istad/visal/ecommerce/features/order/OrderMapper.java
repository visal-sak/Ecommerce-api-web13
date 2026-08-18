package co.istad.visal.ecommerce.features.order;

import co.istad.visal.ecommerce.features.order.dto.OrderLineResponse;
import co.istad.visal.ecommerce.features.order.dto.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    OrderLineResponse toOrderLineResponse(OrderLine orderLine);

    OrderResponse toOrderResponse(Order order);
}
