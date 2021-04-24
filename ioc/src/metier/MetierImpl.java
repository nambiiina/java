package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetierImpl implements IMetier {

    /**
     * Open/Closed principle
     */

    //Dependency injection
    @Autowired
    private IDao dao;

    @Override
    public double calcul() {
        double d = dao.getData();
        double res = d * 23;
        return res;
    }

    public void init() {
        System.out.println("Initialisation de MetierImpl");
    }

    public void setDao(IDao dao) {
        this.dao = dao;
        System.out.println("Injection des dépendances");
    }

    public MetierImpl() {
        System.out.println("Instanciation de MetierImpl");
    }
}
