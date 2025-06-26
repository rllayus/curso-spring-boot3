package com.upb.modulo_01.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upb.modulo_01.entity.dto.StereumRequestDto;
import com.upb.modulo_01.exception.OperationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/stereum")
public class WebhookController {

    @PostMapping(value = "/inbound", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<String> outbound(
            @RequestHeader("x-signature") String signature,
            @RequestHeader("x-timestamp") long xTimestamp,
            @RequestBody String body) {

        var currentTime = System.currentTimeMillis() / 1000;
        if (Math.abs(currentTime - xTimestamp) > 300) {
            throw new OperationException("Firma inválida");
        }

        String apiKey = "API_KEY";
        String hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_256,
                apiKey.getBytes(StandardCharsets.UTF_8))
                .hmacHex(body.getBytes(StandardCharsets.UTF_8));
        if (!signature.equals(hmac)) {
            throw new OperationException("Error en la firma");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        StereumRequestDto requestDto;
        try {
            requestDto = objectMapper.readValue(body, StereumRequestDto.class);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error en la firma");
        }

        if (requestDto.getNotificationType().equals("test")) {
            return ok().build();
        }

        if (!requestDto.getNotificationType().equals("transaction")) {
            throw new OperationException("No corresponde a este método la notificación");
        }

        try {

            return ok().build();
        } catch (com.upb.modulo_01.exception.OperationException e) {
            log.error("Se generó un error al recibir notificación de circle. Causa: {}", e.getMessage());
            throw new com.upb.modulo_01.exception.OperationException("Se generó un error al recibir notificación de circle");
        } catch (Exception e) {
            log.error("Se generó un error al recibir la confirmación de circle", e);
            throw new com.upb.modulo_01.exception.OperationException("Se generó un error al recibir la confirmación de circle");
        }
    }
}
