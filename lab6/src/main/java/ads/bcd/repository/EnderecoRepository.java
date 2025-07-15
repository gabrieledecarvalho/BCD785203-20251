// Pacote onde está essa interface
package ads.bcd.repository;
// Importa a interface CrudRepository do Spring Data
import org.springframework.data.repository.CrudRepository;
// Importa a classe Endereco que será gerenciada
import ads.bcd.model.Endereco;
/**
 * Objetivo da interface repository do Spring Data:
 * Reduzir a quantidade de código repetitivo necessário para a camada
 * de acesso a dados.
 **
 Documentação oficial:
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories
 **
 Métodos herdados da interface CrudRepository:
 **
 <S extends T> S save(S entity); // salva ou atualiza uma entidade no banco
 * Optional<T> findById(ID primaryKey); // busca uma entidade pelo ID
 * Iterable<T> findAll(); // retorna todas as entidades
 * long count(); // retorna a quantidade total de entidades
 * void delete(T entity); // remove uma entidade
 * boolean existsById(ID primaryKey); // verifica se existe uma entidade com dado ID
 */
public interface EnderecoRepository extends CrudRepository<Endereco, Integer> {
}