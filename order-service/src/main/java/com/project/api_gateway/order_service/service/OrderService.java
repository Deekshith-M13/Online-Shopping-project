package com.project.api_gateway.order_service.service;

import com.project.api_gateway.order_service.dto.InventoryResponse;
import com.project.api_gateway.order_service.event.OrderPlacedEvent;
import com.project.api_gateway.order_service.model.Order;
import com.project.api_gateway.order_service.model.OrderLineItems;
import com.project.api_gateway.order_service.repository.OrderRepository;
import com.project.api_gateway.order_service.dto.OrderRequest;
import com.project.api_gateway.order_service.dto.OrderLineItemsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClient;
    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;


    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(orderLineItems1 -> orderLineItems1.getSkuCode()).toList();

        //call inventory and check if the product is available
        //set order only if it is present

        InventoryResponse[] inventoryResponsesArray = webClient.build().get()
                .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductInStock = Arrays.stream(inventoryResponsesArray).allMatch(inventoryResponse -> inventoryResponse.isInStock());
        if(allProductInStock){
            orderRepository.save(order);
            kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
            return "Order placed succefully";
        }
        else{
            throw new IllegalArgumentException("Product is not in stock");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}