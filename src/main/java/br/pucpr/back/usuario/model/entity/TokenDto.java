package br.pucpr.back.usuario.model.entity;

import lombok.Data;

@Data
public class TokenDto {
    private int id;
    private String tipo;
    private String token;

    public TokenDto(int id, String tipo, String token) {
        this.id = id;
        this.tipo = tipo;
        this.token = token;
    }
}
