import java.util.*;

class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }
}

class User {
    private String name;
    private double balance;
    private Map<String, Integer> portfolio;

    public User(String name, double initialBalance) {
        this.name = name;
        this.balance = initialBalance;
        this.portfolio = new HashMap<>();
    }

    public void buyStock(Stock stock, int quantity) {
        double totalCost = stock.getPrice() * quantity;
        if (totalCost > balance) {
            System.out.println("Insufficient balance!");
            return;
        }

        balance -= totalCost;
        portfolio.put(stock.getSymbol(), portfolio.getOrDefault(stock.getSymbol(), 0) + quantity);
        System.out.println("Successfully bought " + quantity + " shares of " + stock.getSymbol());
    }

    public void sellStock(Stock stock, int quantity) {
        int owned = portfolio.getOrDefault(stock.getSymbol(), 0);
        if (quantity > owned) {
            System.out.println("You don't have enough shares to sell.");
            return;
        }

        balance += stock.getPrice() * quantity;
        portfolio.put(stock.getSymbol(), owned - quantity);
        System.out.println("Successfully sold " + quantity + " shares of " + stock.getSymbol());
    }

    public void displayPortfolio(Map<String, Stock> stockMarket) {
        System.out.println("\n--- Portfolio for " + name + " ---");
        System.out.println("Balance: ₹" + balance);
        System.out.println("Holdings:");
        for (String symbol : portfolio.keySet()) {
            int qty = portfolio.get(symbol);
            double price = stockMarket.get(symbol).getPrice();
            System.out.println(symbol + " - " + qty + " shares @ ₹" + price + " = ₹" + (qty * price));
        }
        System.out.println("---------------------------\n");
    }
}

public class StockTradingPlatform {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Stock> stockMarket = new HashMap<>();
    private static User user;

    public static void main(String[] args) {
        System.out.println("Welcome to the Stock Trading Platform!");

        // Setup user
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        user = new User(name, 10000); // Initial balance ₹10,000

        // Setup some sample stocks
        stockMarket.put("TCS", new Stock("TCS", 3400));
        stockMarket.put("INFY", new Stock("INFY", 1550));
        stockMarket.put("WIPRO", new Stock("WIPRO", 430));

        while (true) {
            System.out.println("\n1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> displayMarket();
                case 2 -> buyStock();
                case 3 -> sellStock();
                case 4 -> user.displayPortfolio(stockMarket);
                case 5 -> {
                    System.out.println("Thank you for using the Stock Trading Platform!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void displayMarket() {
        System.out.println("\n--- Market Data ---");
        for (Stock stock : stockMarket.values()) {
            System.out.println(stock.getSymbol() + " : ₹" + stock.getPrice());
        }
        System.out.println("--------------------");
    }

    private static void buyStock() {
        scanner.nextLine(); // consume newline
        System.out.print("Enter stock symbol to buy: ");
        String symbol = scanner.nextLine().toUpperCase();
        Stock stock = stockMarket.get(symbol);

        if (stock == null) {
            System.out.println("Stock not found!");
            return;
        }

        System.out.print("Enter quantity to buy: ");
        int qty = scanner.nextInt();
        user.buyStock(stock, qty);
    }

    private static void sellStock() {
        scanner.nextLine(); // consume newline
        System.out.print("Enter stock symbol to sell: ");
        String symbol = scanner.nextLine().toUpperCase();
        Stock stock = stockMarket.get(symbol);

        if (stock == null) {
            System.out.println("Stock not found!");
            return;
        }

        System.out.print("Enter quantity to sell: ");
        int qty = scanner.nextInt();
        user.sellStock(stock, qty);
    }
}
