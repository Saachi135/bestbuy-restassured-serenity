package com.bestbuy.serenity.crudtest;

import com.bestbuy.serenity.steps.ProductsSteps;
import com.bestbuy.serenity.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class ProductsCRUDTest extends TestBase {
    static int id;

    String name = "Saaya ";
    String type = "Gaurang ";
    int price = 20000;
    int shipping = 200;
    String upc = "hotel";
    String description = "Hot uk deals.";
    String manufacturer = "HotelChocolate";
    String model = "Iphone";
    String url = "www.bowe.com";
    String image = "bowep525.jpg";

    @Steps
    ProductsSteps productsSteps;

    @Title("Creating a new product.")
    @Test
    public void test001() {
        ValidatableResponse response = productsSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        id = response.extract().path("id");
    }

    @Title("Getting single product.")
    @Test
    public void test002() {
        ValidatableResponse response = productsSteps.getProduct(id);
        response.log().all().statusCode(200);
    }

    @Title("Updating the product.")
    @Test
    public void test003() {
        name = "Bowe Dry Cleaning Machine-Updated";
        ValidatableResponse response = productsSteps.updateProduct(id, name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(200);
    }

    @Title("Deleting the product and verifying the deletion.")
    @Test
    public void test004() {
        productsSteps.deleteProduct(id).statusCode(200);
        productsSteps.getProduct(id).statusCode(404);
    }
}
