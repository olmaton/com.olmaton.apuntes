package modelo.dto;

import entidades.Movimiento;

/**
 *
 * @author olmaton
 */
public class MovimientoCuentaACuentaDTO {
    private Movimiento origen;
    private Movimiento destino;

    public Movimiento getOrigen() {
        return origen;
    }

    public void setOrigen(Movimiento origen) {
        this.origen = origen;
    }

    public Movimiento getDestino() {
        return destino;
    }

    public void setDestino(Movimiento destino) {
        this.destino = destino;
    }
    
    
}
