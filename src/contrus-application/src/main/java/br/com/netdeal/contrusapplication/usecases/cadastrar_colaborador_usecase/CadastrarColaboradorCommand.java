package br.com.netdeal.contrusapplication.usecases.cadastrar_colaborador_usecase;

import java.util.Date;

public class CadastrarColaboradorCommand {


    private final long colaboradorHierarquiaSuperiorId;
    private final String nome;
    private final String senha;
    private final String tel;
    private final String email;
    private final Date dataNascimento;

    public CadastrarColaboradorCommand(long colaboradorHierarquiaSuperiorId, String nome, String senha, 
    String tel, String email, Date dataNascimento) {
        this.colaboradorHierarquiaSuperiorId = colaboradorHierarquiaSuperiorId;
        this.nome = nome;
        this.senha = senha;
        this.tel = tel;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public long getColaboradorHierarquiaSuperiorId(){
        return this.colaboradorHierarquiaSuperiorId;
    }
    public String getNome(){
        return this.nome;
    }
    public String getSenha(){
        return this.senha;
    }
    public String getTel(){
        return this.tel;
    }
    public String getEmail(){
        return this.email;
    }
    public Date getDataNascimento(){
        return this.dataNascimento;
    }
}