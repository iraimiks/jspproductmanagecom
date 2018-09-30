package lv.raimonds.beans;


public class UserAccount {
    public static final String EMPLOYEE = "EMP";
    public static final String STOCKEEPER = "STO";
    private String userName;
    private String statusEMP;
    private String password;
    public UserAccount(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatusEMP() {
        return statusEMP;
    }

    public void setStatusEMP(String statusEMP) {
        this.statusEMP = statusEMP;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
