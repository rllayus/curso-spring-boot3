package com.upb.modulo_01.integracion.impl;

import com.upb.modulo_01.crypto.CryptoRSA;
import com.upb.modulo_01.exception.OperationException;
import com.upb.modulo_01.integracion.ArtemisiaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ArtemisiaServiceImpl implements ArtemisiaService {
    private String jwtToken = null;
    @Value("${artemisia.url-base}")
    private String urlBase;
    @Value("${artemisia.username}")
    private String username;
    @Value("${artemisia.password}")
    private String password;
    @Value("${artemisia.connect-timeout}")
    private int connectTimeout;
    @Value("${artemisia.read-timeout}")
    private int readTimeout;

    @Autowired
    private CryptoRSA cryptoRSA;

    @Scheduled(cron = "* 0/1 * * * *")
    public void actualizarToken() {
        log.info("Actualizando token");
        try {
            log.info("Encriptado: " + cryptoRSA.rsaEncryptionOaepSha256("Hola"));
        }catch (Exception e){
            e.printStackTrace();
        }
        login();
    }

    @Override
    public void listarProducto() {
        RestClient restClient = create();
        ResponseEntity<String> response;
        if (jwtToken == null) {
            login();
        }

        try {
            response = restClient.get()
                    .uri(urlBase + "/api/v1/products")
                    .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                    .header("Authorization", "Bearer " + jwtToken)
                    .retrieve()
                    .toEntity(String.class);
        } catch (Exception e) {
            log.error("Error al obtener la empresa artemisia", e);
            throw new OperationException("Error al obtener la empresa en artemisia");
        }

        if (response.getStatusCode().value() != 200) {
            log.error("Artemisia retorno error al recuperar la empresa: {}", response.getStatusCode().value());
            throw new OperationException("Artemisia retorno error al recuperar la empresa");
        }
        log.info("Artemisia Resultado: {}", response.getBody());
    }

    @Override
    public void listarProductoV2() {
        RestClient restClient = create();
        ResponseEntity<String> response;
        ArtemisiaLoginResponseDto tokenDto = login();

        try {
            response = restClient.get()
                    .uri(urlBase + "/api/v1/products")
                    .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                    .header("Authorization", "Bearer " + tokenDto.getToken())
                    .retrieve()
                    .toEntity(String.class);
        } catch (Exception e) {
            log.error("Error al obtener la empresa artemisia", e);
            throw new OperationException("Error al obtener la empresa en artemisia");
        }

        if (response.getStatusCode().value() != 200) {
            log.error("Artemisia retorno error al recuperar la empresa: {}", response.getStatusCode().value());
            throw new OperationException("Artemisia retorno error al recuperar la empresa");
        }
        log.info("Artemisia Resultado: {}", response.getBody());
    }


    private ArtemisiaLoginResponseDto login() {
        RestClient restClient = create();
        ResponseEntity<ArtemisiaLoginResponseDto> response;
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        ArtemisiaLoginRequestDto dto = new ArtemisiaLoginRequestDto();
        dto.setUsername(username);
        dto.setPassword(password);

        try {
            response = restClient.post()
                    .uri(urlBase + "/api/v1/auth/token")
                    .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                    //.body("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}")
                    .body(params)
                    //.body(dto)
                    .retrieve()
                    //.toEntity(String.class);
                    .toEntity(ArtemisiaLoginResponseDto.class);
        } catch (Exception e) {
            log.error("Error al obtener la empresa artemisia", e);
            throw new OperationException("Error al obtener la empresa en artemisia");
        }

        if (response.getStatusCode().value() != 200) {
            log.error("Artemisia retorno error al recuperar la empresa: {}", response.getStatusCode().value());
            throw new OperationException("Artemisia retorno error al recuperar la empresa");
        }
        jwtToken = response.getBody().getToken();
        return response.getBody();
    }


    private RestClient create() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(Duration.ofMillis(connectTimeout));
        clientHttpRequestFactory.setReadTimeout(Duration.ofMillis(readTimeout));
        return RestClient.builder().requestFactory(clientHttpRequestFactory).build();
    }
}
