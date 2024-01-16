package br.com.netdeal.contrusdomain.models;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Hierarquia {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hierarquia_superior_colaborador_id")
    private Colaborador hierarquiaSuperiorColaborador;

    @Column(nullable = false)
    private int valorHierarquia;

    @OneToMany(mappedBy = "hierarquia", cascade = CascadeType.ALL)
    private List<Colaborador> colaboradores;

}
