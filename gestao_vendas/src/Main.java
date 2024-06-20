import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import DAO.VendaDAO;
import venda.Venda;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static final VendaDAO vendaDAO = new VendaDAO();

    public static void main(String[] args) {
        // Criação do frame principal
        JFrame frame = new JFrame("Sistema de Vendas");
        frame.setSize(400, 300);
        frame.setLocation(500,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
       
        // Botão para cadastrar venda
        JButton btnCadastrarVenda = new JButton("Cadastrar Venda");
        btnCadastrarVenda.setBounds(100, 130, 200, 30);
        frame.add(btnCadastrarVenda);
        btnCadastrarVenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarVenda();
            }
        });

        // Botão para listar vendas
        JButton btnListarVendas = new JButton("Listar Vendas");
        btnListarVendas.setBounds(100, 170, 200, 30);
        frame.add(btnListarVendas);
        btnListarVendas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarVendas();
            }
        });

        frame.setVisible(true);
    }
    private static void cadastrarVenda() {
        JFrame frame = new JFrame("Cadastrar Venda");
        frame.setLocation(500,200);
        frame.setSize(400, 300);
        frame.setLayout(null);

        JLabel lblData = new JLabel("Data (AAAA-MM-DD):");
        lblData.setBounds(20, 20, 150, 30);
        frame.add(lblData);

        JTextField txtData = new JTextField();
        txtData.setBounds(170, 20, 200, 30);
        frame.add(txtData);

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(20, 60, 150, 30);
        frame.add(lblCliente);

        JTextField txtCliente = new JTextField();
        txtCliente.setBounds(170, 60, 200, 30);
        frame.add(txtCliente);

        JLabel lblValor = new JLabel("Valor Total:");
        lblValor.setBounds(20, 100, 150, 30);
        frame.add(lblValor);

        JTextField txtValor = new JTextField();
        txtValor.setBounds(170, 100, 200, 30);
        frame.add(txtValor);

        JLabel lblPagamento = new JLabel("Forma de Pagamento:");
        lblPagamento.setBounds(20, 140, 150, 30);
        frame.add(lblPagamento);

        JTextField txtPagamento = new JTextField();
        txtPagamento.setBounds(170, 140, 200, 30);
        frame.add(txtPagamento);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(170, 180, 100, 30);
        frame.add(btnSalvar);
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Date data = Date.valueOf(txtData.getText());
                    String cliente = txtCliente.getText();
                    BigDecimal valorTotal = new BigDecimal(txtValor.getText());
                    String formaPagamento = txtPagamento.getText();
                    Venda venda = new Venda(data, cliente, valorTotal, formaPagamento);
                    vendaDAO.cadastrarVenda(venda);
                    JOptionPane.showMessageDialog(frame, "Venda cadastrada com sucesso!");
                    frame.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao cadastrar venda: " + ex.getMessage());
                }
            }
        });

        frame.setVisible(true);
    }

    private static void listarVendas() {
        JFrame frame = new JFrame("Lista de Vendas");
        frame.setLocation(500,200);
        frame.setSize(400, 300);
        frame.setLayout(null);

        JTextArea txtVendas = new JTextArea();
        txtVendas.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(txtVendas);
        scrollPane.setBounds(20, 20, 350, 200);
        frame.add(scrollPane);

        List<Venda> vendas = vendaDAO.listarVendas();
        if (vendas.isEmpty()) {
            txtVendas.setText("Não há vendas cadastradas.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Venda venda : vendas) {
                sb.append(venda).append("\n");
            }
            txtVendas.setText(sb.toString());
        }

        frame.setVisible(true);
    }

}