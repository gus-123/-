/*
CrosswordPuzzle
https://github.com/hencoappel/CrosswordPuzzle/tree/master/src/crossword
Copyright 2013 Henco Appel / 영문 한글화, 단어의 문자 칸수에 따른 점수표기, 십자말풀이 순서변경, 십자말풀이 게임 조작설명
*/

package crossword;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import crossword.io.CrosswordIO;
import crossword.network.sockets.CrosswordServer;

@SuppressWarnings("serial")
public class PuzzleGUI extends JFrame {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new PuzzleGUI();
			}
		});
	}

	public static final String CW_EXT = "cw";
	public static final String CW_SAVE_EXT = "cws";
	private List<Crossword> crosswords; 
	private Crossword currentCrossword;
	private Cell[][] puzzle; 
	private CrosswordGrid grid; 
	private JLabel crosswordTitle;
	private JList acrossJList, downJList;
	private JTextArea logArea;
	private String userName;
	private Window window; 
	private boolean solvedSupport;

	// Networking objects
	private Socket socket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private boolean connected;
	private Thread input;

	public PuzzleGUI() {
		super("십자말풀이(영어 단어 맞추기)");
		initGUI();
	}

	private void initGUI() {
		acrossJList = new JList();
		acrossJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		acrossJList.setCellRenderer(new ClueRenderer());
		acrossJList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList source = (JList) e.getSource();
				if (!source.isSelectionEmpty()) {
					int selected = source.getSelectedIndex();
					Clue clue = currentCrossword.getAcrossClues().get(selected);
					grid.onlyHighlightClue(clue.getX(), clue.getY(), clue.getNumber(),
							CrosswordGrid.ACROSS);
				}
			}
		});

		downJList = new JList();
		downJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		downJList.setCellRenderer(new ClueRenderer());
		downJList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!((JList) e.getSource()).isSelectionEmpty()) {
					int selected = ((JList) e.getSource()).getSelectedIndex();
					Clue clue = currentCrossword.getDownClues().get(selected);
					grid.onlyHighlightClue(clue.getX(), clue.getY(), clue.getNumber(),
							CrosswordGrid.DOWN);
				}
			}
		});
		crosswordTitle = new JLabel("", SwingConstants.CENTER);

		initialiseCrosswords();
		window = this;

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel crosswordPanel = new JPanel();
		crosswordPanel.setLayout(new BoxLayout(crosswordPanel, BoxLayout.X_AXIS));
		JPanel gridPanel = new JPanel(new BorderLayout(10, 10));
		gridPanel.add(crosswordTitle, BorderLayout.NORTH);
		grid = new CrosswordGrid(puzzle, this);
		gridPanel.add(grid, BorderLayout.CENTER);
		crosswordPanel.add(gridPanel);

		JPanel cluePanel = new JPanel(new GridLayout(2, 1, 5, 5));
		cluePanel.setPreferredSize(new Dimension(220, 200));

		JPanel acrossCluesPanel = new JPanel(new BorderLayout());
		acrossCluesPanel.add(new JLabel("가로줄힌트", SwingConstants.CENTER), BorderLayout.NORTH);
		acrossCluesPanel.add(new JScrollPane(acrossJList), BorderLayout.CENTER);

		JPanel downCluesPanel = new JPanel(new BorderLayout());
		downCluesPanel.add(new JLabel("세로줄힌트", SwingConstants.CENTER), BorderLayout.NORTH);
		downCluesPanel.add(new JScrollPane(downJList), BorderLayout.CENTER);

		cluePanel.add(acrossCluesPanel);
		cluePanel.add(downCluesPanel);
		crosswordPanel.add(cluePanel);
		panel.add(crosswordPanel);

		JPanel textPanel = new JPanel(new BorderLayout());

		JPanel chatPanel = new JPanel();
		chatPanel.add(new JLabel("채팅:"));
		final JTextField chatField = new JTextField(30);
		chatField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (out != null)
						out.println("채팅:" + userName + ":" + chatField.getText());
					else
						JOptionPane.showMessageDialog(window, "네트워크 연결 실패", "경고",
								JOptionPane.ERROR_MESSAGE);
					logArea.append(Tools.getTime() + "\n\t" + userName + " 님의 대화: "
							+ chatField.getText() + "\n");
					chatField.setText("");
				}
			}
		});
		chatPanel.add(chatField);
		textPanel.add(chatPanel, BorderLayout.NORTH);

		logArea = new JTextArea();
		logArea.setEditable(false);
		JScrollPane textAreaPanel = new JScrollPane(logArea);
		textAreaPanel.setAutoscrolls(true);
		textAreaPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textPanel.add(textAreaPanel, BorderLayout.CENTER);
		textPanel.setPreferredSize(new Dimension(500, 140));
		textPanel.setMinimumSize(new Dimension(500, 400));
		textPanel.setMaximumSize(new Dimension(2000, 400));

		panel.add(textPanel);

		setContentPane(panel);
		JMenuBar menuBar = createMenuBar();
		setJMenuBar(menuBar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(600, 600));
		pack();
		setVisible(true);

		do {
			setUser();
			if (userName == null)
				JOptionPane.showMessageDialog(window, "이름을 입력하세요!", "경고",
						JOptionPane.ERROR_MESSAGE);
		} while (userName == null);
	}

	private void addCrossword(Crossword crossword) {
		if (crossword != null && !crosswords.contains(crossword))
			crosswords.add(crossword);
	}

	private Crossword chooseCrossword() {
		JList list = new JList(crosswords.toArray());
		JScrollPane pane = new JScrollPane(list);
		pane.setPreferredSize(new Dimension(160, 200));
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		int option = JOptionPane.showOptionDialog(window, pane, "십자말풀이 선택",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		if (option == JOptionPane.OK_OPTION)
			return (Crossword) list.getSelectedValue();
		else
			return null;

	}

	private void setUser() {
		String option = JOptionPane.showInputDialog(window, "이름: ", "입력", JOptionPane.PLAIN_MESSAGE);
		if (option != null && !option.equals("")) {
			userName = option;
			logArea.append(Tools.getTime() + "\n\t현재 사용자: " + userName + "\n");
		}
	}

	private void initialiseCrosswords() {
		crosswords = new ArrayList<Crossword>();
		crosswords.add(InitialCrosswords.getCrossword1());
		crosswords.add(InitialCrosswords.getCrossword2());
		currentCrossword = crosswords.get(1);
		loadCrossword(crosswords.get(1));
	}

	public class ClueRenderer extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index,
				boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (solvedSupport && ((Clue) value).isSolved())
				setBackground(new Color(151, 206, 139));
			if (isSelected)
				setBorder(BorderFactory.createLineBorder(new Color(99, 130, 191)));

			return this;
		}

	}

	private void loadCrossword(Crossword crossword) {
		currentCrossword.resetCrossword(); 
		currentCrossword = crossword;
		crosswordTitle.setText(crossword.getTitle());
		puzzle = new Cell[currentCrossword.getSize()][currentCrossword.getSize()];

		acrossJList.setListData(currentCrossword.getAcrossClues().toArray());
		downJList.setListData(currentCrossword.getDownClues().toArray());

		for (Clue clue : currentCrossword.getAcrossClues())
			loadClue(clue, true);
		for (Clue clue : currentCrossword.getDownClues())
			loadClue(clue, false);
		if (grid != null) {
			grid.setPuzzle(puzzle);
		}
	}

	private void loadClue(Clue clue, boolean across) {
		char[] answer = clue.getAnswer().replaceAll("(-| )", "").toUpperCase().toCharArray();
		char character = ' ';
		if (clue.isSolved())
			character = answer[0];
		if (puzzle[clue.getX()][clue.getY()] == null)
			puzzle[clue.getX()][clue.getY()] = new Cell(character, answer[0], null, null);

		if (across) {
			puzzle[clue.getX()][clue.getY()].setAcrossClue(clue);
			if (puzzle[clue.getX()][clue.getY()].getC().equals(" ") && clue.isSolved())
				puzzle[clue.getX()][clue.getY()].setC(Character.toString(character));
		} else {
			puzzle[clue.getX()][clue.getY()].setDownClue(clue);
			if (puzzle[clue.getX()][clue.getY()].getC().equals(" ") && clue.isSolved())
				puzzle[clue.getX()][clue.getY()].setC(Character.toString(character));
		}
		puzzle[clue.getX()][clue.getY()].setClueNum(Integer.toString(clue.getNumber()));
		for (int i = 1; i < answer.length; i++) {
			character = ' ';
			if (clue.isSolved())
				character = answer[i];
			if (across) {
				if (puzzle[clue.getX() + i][clue.getY()] == null)
					puzzle[clue.getX() + i][clue.getY()] = new Cell(character, answer[i], clue,
							null);
				else {
					puzzle[clue.getX() + i][clue.getY()].setAcrossClue(clue);
					if (puzzle[clue.getX()][clue.getY()].getC().equals(" ") && clue.isSolved())
						puzzle[clue.getX()][clue.getY()].setC(Character.toString(character));
				}
			} else {
				if (puzzle[clue.getX()][clue.getY() + i] == null)
					puzzle[clue.getX()][clue.getY() + i] = new Cell(character, answer[i], null,
							clue);
				else {
					puzzle[clue.getX()][clue.getY() + i].setDownClue(clue);
					if (puzzle[clue.getX()][clue.getY()].getC().equals(" ") && clue.isSolved())
						puzzle[clue.getX()][clue.getY()].setC(Character.toString(character));
				}
			}
		}
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("기능");

		//십자말풀이 게임 조작설명
		JMenuItem information = new JMenuItem();
		information.setAction(new AbstractAction("십자말풀이 게임 조작설명") {

			@Override
			public void actionPerformed(ActionEvent e) {
				Frame f= new JFrame();
		        f.setTitle("도움말");
		        f.setBounds(100, 100, 300, 300);
		        f.setVisible(true);
			}
		});
		information.setMnemonic(KeyEvent.VK_I);
		information.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		fileMenu.add(information);
		
		JMenuItem resetCrossword = new JMenuItem();
		resetCrossword.setAction(new AbstractAction("십자말풀이 초기화") {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentCrossword != null) {
					currentCrossword.resetCrossword();

					for (Cell[] cellarr : puzzle)
						for (Cell cell : cellarr)
							if (cell != null)
								cell.setC("");
					repaint();
				}
			}
		});
		resetCrossword.setMnemonic(KeyEvent.VK_R);
		resetCrossword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		fileMenu.add(resetCrossword);

		JMenuItem loadCrossword = new JMenuItem();
		loadCrossword.setAction(new AbstractAction("십자말풀이 선택하기") {

			@Override
			public void actionPerformed(ActionEvent e) {
				Crossword c = chooseCrossword();
				if (c != null)
					loadCrossword(c);
			}
		});
		loadCrossword.setMnemonic(KeyEvent.VK_L);
		loadCrossword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		fileMenu.add(loadCrossword);

		fileMenu.addSeparator();

		JMenuItem closeWindow = new JMenuItem();
		closeWindow.setAction(new AbstractAction("끝내기") {

			@Override
			public void actionPerformed(ActionEvent e) {
				WindowEvent wev = new WindowEvent(window, WindowEvent.WINDOW_CLOSING);
				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
			}
		});
		closeWindow.setMnemonic(KeyEvent.VK_Q);
		closeWindow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		fileMenu.add(closeWindow);

		menuBar.add(fileMenu);

		JMenu optionsMenu = new JMenu("옵션");

		JMenuItem setUser = new JMenuItem();
		setUser.setAction(new AbstractAction("사용자 변경") {

			@Override
			public void actionPerformed(ActionEvent e) {
				setUser();
			}
		});
		setUser.setMnemonic(KeyEvent.VK_U);
		setUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		optionsMenu.add(setUser);

		JCheckBoxMenuItem toggleSolvedSupport = new JCheckBoxMenuItem();
		toggleSolvedSupport.setAction(new AbstractAction("정답 체크") {

			@Override
			public void actionPerformed(ActionEvent e) {
				solvedSupport = !solvedSupport;
				window.repaint();
			}
		});
		toggleSolvedSupport.setMnemonic(KeyEvent.VK_H);
		toggleSolvedSupport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				ActionEvent.CTRL_MASK));
		optionsMenu.add(toggleSolvedSupport);

		menuBar.add(optionsMenu);

		JMenu networkingMenu = new JMenu("네트워크 설정");

		final JMenuItem connect = new JMenuItem();
		connect.setAction(new AbstractAction("연결 여부") {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (connected) {
					try {
						socket.close();
						out.close();
						in.close();
					} catch (IOException ex) {
						System.err.println("Couldn't get I/O for the connection to"
								+ socket.getInetAddress());
					}
					connect.setText("연결 여부");
					connected = false;
				} else { 
					try {
						socket = new Socket("linuxproj.ecs.soton.ac.uk", 1292);
						out = new PrintWriter(socket.getOutputStream(), true);
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						connected = true;
						input = new Thread(new InStream());
						input.start();
						connect.setText("disconnect");
					} catch (UnknownHostException ex) {
						JOptionPane.showMessageDialog(window,
								"현재 호스트 서버에 액세스할 수 없습니다. \n"
										+ "새 호스트 서버에 설정을 시도해주시길 바랍니다.", "경고",
								JOptionPane.ERROR_MESSAGE);
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(window, "네트워크 연결 실패",
								"경고", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		networkingMenu.add(connect);

		JMenuItem changeHost = new JMenuItem();
		changeHost.setAction(new AbstractAction("(1) IP 변경") {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (connected) {
					try {
						socket.close();
						out.close();
						in.close();
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(window, "십자말풀이 서버를 실행 해주세요!", "경고",
								JOptionPane.ERROR_MESSAGE);
					}
					connect.setText("연결 여부");
					connected = false;
				}
				try {
					String host = JOptionPane.showInputDialog(window, "IP 주소 입력:",
							"IP 변경", JOptionPane.PLAIN_MESSAGE);
					socket = new Socket(host, 1292);
					out = new PrintWriter(socket.getOutputStream(), true);
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					connected = true;
					input = new Thread(new InStream());
					input.start();
					connect.setText("(2) 연결 해제");
				} catch (UnknownHostException ex) {
					JOptionPane.showMessageDialog(window, "현재 호스트 서버에 액세스할 수 없습니다. \n"
							+ "새 호스트 서버에 설정을 시도해주시길 바랍니다.", "경고",
							JOptionPane.ERROR_MESSAGE);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(window, "IP 주소를 다시 입력 해주세요!", "경고",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		networkingMenu.add(changeHost);

		menuBar.add(networkingMenu);

		return menuBar;
	}

	class InStream implements Runnable {

		@Override
		public void run() {
			String line = "";
			try {
				while ((line = in.readLine()) != null) {
					String[] vals = line.split(":");
					if (!vals[0].equals("chat")) { 
						int x = Integer.parseInt(vals[0]);
						int y = Integer.parseInt(vals[1]);
						char c = vals[2].charAt(0);
						String username = vals[3];
						try {
							grid.setCell(x, y, c, username, false, false);
						} catch (Exception e) {
					
						}
					} else { 
						logArea.append(Tools.getTime() + "\n\t" + vals[1] + " 님의 대화: " + vals[2]
								+ "\n");
					}
				}
				connected = false;
			} catch (IOException e) {
			}
		}
	}

	protected void outStream(int x, int y, char c, String username) {
		String line = "";
		line += Integer.toString(x);
		line += ":";
		line += Integer.toString(y);
		line += ":";
		line += Character.toString(c);
		line += ":";
		line += username;
		out.println(line);
	}

	public String getUser() {
		return userName;
	}

	public boolean supportOn() {
		return solvedSupport;
	}

	public Crossword getCurrentCrossword() {
		return currentCrossword;
	}

	public void appendLog(String string) {
		logArea.append(string);
	}

	public JList getAcrossJList() {
		return acrossJList;
	}

	public JList getDownJList() {
		return downJList;
	}

	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

}