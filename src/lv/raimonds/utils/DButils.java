package lv.raimonds.utils;
import lv.raimonds.beans.Product;
import lv.raimonds.beans.UserAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DButils {
    public static UserAccount findUser(Connection connection, String userName, String password)throws SQLException{
        String sql = "select a.User_Name, a.Status, a.Password from USER_ACCOUNT a "+"where a.User_Name = ? and a.Password=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,userName);
        pstm.setString(2,password);
        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            String status = rs.getString("Status");
            UserAccount userAccount = new UserAccount();
            userAccount.setUserName(userName);
            userAccount.setPassword(password);
            userAccount.setStatusEMP(status);
            return userAccount;
        }
        return null;
    }
    public static UserAccount findUser(Connection connection, String userName)throws SQLException{
        String sql = "select a.User_Name, a.Status,  a.Password from USER_ACCOUNT a "+"where a.User_Name = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,userName);

        ResultSet rs = pstm.executeQuery();
        if(rs.next()){
            String password = rs.getString("Password");
            String status = rs.getString("Status");
            UserAccount userAccount = new UserAccount();
            userAccount.setUserName(userName);
            userAccount.setPassword(password);
            userAccount.setStatusEMP(status);
            return userAccount;
        }
        return null;
    }
    public static List<Product> queryProduct(Connection connection)throws  SQLException{
        String sql = "select a.product_name, a.qty, a.one_product_price from PRODUCT a";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Product> list = new ArrayList<Product>();
        while (rs.next()){
            String produc_name = rs.getString("product_name");
            int  qty = rs.getInt("qty");
            double one_product_price = rs.getDouble("one_product_price");
            Product product = new Product();
            product.setProductName(produc_name);
            product.setQty(qty);
            product.setOneProductPrice(one_product_price);
            list.add(product);

        }
        return list;

    }
    public static Product findProduct(Connection connection, String product_name)throws  SQLException{
        String sql = "select a.product_name, a.qty, a.one_product_price from PRODUCT a where a.product_name=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, product_name);

        ResultSet rs = pstm.executeQuery();
        while (rs.next()){
            int qty = rs.getInt("qty");
            double one_product_price = rs.getDouble("one_product_price");
            Product product = new Product(product_name,qty,one_product_price);
            return  product;
        }
        return null;
    }
    public static void updateProduct(Connection connection, Product product)throws SQLException{
        String sql = "update PRODUCT set qty=?,one_product_price=? where product_name=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1,product.getQty());
        pstm.setDouble(2,product.getOneProductPrice());
        pstm.setString(3,product.getProductName());
        pstm.executeUpdate();
    }
    public  static  void insertProduct(Connection connection, Product product)throws SQLException{
        String sql = "insert into PRODUCT(product_name,qty,one_product_price)values (?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,product.getProductName());
        pstm.setInt(2,product.getQty());
        pstm.setDouble(3,product.getOneProductPrice());
        pstm.executeUpdate();
    }
    public static void deleteProduct(Connection connection, String produc_name)throws SQLException{
        String sql = "delete from PRODUCT where product_name=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,produc_name);
        pstm.executeUpdate();
    }

}
