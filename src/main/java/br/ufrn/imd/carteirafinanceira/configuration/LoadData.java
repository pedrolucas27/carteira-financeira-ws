package br.ufrn.imd.carteirafinanceira.configuration;

import br.ufrn.imd.carteirafinanceira.dao.CreditCardDAO;
import br.ufrn.imd.carteirafinanceira.dao.UserDAO;
import br.ufrn.imd.carteirafinanceira.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class LoadData {
    UserDAO userDAO = new UserDAO();
    CreditCardDAO creditCardDAO = new CreditCardDAO();

    @Bean
    public void loadDefaultData(){
        if(Objects.isNull(userDAO.get("123"))){
            userDAO.save(new User(
                    "123", "root", "root", "root", "root@gmail")
            );
        }

        //creditCardDAO.save(new CreditCard(0, ))
    }
}
