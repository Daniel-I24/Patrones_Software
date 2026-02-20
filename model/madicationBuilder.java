package hospital.model;

public class madicationBuilder {
    private String id;
    private String name;
    private String category    = "General";
    private double price    = 0.0;
    private int stock        = 0;
    private boolean needsPrescription = false;

    // setters
    public madicationBuilder setId(String id)               { 
        this.id = id;               
        return this; 
    }
    public madicationBuilder setName(String name)           { 
        this.name = name;           
        return this; 
    }
    public madicationBuilder setCategory(String category)   { 
        this.category = category;   
        return this; 
    }
    public madicationBuilder setPrice(double price)         { 
        this.price = price;         
        return this; 
    }
    public madicationBuilder setStock(int stock)            { 
        this.stock = stock;         
        return this; 
    }
    public madicationBuilder setNeedsPrescription(boolean np){ 
        this.needsPrescription = np; 
        return this; 
    }
}
