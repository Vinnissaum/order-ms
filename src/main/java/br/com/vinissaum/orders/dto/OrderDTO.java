package br.com.vinissaum.orders.dto;

import br.com.vinissaum.orders.model.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private LocalDateTime dateTime;
    private Status status;
    private List<OrderItemDTO> items = new ArrayList<>();



}
