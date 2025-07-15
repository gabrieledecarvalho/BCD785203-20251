package bcd;

import exemplo01.ExemploMuitoSimples;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

// O uso de assertEquals e assertFalse garante que os métodos estão retornando os valores esperados.
// O banco de dados é restaurado ao estado inicial após cada teste crítico

// Define a ordem dos testes pelo nome do método, para garantir a sequência esperada
@TestMethodOrder(MethodOrderer.MethodName.class)

public class TesteExemplo01SQLite {
    private ExemploMuitoSimples app;

    // O construtor TesteExemplo01SQLite() inicializa o banco com um registro padrão chamando criaBancoDeDados()
    public TesteExemplo01SQLite() throws Exception {
        this.app = new ExemploMuitoSimples();
        this.app.criaBancoDeDados(); // cria a tabela com um registro padrão
    }

    // Testa o cadastro de uma nova pessoa
    @Test
    public void testeAincluirRegistro() throws SQLException {
        int resultado = this.app.cadastrarPessoa("Juca", 71, 174, "juca@email.com");
        assertEquals(1, resultado);
    }

    // Verifica se os dados estão sendo listados corretamente
    @Test
    public void testeBlistarRegistros() throws SQLException {
        String registros = this.app.listarRegistros();
        assertFalse(registros.equals(""), "Banco sem registros iniciais");
        Logger.getLogger(TesteExemplo01SQLite.class.getName()).log(Level.INFO, "\n" + registros);
    }

    // Altera os dados de uma pessoa com ID 1
    @Test
    public void testeDalterarRegistro() throws Exception {
        int resultado = this.app.alterarDadosPessoa(1, "Novo nome", 82, 180, "aluno@teste.com.br");
        assertEquals(1, resultado);
        this.app.criaBancoDeDados(); // reinicia o banco
    }

    // Exclui a pessoa com ID 1
    @Test
    public void testeEexcluirRegistro() throws Exception {
        this.app.criaBancoDeDados(); // reinicia o banco
        assertEquals(1, this.app.excluirPessoa(1));
        this.app.criaBancoDeDados(); // reinicia o banco novamente
    }
}