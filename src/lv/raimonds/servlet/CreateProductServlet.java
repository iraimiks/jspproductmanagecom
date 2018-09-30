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

@WebServlet(urlPatterns = { "/createProduct" })
public class CreateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateProductServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection connection = myUtils.getStoredConnection(request);
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

        if (produc_name == null ) {
            errorString = "Product Name invalid!";
        }
        if (errorString == null) {
            try {
                DButils.insertProduct(connection, product);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/productList");
        }



    }

}
