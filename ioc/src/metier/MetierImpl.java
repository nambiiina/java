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

    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
