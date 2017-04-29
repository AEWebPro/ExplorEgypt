package com.example.ae.smartvisit.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.modules.CurrencyRateModel;
import com.example.ae.smartvisit.rest.CurrencyApiClient;
import com.example.ae.smartvisit.rest.CurrencyRateEndPoint;
import com.wajahatkarim3.easymoneywidgets.EasyMoneyEditText;
import com.wajahatkarim3.easymoneywidgets.EasyMoneyTextView;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyActivity extends BaseActivity {

    @Bind(R.id.currency_from_spinner)
    Spinner currencyFromSpinner;
    @Bind(R.id.currency_moneyEditText)
    EasyMoneyEditText currencyMoneyEditText;
    @Bind(R.id.currency_edit_text_layout)
    TextInputLayout currencyEditTextLayout;
    @Bind(R.id.currency_after_convert_text)
    TextView currencyAfterConvertText;
    @Bind(R.id.currency_moneyTextView)
    EasyMoneyTextView currencyMoneyTextView;

    private String toCurrency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        ButterKnife.bind(this);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //Default
        toCurrency = "EGP";

        // set Spinner Adapter
        final ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.currencies_spinner_list,android.R.layout.simple_list_item_1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        currencyFromSpinner.setAdapter(spinnerAdapter);

        currencyFromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                changeBaseUnitsAndText(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Default currency
        changeBaseUnitsAndText(0);
        currencyMoneyTextView.setCurrency("EGP");
        currencyMoneyTextView.showCurrencySymbol();
        currencyMoneyTextView.showCommas();


        getSupportActionBar().setTitle("Convert currency");
    }

    @OnClick(R.id.currency_convert_btn)
    public void onViewClicked(View view) {
        loadData(toCurrency);
        }

    private void changeBaseUnitsAndText(int baseCurrencyPosition){
        switch (baseCurrencyPosition){
            case 0:
                toCurrency = "USD";
                currencyMoneyEditText.setCurrency(Locale.US);
                break;
            case 1:
                toCurrency = "EUR";
                currencyMoneyEditText.setCurrency("€");
                break;
            case 2:
                toCurrency = "AED";
                currencyMoneyEditText.setCurrency("Dirham");
                break;
            case 3:
                toCurrency = "INR";
                currencyMoneyEditText.setCurrency("₨");
                break;
        }
    }


    private void loadData(String type){
        final CurrencyRateEndPoint apiService =
                CurrencyApiClient.getClient().create(CurrencyRateEndPoint.class);

        Call<CurrencyRateModel> call = apiService.getRate(type);
        call.enqueue(new Callback<CurrencyRateModel>() {
            @Override
            public void onResponse(Call<CurrencyRateModel> call, Response<CurrencyRateModel> response) {
                CurrencyRateModel currencyRateModel = response.body();
                Float rateNumber = currencyRateModel.getRates().values().iterator().next();
                convertCurrency(rateNumber);
            }

            @Override
            public void onFailure(Call<CurrencyRateModel> call, Throwable t) {
                Toast.makeText(CurrencyActivity.this, "Error in converting!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void convertCurrency(Float rate){
        Float rateInEgp = rate / 18;
        Float numberToDisplay = Float.parseFloat(currencyMoneyEditText.getValueString().toString()) / rateInEgp ;
        currencyMoneyTextView.setText( Float.toString(numberToDisplay));
        currencyMoneyTextView.showCurrencySymbol();
        currencyMoneyTextView.showCommas();

    }
}
