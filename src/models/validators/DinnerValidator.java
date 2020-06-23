package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Dinner;

public class DinnerValidator {
    public static List<String> validate(Dinner d){
        List<String> errors = new ArrayList<String>();

        String Dinner_error = _validateDinner(d.getDinner());
        if(!Dinner_error.equals("")){
            errors.add(Dinner_error);
        }

        return errors;
    }

    private static String _validateDinner(String dinner){
        if(dinner == null || dinner.equals("")){
            return "料理名を入力してください";
        }

        return "";
    }
}
