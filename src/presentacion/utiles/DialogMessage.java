package presentacion.utiles;

import configuracion.Colores;
import java.awt.Frame;

/**
 *
 * @author olmaton
 */
public class DialogMessage {
    JdiMessage jdiMessage;
    JdiMessageConfirm jdiMessageConfirmar;
    private static DialogMessage instancia = null;
    public static DialogMessage getInstancia() {
        if (instancia == null) {
            instancia = new DialogMessage();
        }
        return instancia;
    }

    private DialogMessage() {     
        
    }
    
    public void setMensaje(Frame parent,String mensaje, int code){
        switch(code){
            case 0:{
                //Mensaje OK
                jdiMessage = new JdiMessage(parent);
                jdiMessage.setLabelTitle("Mensaje",mensaje,Colores.getAlert0(),Colores.getAlert0Dark());
                jdiMessage.setVisible(true);
                break;
            }
            
            case 1:{
                //Mensaje informativo
                jdiMessage = new JdiMessage(parent);
                jdiMessage.setLabelTitle("Mensaje",mensaje,Colores.getAlert1(),Colores.getAlert1Dark());
                jdiMessage.setVisible(true);
                break;
            }
            
            case 2:{
                //Mensaje de alerta
                jdiMessage = new JdiMessage(parent);
                jdiMessage.setLabelTitle("Alerta",mensaje,Colores.getAlert2(),Colores.getAlert2Dark());
                jdiMessage.setVisible(true);
                break;
            }
            
            case 3:{
                //Mensaje de error
                jdiMessage = new JdiMessage(parent);
                jdiMessage.setLabelTitle("Error",mensaje,Colores.getAlert3(),Colores.getAlert3Dark());
                jdiMessage.setVisible(true);
                break;
            }            
         
            default:{
                jdiMessage = new JdiMessage(parent);
                jdiMessage.setLabelTitle("Mensaje",mensaje,Colores.getAlert1(),Colores.getAlert1Dark());
                jdiMessage.setVisible(true);
            }
        }
    }
    
    public boolean confirmar(Frame parent,String mensaje){
        jdiMessageConfirmar = new JdiMessageConfirm(parent);
        jdiMessageConfirmar.setLabelTitle("Confirme",mensaje,Colores.getAlert2());
        jdiMessageConfirmar.setVisible(true);
        return jdiMessageConfirmar.isConfirmado();
    }   
    
}
