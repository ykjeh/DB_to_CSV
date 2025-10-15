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
import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionListener;
import java.io.IOException;


class PesquisaSQL {

        String executeQuery(String query, String caminhoStr, String colunasStr) throws IOException {
            String url = "jdbc:mysql://127.10.20.30:3306/medicoes_indiretas";
            String user = "root";
            String password = "#Curitiba@123";
            Path caminho = Paths.get(caminhoStr);
            String[] colunas = colunasStr.split(",");


            try {
                Files.createFile(caminho);
            } catch (Exception e) {
                System.err.println("Archive already exists: " + e.getMessage());
            }
            String header = String.join(", ", colunasStr) + "\n";
            Files.write(caminho, header.getBytes(), StandardOpenOption.APPEND);

            //   do the connection and execute query
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                // runs to the next record
                while (rs.next()) {
                    // string mutavel
                    StringBuilder linha = new StringBuilder();
                    //insert the results in the row according to the number of columns
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
                JOptionPane.showMessageDialog(null, "Conection error, make sure the information is correct.",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                System.out.println(ex);
            }
            return url;
        }
    }

class Testar extends Thread {
    public static void main(String[] args) throws IOException {
        Testar thread = new Testar();
        thread.start();
    }
}

