package me.nullbyte.selfbot.log;

import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NLogger {

	private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd -> hh:mm:ss");
	private final static File dataFolder;

	static {
		File folder = null;
		try {
			folder = new File(NLogger.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
		} catch (URISyntaxException e) {
			error("Failed to bind to a data folder.");
			System.exit(1);
		}
		dataFolder = new File(folder, "logs");
		dataFolder.mkdirs();
	}

	public static void log(final String message, final String sender, final String ID, final long mID, final String target) {
		print("[%s] %s -> %s: %s", "MSG", sender, target, message);
		new Task(new LogTaskRunnable(sender, message, ID).withFolder(dataFolder).withMID(mID)).start();
	}

	public static void logUpdate(final String message, final String sender, final String ID, final long mID, final String target) {
		print("[%s] %s -> %s: %s", "EDIT", sender, target, message);
		new Task(new LogTaskRunnable(sender, message, ID).withType("EDIT").withFolder(dataFolder).withMID(mID)).start();
	}

	public static void debug(String message) {
		print("[%s] " + message, "DEBUG");
	}

	public static void info(String message) {
		print("[%s] " + message, "INFO");
	}

	public static void warn(String message) {
		print("[%s] " + message, "WARNING");
	}

	public static void error(String message) {
		print("[%s] " + message, "ERROR");
	}

	private final static void print(String str, Object... args) {
		str = String.format(str, args);
		str = '[' + dateFormat.format(new Date()) + ']' + ' ' + str;
		System.out.println(str);
	}

}
