import java.sql.*;
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


class Search {

    String executeQuery(String url, String user, String password, String query, String nomeTxt) throws IOException {

        String desktopPath = System.getProperty("user.home") + "/Desktop/";
        String caminhoNome;
        Path Caminho;

            if (nomeTxt.equals("")){
                caminhoNome = desktopPath + "Extracao.txt";
                Caminho = Paths.get(caminhoNome);
            } else {
                caminhoNome = desktopPath + nomeTxt + ".txt";
                Caminho = Paths.get(caminhoNome);
            }
            try {
                Files.createFile(Caminho);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Aviso, o caminho pode estar errado" +
                                " ou um arquivo com o mesmo caminho e nome ja existe. Verifique, por gentileza.",
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
                System.err.println(e.getMessage());
            }

            try (Connection conexao = DriverManager.getConnection(url, user, password)) {
                try (Statement stmt = conexao.createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {
                    ResultSetMetaData metaData = rs.getMetaData();
                    int numeroColunas = metaData.getColumnCount() - 1;
                    StringBuilder linha = new StringBuilder();
                    for (int i = 1; i <= numeroColunas; i++) {
                        String coluna = (metaData.getColumnName(i));
                        linha.append(coluna);
                        if (i < numeroColunas) {
                            linha.append(", ");
                        }
                    }
                    linha.append("\n");

                    // runs to the next record
                    while (rs.next()) {
                        for (int j = 0; j < numeroColunas; j++) {
                            String valor = rs.getString(j + 1);
                            valor = valor.replace(",", ".");
                            linha.append(valor);
                            if (valor == null) valor = "";
                            if (j < numeroColunas - 1) {
                                linha.append(", ");
                            }
                        }
                        linha.append("\n");

                    }
                    Files.write(Caminho, linha.toString().getBytes(), StandardOpenOption.APPEND);

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro na conexão, certifique-se de que os dados estão corretos.",
                            "Erro", JOptionPane.WARNING_MESSAGE);

                    throw new RuntimeException(e);

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Erro na conexão, verifique se o caminho e o nome das tabelas estão corretos!",
                            "Erro", JOptionPane.WARNING_MESSAGE);
                    throw new RuntimeException(e);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro na conexão, certifique-se de que os dados estão corretos.",
                        "Erro", JOptionPane.WARNING_MESSAGE);
                throw new RuntimeException(e);
            }

        return url;

    }
}

class Testar extends Thread {
    public static void main(String[] args) throws IOException {
        Testar thread = new Testar();
        thread.start()
    }
}


