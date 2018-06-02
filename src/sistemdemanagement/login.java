
package sistemdemanagement;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class login extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    

    public login() {
        initComponents();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2,size.height/2 - getHeight()/2);
        
        conn = db.java_db();
        currentDate();
    }

    public void currentDate(){
        
        Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        
        lbl_date.setText(day+"/"+(month+1)+"/"+year);
        
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);
        
        lbl_time.setText(hour+":"+minute+":"+second);
        
        
        
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        txt_username = new javax.swing.JTextField();
        txt_combo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        txt_password = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        lbl_date = new javax.swing.JMenu();
        lbl_time = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setLayout(null);
        jPanel2.add(txt_username);
        txt_username.setBounds(200, 210, 140, 30);

        txt_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Resurse Umane", "Contabilitate" }));
        jPanel2.add(txt_combo);
        txt_combo.setBounds(200, 290, 140, 30);

        jButton1.setText("Logare");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(243, 331, 100, 23);
        jPanel2.add(txt_password);
        txt_password.setBounds(200, 250, 140, 30);

        jLabel4.setForeground(new java.awt.Color(255, 255, 204));
        jLabel4.setText("Departament:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(60, 296, 100, 14);

        jLabel3.setForeground(new java.awt.Color(255, 255, 204));
        jLabel3.setText("Parola:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(57, 258, 70, 14);

        jLabel2.setForeground(new java.awt.Color(255, 255, 204));
        jLabel2.setText("Nume utilizator:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(58, 220, 110, 14);

        jLabel1.setForeground(new java.awt.Color(255, 255, 204));
        jLabel1.setText("Introduceti numele de utilizator si parola");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(50, 150, 280, 20);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemdemanagement/images/bk3.jpg"))); // NOI18N
        jLabel6.setText("jLabel6");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(0, 0, 530, 390);

        jMenu1.setText("File");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemdemanagement/images/Exit.png"))); // NOI18N
        jMenuItem1.setText("Iesire");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        lbl_date.setText("Date");
        jMenuBar1.add(lbl_date);

        lbl_time.setText("Time");
        jMenuBar1.add(lbl_time);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        String sql ="select id,numeutilizator,parola,departament from Utilizatori where (numeutilizator =? and parola =? and departament =?)";
        
        try{
            int count = 0;
            
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, txt_username.getText());
            pst.setString(2, txt_password.getText());
            pst.setString(3, txt_combo.getSelectedItem().toString());
            
            rs = pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                Emp.empId = id;
                String username = rs.getString("numeutilizator");
                Emp.empname = username;
                count = count + 1;
                String departament = rs.getString("departament");
                Emp.dep = departament;    
            }
            String access = (txt_combo.getSelectedItem().toString());
            
            if(access =="Admin" ||access =="Contabilitate" ||access =="Resurse Umane"){
                if(count == 1){
                    JOptionPane.showMessageDialog(null, "Succes !");
                    MainMenu j = new MainMenu();
                    j.setVisible(true);
                    this.dispose();
                    
                  Date currentDate = GregorianCalendar.getInstance().getTime();
                  DateFormat df = DateFormat.getDateInstance();
                  String dateString = df.format(currentDate);

                  Date d = new Date();
                  SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                  String timeString = sdf.format(d);

                  String value0 = timeString;
                  String values = dateString;
                  
                  int value = Emp.empId;
                  String reg = "insert into Control (ang_id,data,stare) values ('"+value+"','"+value0+" / "+values+"','Conectat')";
                  pst=conn.prepareStatement(reg);
                  pst.execute();
                  this.dispose();
                
                }else{
                    JOptionPane.showMessageDialog(null, "Nume de utilizator si parola sunt incorecte !");
                }
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
            try{
               rs.close();
               pst.close();
            }catch (Exception e){
                
            }
        }
   
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        System.exit(0); 
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JMenu lbl_date;
    private javax.swing.JMenu lbl_time;
    private javax.swing.JComboBox<String> txt_combo;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
