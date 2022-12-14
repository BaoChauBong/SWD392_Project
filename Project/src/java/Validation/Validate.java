/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validation;

import java.sql.Date;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Tạ Văn Tân
 */
public class Validate {

    //hàm check đầu vào cho Fullname
    public static boolean checkFullName(String fullName) {
        String regex = "^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪ"
                + "ẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễếệỉịọỏốồ"
                + "ổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]{4,32}(?:[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨ"
                + "ŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềề"
                + "ểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễếệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵ"
                + "ỷỹ]+){0,2}$";   // regex cho tên tiếng việt

        Pattern pattern = Pattern.compile(regex);   //biên dịch chuỗi chuỗi regex được tạo ra 
        Matcher matcher = pattern.matcher(fullName); //so sánh giá trị truyền vào với mẫu xem có trùng nhau không
        return matcher.matches(); //trả về true nếu giá trị truyền vào khớp nếu không sẽ trả về false
    }

    //hàm check đầu vào cho Username
    public static boolean checkUserName(String userName) {
        String regex = "^[a-zA-Z0-9]{1,32}$"; //ít nhất 6 ký tự và không có ký tự đặc biệt
        Pattern pattern = Pattern.compile(regex);   //biên dịch chuỗi chuỗi regex được tạo ra 
        Matcher matcher = pattern.matcher(userName); //so sánh giá trị truyền vào với mẫu xem có trùng nhau không
        return matcher.matches(); //trả về true nếu giá trị truyền vào khớp nếu không sẽ trả về false
    }

    //hàm check đầu vào cho email
    public static boolean checkEmail(String email) {
        //email phải có định dạng example@gmail.com 
        String regex = "^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z]{2,}(\\.[a-zA-Z]+){1,3}$";

        Pattern pattern = Pattern.compile(regex);   //biên dịch chuỗi chuỗi regex được tạo ra 
        Matcher matcher = pattern.matcher(email); //so sánh giá trị truyền vào với mẫu xem có trùng nhau không
        return matcher.matches(); //trả về true nếu giá trị truyền vào khớp nếu không sẽ trả về false
    }

    //hàm check đầu vào cho password
    public static boolean checkPassword(String pass) {
        //mật khẩu phải bao gồm từ 8-20 ký tự it nhất 1 chữ thường,hoa, số, đặc biệt
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@.$!%*?&])[A-Za-z\\d@$!.%*?&]{1,20}$";
        Pattern pattern = Pattern.compile(regex);   //biên dịch chuỗi chuỗi regex được tạo ra 
        Matcher matcher = pattern.matcher(pass); //so sánh giá trị truyền vào với mẫu xem có trùng nhau không
        return matcher.matches(); //trả về true nếu giá trị truyền vào khớp nếu không sẽ trả về false
    }

    //check String
    public static boolean checkString(String desc, int a, int b) {
        String regex = "^.{" + a + "," + b + "}$"; //ít nhất 6 ký tự và không có ký tự đặc biệt
        Pattern pattern = Pattern.compile(regex);   //biên dịch chuỗi chuỗi regex được tạo ra 
        Matcher matcher = pattern.matcher(desc); //so sánh giá trị truyền vào với mẫu xem có trùng nhau không
        return matcher.matches(); //trả về true nếu giá trị truyền vào khớp nếu không sẽ trả về false
    }

}
