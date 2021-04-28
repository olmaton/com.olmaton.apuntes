/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao.pgsql;

import entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import modelo.intefaces.IAuthModelDao;
import presentacion.utiles.OlmException;

/**
 *
 * @author olmaton
 */
public class AuthDao implements IAuthModelDao{

    @Override
    public Usuario login(String email, String password) throws OlmException {
        try {
            PreparedStatement ps = ConexionPgsql.get().prepareStatement("select * from usuarios where email=?;");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombres(rs.getString("Nombres"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setPassword(rs.getString("Password"));
                usuario.setCreado(rs.getObject("creado", LocalDateTime.class));
                return usuario;
            }
        } catch (SQLException e) {
            throw new OlmException(e.getMessage(), 3,this.getClass().getName()+".login");
        }
        return null;
    }
    
}
