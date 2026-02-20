package hospital.model;

public class medicationBuilder {
    private String id;
    private String name;
    private String category    = "General";
    private double price    = 0.0;
    private int stock        = 0;
    private boolean needsPrescription = false;

    
    public medicationBuilder setId(String id)               { 
        this.id = id;               
        return this; 
    }
    public medicationBuilder setName(String name)           { 
        this.name = name;           
        return this; 
    }
    public medicationBuilder setCategory(String category)   { 
        this.category = category;   
        return this; 
    }
    public medicationBuilder setPrice(double price)         { 
        this.price = price;         
        return this; 
    }
    public medicationBuilder setStock(int stock)            { 
        this.stock = stock;         
        return this; 
    }
    public medicationBuilder setNeedsPrescription(boolean np){ 
        this.needsPrescription = np; 
        return this; 
    }

    public Medication build() {
        if (id == null || id.isEmpty()) {
            throw new IllegalStateException("ID es obligatorio");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalStateException("Nombre es obligatorio");
        }
        if (price < 0) {
            throw new IllegalStateException("El precio no puede ser negativo");
        }
        return new Medication(id, name, category, price, stock, needsPrescription);
    }

    // Simple Medication implementation included so the builder compiles.
    public static class Medication {
        private final String id;
        private final String name;
        private final String category;
        private final double price;
        private final int stock;
        private final boolean needsPrescription;

        public Medication(String id, String name, String category, double price, int stock, boolean needsPrescription) {
            this.id = id;
            this.name = name;
            this.category = category;
            this.price = price;
            this.stock = stock;
            this.needsPrescription = needsPrescription;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public String getCategory() { return category; }
        public double getPrice() { return price; }
        public int getStock() { return stock; }
        public boolean isNeedsPrescription() { return needsPrescription; }
    }
}
