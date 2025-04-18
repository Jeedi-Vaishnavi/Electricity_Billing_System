
package electricity.billing.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class CustomerDetails extends JFrame implements ActionListener {
  Choice meternumber,cmonth;
    JTable table;
    JButton search,print;
    CustomerDetails(){
        super("Customer Details");
        setBounds(200,150,1200,650);
        
       
        table=new JTable();
        try{
          Conn c=new Conn();
          ResultSet rs=c.s.executeQuery("select * from customer");
          table.setModel(DbUtils.resultSetToTableModel(rs));
          
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        JScrollPane sp=new JScrollPane(table);
        
        add(sp);
        
        print=new JButton("Print");
       
        print.addActionListener(this);
        add(print,"South");
        
        
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
       
            try{
                  table.print(); 
            }
            catch(Exception e){
                e.printStackTrace();
            }
       
    }
    
    public static void main(String args[]){
        new CustomerDetails();
    }
}
