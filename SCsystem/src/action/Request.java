package action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Request {
	protected Socket socket;
	protected InputStream in;
	protected InputStreamReader inreader;
	protected BufferedReader br;
	protected OutputStream out;
	protected OutputStreamWriter outwriter;
	protected BufferedWriter bw;

	public Request(String host, int port) {
		try {
			socket = new Socket(host, port);
			in = socket.getInputStream();
			inreader = new InputStreamReader(in);
			br = new BufferedReader(inreader);

			out = socket.getOutputStream();
			outwriter = new OutputStreamWriter(out);
			bw = new BufferedWriter(outwriter);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
