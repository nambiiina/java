import dao.DaoImpl;
import dao.IDao;
import metier.IMetier;
import metier.MetierImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // staticIoc();
        //dynamicIoc();

        //Run spring
        //create a collection (Map) of beans
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        springIoc(context);
    }
    private static void staticIoc() {
        /**
         * Static IOC
         * Strong coupling
         */
        MetierImpl metierImpl = new MetierImpl();
        DaoImpl daoImpl = new DaoImpl();
        metierImpl.setDao(daoImpl);
        System.out.println(metierImpl.calcul());
    }
    private static void dynamicIoc() {
        /**
         * Dynamic IOC
         * Weak coupling
         */
        try {
            Scanner scanner = new Scanner(new File("config.txt"));
            String daoClassName = scanner.nextLine();
            //load a class dynamically
            Class cDao = Class.forName(daoClassName);
            //instantiate a class dynamically
            IDao dao = (IDao) cDao.newInstance();

            String metierClassName = scanner.nextLine();
            Class cMetier = Class.forName(metierClassName);
            IMetier metier = (IMetier) cMetier.newInstance();

            //injection of dependance
            Method method = cMetier.getMethod("setDao", IDao.class);
            method.invoke(metier, dao);

            System.out.println(metier.calcul());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void springIoc(ApplicationContext context) {
        /**
         * Spring IOC by using xml file
         */
        IMetier metier = context.getBean(IMetier.class);
        System.out.println("Résultat du calcul : " + metier.calcul());
    }
}
