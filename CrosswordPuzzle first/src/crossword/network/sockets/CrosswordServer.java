/*
CrosswordPuzzle
https://github.com/hencoappel/CrosswordPuzzle/tree/master/src/crossword
Copyright 2013 Henco Appel / 영문 한글화, 단어의 문자 칸수에 따른 점수표기, 십자말풀이 순서변경, 십자말풀이 게임 조작설명
*/

package crossword.network.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CrosswordServer implements Runnable {

	ServerSocket serverSocket;
	// all clients + their (in|out)put streams
	final ArrayList<Socket> clientSockets = new ArrayList<Socket>();
	final Map<Socket, PrintWriter> outStreams = new HashMap<Socket, PrintWriter>();
	final Map<Socket, BufferedReader> inStreams = new HashMap<Socket, BufferedReader>();

	public CrosswordServer() {
		try {
			// get server socket
			serverSocket = new ServerSocket(1292);
		} catch (IOException e) { // stop if failed
			System.out.println("Socket in use");
			System.exit(1);
		}
	}

	// start program
	public static void main(String[] args) throws IOException {
		new Thread(new CrosswordServer()).start();
	}

	@Override
	public void run() {
		while (true) {// wait for sockets
			Socket s = null;
			try {
				s = serverSocket.accept();
			} catch (IOException e) {
			}
			// once a client connects, add client
			newClient(s);
			System.out.println("Socket added");
		}
	}

	private void newClient(Socket s) {
		clientSockets.add(s);// keep record
		try {
			// create (in|out)streams and store in map for easy access
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			outStreams.put(s, out);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			inStreams.put(s, in);
			// start thread that waits for input from this client
			new Thread(new ClientsRead(s)).start();
		} catch (IOException e) {
		}
	}

	private void removeClient(Socket s) {
		clientSockets.remove(s);// keep record
		try {// remove and close I/O streams
			outStreams.remove(s).close();
			inStreams.remove(s).close();
		} catch (IOException e) {
		}
	}

	private class ClientsRead implements Runnable {

		BufferedReader in;
		Socket s;

		public ClientsRead(Socket s) {
			this.s = s;
			in = inStreams.get(s);
		}

		@Override
		public void run() {
			String line = "";
			try {
				while ((line = in.readLine()) != null) {// wait for line of text
					System.out.println("recieved" + line);
					broadcast(line, s); // broadcast to all other clients
				}
				// client closed, remove client
				removeClient(s);
				System.out.println("Stop");
			} catch (IOException e) {
			}
		}
	}

	private void broadcast(String line, Socket s2) {
		for (Socket s : outStreams.keySet()) {
			if (!s.equals(s2))
				if (outStreams.get(s) != null)
					outStreams.get(s).println(line);
		}
	}

}