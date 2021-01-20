package com.example.asd.imtired.common;

import com.example.asd.imtired.model.Question;
import com.example.asd.imtired.model.User;

import java.util.ArrayList;
import java.util.List;


// common.java class to store some global variable (luu mot so bien toan cuc)
public class Common {

    // create global variable list of Question

    public static String categoryId;
    public static User currentUser;
    public static List<Question> questionList = new ArrayList<>();

}
