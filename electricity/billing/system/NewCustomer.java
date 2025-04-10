
package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class NewCustomer extends JFrame implements ActionListener {
    JTextField tfname,address,city,state,email,phone;
    JButton next,cancel;
    JLabel tfmeter;
    NewCustomer(){
        
        setBounds(500,170,700,500);
        
        
        
        JPanel p=new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        add(p);
        
        JLabel heading = new JLabel("New Customer");
        heading.setBounds(230,20,200,25);
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        p.add(heading);
        
        JLabel lblname = new JLabel("Customer Name");
        lblname.setBounds(100,80,100,20);
        p.add(lblname);
        
        tfname=new JTextField();
        tfname.setBounds(240,80,200,20);
        p.add(tfname);
        
         JLabel lblmeter = new JLabel("Meter No");
        lblmeter.setBounds(100,120,100,20);
        p.add(lblmeter);
        
          tfmeter = new JLabel("");
        tfmeter.setBounds(240,120,100,20);
        p.add(tfmeter);
        Random rand=new Random();
        long number=rand.nextLong()% 1000000;
        tfmeter.setText("" + Math.abs(number));
        
        JLabel lbladdress = new JLabel("Adress");
        lbladdress.setBounds(100,160,100,20);
        p.add(lbladdress);
        
        address=new JTextField();
        address.setBounds(240,160,200,20);
        p.add(address);
        
         JLabel lblcity = new JLabel("City");
        lblcity.setBounds(100,200,100,20);
        p.add(lblcity);
        
        city=new JTextField();
        city.setBounds(240,200,200,20);
        p.add(city);
        
         JLabel lblstate = new JLabel("State");
        lblstate.setBounds(100,240,100,20);
        p.add(lblstate);
        
        state=new JTextField();
        state.setBounds(240,240,200,20);
        p.add(state);
        
         JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(100,280,100,20);
        p.add(lblemail);
        
        email=new JTextField();
        email.setBounds(240,280,200,20);
        p.add(email);
        
        JLabel lblphone = new JLabel("PhoneNo");
        lblphone.setBounds(100,320,100,20);
        p.add(lblphone);
        
        phone=new JTextField();
        phone.setBounds(240,320,200,20);
        p.add(phone);
        
        next=new JButton("Next");
        next.setBounds(120,390,100,25);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        p.add(next);
        
        cancel=new JButton("Cancel");
        cancel.setBounds(250,390,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);
        
        
        setLayout(new BorderLayout());
        add(p,"Center");
       
         ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i2=i1.getImage().getScaledInstance(150,300,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel label=new JLabel(i3);
        add(label,"West");
        
        getContentPane().setBackground(Color.WHITE);
        
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==next){
            String name=tfname.getText();
            String meter=tfmeter.getText();
            String saddress=address.getText();
            String scity=city.getText();
            String sstate=state.getText();
            String semail=email.getText();
            String sphone=phone.getText();
            
String query1="insert into customer values('"+name+"', '"+meter+"', '"+saddress+"', '"+scity+"', '"+sstate+"', '"+semail+"', '"+sphone+"')";
   String query2="insert into login values('"+meter+"','', '"+name+"','', '' )";
   
            try{
                Conn c=new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null,"Customer Details Added Successfully");
                setVisible(false);
                
                new MeterInfo(meter);
                
            }catch(Exception e){
                e.printStackTrace();
            }
                    
            
        }
        else{
           setVisible(false); 
        }
    }
    public static void main(String args[]){
        new NewCustomer();
    }
    
}
