package br.com.netdeal.contrusapplication.usecases.listar_colaboradores_hierarquia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.netdeal.contrusapplication.interfaces.repositories.IColaboradorRepository;
import br.com.netdeal.contrusdomain.dtos.ColaboradorHierarquiaResponseDto;
import br.com.netdeal.contrusdomain.models.Colaborador;

@Component
public class ListarColaboradoresHierarquiaCommandHandler {
    
    @Autowired
    private IColaboradorRepository colaboradorRepository;

    private List<ColaboradorHierarquiaResponseDto> response;

    @CommandHandler
    public List<ColaboradorHierarquiaResponseDto> handle(ListarColaboradoresHierarquiaCommand command) {
        response = new ArrayList<ColaboradorHierarquiaResponseDto>();
        var listaColaboradores = colaboradorRepository.bustarTodosComHierarquiaZero();

        montarColaboradorHierarquiaResponseDto(listaColaboradores);

        return response;

    }

    //função recursiva
    private void montarColaboradorHierarquiaResponseDto(List<Colaborador> colaboradores){

        for(Colaborador colab : colaboradores){
            var dto = new ColaboradorHierarquiaResponseDto();
            dto.nomeColaborador = colab.getNome();
            dto.valorHierarquia = colab.getHierarquia().getValorHierarquia();
            int valor = darPontosQuantidadeCaracteres(colab.senha);
            valor += darPontosLetrasMaiusculas(colab.senha);
            valor += darPontosLetrasMinusculas(colab.senha);
            valor += darPontosNumeros(colab.senha);
            valor += darPontosSimbolos(colab.senha);
            valor += darPontosNumerosLetrasNoMeio(colab.senha);
            valor += darPontosRequerimentos(colab.senha);
            valor += tirarPontosApenasLetras(colab.senha);
            valor += tirarPontosApenasNumeros(colab.senha);
            valor += tirarPontosCaracteresRepetidos(colab.senha);
            valor += tirarPontosCaracteresMaiusculosConsecutivos(colab.senha);
            valor += tirarPontosCaracteresMinusculosConsecutivos(colab.senha);
            valor += tirarPontosCaracteresDigitosConsecutivos(colab.senha);
            valor += tirarPontosCaracteresLetrasSequencial(colab.senha);
            valor += tirarPontosCaracteresDigitoSequencial(colab.senha);
            valor += tirarPontosCaracteresSimboloSequencial(colab.senha);
            dto.setSegurancaSenhaPorcentagem(valor > 100? 100 : valor);
            if(valor<26){
                dto.setLabelSegurancaSenhaPorcentagem("ruim");
            }else if(valor<51){
                dto.setLabelSegurancaSenhaPorcentagem("mediana");
            }else if(valor<76){
                dto.setLabelSegurancaSenhaPorcentagem("bom");
            }else if(valor>75){
                dto.setLabelSegurancaSenhaPorcentagem("forte");
            }
            response.add(dto);
            List<Colaborador> colabHierarquiaSeguinte = colaboradorRepository.buscarColaboradorHierarquiaSeguinte(colab.getId(), colab.getHierarquia().getValorHierarquia()+1);
            montarColaboradorHierarquiaResponseDto(colabHierarquiaSeguinte);
        }

    }

    private int darPontosQuantidadeCaracteres(String senha){
        return Math.abs(((senha.length())*4));
    }


    private int darPontosLetrasMaiusculas(String senha){
        int count = 0;
        for (char c : senha.toCharArray()) {
            if (Character.isUpperCase(c)) {
                count++;
            }
        }
        return Math.abs(((senha.length()-count)*2));
    }

    private int darPontosLetrasMinusculas(String senha){
        int count = 0;
        for (char c : senha.toCharArray()) {
            if (Character.isLowerCase(c)) {
                count++;
            }
        }
        return Math.abs(((senha.length()-count)*2));
    }

    private int darPontosNumeros(String senha){
        int count = 0;
        for (char c : senha.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return ((count)*4);
    }

    private int darPontosSimbolos(String senha){
        int count = 0;
        for (char c : senha.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                count++;
            }
        }
        return ((count)*6);
    }

    private int darPontosNumerosLetrasNoMeio(String senha){
        int count = 0;
        for (int i=0; i< senha.length(); i++) {
            if(i>2){
                if (Character.isDigit(senha.charAt(i)) || !Character.isLetterOrDigit(senha.charAt(i))) {
                    count++;
                }
            }
        }
        return ((count)*2);
    }

    private int darPontosRequerimentos(String senha){
        int requerimentos = 0;

        if(senha.length()>=8){
            requerimentos++;
        }

        if(senha.chars().anyMatch(Character::isUpperCase))
            requerimentos++;
        if(senha.chars().anyMatch(Character::isLowerCase))
            requerimentos++;
        if(senha.chars().anyMatch(Character::isDigit))
            requerimentos++;
        if(!senha.chars().anyMatch(Character::isLetterOrDigit))
            requerimentos++;


        if(requerimentos>=3){
            return ((requerimentos)*2);
        }
        return 0;
    }

    private int tirarPontosApenasLetras(String senha){
        if(senha.chars().allMatch(Character::isLetter)){
            return -senha.length();
        }
        return 0;
    }

    private int tirarPontosApenasNumeros(String senha){
        if(senha.chars().allMatch(Character::isDigit)){
            return -senha.length();
        }
        return 0;
    }

