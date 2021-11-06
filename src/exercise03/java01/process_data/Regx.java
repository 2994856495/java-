package exercise03.java01.process_data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regx{
//   密码由数字和字母组成，并且要同时含有数字和字母，且长度至少要8位。
    private static final String valicatePassword = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}$";
    public static boolean checkPassword(String passWord) {
        Pattern pattern = Pattern.compile(valicatePassword);
        Matcher matcher = pattern.matcher(passWord);
        return matcher.matches();
    }

}

