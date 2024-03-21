package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.end.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Medico(DadosCadastroMedico dado) {
        this.nome = dado.nome();
        this.email = dado.email();
        this.telefone = dado.telefone();
        this.crm = dado.crm();
        this.especialidade = dado.especialidade();
        this.endereco = new Endereco(dado.endereco());
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dado) {
        if (dado.nome() != null){
            this.nome = dado.nome();
        }

        if (dado.telefone() != null){
            this.telefone = dado.telefone();
        }

        if (dado.endereco() != null){
            this.endereco.atualizarInformacoes(dado.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
