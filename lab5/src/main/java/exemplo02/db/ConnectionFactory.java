// Define o pacote da classe como exemplo02.db, indicando que está relacionada ao banco de dados
package exemplo02.db;

// Importa classes necessárias para a manipulação de conexões JDBC
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.sqlite.SQLiteConfig; // Permite configurar detalhes específicos do banco SQLite (como ativar chaves estrangeiras).

/**
 * Classe responsável por criar conexões com o banco
 *
 * A classe ConnectionFactory foi criada para centralizar e padronizar a criação de conexões com o banco de
 * dados, utilizando o padrão de projeto Factory Method.
 *
 * Por que usar essa classe?
 * • Organização: Em vez de repetir código para abrir conexões em vários pontos do sistema, colocamos
 * toda a lógica em um só lugar.
 * • Segurança e controle: Permite configurar o banco de forma centralizada. Neste exemplo, ativamos a
 * verificação de chaves estrangeiras com PRAGMA foreign_keys = ON.
 * • Reaproveitamento: Sempre que for necessário obter uma conexão, basta chamar o método getDBConnection(), evitando duplicação de código.
 * • Facilidade de manutenção: Se precisar mudar a forma como o banco é acessado (por exemplo, trocar
 * SQLite por outro banco), isso será feito apenas nesta classe.
 * Resumo: A ConnectionFactory melhora a qualidade e a manutenibilidade do código, facilitando a conexão
 * com o ban
 */

//Declara uma classe abstrata com a responsabilidade de fornecer conexões com o banco de dados.
public abstract class ConnectionFactory {

    // Ser abstrata impede sua instanciação direta, já que só fornece métodos utilitários.
    private static final String DB_URI = "jdbc:sqlite:lab01.sqlite";
    private static Connection cnx;
    private static SQLiteConfig sqLiteConfig = new SQLiteConfig();

    // DB_URI: especifica o caminho do banco SQLite (armazenado como recurso).


    public static synchronized Connection getDBConnection() throws SQLException {
        sqLiteConfig.enforceForeignKeys(true);
        try {
            // cnx: referência para a conexão com o banco
            cnx = DriverManager.getConnection(DB_URI, sqLiteConfig.toProperties()); // sqLiteConfig: configuração para o banco SQLite
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar no banco de dados", e);
        }
        return cnx;
    }
}

/*
• Método estático e sincronizado: garante que apenas uma thread por vez execute esse método.
• Ativa o uso de chaves estrangeiras com enforceForeignKeys(true).
• Tenta obter uma conexão com o banco de dados via DriverManager.
• Em caso de erro, lança uma exceção personalizada com uma mensagem mais descritiva.
• Retorna a conexão cnx
 */