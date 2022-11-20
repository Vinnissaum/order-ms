package br.com.vinissaum.orders.controller;

import br.com.vinissaum.orders.dto.OrderDTO;
import br.com.vinissaum.orders.dto.StatusDTO;
import br.com.vinissaum.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

        @Autowired
        private OrderService service;

        @GetMapping()
        public List<OrderDTO> index() {
            return service.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<OrderDTO> show(@PathVariable @NotNull Long id) {
            OrderDTO dto = service.findById(id);

            return  ResponseEntity.ok(dto);
        }

        @PostMapping()
        public ResponseEntity<OrderDTO> create(@RequestBody @Valid OrderDTO dto, UriComponentsBuilder uriBuilder) {
            OrderDTO orderRealized = service.createOrder(dto);

            URI uri = uriBuilder.path("/orders/{id}").buildAndExpand(orderRealized.getId()).toUri();

            return ResponseEntity.created(uri).body(orderRealized);

        }

        @PutMapping("/{id}/status")
        public ResponseEntity<OrderDTO> update(@PathVariable Long id, @RequestBody StatusDTO status){
           OrderDTO dto = service.updateStatus(id, status);

            return ResponseEntity.ok(dto);
        }


        @PutMapping("/{id}/pago")
        public ResponseEntity<Void> approvePayment(@PathVariable @NotNull Long id) {
            service.approvePayment(id);

            return ResponseEntity.ok().build();
        }
}
