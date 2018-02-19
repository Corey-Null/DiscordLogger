package me.nullbyte.selfbot;

import javax.security.auth.login.LoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.nullbyte.selfbot.listener.PrivateMessageListener;
import me.nullbyte.selfbot.log.NLogger;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;

public class JSelfBot {

	public final static Logger logger = LoggerFactory.getLogger("JSelfBot");

	public static void main(String[] args) {
		String token = Config.getInfo(0);
		boolean keepPrivate = false;
		try {
			keepPrivate = Boolean.parseBoolean(Config.getInfo(1));
		} catch (IllegalArgumentException | NullPointerException ex) {
			NLogger.warn("Private status not set, assuming false.");
		}
		String usable = null;
		try {
			usable = String.valueOf(token);
			if (keepPrivate) {
				usable = "***";
			}
			if (token == null) {
				NLogger.error("Token cannot be null.");
				System.exit(1);
				return;
			}
			NLogger.info("Logging into Discord with token: " + usable);
			new JDABuilder(AccountType.CLIENT).setToken(token).addEventListener(new PrivateMessageListener()).buildAsync();
			NLogger.info("Successfully logged into Discord.");
			NLogger.info("<------------------------------->");
		} catch (LoginException ex) {
			NLogger.error("Using Token: " + String.valueOf(usable));
			NLogger.error("Failed to login to client.");
			System.exit(1);
		}
	}

}
