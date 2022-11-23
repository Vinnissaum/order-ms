package br.com.vinissaum.orders.dto;

import br.com.vinissaum.orders.model.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDTO {

    private Long id;

    private BigDecimal value;

    private String name;

    private String number;

    private String expirationDate;

    private String cvv;

    private PaymentStatus status;

    private Long orderId;

    private Long paymentTypeId;

}
