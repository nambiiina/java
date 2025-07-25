package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetierImpl implements IMetier {

    /**
     * Open/Closed principle
     */

    //Dependency injection
    @Autowired
    private IDao dao;

    public MetierImpl() {
        System.out.println("Instanciation de MetierImpl");
    }

    public MetierImpl(IDao iDao) {
        this.dao = iDao;
    }

    @Override
    public double calcul() {
        double d = dao.getData();
        double res = d * 23;
        return res;
    }

    public void init() {
        System.out.println("Initialisation de MetierImpl");
    }

    /**
     * Pour injecter dans la variable dao un objet d'une classe qui implémente l'interface IDao.
     * Mais il est aussi possible d'injecter via le constructeur.
     * @param dao
     */
    public void setDao(IDao dao) {
        this.dao = dao;
        System.out.println("Injection des dépendances");
    }
}
