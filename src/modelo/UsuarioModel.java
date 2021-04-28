package modelo;

import presentacion.utiles.OlmException;
import configuracion.Configuracion;
import entidades.Cuenta;
import entidades.Tipo;
import entidades.Usuario;
import java.util.ArrayList;
import servicios.Encryption;
import modelo.intefaces.ICuentaModelDao;
import modelo.intefaces.IUsuarioModel;
import modelo.intefaces.IUsuarioModelDao;
import modelo.intefaces.ITipoModelDao;

/**
 *
 * @author olmaton
 */
public class UsuarioModel implements IUsuarioModel{
    IUsuarioModelDao dao;
    ICuentaModelDao daoCuenta;
    ITipoModelDao daoTipo;
     public UsuarioModel() {
        switch (Configuracion.ORIGEN_DATOS_BD) {
            case "mysql": {
                dao = new modelo.dao.mysql.UsuarioDao();
                daoCuenta = new modelo.dao.mysql.CuentaDao();
                daoTipo = new modelo.dao.mysql.TipoDao();
                break;
            } 
            case "pgsql": {      
                dao = new modelo.dao.pgsql.UsuarioDao();
                daoCuenta = new modelo.dao.pgsql.CuentaDao();
                daoTipo = new modelo.dao.pgsql.TipoDao();
                break;
            } 
            default: {
                break;
            }
        }
    }
     
    private Cuenta cuentaDefecto(Usuario usuario){
        Cuenta cuenta = new Cuenta();
        cuenta.setNombre("Principal");
        cuenta.setDescripcion("Esta cuenta se crea cuando se registra como nuevo usuario.");
//        cuenta.setUsuario(usuario);
        return cuenta;
    }

    @Override
    public boolean registrarme(Usuario usuario) throws OlmException {
        if( dao.existeEmail(usuario.getEmail()))throw new OlmException("Este email ya existe", 2, this.getClass().getName()+".registrarme");
        
        usuario.setPassword(Encryption.getMD5(usuario.getPassword()));
        
        if(dao.registrarme(usuario)){            
            daoCuenta.guardar(cuentaDefecto(usuario),usuario);            
            ArrayList<Tipo> tiposPorDefecto = daoTipo.listarGenerales();
            for (Tipo tipo : tiposPorDefecto) {
                daoTipo.guardarTiposUsuarios(tipo, usuario);
            }            
            return true;
        }
        return false;
    }

    @Override
    public boolean existeEmail(String email) throws OlmException {
        return dao.existeEmail(email);
    }

   
    
    
    
}
