package exemplo02;

// Importando a ConnectionFactory e recursos do JDBC
import exemplo02.db.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe que implementa padrões de projeto para manipulação de dados de pessoas.
 *
 * Esta classe tem como objetivo demonstrar o uso de boas práticas na manipulação de banco de dados utilizando
 * o padrão de projeto Factory para criação de conexões.
 *
 * Ela implementa o método listarPessoas(), que realiza uma consulta no banco SQLite e apresenta os
 * resultados formatados no terminal. Os principais pontos trabalhados na classe são:
 *
 * • Uso de try-with-resources para garantir o fechamento automático da conexão com o banco.
 * • Aplicação de StringBuilder para construção eficiente da saída.
 * • Formatação tabular da saída para melhor visualização no terminal.
 * • Tratamento de exceções com SQLException.
 */

// Iniciando a classe que contém a lógica para listar pessoas do banco
public class PadroesDeProjeto {
    private final String DIVISOR = "---------------------------------------------------------------------------------\n";

    // Método que consulta o banco e retorna os dados formatados
    public String listarPessoas() throws SQLException {
        StringBuilder sb = new StringBuilder(); // Usado para montar o texto final.
        String sql = "SELECT * FROM Pessoa"; // Comando SELECT para buscar todas as pessoas

        try (Connection conexao = ConnectionFactory.getDBConnection();
             Statement stmt = conexao.createStatement(); // // Abrindo conexão com o banco e executando a consulta SQL
             ResultSet rs = stmt.executeQuery(sql)) { // Os recursos são fechados automaticamente após o uso

            // Verifica se o banco está vazio e informa o usuário
            if (!rs.next()) {
                sb.append("\nNenhuma pessoa cadastrada no banco\n");
            } else {
                // Senão, imprime o cabeçalho da tabela com formatação
                sb.append(DIVISOR);
                sb.append(String.format("|%-5s|%-25s|%-10s|%-10s|%-25s|\n", "ID", "Nome", "Peso", "Altura", " Email"));
                sb.append(DIVISOR);

                // Percorre todos os registros e formata a saída para cada pessoa
                do {
                    sb.append(String.format("|%-5d|%-25s|%-10.2f|%-10d|%-25s|\n",
                            rs.getInt("idPessoa"),
                            rs.getString("Nome"),
                            rs.getDouble("peso"),
                            rs.getInt("altura"),
                            rs.getString("email")));
                } while (rs.next());
                sb.append(DIVISOR);
            }
            // Tratando erros de SQL
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        // Retornando os dados formatados em texto
        return sb.toString();
    }
}