package lv.raimonds.servlet;


import lv.raimonds.beans.UserAccount;
import lv.raimonds.utils.DButils;
import lv.raimonds.utils.myUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Forward to /WEB-INF/views/loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String remeberMeStr = request.getParameter("remeberMe");
        boolean remeber= "Y".equals(remeberMeStr);
        UserAccount userAccount = null;
        boolean hasErro = false;
        String erroString = null;
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0){
            hasErro = true;
            erroString = "Required username and password!";

        }else{
            Connection connection = myUtils.getStoredConnection(request);
            try {
                userAccount = DButils.findUser(connection,userName,password);
                if(userAccount == null){
                    hasErro = true;
                    erroString = "User Name or password invalid";
                }
            }catch (SQLException e){
                e.printStackTrace();
                hasErro = true;
                erroString = e.getMessage();
            }
        }
        if(hasErro){
            userAccount = new UserAccount();
            userAccount.setUserName(userName);
            userAccount.setPassword(password);
            request.setAttribute("errorString", erroString);
            request.setAttribute("user", userAccount);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

            dispatcher.forward(request, response);
        }else{
            HttpSession session = request.getSession();
            myUtils.storeLoginedUser(session, userAccount);

            // If user checked "Remember me".
            if (remeber) {
                myUtils.storeUserCookie(response, userAccount);
            }
            // Else delete cookie.
            else {
                myUtils.deleteUserCookie(response);
            }

            // Redirect to userInfo page.
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }
}
