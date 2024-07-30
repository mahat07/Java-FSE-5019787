import java.util.HashMap;

class Product {
    private int productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class InventoryManagementSystem {
    private HashMap<Integer, Product> inventory;

    public InventoryManagementSystem() {
        inventory = new HashMap<>();
    }

    public void addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Product ID " + product.getProductId() + " already exists. Use updateProduct to update the product.");
        } else {
            inventory.put(product.getProductId(), product);
            System.out.println("Product " + product.getProductName() + " added.");
        }
    }

    public void updateProduct(int productId, String productName, Integer quantity, Double price) {
        if (inventory.containsKey(productId)) {
            Product product = inventory.get(productId);
            if (productName != null) {
                product.setProductName(productName);
            }
            if (quantity != null) {
                product.setQuantity(quantity);
            }
            if (price != null) {
                product.setPrice(price);
            }
            System.out.println("Product ID " + productId + " updated.");
        } else {
            System.out.println("Product ID " + productId + " not found. Use addProduct to add the product.");
        }
    }

    public void deleteProduct(int productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
            System.out.println("Product ID " + productId + " deleted.");
        } else {
            System.out.println("Product ID " + productId + " not found.");
        }
    }

    // Example usage
    public static void main(String[] args) {
        InventoryManagementSystem inventorySystem = new InventoryManagementSystem();

        // Adding products
        Product product1 = new Product(1, "Laptop", 10, 999.99);
        Product product2 = new Product(2, "Smartphone", 20, 499.99);
        inventorySystem.addProduct(product1);
        inventorySystem.addProduct(product2);

        // Updating a product
        inventorySystem.updateProduct(1, null, 15, null);

        // Deleting a product
        inventorySystem.deleteProduct(2);
    }
}
