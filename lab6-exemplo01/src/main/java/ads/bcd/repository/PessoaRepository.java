// Define o pacote do projeto
package ads.bcd.repository;
// Importa List para retorno de consultas personalizadas
import java.util.List;
// Importa CrudRepository do Spring Data para operações CRUD básicas
import org.springframework.data.repository.CrudRepository;
// Importa a entidade Pessoa que será gerenciada pelo repositório
import ads.bcd.model.Pessoa;
/**
 * O objetivo da interface repository do Spring Data é reduzir a quantidade
 * de código repetitivo para acesso a dados.
 **
 Documentação oficial:
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories
 **
 Métodos herdados da interface CrudRepository:
 **
 <S extends T> S save(S entity); // salva ou atualiza uma entidade
 * Optional<T> findById(ID primaryKey); // busca uma entidade pelo ID
 * Iterable<T> findAll(); // busca todas as entidades
 * long count(); // conta o total de entidades
 * void delete(T entity); // remove uma entidade
 * boolean existsById(ID primaryKey); // verifica existência por ID
 */
public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {
    /*
    * Consultas personalizadas podem ser criadas apenas declarando o método,
    * sem implementar. O Spring Data gera a implementação automaticamente,
    * basta seguir regras para nomeação dos métodos.
    **
    Documentação:
    * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.querycreation
    * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#appendix.query.method.subject
    */
    List<Pessoa> findByCpf(String cpf);
}