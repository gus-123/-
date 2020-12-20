///Copyright 2020 Hyun Min///
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class test extends JFrame{
   private JPanel loginPanel = new JPanel(new GridLayout(3, 2));
   private JLabel idLabel = new JLabel("ID"); 
   private JLabel pwLabel = new JLabel("비밀번호");
   private JTextField idText = new JTextField();
   private JPasswordField pwText = new JPasswordField();
   private JButton loginBtn = new JButton("로그인");
   private JButton idpwSearchBtn = new JButton("회원가입");

   public test() {
      super("로그인 창");
      
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
               JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력 하셔야 됩니다.", "아이디나 비번을 입력!", JOptionPane.DEFAULT_OPTION);
               return;
            }
            
            if(id.equals("test") && pw.equals("1234")) {
               new menu();
               return;
            }
            
	JOptionPane.showMessageDialog(null, "로그인 실패", "로그인 확인!", JOptionPane.DEFAULT_OPTION);
            
         }
      });
      
      idpwSearchBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
	      JOptionPane.showMessageDialog(null, "아직 만들어지지 않았습니다."
						+ "", "회원가입", JOptionPane.DEFAULT_OPTION); 
	      //new GUI();
              //return;         
         }
      });
      
   }
   
   public static void main(String[] args) {
      new test();
   }
}
