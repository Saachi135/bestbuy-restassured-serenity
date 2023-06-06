package com.bestbuy.serenity.steps;

import com.bestbuy.serenity.constants.Endpoints;
import com.bestbuy.serenity.model.ProductPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ProductSteps {
    @Step("Create Product with name: ")
    public ValidatableResponse createProduct(String name, String type, int price, int shipping,String upc,String description, String manufacturer, String model,String url){
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);

        return SerenityRest.given().log().all()
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(productPojo)
                .when()
                .post(Endpoints.CREATE_PRODUCTS)
                .then().log().all();
    }
    @Step("Get product by id: {0}")
    public ValidatableResponse getProduct(int id) {
        return SerenityRest.given().log().all()
                .pathParam("id", id)
                .when()
                .get(Endpoints.GET_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Update product with id: {0},name: {1},type: {2},price: {3},shipping: {4},upc: {5},description: {6},manufacturer: {7},model: {8},url: {9},image: {10}")
    public ValidatableResponse updateProduct(int id, String name, String type, int price, int shipping, String upc, String description, String manufacturer, String model, String url, String image) {
        ProductPojo productsPojo = new ProductPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setShipping(shipping);
        productsPojo.setUpc(upc);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        return SerenityRest.given().log().all()
                .pathParam("id", id)
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(productsPojo)
                .when()
                .put(Endpoints.UPDATE_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Deleting product with id: {0}")
    public ValidatableResponse deleteProduct(int id) {
        return SerenityRest.given().log().all()
                .pathParam("id", id)
                .when()
                .delete(Endpoints.DELETE_PRODUCT_BY_ID)
                .then();
    }
}
