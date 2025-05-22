package com.upb.modulo_01.service.impl;

import com.upb.modulo_01.entity.DetalleNotaVenta;
import com.upb.modulo_01.entity.MyUser;
import com.upb.modulo_01.entity.NotaVenta;
import com.upb.modulo_01.entity.Product;
import com.upb.modulo_01.entity.dto.DetalleNotaVentaRequestDto;
import com.upb.modulo_01.entity.dto.NotaVentaResponseDto;
import com.upb.modulo_01.entity.dto.VentaRequestDto;
import com.upb.modulo_01.repository.NotaVentaRepository;
import com.upb.modulo_01.service.DetalleNotaVentaService;
import com.upb.modulo_01.service.NotaVentaService;
import com.upb.modulo_01.service.ProductService;
import com.upb.modulo_01.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class NotaVentaServiceImpl implements NotaVentaService {
    private final NotaVentaRepository repository;
    private final ProductService productService;
    private final UserService userService;
    private final DetalleNotaVentaService detalleNotaVentaService;

    @Override
    public List<NotaVentaResponseDto> listAll() {
        // Inicia la transacción
        return repository.findAllV1();
    }

    @Override
    @Transactional(readOnly = true)// Esta etiqueta es para indicar
    // que la transacción sea sólo de lectura
    public List<NotaVentaResponseDto> listByCutomerId(Long id) {
        // Inicia transaccion
        List<NotaVentaResponseDto> list = new ArrayList<>();
        for (NotaVenta notaVenta : repository.findAllV2(id)) {
            if (notaVenta.getCustomer().getName().contains("Nombre")) {
                list.add(new NotaVentaResponseDto(notaVenta));
            }
        }
        return list;
    }

    @Override
    @Transactional
    public void guardar(NotaVentaResponseDto notaVentaResponseDto) {
        // Inicia la transaccion
        //log.info("Iniciando la transaccion");
        repository.saveAndFlush(NotaVenta.builder().build());
        // dadasda
        this.productService.saveTx(Product.builder().build());
        this.productService.saveTx(Product.builder().build());
        //
        // dasdasdas
    }

    private void save() {

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void notificar(NotaVentaResponseDto notaVentaResponseDto) {
        // Notifiacion
    }

    @Override
    @Transactional
    public void vender(VentaRequestDto requestDto) {
        Optional<MyUser> optionalMyUser = this.userService.findById(requestDto.getCustomerId());
        if (optionalMyUser.isEmpty()) {
            log.error("[vender], No existe el usuario con el id: {}", requestDto.getCustomerId());
            throw new RuntimeException("No existe el usuario con el id: " + requestDto.getCustomerId());
        }

        MyUser customer = optionalMyUser.get();
        NotaVenta notaVenta = repository.save(NotaVenta.builder()
                .total(requestDto.getTotal())
                .date(new Date())
                .customer(customer)
                .build());

        for (DetalleNotaVentaRequestDto dto : requestDto.getDetalle()) {
            Optional<Product> productOptional = this.productService.findById(dto.getId());
            if (productOptional.isEmpty()) {
                log.error("[vender], No existe el producto con el id: {}", dto.getId());
                throw new RuntimeException("No existe el producto con el id: " + dto.getId());
            }
            Product product = productOptional.get();
            if (product.getStock() < dto.getCantidad()) {
                log.error("[vender], No hay stock del producto con el id: {}", dto.getId());
                throw new RuntimeException("No hay stock del producto con el id: " + dto.getId());
            } else {
                product.setStock(product.getStock() - dto.getCantidad());
            }
            detalleNotaVentaService.save(DetalleNotaVenta.builder()
                    .notaVenta(notaVenta)
                    .product(product)
                    .total(dto.getPrecio().multiply(new java.math.BigDecimal(dto.getCantidad())))
                    .quantity(dto.getCantidad())
                    .price(dto.getPrecio())
                    .build());
        }


    }
}
