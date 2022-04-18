package br.ufscar.dc.dsw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.ufscar.dc.dsw.dao.UserDAO;
import br.ufscar.dc.dsw.domain.User;
 
public class UsuarioDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UserDAO dao;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User usuario = dao.getUserByUsername(username);
         
        if (usuario == null) {
            System.out.print("Usuário não encontrado");
            throw new UsernameNotFoundException("Could not find user");
        }
        return new UsuarioDetails(usuario);
    }
 
}