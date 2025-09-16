package fooddeliveryapp.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fooddeliveryapp.config.kafka.KafkaProcessor;
import fooddeliveryapp.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MenuRegistered'"
    )
    public void wheneverMenuRegistered_PropagateMenuRegisteredToOrder(
        @Payload MenuRegistered menuRegistered
    ) {
        MenuRegistered event = menuRegistered;
        System.out.println(
            "\n\n##### listener PropagateMenuRegisteredToOrder : " +
            menuRegistered +
            "\n\n"
        );

        // Comments //
        //메뉴가 등록될 때 주문/배달 컨텍스트에 실시간으로 반영하여 고객 주문 시 최신 메뉴 정보를 제공하기 위함입니다.

        // Sample Logic //
        Order.propagateMenuRegisteredToOrder(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MenuUpdated'"
    )
    public void wheneverMenuUpdated_PropagateMenuUpdatedToOrder(
        @Payload MenuUpdated menuUpdated
    ) {
        MenuUpdated event = menuUpdated;
        System.out.println(
            "\n\n##### listener PropagateMenuUpdatedToOrder : " +
            menuUpdated +
            "\n\n"
        );

        // Comments //
        //메뉴 정보가 수정될 때 주문/배달 컨텍스트에 최신 정보가 반영되어야 하므로, 실시간 동기화를 위해 필요합니다.

        // Sample Logic //
        Order.propagateMenuUpdatedToOrder(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MenuDeleted'"
    )
    public void wheneverMenuDeleted_PropagateMenuDeletedToOrder(
        @Payload MenuDeleted menuDeleted
    ) {
        MenuDeleted event = menuDeleted;
        System.out.println(
            "\n\n##### listener PropagateMenuDeletedToOrder : " +
            menuDeleted +
            "\n\n"
        );

        // Comments //
        //메뉴가 삭제될 때 주문/배달 컨텍스트에서 해당 메뉴가 더 이상 노출되지 않도록 실시간으로 반영해야 합니다.

        // Sample Logic //
        Order.propagateMenuDeletedToOrder(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
