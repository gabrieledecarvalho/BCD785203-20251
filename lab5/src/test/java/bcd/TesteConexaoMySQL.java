package bcd;
// Importa a fábrica de conexões para o MySQL
import exemplo05.db.ConnectionFactory;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
// Importações do JUnit 5 para testes unitários
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * Classe para executar teste de unidade sobre a conexão com MySQL
 */
public class TesteConexaoMySQL {
    @Test
    public void testarConexao() throws IOException, SQLException {
// Tenta obter uma conexão com o banco de dados
        Connection conexao = ConnectionFactory.getDBConnection();
// Verifica se a conexão não é nula (conexão estabelecida com sucesso)
        assertNotNull(conexao, "Não foi possível conectar no servidor MySQL");
    }
}