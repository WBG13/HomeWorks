package com.firstshop.Containers;

public class ProductContainerBuilder {
    private String name;
    private String category;
    private String stored;
    private String imageLocation;
    private String price;
    private String productDescription;
    private String id;
    private String amount;

    public String getId() {return id;}
    public String getAmount() {return amount;}
    public String getProductDescription() {
        return productDescription;
    }
    public String getName() {
        return this.name;
    }
    public String getCategory() {
        return this.category;
    }
    public String getStored() {
        return this.stored;
    }
    public String getImageLocation() {
        return this.imageLocation;
    }
    public String getPrice() {
        return this.price;
    }

    public ProductContainerBuilder name(String name){
        this.name = name;
        return this;
    }
    public ProductContainerBuilder category(String category){
        this.category = category;
        return this;
    }
    public ProductContainerBuilder stored(String howMany){
        this.stored = howMany;
        return this;
    }
    public ProductContainerBuilder imageLocation(String imageLocation){
        this.imageLocation = imageLocation;
        return this;
    }
    public ProductContainerBuilder price(String price){
        this.price = price;
        return this;
    }
    public ProductContainerBuilder productDescription(String productDescription){
        this.productDescription = productDescription;
        return this;
    }
    public ProductContainerBuilder amount(String amount){
        this.amount = amount;
        return this;
    }
    public ProductContainerBuilder id(String id){
        this.id = id;
        return this;
    }

    public ProductContainer build(){
        return new ProductContainer(this);
    }
}