    //Está Case Insensitive mas na prática não está funciona
    private int tirarPontosCaracteresRepetidos(String senha){
        Set<Character> caracteresVistos = new HashSet<>();
        int contarRepeticoes = 0;

        for (char c : senha.toCharArray()) {
            if (!caracteresVistos.contains(c)) {
                caracteresVistos.add(c);
            }else{
                contarRepeticoes++;
            }
        }

        return -contarRepeticoes;
    }

    private int tirarPontosCaracteresMaiusculosConsecutivos(String senha){
        boolean consecutivoCaracterMaiusculo = false;
        int contadorConsecutivo = 0;

        for (int i = 0; i < senha.length(); i++) {
            if (Character.isUpperCase(senha.charAt(i))) {
                if(consecutivoCaracterMaiusculo==true){
                    contadorConsecutivo++;
                }
                consecutivoCaracterMaiusculo = true;
            } else {
                consecutivoCaracterMaiusculo=false;
            }
        }   
        return -(contadorConsecutivo*2);
    }

    private int tirarPontosCaracteresMinusculosConsecutivos(String senha){
        boolean consecutivoCaracterMinusculo = false;
        int contadorConsecutivo = 0;

        for (int i = 0; i < senha.length(); i++) {
            if (Character.isLowerCase(senha.charAt(i))) {
                if(consecutivoCaracterMinusculo==true){
                    contadorConsecutivo++;
                }
                consecutivoCaracterMinusculo = true;
            } else {
                consecutivoCaracterMinusculo=false;
            }
        }   
        return -(contadorConsecutivo*2);
    }

    private int tirarPontosCaracteresDigitosConsecutivos(String senha){
        boolean consecutivoCaracterDigito = false;
        int contadorConsecutivo = 0;

        for (int i = 0; i < senha.length(); i++) {
            if (Character.isDigit(senha.charAt(i))) {
                if(consecutivoCaracterDigito==true){
                    contadorConsecutivo++;
                }
                consecutivoCaracterDigito = true;
            } else {
                consecutivoCaracterDigito=false;
            }
        }   
        return -(contadorConsecutivo*2);
    }

    private int tirarPontosCaracteresLetrasSequencial(String senha){
        String alfabetoMinusculo = "abcdefghijklmnopqrstuvwxyz";
        int contadorConsecutivo = 0;

        for (int i = 2; i < senha.length(); i++) {
            if(Character.isLetter(senha.toLowerCase().charAt(i)) && Character.isLetter(senha.toLowerCase().charAt(i-1)) && Character.isLetter(senha.toLowerCase().charAt(i-2))){
                int indexAtualAlfabeto = alfabetoMinusculo.indexOf(senha.toLowerCase().charAt(i));
                int indexAnteriorAlfabeto = alfabetoMinusculo.indexOf(senha.toLowerCase().charAt(i-1));
                int indexAntesAnteriorAlfabeto = alfabetoMinusculo.indexOf(senha.toLowerCase().charAt(i-2));
                if(indexAtualAlfabeto == (indexAnteriorAlfabeto+1) && indexAtualAlfabeto == indexAntesAnteriorAlfabeto+2){
                    contadorConsecutivo++;
                }
            }
        }   
        return -(contadorConsecutivo*2);
    }

    private int tirarPontosCaracteresDigitoSequencial(String senha){
        int contadorConsecutivo = 0;

        for (int i = 2; i < senha.length(); i++) {
            if(Character.isDigit(senha.charAt(i)) && Character.isDigit(senha.charAt(i-1)) && Character.isDigit(senha.charAt(i-2))){
                int indexAtualAlfabeto = Integer.parseInt(String.valueOf(senha.charAt(i)));
                int indexAnteriorAlfabeto = Integer.parseInt(String.valueOf(senha.charAt(i-1)));
                int indexAntesAnteriorAlfabeto = Integer.parseInt(String.valueOf(senha.charAt(i-2)));
                if(indexAtualAlfabeto == (indexAnteriorAlfabeto+1) && indexAtualAlfabeto == indexAntesAnteriorAlfabeto+2){
                    contadorConsecutivo++;
                }
            }
        }   
        return -(contadorConsecutivo*2);
    }

    //Digito Sequencial?? Procurei e não encontrei referencia disso.
    private int tirarPontosCaracteresSimboloSequencial(String senha){
        String simboloSequencial = "~`!@#$%^&*()_+-={}[]\\|;:'\",<.>/?";
        int contadorConsecutivo = 0;

        for (int i = 2; i < senha.length(); i++) {
            if(Character.isDigit(senha.charAt(i)) && Character.isDigit(senha.charAt(i-1)) && Character.isDigit(senha.charAt(i-2))){
                int indexAtualAlfabeto = simboloSequencial.indexOf(senha.charAt(i));
                int indexAnteriorAlfabeto = simboloSequencial.indexOf(senha.charAt(i-1));
                int indexAntesAnteriorAlfabeto = simboloSequencial.indexOf(senha.charAt(i-2));
                if(indexAtualAlfabeto == (indexAnteriorAlfabeto+1) && indexAtualAlfabeto == indexAntesAnteriorAlfabeto+2){
                    contadorConsecutivo++;
                }
            }
        }   
        return -(contadorConsecutivo*2);
    }

}
