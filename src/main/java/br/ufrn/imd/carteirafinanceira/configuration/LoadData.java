package br.ufrn.imd.carteirafinanceira.configuration;

import br.ufrn.imd.carteirafinanceira.dao.CreditCardDAO;
import br.ufrn.imd.carteirafinanceira.dao.UsuarioDAO;
import br.ufrn.imd.carteirafinanceira.model.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class LoadData {
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    CreditCardDAO creditCardDAO = new CreditCardDAO();

    @Bean
    public void loadDefaultData(){
        if(Objects.isNull(usuarioDAO.get("123"))){
            usuarioDAO.save(new Usuario(
                    "123", "root", "root", "root", "root@gmail")
            );
        }

        //creditCardDAO.save(new CreditCard(0, ))
    }
}
