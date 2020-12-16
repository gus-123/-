///Copyright 2020 Hyun Min///
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Font;
//import javax.imageio.ImageIO; Robot 안쓰는 캡쳐방식
//import java.io.File; Robot 안쓰는 캡쳐방식
//import java.io.IOException; Robot 안쓰는 캡쳐방식
//import java.awt.image.BufferedImage; Robot 안쓰는 캡쳐방식
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class store {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					store window = new store();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public store() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ImageIcon img1;
		ImageIcon img2;
		ImageIcon img3;
		ImageIcon img4;
		
		frame = new JFrame("캐릭터 상점");
		frame.setBounds(100, 100, 717, 345);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		img1 = new ImageIcon("../images/1.PNG");
		img2 = new ImageIcon("../images/2.PNG");
		img3 = new ImageIcon("../images/3.PNG");
		img4 = new ImageIcon("../images/4.PNG");
		BufferedImage img = new BufferedImage(160, 160, BufferedImage.TYPE_INT_RGB );
		
		JLabel lblNewLabel_2 = new JLabel("\uC0C1\uC810\uC5D0 \uC624\uC2E0\uAC78 \uD658\uC601\uD569\uB2C8\uB2E4!!");
		lblNewLabel_2.setFont(new Font("HY그래픽M", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 7;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 1;
		frame.getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel(img1);
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.gridwidth = 5;
		gbc_lblNewLabel_1_1.gridheight = 8;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 4;
		gbc_lblNewLabel_1_1.gridy = 3;
		frame.getContentPane().add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel(img2);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridheight = 8;
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 12;
		gbc_lblNewLabel_1.gridy = 3;
		frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel(img3);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.gridheight = 8;
		gbc_lblNewLabel_3.gridwidth = 2;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 16;
		gbc_lblNewLabel_3.gridy = 3;
		frame.getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JButton btnNewButton = new JButton("구매");
		btnNewButton.setFont(new Font("HY신명조", Font.BOLD | Font.ITALIC, 12));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 14;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_1_1.setIcon(img1);
				Object[] option = {"확인"};     //Made by Kim Soo-na
  				int complete = JOptionPane.showOptionDialog     
						(null, "구매 완료되었습니다!","구매 완료 확인창"
								, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, option[0]);     //Made by Kim Soo-na
			}
		});
		
		JButton btnNewButton_1 = new JButton("적용");
		btnNewButton_1.setFont(new Font("HY신명조", Font.BOLD | Font.ITALIC, 12));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 8;
		gbc_btnNewButton_1.gridy = 14;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_1_1.setIcon(img4);
				Object[] option = {"확인"};     //Made by Kim Soo-na
				int complete = JOptionPane.showOptionDialog
						(null, "적용 완료되었습니다!","적용 완료 확인창"
								, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, option[0]);     //Made by Kim Soo-na
				try {
					Robot robot = new Robot();
					int x = 133;
				    int y = 158;
				    int width = 199;
				    int height = 229;
					Rectangle rectangle = new Rectangle(x, y, width, height);

					BufferedImage bi = robot.createScreenCapture(rectangle);
					ImageIO.write(bi, "jpg", new File("C:\\Users\\hyunm\\Downloads\\screenshot.jpg"));
				} catch(IOException ioe) {
					ioe.printStackTrace();
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				//try { ImageIO.write(img, "PNG", new File("C:\\Users\\hyunm\\Downloads\\yourImageName.PNG")); }
				//catch (IOException e1) { e1.printStackTrace(); }
			}
		});
		
		JButton btnNewButton_2 = new JButton("구매");
		btnNewButton_2.setFont(new Font("HY신명조", Font.BOLD | Font.ITALIC, 12));
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 12;
		gbc_btnNewButton_2.gridy = 14;
		frame.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_1.setIcon(img2);
				Object[] option = {"확인"};     //Made by Kim Soo-na
				int complete = JOptionPane.showOptionDialog
						(null, "구매 완료되었습니다!","구매 완료 확인창"
								, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, option[0]);     //Made by Kim Soo-na
			}
		});
		
		JButton btnNewButton_3 = new JButton("적용");
		btnNewButton_3.setFont(new Font("HY신명조", Font.BOLD | Font.ITALIC, 12));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 13;
		gbc_btnNewButton_3.gridy = 14;
		frame.getContentPane().add(btnNewButton_3, gbc_btnNewButton_3);
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_1.setIcon(img4);
				Object[] option = {"확인"};     //Made by Kim Soo-na
				int complete = JOptionPane.showOptionDialog
						(null, "적용 완료되었습니다!","적용 완료 확인창"
								, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, option[0]);     //Made by Kim Soo-na
				try {
					Robot robot = new Robot();
					int x = 355;
				    int y = 160;
				    int width = 199;
				    int height = 229;
					Rectangle rectangle = new Rectangle(x, y, width, height);

					BufferedImage bi = robot.createScreenCapture(rectangle);
					ImageIO.write(bi, "jpg", new File("C:\\Users\\hyunm\\Downloads\\screenshot.jpg"));
				} catch(IOException ioe) {
					ioe.printStackTrace();
				} catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		JButton btnNewButton_2_1 = new JButton("구매");
		btnNewButton_2_1.setFont(new Font("HY신명조", Font.BOLD | Font.ITALIC, 12));
		GridBagConstraints gbc_btnNewButton_2_1 = new GridBagConstraints();
		gbc_btnNewButton_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2_1.gridx = 16;
		gbc_btnNewButton_2_1.gridy = 14;
		frame.getContentPane().add(btnNewButton_2_1, gbc_btnNewButton_2_1);
		
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_3.setIcon(img3);
				Object[] option = {"확인"};     //Made by Kim Soo-na
				int complete = JOptionPane.showOptionDialog
						(null, "구매 완료되었습니다!","구매 완료 확인창"
								, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, option[0]);     //Made by Kim Soo-na
			}
		});
		
		JButton btnNewButton_3_1 = new JButton("적용");
		btnNewButton_3_1.setFont(new Font("HY신명조", Font.BOLD | Font.ITALIC, 12));
		GridBagConstraints gbc_btnNewButton_3_1 = new GridBagConstraints();
		gbc_btnNewButton_3_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3_1.gridx = 17;
		gbc_btnNewButton_3_1.gridy = 14;
		frame.getContentPane().add(btnNewButton_3_1, gbc_btnNewButton_3_1);
		
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel_3.setIcon(img4);
				Object[] option = {"확인"};     //Made by Kim Soo-na
				int complete = JOptionPane.showOptionDialog
						(null, "적용 완료되었습니다!","적용 완료 확인창"
								, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, option, option[0]);     //Made by Kim Soo-na
				try {
					Robot robot = new Robot();
					int x = 590;
				    int y = 156;
				    int width = 199;
				    int height = 229;
					Rectangle rectangle = new Rectangle(x, y, width, height);

					BufferedImage bi = robot.createScreenCapture(rectangle);
					ImageIO.write(bi, "jpg", new File("C:\\Users\\hyunm\\Downloads\\screenshot.jpg"));
				} catch(IOException ioe) {
					ioe.printStackTrace();
				} catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
