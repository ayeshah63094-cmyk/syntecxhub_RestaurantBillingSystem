import java.util.ArrayList;
import java.util.Scanner;

public class Resbill {

    static class Item {
        String name;
        double price;
        int quantity;

        Item(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        double getTotal() {
            return price * quantity;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Item> order = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n--- Restaurant Billing System ---");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. View Order");
            System.out.println("4. Generate Bill & Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    order.add(new Item(name, price, qty));
                    System.out.println("Item added!");
                    break;

                case 2:
                    if (order.isEmpty()) {
                        System.out.println("Order is empty!");
                        break;
                    }
                    System.out.println("Enter item number to remove (1-" + order.size() + "):");
                    for (int i = 0; i < order.size(); i++) {
                        System.out.println((i + 1) + ". " + order.get(i).name);
                    }
                    int removeIndex = sc.nextInt() - 1;
                    sc.nextLine(); // Consume newline
                    if (removeIndex >= 0 && removeIndex < order.size()) {
                        order.remove(removeIndex);
                        System.out.println("Item removed!");
                    } else {
                        System.out.println("Invalid item number!");
                    }
                    break;

                case 3:
                    if (order.isEmpty()) {
                        System.out.println("Order is empty!");
                    } else {
                        System.out.println("\nCurrent Order:");
                        for (int i = 0; i < order.size(); i++) {
                            Item it = order.get(i);
                            System.out.println((i + 1) + ". " + it.name + " - " + it.quantity + " x " + it.price);
                        }
                    }
                    break;

                case 4:
                    if (order.isEmpty()) {
                        System.out.println("No items in order. Exiting...");
                        break;
                    }
                    double total = 0;
                    System.out.println("\n--- Bill ---");
                    for (Item it : order) {
                        double itemTotal = it.getTotal();
                        total += itemTotal;
                        System.out.println(it.name + " (" + it.quantity + " x " + it.price + ") = " + itemTotal);
                    }
                    double gst = total * 0.05; // 5% GST
                    System.out.println("GST (5%): " + gst);
                    System.out.println("Total Amount: " + (total + gst));
                    System.out.println("--- Thank you! ---");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}