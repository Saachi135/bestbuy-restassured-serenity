package com.bestbuy.serenity.crudtest;


import com.bestbuy.serenity.model.StoresPojo;
import com.bestbuy.serenity.steps.StoresSteps;
import com.bestbuy.serenity.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoresCRUDTest extends TestBase {
    String name = "Saaya";
    String type = "Hot uk deals";
    String address = "Ruislip";
    String address2 = "Harrow";
    String city = "Lodon";
    String state = "England";
    String zip = "HA4";
    int lat = 12345;
    int lng = 6789;
    String hours = "Everyday";
    StoresPojo.Services services;
    static int id;

    @Steps
    StoresSteps storesSteps;


    @Title("Creating store and verifying the creation.")
    @Test
    public void test001() {
        ValidatableResponse response = storesSteps.createStores(name, type, address, address2, city, state, zip, lat, lng, hours, services);
        response.log().all().statusCode(201);
        id = response.extract().path("id");
        HashMap<String, Object> value = response.extract().path("");
        Assert.assertThat(value, hasValue(id));

    }

    @Title("Getting store information by id.")
    @Test
    public void test002() {
        ValidatableResponse response = storesSteps.getStores(id);
        response.log().all().statusCode(200);
        HashMap<String, Object> value = response.extract().path("");
        Assert.assertThat(value, hasValue(id));
    }

    @Title("Updating store information by id.")
    @Test
    public void test003() {
        name = name + "-Updated";
        storesSteps.updateStores(id, name, type, address, address2, city, state, zip, lat, lng, hours, services);
        ValidatableResponse response = storesSteps.getStores(id);
        response.log().all().statusCode(200);
        HashMap<String, Object> value = response.extract().path("");
        Assert.assertThat(value, hasValue(name));
    }

    @Title("Deleting the store and verifying the deletion.")
    @Test
    public void test004() {
        storesSteps.deleteStores(id).statusCode(200);
        storesSteps.getStores(id).statusCode(404);
    }
}
