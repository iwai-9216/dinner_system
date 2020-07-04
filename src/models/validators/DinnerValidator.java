package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.Dinner;

public class DinnerValidator {
    public static List<String> validate(Dinner d){
        List<String> errors = new ArrayList<String>();

        String dish_error = _validateDish(d.getDish());
        if(!dish_error.equals("")){
            errors.add(dish_error);
        }

        return errors;
    }

    private static String _validateDish(String dish){
        if(dish == null || dish.equals("")){
            return "料理名を入力してください";
        }

        return "";
    }
}
