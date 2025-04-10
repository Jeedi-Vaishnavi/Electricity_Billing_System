
package electricity.billing.system;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignUp extends JFrame implements ActionListener {
    JButton back,create;
    Choice actype;
    JTextField meter,uname,name,password;
    
    SignUp(){
      
        setBounds(450,150,700,400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JPanel panel=new JPanel();
        panel.setBounds(30,30,650,300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230),2),
                "Create-Account",TitledBorder.LEADING,TitledBorder.TOP,null,new Color(172,216,230)));
       panel.setBackground(Color.WHITE);
       panel.setLayout(null);
       panel.setForeground(new Color(34,139,34));
        add(panel);
        
        JLabel heading=new JLabel("Create Account As");
        heading.setBounds(100,50,140,20);
        heading.setForeground(Color.GRAY);
        heading.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(heading);
        
         actype=new Choice();
        actype.add("Admin");
        actype.add("Customer");
        actype.setBounds(260,50,150,20);
        panel.add(actype);
        
       
        
        JLabel lblmeter=new JLabel("Meter Number");
        lblmeter.setBounds(100,90,140,20);
        lblmeter.setForeground(Color.GRAY);
        lblmeter.setFont(new Font("Tahoma",Font.BOLD,14));
        lblmeter.setVisible(false);
        panel.add(lblmeter);
        
         meter=new JTextField();
        meter.setBounds(260,90,150,20);
        meter.setVisible(false);
        panel.add(meter);
        
        
        
        JLabel lbluname=new JLabel("Username");
        lbluname.setBounds(100,130,140,20);
        lbluname.setForeground(Color.GRAY);
        lbluname.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lbluname);
        
         uname=new JTextField();
        uname.setBounds(260,130,150,20);
        panel.add(uname);
        
         JLabel lblname=new JLabel("Name");
        lblname.setBounds(100,170,140,20);
        lblname.setForeground(Color.GRAY);
        lblname.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lblname);
        
        name=new JTextField();
        name.setBounds(260,170,150,20);
        panel.add(name);
        
         meter.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
                
            }public void focusLost(FocusEvent fe) {
                try{
                Conn c=new Conn();
                ResultSet rs=c.s.executeQuery("select * from login where meter_no='"+meter.getText()+"'");
                while(rs.next()){
                    name.setText(rs.getString("name"));
                    
                }
            }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        
        JLabel lblpassword=new JLabel("Password");
        lblpassword.setBounds(100,210,140,20);
        lblpassword.setForeground(Color.GRAY);
        lblpassword.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(lblpassword);
        
        password=new JTextField();
        password.setBounds(260,210,150,20);
        panel.add( password);
        
        
        
        actype.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent ae){
            String user=actype.getSelectedItem();
            if(user.equals("Customer"))
            {
              lblmeter.setVisible(true);
              meter.setVisible(true);
              name.setEditable(false);
            }
          else{
           lblmeter.setVisible(false);
              meter.setVisible(false);
               name.setEditable(true);
            }
            }
            });


    
        
        create=new JButton("Create");
        create.setBounds(140,250,120,25);
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.addActionListener(this);
        panel.add(create);
        
         back=new JButton("Back");
        back.setBounds(300,250,120,25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        panel.add(back);
        
         ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2=i1.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(420,25,250,250);
        panel.add(image);
        
        setVisible(true);
    }   
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==create){
            String atype=actype.getSelectedItem();
            String susername=uname.getText();
            String sname=name.getText();
            String spassword=password.getText();
            String smeter=meter.getText();
            
            try{
                Conn c=new Conn();
                String query=null;
                if(atype.equals("Admin"))
                {
                   query = "insert into login values('"+smeter+"', '"+susername+"', '"+sname+"', '"+spassword+"', '"+atype+"')";
                }
                else{
                    query="update login set username='"+susername+"',password= '"+spassword+"',user = '"+atype+"' where meter_no = '"+smeter+"'" ;
                    
                }
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Account created successfully");
                setVisible(false);
                new Login();      
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
        }
        else if(ae.getSource()==back){
            setVisible(false);
            new Login();
        }
    }
    
    public static void main(String args[]){
        new SignUp();
    }
}
