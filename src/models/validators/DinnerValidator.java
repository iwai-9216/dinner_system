package models.validators;

import java.util.List;

import models.Dinner;

public class DinnerValidator {
    public static List<String> validate(Dinner d){
        List<String> errors = newArrayList<String>();

        String Dinnser_error = _validateDinnser(d.getDinner());
        if(!dinner_error.equals("")){
            errors.add(Dinnser_error);
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
