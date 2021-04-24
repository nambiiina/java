package metier;

import dao.IDao;

public class MetierImpl implements IMetier {

    /**
     * Open/Closed principle
     */

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
