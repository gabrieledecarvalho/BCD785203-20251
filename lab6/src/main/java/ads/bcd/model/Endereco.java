// Pacote onde a classe está localizada
package ads.bcd.model;
// Importações necessárias para trabalhar com JPA e mapeamentos
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
/**
 * Representa a entidade Endereco do banco de dados.
 * É uma classe POJO (Plain Old Java Object).
 */
@Entity // Informa ao JPA que essa classe será mapeada para uma tabela
public class Endereco {
    // Chave primária gerada automaticamente (AUTO_INCREMENT no MySQL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEndereco;
    // Campos simples da entidade
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    /**
     * Relacionamento um-para-um com Pessoa.
     * FetchType.LAZY evita que Pessoa seja carregada imediatamente.
     * optional = false exige que um Endereco sempre tenha uma Pessoa.
     * A chave estrangeira será a coluna id_pessoa.
     */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
    // Construtor padrão obrigatório para JPA
    protected Endereco() {
    }
    // Construtor com todos os atributos para facilitar instâncias
    public Endereco(String rua, String cidade, String estado, String cep, Pessoa p) {
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.pessoa = p;
    }
    // Getters e Setters gerados automaticamente ou com Lombok
    public Integer getIdEndereco() {
        return idEndereco;
    }
    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    // Representação textual da entidade
    @Override
    public String toString() {
        return "Endereco [cep=" + cep + ", cidade=" + cidade + ", estado=" + estado +
                ", idEndereco=" + idEndereco + ", rua=" + rua + "]";
    }
    // hashCode e equals baseados no idEndereco (boas práticas)
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idEndereco == null) ? 0 : idEndereco.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Endereco other = (Endereco) obj;
        return idEndereco != null && idEndereco.equals(other.idEndereco);
    }
}