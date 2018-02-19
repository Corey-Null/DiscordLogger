package me.nullbyte.selfbot.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogTaskRunnable implements Runnable {

	private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd -> hh:mm:ss");
	private final static String FORMAT = "[%s] [%s] [%s] %s: %s";
	private final String sender;
	private final String message;
	private final String id;
	private long mid;
	private String type;
	private File folder;

	public LogTaskRunnable(String sender, String message, String id) {
		this.sender = sender;
		this.message = message;
		this.id = id;
		this.mid = 0L;
		this.type = "MSG";
	}

	public final LogTaskRunnable withFolder(File folder) {
		this.folder = folder;
		return this;
	}

	public final LogTaskRunnable withType(String type) {
		this.type = type;
		return this;
	}

	public final LogTaskRunnable withMID(long mid) {
		this.mid = mid;
		return this;
	}

	@Override
	public void run() {
		try {
			File log = new File(folder, id + ".log");
			if (!log.exists()) {
				log.getParentFile().mkdirs();
				log.createNewFile();
			}
			PrintWriter writer = new PrintWriter(new FileWriter(log, true));
			writer.println(String.format(FORMAT, dateFormat.format(new Date()), type, mid, sender, message));
			writer.close();
		} catch (IOException ex) {
			NLogger.error("Failed to save message from " + sender + " as " + message);
		}
	}

}
