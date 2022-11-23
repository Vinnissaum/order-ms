package br.com.vinissaum.orders.amqp;

import br.com.vinissaum.orders.dto.PaymentDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentListener {

    @RabbitListener(queues = "payment.detail-order")
    public void getMessage(PaymentDTO dto) {
        String message = """
                Payment id: %s
                Value: %s
                Order id: %s
                """.formatted(dto.getId(), dto.getValue(), dto.getOrderId());

        System.out.println(message);
    }

}
