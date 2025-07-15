// Define o pacote da aplicação
package ads.bcd;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ads.bcd.model.Endereco;
import ads.bcd.model.Pessoa;
import ads.bcd.repository.EnderecoRepository;
import ads.bcd.repository.PessoaRepository;
/**
 * A anotação @SpringBootApplication indica que esta é a classe principal da aplicação Spring Boot.
 */
@SpringBootApplication
public class ExemploJpaApplication {
    // Logger para registrar mensagens úteis durante a execução da aplicação
    private static final Logger log = LoggerFactory.getLogger(ExemploJpaApplication.class);
    // Injeção automática dos repositórios para acessar o banco de dados
    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    // Método main que inicia a aplicação Spring Boot
    public static void main(String[] args) {
        SpringApplication.run(ExemploJpaApplication.class, args);
        log.info("Aplicação finalizada");
    }
/**
 *Bean que será executado logo após a aplicação iniciar, graças à interface CommandLineRunner.
 * Serve para rodar código de exemplo, como popular banco e listar dados.
*/
    @Bean
    public CommandLineRunner demoOneToOne() {
        return (args) -> {
            try {
                log.info("Iniciando aplicação");
// Método para inserir dados de exemplo
                this.povoarBase();
// Método para listar dados inseridos
                this.listandoRegistros();
            } catch (Exception e) {
// Registra erros no log caso ocorram
                log.error(e.toString());
            }
        };
    }
    /**
     * Método para popular o banco de dados com registros de Pessoa e Endereco.
     * Exemplo simples para demonstrar relacionamento um-para-um.
     */
    private void povoarBase() throws Exception {
// Criando objetos Pessoa
        Pessoa juca = new Pessoa("Juca de Oliveira", "juca@email.com", "123.456.789-00");
        Pessoa pedro = new Pessoa("Pedro", "pedro@email.com", "456-789-012-33");
// Criando objetos Endereco associados às pessoas
        Endereco enderecoJuca = new Endereco("Rua das Oliveiras, 10", "São José", "SC", "88.103-30", juca);
        Endereco enderecoPedro = new Endereco("Rua José Lino Kretzer, 608", "São José", "SC", "88.103-30",
                pedro);
// Atribuindo os endereços às pessoas
        juca.setEndereco(enderecoJuca);
        pedro.setEndereco(enderecoPedro);
// Salvando as pessoas no banco, que por cascata salvam os endereços
        pessoaRepository.save(juca);
        pessoaRepository.save(pedro);
    }
    /**
     * Método para listar todos os registros de Pessoa e buscar pessoas por CPF específico.
     */
    private void listandoRegistros() throws Exception {
        System.out.println("----------- Todas Pessoas ---------------------");
        for (var pessoa : pessoaRepository.findAll()) {
            System.out.println(pessoa);
        }
        System.out.println("-----------------------------------------------");
        System.out.println("------- Pessoas com CPF específico ------------");
        List<Pessoa> resultado = pessoaRepository.findByCpf("123.456.789-00");
        resultado.forEach(System.out::println);
        System.out.println("-----------------------------------------------");
    }
}