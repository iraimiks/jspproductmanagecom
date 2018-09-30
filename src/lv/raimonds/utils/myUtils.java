package lv.raimonds.utils;

import lv.raimonds.beans.UserAccount;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

public class myUtils {
    public  static final String ATTRIBUTE_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
    private static final String ATTRIBUTE_NAME_USER_NAME = "ATRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
    public static void storeConnection(ServletRequest request, Connection connection){
        request.setAttribute(ATTRIBUTE_NAME_CONNECTION,connection);
    }
    public static Connection getStoredConnection(ServletRequest request){
        Connection connection = (Connection)request.getAttribute(ATTRIBUTE_NAME_CONNECTION);
        return connection;
    }
    public static void storeLoginedUser(HttpSession session, UserAccount loginedUser){
        session.setAttribute("loginedUser",loginedUser);
    }
    public static UserAccount getLoginedUser(HttpSession session){
        UserAccount loginedUser = (UserAccount)session.getAttribute("loginedUser");
        return loginedUser;
    }
    public static void storeUserCookie(HttpServletResponse response, UserAccount user){
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATTRIBUTE_NAME_USER_NAME, user.getUserName());
        cookieUserName.setMaxAge(24*60*60);
        response.addCookie(cookieUserName);
    }
    public static String getUserNameInCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie: cookies){
                if(ATTRIBUTE_NAME_USER_NAME.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    public static void deleteUserCookie(HttpServletResponse response){
        Cookie cookieUserName = new Cookie(ATTRIBUTE_NAME_USER_NAME, null);
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }

}
