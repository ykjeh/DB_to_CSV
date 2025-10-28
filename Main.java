import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(20,10,490,83);
        panel.setBackground(Color.DARK_GRAY);


        JLabel labelStatus = new JLabel("Status:");
        labelStatus.setForeground(Color.BLACK);
        labelStatus.setBounds(50, 340, 450, 90);
        JLabel labelConectando = new JLabel("Desconectado");
        labelConectando.setForeground(Color.DARK_GRAY);
        labelConectando.setBounds(97, 340, 450, 90);
        JLabel labelQuery = new JLabel("Query:");
        labelQuery.setForeground(Color.BLACK);
        labelQuery.setBounds(50, 70, 400, 90);
        JLabel labelCaminho = new JLabel("Nome que deseja salvar o arquivo: ");
        labelCaminho.setBounds(50, 140, 400, 90);
        labelCaminho.setForeground(Color.BLACK);
        JLabel labelurl = new JLabel("URL:");
        labelurl.setForeground(Color.WHITE);
        labelurl.setBounds(50, -12, 400, 90);
        JLabel labelRoot = new JLabel("Root:");
        labelRoot.setForeground(Color.WHITE);
        labelRoot.setBounds(50, 23, 400, 90);
        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setForeground(Color.WHITE);
        labelSenha.setBounds(300, 23, 400, 90);

        JTextField fieldQuery = new JTextField();
        fieldQuery.setBounds(50, 130, 435, 25);
        JTextField fieldCaminho = new JTextField();
        fieldCaminho.setBounds(50, 200, 435, 25);
        JTextField fieldUrl = new JTextField();
        fieldUrl.setBounds(85, 20, 390, 25);
        JTextField fieldRoot = new JTextField();
        fieldRoot.setBounds(85, 55, 210, 25);
        JTextField fieldSenha = new JTextField();
        fieldSenha.setBounds(347, 55, 130, 25);

        JButton buttonConectar = new JButton("CONECTAR");
        buttonConectar.setBounds(170, 260, 200, 80);
        buttonConectar.addActionListener(new ActionListener() {

            @Override
            // adiciona uma função ao clicar no botao
            public void actionPerformed(ActionEvent e) {
                String user = fieldRoot.getText();
                String password = fieldSenha.getText();
                String url = fieldUrl.getText();
                String query = fieldQuery.getText();
                String nameArchive = fieldCaminho.getText();
                Search pesquisar = new Search();


                if (fieldQuery.getText().trim().equals("") || fieldRoot.getText().equals("") ||
                fieldSenha.getText().equals("") || fieldUrl.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos.",
                            "Erro", JOptionPane.WARNING_MESSAGE);
                } else {
                if (fieldCaminho.getText().trim().equals("")){
                    JOptionPane.showMessageDialog(null, "O arquivo sera salvo na sua area de trabalho" +
                                    " com o nome Extracao.txt.",
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        pesquisar.executeQuery(url, user, password, query, "");
                        labelConectando.setForeground(Color.GREEN);
                        labelConectando.setText("Conexão bem sucedida!");
                    } catch (IOException ex) {
                        labelConectando.setText("Conexão falhou!");
                        labelConectando.setForeground(Color.RED);
                        throw new RuntimeException(ex);

                    }
                } else {
                   
                    try {
                        pesquisar.executeQuery(url, user, password, query, nameArchive);
                        labelConectando.setForeground(Color.GREEN);
                        labelConectando.setText("Conexão bem sucedida!");


                    } catch (IOException ex) {
                        labelConectando.setText("Conexão falhou!");
                        labelConectando.setForeground(Color.RED);
                        throw new RuntimeException(ex);
                    }
                }
            }}
        });
            
        frame.add(buttonConectar);
        frame.add(labelStatus);
        frame.add(fieldQuery);
        frame.add(labelQuery);
        frame.add(labelCaminho);
        frame.add(fieldCaminho);
        frame.add(labelConectando);
        frame.add(labelurl);
        frame.add(fieldUrl);
        frame.add(labelRoot);
        frame.add(labelSenha);
        frame.add(fieldRoot);
        frame.add(fieldSenha);
        frame.add(panel);


        frame.getContentPane().setBackground(Color.gray);
        frame.setLayout(null);

        frame.setBounds(600, 400, 550, 460);
        //torna o tamanho do frame fixo
        frame.setResizable(false);

        //encerra o programa ao fechar a interface
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
//}
