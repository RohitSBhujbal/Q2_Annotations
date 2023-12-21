package productSimulation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;

public class ProductCRUDOperations {
    private static Scanner sc=new Scanner(System.in);
    static  SessionFactory factory;
            static{
                Configuration cfg=new Configuration().configure().addAnnotatedClass(Product.class);
                factory= cfg.buildSessionFactory();
            }
            private static void Operations() {
                boolean status = true;
                while (status) {
                    System.out.println("1. ADD PRODUCT");
                    System.out.println("2. UPDATE PRODUCT");
                    System.out.println("3. DELETE PRODUCT");
                    System.out.println("4. DISPLAY PRODUCT");
                    System.out.println("5. EXIT");
                    System.out.println("ENTER YOUR CHOICE");
                    int choice = sc.nextInt();

                    switch (choice){
                        case 1:
                            addData();
                            break;
                        case 2:
                            updateData();
                            break;
                        case 3:
                            deleteData();
                            break;
                        case 4:
                            displayData();
                            break;
                        case 5:
                            System.out.println("THANK U FOR VISITING...!");
                            status=false;
                            break;
                        default:
                            System.out.println("ENTER VALID CHOICE...!");
                            break;
                    }
                }
            }

    private static void deleteData() {
        System.out.println("ENTER PRODUCT ID");
        int id=sc.nextInt();
        Session session=factory.openSession();
        Product p1=session.get(Product.class,id);
        Transaction tx=session.beginTransaction();
        session.delete(p1);
        tx.commit();
        session.close();
    }

    private static void addData(){
                System.out.println("ENTER PRODUCT ID");
                int id=sc.nextInt();
                System.out.println("ENTER PRODUCT NAME");
                String name=sc.next();
                System.out.println("ENTER PRODUCT PRICE");
                double price=sc.nextDouble();
                System.out.println("ENTER PRODUCT CATEGORY");
                String cat=sc.next();
                System.out.println("ENTER PRODUCT QUANTITY");
                int qty=sc.nextInt();

                Product p1=new Product();
                p1.setProductId(id);
                p1.setProductName(name);
                p1.setProductPrice(price);
                p1.setProductCategory(cat);
                p1.setProductQuantity(qty);

                Session session=factory.openSession();
                Transaction tx=session.beginTransaction();
                session.save(p1);
                tx.commit();
                session.close();
                System.out.println("PRODUCT DATA INSERTED SUCCESSFULLY...!");
            }
            private static void updateData(){
                System.out.println("ENTER ID FOR UPDATE DATA");
                int id=sc.nextInt();
                System.out.println("ENTER PRICE");
                double price=sc.nextDouble();

                Session session=factory.openSession();
                Product p2=session.get(Product.class,id);
                p2.setProductPrice(price);
                session.update(p2);
                Transaction tx=session.beginTransaction();
                tx.commit();
                session.close();

            }

            private static void displayData(){
                System.out.println("ENTER PRODUCT ID");
                int id=sc.nextInt();
                Session session=factory.openSession();
                Product p1=session.get(Product.class,id);
                System.out.println("ID :"+p1.getProductId());
                System.out.println("NAME :"+p1.getProductName());
                System.out.println("PRICE :"+p1.getProductPrice());
                System.out.println("CAT :"+p1.getProductCategory());
                System.out.println("QTY :"+p1.getProductQuantity());
                Transaction tx=session.beginTransaction();
                tx.commit();
                session.close();

            }
    public static void main(String[] args) {
                Operations();
    }
}
