package dao;

public class DaoImplV2 implements IDao {

    public void init() {
        System.out.println("Initialisation de DaoImplV2");
    }

    public DaoImplV2() {
        System.out.println("Instanciation de DaoImplV2");
    }

    @Override
    public double getData() {
        /**
         * Get data from web service
         */
        System.out.println("Web service version");
        double data = 12;
        return data;
    }
}
