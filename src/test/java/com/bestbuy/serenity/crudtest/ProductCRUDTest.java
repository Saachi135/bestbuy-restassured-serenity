package com.bestbuy.serenity.crudtest;

import com.bestbuy.serenity.steps.ProductSteps;
import com.bestbuy.serenity.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

public class ProductCRUDTest extends TestBase {
    static int id;

    String name = "Bowe Dry Cleaning Machine";
    String type = "Dry Cleaning Machine";
    int price = 25000;
    int shipping = 150;
    String upc = "XYZ";
    String description = "Dry Cleaning Machine for Garments.";
    String manufacturer = "Bowe";
    String model = "P525";
    String url = "www.bowe.com";
    String image = "bowep525.jpg";

    @Steps
    ProductSteps productSteps;

    @Title("Creating a new product")
    @Test
    public void test001() {
        ValidatableResponse response = productSteps.createProduct(name, type, price,  shipping, upc, description, manufacturer, model, url);
        response.log().all().statusCode(201);
        id = response.extract().path("id");
    }

    @Title("Getting single product.")
    @Test
    public void test002() {
        ValidatableResponse response = productSteps.getProduct(id);
        response.log().all().statusCode(200);
    }

    @Title("Updating the product.")
    @Test
    public void test003() {
        name = "Bowe Dry Cleaning Machine-Updated";
        ValidatableResponse response = productSteps.updateProduct(id, name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(200);
    }

    @Title("Deleting the product and verifying the deletion.")
    @Test
    public void test004() {
        productSteps.deleteProduct(id).statusCode(200);
        productSteps.getProduct(id).statusCode(404);
    }
}
