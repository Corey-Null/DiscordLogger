package me.nullbyte.selfbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import me.nullbyte.selfbot.log.NLogger;

public class Config {

	private static File info;
	private static List<String> infoText;

	static {
		try {
			File folder = new File(Config.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
			info = new File(folder, "info.txt");
			infoText = new ArrayList<>();
			if (!info.exists()) {
				NLogger.error("Could not find `info.txt`");
				System.exit(1);
			} else {
				BufferedReader reader = new BufferedReader(new FileReader(info));
				String line = null;
				while ((line = reader.readLine()) != null) {
					infoText.add(line);
				}
				reader.close();
			}
		} catch (IOException | URISyntaxException ex) {
			NLogger.error("Failed to load Configuration.");
			System.exit(1);
		}
	}

	public static String getInfo(int index) {
		if (index >= infoText.size() || index < 0) {
			return null;
		}
		return infoText.get(index);
	}

}
