package com.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.DAO.CustomerDAO;
import com.DAO.OrderDAO;
import com.DAO.ProductDAO;
import com.model.Customer;
import com.model.Orders;
import com.model.Product;
import com.util.TableCreator;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ProductDAO productDAO = new ProductDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        OrderDAO orderDAO = new OrderDAO();

        TableCreator tableCreator = new TableCreator();
        tableCreator.createTables();

        int choice;

        do {

            System.out.println("\n========================================");
            System.out.println(" E-Commerce Product Management System");
            System.out.println("========================================");
            System.out.println("1. Insert Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product Price");
            System.out.println("4. Delete Product");
            System.out.println("5. Search Product By Category");
            System.out.println("6. Update Product Stock");
            System.out.println("7. Insert Customer");
            System.out.println("8. View Customers");
            System.out.println("9. Update Customer");
            System.out.println("10. Delete Customer");
            System.out.println("11. Place Order");
            System.out.println("12. View Orders");
            System.out.println("13. Cancel Order");
            System.out.println("14. Batch Insert Products");
            System.out.println("15. Order Tracking");
            System.out.println("16. View Customer Orders");
            System.out.println("17. Exit");
            System.out.print("Enter Choice : ");

            choice = sc.nextInt();

            switch (choice) {

            case 1:

                Product product = new Product();

                sc.nextLine();

                System.out.print("Enter Product Name : ");
                product.setProductName(sc.nextLine());

                System.out.print("Enter Category : ");
                product.setCategory(sc.nextLine());

                System.out.print("Enter Price : ");
                product.setPrice(sc.nextDouble());

                System.out.print("Enter Stock : ");
                product.setStock(sc.nextInt());

                productDAO.insertProduct(product);

                break;

            case 2:

                productDAO.viewProducts();

                break;

            case 3:

                System.out.print("Enter Product ID : ");
                int productId = sc.nextInt();

                System.out.print("Enter New Price : ");
                double price = sc.nextDouble();

                productDAO.updateProductPrice(productId, price);

                break;

            case 4:

                System.out.print("Enter Product ID : ");
                productDAO.deleteProduct(sc.nextInt());

                break;

            case 5:

                sc.nextLine();

                System.out.print("Enter Category : ");
                productDAO.searchByCategory(sc.nextLine());

                break;

            case 6:

                System.out.print("Enter Product ID : ");
                int pid = sc.nextInt();

                System.out.print("Enter New Stock : ");
                int stock = sc.nextInt();

                productDAO.updateStock(pid, stock);

                break;

            case 7:

                Customer customer = new Customer();

                sc.nextLine();

                System.out.print("Enter Customer Name : ");
                customer.setCustomerName(sc.nextLine());

                System.out.print("Enter Email : ");
                customer.setEmail(sc.nextLine());

                System.out.print("Enter Phone : ");
                customer.setPhone(sc.nextLine());

                customerDAO.insertCustomer(customer);

                break;

            case 8:

                customerDAO.viewCustomers();

                break;

            case 9:

                System.out.print("Enter Customer ID : ");
                int customerId = sc.nextInt();

                sc.nextLine();

                System.out.print("Enter New Email : ");
                String email = sc.nextLine();

                System.out.print("Enter New Phone : ");
                String phone = sc.nextLine();

                customerDAO.updateCustomer(customerId, email, phone);

                break;

            case 10:

                System.out.print("Enter Customer ID : ");
                customerDAO.deleteCustomer(sc.nextInt());

                break;

            case 11:

                Orders order = new Orders();

                System.out.print("Enter Customer ID : ");
                order.setCustomerId(sc.nextInt());

                sc.nextLine();

                System.out.print("Enter Order Date (YYYY-MM-DD) : ");
                order.setOrderDate(sc.nextLine());

                System.out.print("Enter Total Amount : ");
                order.setTotalAmount(sc.nextDouble());

                orderDAO.placeOrder(order);

                break;

            case 12:

                orderDAO.viewOrders();

                break;

            case 13:

                System.out.print("Enter Order ID : ");
                orderDAO.cancelOrder(sc.nextInt());

                break;

            case 14:

                List<Product> products = new ArrayList<>();

                products.add(new Product(0, "Laptop", "Electronics", 65000, 10));
                products.add(new Product(0, "Mouse", "Accessories", 500, 50));
                products.add(new Product(0, "Keyboard", "Accessories", 1200, 30));

                orderDAO.batchInsertProducts(products);

                break;

            case 15:

                System.out.print("Enter Order ID : ");
                orderDAO.orderTracking(sc.nextInt());

                break;

            case 16:

                orderDAO.viewCustomerOrders();

                break;

            case 17:

                System.out.println("Thank You...!");

                break;

            default:

                System.out.println("Invalid Choice.");

            }

        } while (choice != 17);

        sc.close();

    }

}