import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

public class Desafio extends JFrame {
    public static void main(String[] args) {
        String listaTransacoes[] = {"Consultar saldo", "Crédito", "Débito"};
        Locale localeBR = new Locale("pt","BR");
        NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
        Conta c = new Conta();
        DataHora dt = new DataHora();

        //GUI
        JFrame janela = new JFrame("Conta Bancária");
        janela.setSize(410,300);

        JPanel panel = new JPanel();
        panel.setSize(410, 500);
        panel.setLayout(null);
        janela.add(panel);

        JLabel lbDadosCliente = new JLabel("Dados do Cliente");
        lbDadosCliente.setBounds(100, 10, 100, 20);

        JLabel lbNome = new JLabel("Nome:");
        lbNome.setBounds(10, 50, 90, 20);

        JTextField tfNome = new JTextField("Márcio Pires Barboza");
        tfNome.setBounds(100, 50, 290, 20);
        tfNome.setEditable(false);

        JLabel lbTipoConta = new JLabel("Tipo da Conta:");
        lbTipoConta.setBounds(10, 80, 90, 20);

        JTextField tfTipoConta = new JTextField("Conta Corrente Pessoa Física - (Nº 12345-0)");
        tfTipoConta.setBounds(100, 80, 290, 20);
        tfTipoConta.setEditable(false);

        JLabel lbSaldo = new JLabel("Saldo:");
        lbSaldo.setBounds(10, 110, 90, 20);

        JTextField tfSaldo = new JTextField(""+c.getSaldo());
        tfSaldo.setBounds(100, 110, 290, 20);
        tfSaldo.setHorizontalAlignment(JTextField.RIGHT);
        tfSaldo.setEditable(false);

        JLabel lbTipoTransacao = new JLabel("Escolha a Transação:");
        lbTipoTransacao.setBounds(10, 150, 150, 20);

        JComboBox cbLista = new JComboBox(listaTransacoes);
        cbLista.setSelectedIndex(0);
        cbLista.setBounds(10, 170, 280, 20);

        DefaultListModel model = new DefaultListModel();
        JList listaExtrato = new JList(model);

        JScrollPane scrollPane = new JScrollPane(listaExtrato);
        scrollPane.setViewportView(listaExtrato);
        scrollPane.setBounds(410, 10, 270, 250);
        listaExtrato.setLayoutOrientation(JList.VERTICAL);
        model.addElement("############## EXTRATO ##############");
        model.addElement(dt.dataHora());
        model.addElement("Saldo: " + dinheiro.format(c.getSaldo()));
        model.addElement("------------------------------------------------------------");

        JButton btEfetivar = new JButton("Efetivar");
        btEfetivar.setBounds(300, 170, 90, 20);

        JButton btExtrato = new JButton("Extrato ->");
        btExtrato.setBounds(300, 200, 90, 20);

        JButton btSair = new JButton("Sair");
        btSair.setBounds(300, 240, 90, 20);

        //panel
        panel.add(lbDadosCliente);
        panel.add(lbNome);
        panel.add(lbTipoConta);
        panel.add(lbSaldo);
        panel.add(lbTipoTransacao);
        panel.add(tfNome);
        panel.add(tfTipoConta);
        panel.add(tfSaldo);
        panel.add(cbLista);
        panel.add(scrollPane);
        panel.add(btEfetivar);
        panel.add(btExtrato);
        panel.add(btSair);

        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        janela.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //logica
        btEfetivar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (cbLista.getSelectedIndex()){
                    case 0 : {
                        String msg = "O saldo da sua conta é: " + dinheiro.format(c.getSaldo());
                        JOptionPane.showMessageDialog(null, msg);
                        break;
                    }
                    case 1: {
                        try {
                            String valor = JOptionPane.showInputDialog("Digite o valor a ser creditado!");
                            valor = valor.replaceAll( "," , "." );
                            c.setSaldo(c.getSaldo() + Double.parseDouble(valor));
                            tfSaldo.setText(dinheiro.format(c.getSaldo()));

                            model.addElement(dt.dataHora());
                            model.addElement("Crédito de: " + dinheiro.format(Double.parseDouble(valor)) + " C");
                            model.addElement("Saldo: " + dinheiro.format(c.getSaldo()));
                            model.addElement("------------------------------------------------------------");
                        } catch (NumberFormatException erro){
                            JOptionPane.showMessageDialog(null, "Valor incorreto, tente novamente!");
                            System.out.println(erro);
                        }
                        break;
                    }
                    case 2: {
                        try {
                            String valor = JOptionPane.showInputDialog("Digite o valor a ser debitado!");
                            valor = valor.replaceAll( "," , "." );
                            c.setSaldo(c.getSaldo() - Double.parseDouble(valor));
                            tfSaldo.setText(""+dinheiro.format(c.getSaldo()));

                            model.addElement(dt.dataHora());
                            model.addElement("Débito de: " + dinheiro.format(Double.parseDouble(valor)) + " D");
                            model.addElement("Saldo: " + dinheiro.format(c.getSaldo()));
                            model.addElement("------------------------------------------------------------");
                        } catch (NumberFormatException erro){
                            JOptionPane.showMessageDialog(null, "Valor incorreto, tente novamente!");
                            System.out.println(erro);
                        }
                        break;
                    }
                }
            }
        });

        btExtrato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (btExtrato.getText().equals("Extrato ->")) {
                    janela.setSize(700,300);
                    btExtrato.setText("Extrato <-");
                } else {
                    janela.setSize(410,300);
                    btExtrato.setText("Extrato ->");
                }
            }
        });

        btSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}