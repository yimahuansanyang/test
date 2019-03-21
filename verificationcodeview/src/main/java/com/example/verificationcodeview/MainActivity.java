package com.example.verificationcodeview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.cooltechworks.creditcarddesign.CardEditActivity;
import com.cooltechworks.creditcarddesign.CreditCardUtils;
import com.cooltechworks.creditcarddesign.CreditCardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        CreditCardView creditCardView = new CreditCardView(this);

//        String name = "HARISH SRIDHARAN";
//        String cvv = "522";
//        String expiry = "01/17";
//        String cardNumber = "38056789000000000";
//
//        creditCardView.setCVV(cvv);
//        creditCardView.setCardHolderName(name);
//        creditCardView.setCardExpiry(expiry);
//        creditCardView.setCardNumber(cardNumber);

        final int GET_NEW_CARD = 2;
        Intent intent = new Intent(MainActivity.this, CardEditActivity.class);
        startActivityForResult(intent,GET_NEW_CARD);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {

            String cardHolderName = data.getStringExtra(CreditCardUtils.EXTRA_CARD_HOLDER_NAME);
            String cardNumber = data.getStringExtra(CreditCardUtils.EXTRA_CARD_NUMBER);
            String expiry = data.getStringExtra(CreditCardUtils.EXTRA_CARD_EXPIRY);
            String cvv = data.getStringExtra(CreditCardUtils.EXTRA_CARD_CVV);
            Log.d("TAG",cardHolderName+cardNumber+expiry+cvv);
            CreditCardView creditCardView = new CreditCardView(this);

//        String name = "HARISH SRIDHARAN";
//        String cvv = "522";
//        String expiry = "01/17";
//        String cardNumber = "38056789000000000";

        creditCardView.setCVV(cvv);
        creditCardView.setCardHolderName(cardHolderName);
        creditCardView.setCardExpiry(expiry);
        creditCardView.setCardNumber(cardNumber);


        }
    }
}
