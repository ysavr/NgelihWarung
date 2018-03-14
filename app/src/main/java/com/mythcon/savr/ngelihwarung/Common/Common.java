package com.mythcon.savr.ngelihwarung.Common;

import com.mythcon.savr.ngelihwarung.Model.User;

/**
 * Created by SAVR on 07/03/2018.
 */

public class Common {
    public static User currentUser;

    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";

    public static final int PICT_IMAGE_REQUEST = 71;

    public static String convertCodeToStatus(String code){
        if (code.equals("0"))
            return "Placed";
        else if (code.equals("1"))
            return "On my way";
        else
            return "Shipped";
    }
}
