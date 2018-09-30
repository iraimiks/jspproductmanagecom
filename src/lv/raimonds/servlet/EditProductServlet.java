package lv.raimonds.servlet;


import lv.raimonds.beans.Product;
import lv.raimonds.utils.DButils;
import lv.raimonds.utils.myUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = { "/editProduct" })
public class EditProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditProductServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = myUtils.getStoredConnection(request);

        String productName = (String) request.getParameter("productName");

        Product product = null;

        String errorString = null;

        try {
            product = DButils.findProduct(conn, productName);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        if (errorString != null && product == null) {
            response.sendRedirect(request.getServletPath() + "/productList");
            return;
        }

        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
        dispatcher.forward(request, response);

        }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = myUtils.getStoredConnection(request);

        String produc_name = (String)request.getParameter("productName");
        String qtyToString = (String)request.getParameter("qty");
        String onePeacPriceToString = (String) request.getParameter("oneProductPrice");
        double onePeacPrice  = 0;
        int qty =  0;
        try{
            qty = Integer.parseInt(qtyToString);
            onePeacPrice = Double.parseDouble(onePeacPriceToString);

        }catch (Exception e){

        }
        Product product = new Product(produc_name,qty,onePeacPrice);

        String errorString = null;

        try {
            DButils.updateProduct(conn, product);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);

        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/productList");
        }
    }
}
