import java.util.Scanner;

public class AsifAli_Coffee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] resources = {300, 200, 100, 0}; // water, milk, coffee, money
        String[] coffeeTypes = {"espresso", "latte", "cappuccino"};
        int[][] requirements = {
            {50, 0, 18, 150},   // Espresso
            {200, 150, 24, 250}, // Latte
            {250, 100, 24, 300}  // Cappuccino
        };

        while (true) {
            System.out.print("What would you like? (espresso/latte/cappuccino/report/exit): ");
            String choice = sc.next().toLowerCase();

            if (choice.equals("report")) {
                System.out.println("Water: " + resources[0] + "ml");
                System.out.println("Milk: " + resources[1] + "ml");
                System.out.println("Coffee: " + resources[2] + "g");
                System.out.printf("Money: $%.2f%n", resources[3] / 100.0);
            } else if (choice.equals("exit")) {
                System.out.println("Shutting down...");
                break;
            } else {
                int index = -1;
                for (int i = 0; i < coffeeTypes.length; i++) {
                    if (coffeeTypes[i].equals(choice)) {
                        index = i;
                        break;
                    }
                }

                if (index == -1) {
                    System.out.println("Invalid option.");
                    continue;
                }

                boolean enoughResources = true;
                for (int i = 0; i < 3; i++) {
                    if (resources[i] < requirements[index][i]) {
                        System.out.println("Sorry, there is not enough " + (i == 0 ? "water" : i == 1 ? "milk" : "coffee") + ".");
                        enoughResources = false;
                    }
                }

                if (!enoughResources) continue;

                System.out.println("Please insert coins.");
                int[] coinValues = {25, 10, 5, 1};
                int totalCents = 0;

                for (int i = 0; i < coinValues.length; i++) {
                    System.out.print("How many " + (i == 0 ? "quarters" : i == 1 ? "dimes" : i == 2 ? "nickels" : "pennies") + "?: ");
                    totalCents += sc.nextInt() * coinValues[i];
                }

                if (totalCents < requirements[index][3]) {
                    System.out.println("Sorry, that's not enough money. Money refunded.");
                } else {
                    resources[3] += requirements[index][3];
                    for (int i = 0; i < 3; i++) {
                        resources[i] -= requirements[index][i];
                    }
                    System.out.printf("Here is $%.2f in change.%n", (totalCents - requirements[index][3]) / 100.0);
                    System.out.println("Here is your " + choice + ". Enjoy!");
                }
            }
        }
        sc.close();
    }
}
