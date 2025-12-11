package model;

// Ürün durumu için Enum (Farklı bir Enum tipi)
 enum Status { 
    Kullanılmış,Yeni, 
}

public class Product {
    
    // --- Alanlar (Fields) ---
    private String name = "Laptop";
    private double price = 1250.99;
    private boolean isDiscounted = true; 
    private Status productStatus = Status.Kullanılmış; 
    private int quantity = 50;

    // Constructor
    public Product() {}

    // --- Getter Metotları ---

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isDiscounted() {
        return isDiscounted;
    }

    public Status getProductStatus() {
        return productStatus;
    }
    
    public int getQuantity() {
        return quantity;
    }

    // --- Setter Metotları ---

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscounted(boolean isDiscounted) {
        this.isDiscounted = isDiscounted;
    }

    public void setProductStatus(Status productStatus) {
        this.productStatus = productStatus;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}