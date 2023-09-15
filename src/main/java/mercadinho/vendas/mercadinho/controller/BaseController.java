package mercadinho.vendas.mercadinho.controller;

import mercadinho.vendas.mercadinho.dtos.BaseResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    static final String SUCESSO = "sucesso!";
    static final String ERRO = "ERRO!";

    static ResponseEntity<BaseResponseDTO> sucess(Integer codeStatus, Object data) {
        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        baseResponseDTO.setCodigo(codeStatus);
        baseResponseDTO.setMensagem(SUCESSO);
        baseResponseDTO.setDados(data);
        return ResponseEntity.ok(baseResponseDTO);
    }

    static ResponseEntity<BaseResponseDTO> sucess(Integer codeStatus) {
        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        baseResponseDTO.setCodigo(codeStatus);
        baseResponseDTO.setMensagem(SUCESSO);
        return ResponseEntity.ok(baseResponseDTO);
    }

    static ResponseEntity<BaseResponseDTO> error(Object data) {
        BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
        baseResponseDTO.setCodigo(HttpStatus.NOT_FOUND.value());
        baseResponseDTO.setMensagem(ERRO);
        baseResponseDTO.setDados(data);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(baseResponseDTO);
    }

    static ResponseEntity<BaseResponseDTO> ok(Object data) {
        return sucess(HttpStatus.OK.value(), data);
    }

    static ResponseEntity<BaseResponseDTO> ok() {
        return sucess(HttpStatus.OK.value());
    }



}
