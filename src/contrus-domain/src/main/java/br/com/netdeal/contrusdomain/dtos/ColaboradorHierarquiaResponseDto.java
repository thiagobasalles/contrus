package br.com.netdeal.contrusdomain.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ColaboradorHierarquiaResponseDto {

    public String nomeColaborador;
    public int valorHierarquia;
    public int segurancaSenhaPorcentagem;
    public String labelSegurancaSenhaPorcentagem;

}
