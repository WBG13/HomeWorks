package com.firstshop.Containers;

public class ProductContainer {
    private String name;
    private String category;
    private String stored;
    private String imageLocation;
    private String amount;
    private String id;
    private String productDescription;
    private String price;

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
    public String getAmount() {return amount;}
    public String getId() {return id;}
    public String getProductDescription() {
        return productDescription;
    }

    ProductContainer (final ProductContainerBuilder productContainerBuilder){
        this.name = productContainerBuilder.getName();
        this.category = productContainerBuilder.getCategory();
        this.stored = productContainerBuilder.getStored();
        this.imageLocation = productContainerBuilder.getImageLocation();
        this.price = productContainerBuilder.getPrice();
        this.productDescription = productContainerBuilder.getProductDescription();
        this.amount = productContainerBuilder.getAmount();
        this.id = productContainerBuilder.getId();
    }
}
