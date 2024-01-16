package br.com.netdeal.contrusdomain.models;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Colaborador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column(nullable = false)
    public String nome;
    @Column(nullable = false)
    public String senha;
    @Column(nullable = true)
    public String email;
    @Column(nullable = true)
    public String telefone;
    @Column(nullable = false)
    public Date dataNascimento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hierarquia_id")
    private Hierarquia hierarquia;

}
