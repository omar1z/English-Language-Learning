package Proxy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DataClass.DataBaseConnection;
import DataClass.User;

import PanelPackage.Login;
import PanelPackage.SignUp;
import PanelPackage.*;
public class LogInController {
    int i = 1;
    Login login;
    SignUp signUp;
    JFrame main = new JFrame();
    JPanel backgroundPanel;
    private String enteredGmail;
    private String enteredPassword;
    private String enteredGmailSin;
    private String enteredPasswordSin ;

    User user;

    public LogInController() {
        login = new Login();
        signUp = new SignUp();
        
        
        JPanel panel = new JPanel(new BorderLayout());
        backgroundPanel = new Imagepan();
   
        signUp.getSignUpButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
            	 enteredGmailSin = signUp.getEmail();
            	 enteredPasswordSin = signUp.getPassword();
                 boolean t=EmailValidator.isValidEmail(enteredGmailSin);
                if(t) {
                String hashedPassword = hashPassword(enteredPasswordSin);
                try {
                    ResultSet userAuth = DataBaseConnection.getStatment()
                            .executeQuery("select full_name from users where email = '" + enteredGmailSin + "';");
                   
                    if (!userAuth.next()) {
                    	System.out.println("hello 1");
                        DataBaseConnection.getStatment()
                                .executeUpdate("Insert into Users (full_name,email,password) value('"
                                        + signUp.getUsername() + "','" + enteredGmailSin + "','" + hashedPassword
                                        + "' );");
//                        ResultSet userIdSet = DataBaseConnection.getStatment()
//                                .executeQuery("select user_id from users where gamil = '" + enteredGmailSin + "',");
//                        userIdSet.next();
//                        user = new User(userIdSet.getInt("user_id"), signUp.getUsername(), enteredGmailSin);
                        StartUpControllerProxy startUpControllerProxy = new StartUpControllerProxy(main,enteredGmailSin,
                                hashedPassword);
                        startUpControllerProxy.showPage();
                    } else {
                       // panel.add(login);
                    	
                        ((JPanel) main.getContentPane().getComponent(0)).removeAll();
                        backgroundPanel.removeAll();
                        login.repaint();
                        login.getPanel().repaint();
                        backgroundPanel.add(login);
                        panel.add(backgroundPanel,BorderLayout.CENTER);
                        main.add(panel,BorderLayout.CENTER);
                        main.revalidate();
                        main.repaint();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
}
else {
	JOptionPane.showMessageDialog(null, "InValied Email");
}
            }
        });

        login.getLoginButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
            	enteredPassword = login.getPassword();
            	enteredGmail = login.getEmail();
                String hashedPassword = hashPassword(enteredPassword);
                StartUpControllerProxy startUpControllerProxy = new StartUpControllerProxy(main,enteredGmail,
                        hashedPassword);
                startUpControllerProxy.showPage();

            }
        });

        login.getSignUpButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
            	panel.removeAll();
                ((JPanel) main.getContentPane().getComponent(0)).removeAll();
                backgroundPanel.removeAll();
                signUp.repaint();
                backgroundPanel.add(signUp);
                panel.add(backgroundPanel);
                main.add(panel,BorderLayout.CENTER);
                main.revalidate();
                main.repaint();
            }
        });

        signUp.getLogInButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
            	panel.removeAll();
                ((JPanel) main.getContentPane().getComponent(0)).removeAll();
                backgroundPanel.removeAll();
                login.repaint();
                backgroundPanel.add(login);
                panel.add(backgroundPanel);
                main.add(panel,BorderLayout.CENTER);
                main.revalidate();
                main.repaint();
            }
        });
        backgroundPanel.add(login);
        panel.add(backgroundPanel,BorderLayout.CENTER);
        main.add(panel,BorderLayout.CENTER);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        main.setVisible(true);
    }
    private static final String EMAIL_REGEX = "^[a-zA-Z][a-zA-Z0-9\\.]+@gmail+\\.com$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public String hashPassword(String password) {
        String generatedPassword = null;
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(password.getBytes());

            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}