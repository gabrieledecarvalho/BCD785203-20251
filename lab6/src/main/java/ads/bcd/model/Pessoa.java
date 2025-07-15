// Pacote da classe
package ads.bcd.model;
// Importações para JPA e relacionamentos
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
/**
 * POJO para representar a entidade Pessoa.
 **
 Essa classe será mapeada para a tabela Pessoa no banco de dados.
 */
@Entity // Indica que essa classe é uma entidade JPA
@Table(name = "Pessoa") // Define explicitamente o nome da tabela
public class Pessoa {
    /**
     * Chave primária da entidade, com geração automática (AUTO_INCREMENT).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAluno;
    /**
     * Coluna que não aceita valores nulos.
     */
    @Column(nullable = false)
    private String nome;
    // Campo email, pode aceitar nulo por padrão
    private String email;
    /**
     * CPF único e obrigatório. A anotação unique cria restrição UNIQUE no banco.
     */
    @Column(nullable = false, unique = true)
    private String cpf;
    /**
     * Relacionamento bidirecional OneToOne com a entidade Endereco.
     **
     mappedBy = "pessoa" indica que Pessoa é o lado inverso da relação,
     * e que Endereco é o dono da associação.
     **
     fetch LAZY: Endereco será carregado somente quando acessado.
     **
     cascade ALL: operações cascata, ex: excluir Pessoa exclui Endereco.
     */
    @OneToOne(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Endereco endereco;
    // Construtor padrão (obrigatório para JPA)
    protected Pessoa() {
    }
    // Construtor com os principais atributos para facilitar a criação
    public Pessoa(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }
    // Getters e setters
    public Integer getIdAluno() {
        return idAluno;
    }
    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    // Método toString para impressão legível do objeto
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pessoa [cpf=").append(cpf)
                .append(", email=").append(email)
                .append(", idAluno=").append(idAluno)
                .append(", nome=").append(nome);
        if (this.endereco != null) {
            sb.append(", endereco=").append(endereco);
        }
        sb.append("]");
        return sb.toString();
    }
    // hashCode baseado na chave primária
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idAluno == null) ? 0 : idAluno.hashCode());
        return result;
    }
// equals baseado na chave primária para comparar objetos corretamente
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Pessoa other = (Pessoa) obj;
        if (idAluno == null) {
            if (other.idAluno != null)
                return false;
        } else if (!idAluno.equals(other.idAluno))
            return false;
        return true;
    }
}