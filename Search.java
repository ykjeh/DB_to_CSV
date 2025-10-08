import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;


class Search {
    // pegas prametros query, caminho do arquivo e colunas da tabela
    String executarQuery(String query, Path caminho, String[] colunas) throws IOException {
        String url = "jdbc:mysql://127.10.20.30:3306/medicoes_indiretas";
        String user = "root";
        String password = "#Curitiba@123";
        String result;
        try {
            Files.createFile(caminho);
        } catch (Exception e) {
            System.err.println("Error, archive already exists: " + e.getMessage());
        }
        String header = String.join(", ", colunas) + "\n";
        Files.write(caminho, header.getBytes(), StandardOpenOption.APPEND);

        // standard query (this query is a example only where you can put your mysql query)
        

        // do the connection and execute query
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // runs to the next record
            while (rs.next()) {
                StringBuilder linha = new StringBuilder();
                // insert the results in the row according to the number of columns
                for (int i = 0; i < colunas.length; i++) {
                    // get the column results
                    String valor = rs.getString(colunas[i]);
                    if (valor == null) valor = "";
                    valor = valor.replace(",", ".");
                    linha.append(valor);
                    if (i < colunas.length - 1)
                        linha.append(", ");
                }
                // break row after expand the limit of columns
                linha.append("\n");
                Files.write(caminho, linha.toString().getBytes(), StandardOpenOption.APPEND);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return url;
    }
}


class FazerBusca {
    public static void main(String[] args) throws IOException {
      // examples of objects
        Search search1 = new Search();
        pesquisar.executarQuery("select * from table1;",
                Paths.get("C:/path/to/save/txt/txt_result1.txt") // the results will be exports with csv format in txt file
                , new String[]{"column1", "column2", "column3", "column4", "column5"});

        Search search2 = new Search();
        especs.executarQuery("select * from table2;", Paths.get("C:/path/to/save/txt/txt_result2.txt"),
                new String[]{"column1", "column2", "column3"});
      
    }
}
