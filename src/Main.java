import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("CoffeeMachine");

        System.out.print("Enter the amount of your money to buy ");
        int moneyAmount = new Scanner(System.in).nextInt();

        ArrayList<Product> products = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("drinks.txt"));
            if(lines.equals(null)){
                System.out.println("File is empty");
            }
        } catch (IOException exception) {
            System.out.println("File is not readable");
        }

        for (String line : lines) {
            String[] parts = line.split("\s+");
            int price = Integer.parseInt(parts[1]);
            products.add(new Product(price, parts[0]));
        }

        checkPrice(products, moneyAmount);
    }

    public static void checkPrice(ArrayList<Product> products, int moneyAmount) {
        boolean canBuyAnything = false;

        for (Product product : products) {
            if (moneyAmount >= product.getPrice()) {
                System.out.println("You may buy " + product.getName());
                canBuyAnything = true;
            }
        }

        if (!canBuyAnything) {
            System.out.println("Insufficient funds :(");
        }
    }
}
