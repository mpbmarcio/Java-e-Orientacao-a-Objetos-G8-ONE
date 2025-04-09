import java.text.SimpleDateFormat;
import java.util.Date;

public class DataHora {

    public String dataHora() {
        Date dh = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dh);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dh);

        return (data + " " + hora);
    };
}
