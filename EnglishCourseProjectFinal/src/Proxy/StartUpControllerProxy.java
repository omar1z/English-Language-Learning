package Proxy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DataClass.DataBaseConnection;
import DataClass.User;

public class StartUpControllerProxy implements ProxyInterface {
    private ProxyInterface realController;
    private DataClass.User user;
    private String email;
    private String password;
    private JFrame frame;

    public StartUpControllerProxy(JFrame frame,String email, String password) {
    	this.frame = frame;
        this.email = email;
        this.password = password;
    }

    public void showPage() {
    	User user = authenticateUser(email, password);
        if (user != null) {
        	
            StartUpController s = new StartUpController(user,frame);
        } else {
            JOptionPane.showMessageDialog(null, "Authentication failed. Please log in.");
        }
    }

    public User authenticateUser(String enteredGmail, String enteredPassword) {
        Statement s = DataBaseConnection.getStatment();
        try {
            ResultSet verification = s.executeQuery(
                    "select * from Users where email ='" + enteredGmail + "' and password ='" + enteredPassword+"' ; ");
            boolean userAuth=verification.next();	
            if(userAuth) {
            	user=new User(verification.getInt("user_id"),verification.getString("full_name"),enteredGmail);
            }
            else {
            	user =null;
            }
            
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}