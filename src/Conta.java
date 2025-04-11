import java.math.BigDecimal;

public class Conta {
    BigDecimal saldo = new BigDecimal(0.00);

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = new BigDecimal(saldo);
    }

    //desenvolvido com apoio de meu amigo Carlos Welliton
    public boolean transferir(String valor) {
        try {
            int verificador = new BigDecimal(""+saldo).compareTo(new BigDecimal(valor));
            if ((verificador == 0) || (verificador == 1)) {
                this.saldo = saldo.subtract(new BigDecimal(valor));
                return true;

            } else{
                System.out.println("Valor insuficiente");
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
