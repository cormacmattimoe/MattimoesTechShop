package com.example.mattimoestechshop.StatePattern;


import com.example.mattimoestechshop.Model.ProductItem;

public class InStock implements ProductState{
        ProductItem productItem;



        public InStock(ProductItem newProductItem)
        {
            productItem = newProductItem;
        }


        public void buyProduct(int quantityBought) {
            //If customer is buying product and the product is inStock set product to Instock
            //else if customer is buying product and product is not in stock set product to OutOfStock

            System.out.println("This is the stock on hand inStock = "  + productItem.getProductStockOnhand());
            System.out.println("This is the quantity bought inStock = "  + quantityBought);
            if(productItem.getProductStockOnhand() >= quantityBought)
            {
                productItem.setProductStockOnhand( - quantityBought);
                System.out.println("This is the product quantity after sale inStock = "  + productItem.getProductStockOnhand());
            }
            else{
                //Edge case that needs to be corrected - someone looking to buy 5 when 4 is available will cause the state to be set to
                // outOfStock
                productItem.setProductState(productItem.getNoProductAvailable());
            }
        }
        public void resupplyProduct(int quantityResupplied) {
            //When updating stock as an admin the current state of that product is OutOfstock
            // this needs to be changed to inStock when updated
            productItem.setProductStockOnhand( + quantityResupplied);
            System.out.println("This is the product quantity now = " + productItem.getProductStockOnhand());
        }

        public void returnProduct(int quantityReturned) {
            //When product is requested this should return the product if it is inStock
            productItem.setProductStockOnhand(+ quantityReturned);

        }
    }
