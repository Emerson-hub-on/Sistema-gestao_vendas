package venda;
import java.math.BigDecimal;
import java.sql.Date;

public class Venda {
    private int id;
    private Date data;
    private String cliente;
    private BigDecimal valorTotal;
    private String formaPagamento;

    public Venda( Date data, String cliente, BigDecimal valorTotal, String formaPagamento) {
        this.data = data;
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String toString() {
        return "\n\nCódigo = " + id + "\nData da venda = " + data + "\nNome do cliente = " + cliente + "\nTotal = " + valorTotal
                + "\nForma de pagamento = " + formaPagamento + "";
    }
    
    
}