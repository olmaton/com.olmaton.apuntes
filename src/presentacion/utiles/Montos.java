package presentacion.utiles;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author olmaton
 */
public class Montos {
    
    public static String formatoDosDecimales(Double valor){
        NumberFormat nf = new DecimalFormat("#0.00");
        return  nf.format(valor).replace(",", ".");
    }
}
