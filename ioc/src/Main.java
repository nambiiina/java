import dao.DaoImpl;
import metier.MetierImpl;

public class Main {
    public static void main(String[] args) {
        /**
         * Static IOC
         * Strong coupling
         */
        MetierImpl metierImpl = new MetierImpl();
        DaoImpl daoImpl = new DaoImpl();
        metierImpl.setDao(daoImpl);
        System.out.println(metierImpl.calcul());
    }
}
