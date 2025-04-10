
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton log,cancel,signup;
    JTextField uname,password;
    Choice login;
    Login(){
         super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lbluname=new JLabel("Username");
        lbluname.setBounds(300,20,100,20);
        add(lbluname);
        
         uname=new JTextField();
        uname.setBounds(400,20,150,20);
        add(uname);
        
        JLabel lblpassword=new JLabel("Password");
        lblpassword.setBounds(300,65,100,20);
        add(lblpassword);
                
        password=new JTextField();
        password.setBounds(400,65,150,20);
        add(password);
        
        
        JLabel logininas=new JLabel("Loggin in as");
        logininas.setBounds(300,115,100,20);
        add(logininas);
        
         login=new Choice();
        login.add("admin");
        login.add("customer");
        login.setBounds(400,115,150,20);
        add(login);
        
         
         log=new JButton("Login");
        log.setBounds(330,160,100,20);
        log.addActionListener(this);
        add(log);
    
       cancel=new JButton("Cancel");
        cancel.setBounds(450,160,100,20);
        cancel.addActionListener(this);
        add(cancel);
        
    
        signup=new JButton("Signup");
        signup.setBounds(380,200,100,20);
        signup.addActionListener(this);
        add(signup);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i2=i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,250,250);
        add(image);
        
        setSize(640,300);
        setLocation(400,200);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==log){
            String susername=uname.getText();
            String spassword=password.getText();
            String suser= login.getSelectedItem();
            
            try{
                Conn c=new Conn();
                String query="select * from login where username='"+susername+"' and password='"+spassword+"' and user='"+suser+"'";
                ResultSet rs=c.s.executeQuery(query);
                if(rs.next()){
                    String meter=rs.getString("meter_no");
                    setVisible(false);
                    new Project(suser,meter);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Login");
                    uname.setText("");
                    password.setText("");
                    
                }
            }
            catch(Exception e){
                e.printStackTrace();
                
            }
        }
        else if(ae.getSource()==cancel){
            setVisible(false);
        }
        else if(ae.getSource()==signup){
            setVisible(false);
            new SignUp();
        }
    }
   public static void main(String args[]){
       new Login();
   } 
}
