package com.mythcon.savr.ngelihwarung.Service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.mythcon.savr.ngelihwarung.Common.Common;
import com.mythcon.savr.ngelihwarung.Model.Token;

/**
 * Created by SAVR on 27/03/2018.
 */

public class MyFirebaseIdServices extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String tokenRefreshed = FirebaseInstanceId.getInstance().getToken();
        updateToServer(tokenRefreshed);
    }

    private void updateToServer(String tokenRefreshed) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference tokens = db.getReference("Tokens");
        Token token = new Token(tokenRefreshed,true);  //true karena token ini dikirim dari Server
        tokens.child(Common.currentUser.getPhone()).setValue(token);
    }
}
