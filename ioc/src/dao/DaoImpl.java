package dao;

public class DaoImpl implements IDao {
    @Override
    public double getData() {
        /**
         * Open/Closed principle
         */
        /**
         * Connect to database
         */
        return 98;
    }
    public void init() {
        System.out.println("Initialisation de DaoImpl");
    }

    public DaoImpl() {
        System.out.println("Instanciation de DaoImpl");
    }
}
