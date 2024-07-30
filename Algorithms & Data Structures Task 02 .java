import java.util.Arrays;
import java.util.Comparator;

public class ECommerceSearch {

    public static void main(String[] args) {
        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Smartphone", "Electronics"),
            new Product(3, "Book", "Books"),
            new Product(4, "Shoes", "Footwear"),
            new Product(5, "Watch", "Accessories")
        };

        // Linear Search
        Product foundProductLinear = LinearSearch.linearSearch(products, "Book");
        if (foundProductLinear != null) {
            System.out.println("Linear Search found: " + foundProductLinear);
        } else {
            System.out.println("Product not found using Linear Search.");
        }

        // Binary Search
        Product foundProductBinary = BinarySearch.binarySearch(products, "Book");
        if (foundProductBinary != null) {
            System.out.println("Binary Search found: " + foundProductBinary);
        } else {
            System.out.println("Product not found using Binary Search.");
        }
    }
}

class Product {
    private int productId;
    private String productName;
    private String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", category=" + category + "]";
    }
}

class LinearSearch {
    public static Product linearSearch(Product[] products, String productName) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }
}

class BinarySearch {
    public static Product binarySearch(Product[] products, String productName) {
        Arrays.sort(products, Comparator.comparing(Product::getProductName));
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = products[mid].getProductName().compareToIgnoreCase(productName);

            if (comparison == 0) {
                return products[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}
