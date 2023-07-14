package br.ufrn.imd.carteirafinanceira.configuration;

import br.ufrn.imd.carteirafinanceira.dao.CreditCardDAO;
import br.ufrn.imd.carteirafinanceira.dao.UserDAO;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadData {
    UserDAO userDAO = new UserDAO();
    CreditCardDAO creditCardDAO = new CreditCardDAO();

  /*  @Bean
    public void loadDefaultData(){
        if(Objects.isNull(usuarioDAO.get("123"))){
            usuarioDAO.save(new Usuario(
                    "123", "root", "root", "root", "root@gmail")
            );
        }

        //creditCardDAO.save(new CreditCard(0, ))
    }*/
}
