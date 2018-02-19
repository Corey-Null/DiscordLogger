package me.nullbyte.selfbot.listener;

import me.nullbyte.selfbot.log.NLogger;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.MessageUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class PrivateMessageListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getMessage().getChannel() instanceof PrivateChannel) {
			String message = event.getMessage().getContentRaw().replace("\n", "\n ->");
			User target = event.getPrivateChannel().getUser();
			if (event.getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
				NLogger.log(message, event.getAuthor().getName(), target.getId(), event.getMessageIdLong(), target.getName());
				if (message.startsWith("$")) {
					switch (message.split(" ")[0].toLowerCase().substring(1)) {
					case "id": {
						event.getChannel().editMessageById(event.getMessageIdLong(), event.getPrivateChannel().getUser().getId()).queue();
						break;
					}
					}
				}
			} else {
				NLogger.log(message, event.getAuthor().getName(), target.getId(), event.getMessageIdLong(), "Me");
			}
		}
	}

	@Override
	public void onMessageUpdate(MessageUpdateEvent event) {
		if (event.getMessage().getChannel() instanceof PrivateChannel) {
			String message = event.getMessage().getContentRaw().replace("\n", "\n ->");
			User target = event.getPrivateChannel().getUser();
			if (event.getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
				NLogger.logUpdate(message, event.getAuthor().getName(), target.getId(), event.getMessageIdLong(), target.getName());
			} else {
				NLogger.logUpdate(message, event.getAuthor().getName(), target.getId(), event.getMessageIdLong(), "Me");
			}
		}
	}

}
