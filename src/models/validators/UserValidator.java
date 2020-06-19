package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class UserValidator {
    public static List<String> validate(User u, Boolean user_id_duplicate_check_flag, Boolean password_check_flag){
        List<String> errors = new ArrayList<String>();

        String user_id_error = _validateUser_id(u.getUser_id(), user_id_duplicate_check_flag);
        if(!user_id_error.equals("")){
            errors.add(user_id_error);
        }

        String user_name_error = _validateUser_name(u.getUser_name());
        if(!user_name_error.equals("")){
            errors.add(user_name_error);
        }

        String password_error = _validatePassword(u.getPassword(), password_check_flag);
        if(!password_error.equals("")){
            errors.add(password_error);
        }

        return errors;
    }

    //ユーザーID
    private static String _validateUser_id(String user_id, Boolean user_id_duplicate_check_flag){
        //必須入力チェック
        if(user_id == null || user_id.equals("")){
            return "ユーザーIDを入力してください";
        }

        //既に登録しているIDとの重複チェック
        if(user_id_duplicate_check_flag){
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkRegisteredUser_id", Long.class)
                                        .setParameter("user_id", user_id)
                                            .getSingleResult();
            em.close();
            if(users_count > 0){
                return "入力されたIDは既に存在しています";
            }
        }

        return "";
    }

    //ユーザー名の必須入力チェック
    private static String _validateUser_name(String user_name){
        if(user_name == null || user_name.equals("")){
            return "ユーザー名を入力してください";
        }
        return "";
    }

    //パスワードの必須入力チェック
    private static String _validatePassword(String password, Boolean password_check_flag){
        //パスワードを変更する場合のみ実行
        if(password_check_flag && (password == null || password.equals(""))){
            return "パスワードを入力してください";
        }
        return "";
    }
}
