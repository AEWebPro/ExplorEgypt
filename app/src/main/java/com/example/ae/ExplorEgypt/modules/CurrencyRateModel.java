package com.example.ae.ExplorEgypt.modules;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CurrencyRateModel {
    @SerializedName("quotes")
    Map<String, Float> rates;

    public CurrencyRateModel(Map<String, Float> rates) {
        this.rates = rates;
    }

    public Map<String, Float> getRates() {
        return rates;
    }

    public void setRates(Map<String, Float> rates) {
        this.rates = rates;
    }
}
