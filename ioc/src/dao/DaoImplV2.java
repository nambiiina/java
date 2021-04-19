package dao;

public class DaoImplV2 implements IDao {
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
