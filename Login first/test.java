
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class test extends JFrame{
   private JPanel loginPanel = new JPanel(new GridLayout(3, 2));
   private JLabel idLabel = new JLabel("ID"); 
   private JLabel pwLabel = new JLabel("��й�ȣ");
   private JTextField idText = new JTextField();
   private JPasswordField pwText = new JPasswordField();
   private JButton loginBtn = new JButton("�α���");
   private JButton idpwSearchBtn = new JButton("ȸ������");

   public test() {
      super("ȸ������");
      
      this.setContentPane(loginPanel);
      loginPanel.add(idLabel);
      loginPanel.add(pwLabel);
      loginPanel.add(idText);
      loginPanel.add(pwText);
      loginPanel.add(idpwSearchBtn);
      loginPanel.add(loginBtn);
      
      idLabel.setHorizontalAlignment(NORMAL);
      pwLabel.setHorizontalAlignment(NORMAL);
      
      setSize(350,150);
      this.setLocationRelativeTo(null);      
      
      this.setVisible(true);
   
      
      loginBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            
            String id = idText.getText().trim();
            String pw = pwText.getText().trim();
            
            if(id.length()==0 || pw.length()==0) {
               JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� �Է� �ϼž� �˴ϴ�.", "���̵� ����� �Է�!", JOptionPane.DEFAULT_OPTION);
               return;
            }
            
            if(id.equals("test") && pw.equals("1234")) {
               new menu();
               return;
            }
            
	JOptionPane.showMessageDialog(null, "�α��� ����", "�α��� Ȯ��!", JOptionPane.DEFAULT_OPTION);
            
         }
      });
      
      idpwSearchBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
	  new GUI();
              return;         
         }
      });
      
   }
   
   public static void main(String[] args) {
      new test();
   }
}