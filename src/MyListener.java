import net.dv8tion.jda.client.entities.Group;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.*;

public class MyListener extends ListenerAdapter
{
    Tools tool = new Tools();
    private boolean logEnable = false;
    List<MessageChannel> discoveredChannels = new ArrayList<MessageChannel>();
    List<MessageChannel> discoveredDMs = new ArrayList<MessageChannel>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        String content = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        Guild guild = event.getGuild();
        
        if(logEnable) {
            tool.logMsg = tool.getCurrentTime() + ": " + message + " in " + event.getChannel();
            if(guild != null) {
                tool.logMsg = tool.logMsg + " on " + guild;
            }
            tool.printLog(tool.logMsg);
        }

        addChannel(channel.getType(), channel);

        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        if (content.equals("!ping"))
        {
            channel.sendMessage("Pong!").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        }
    }

    public void addChannel(ChannelType channeltype, MessageChannel channel) {
        if(channeltype.equals(ChannelType.TEXT) && !isInList(channel)) {
            discoveredChannels.add(channel);
        }
        else if(channeltype.equals(ChannelType.PRIVATE) && !isInList(channel)) {
            discoveredDMs.add(channel);
        }
    }

    public void sendMessage(String message, MessageChannel channel) {
        try {
            channel.sendMessage(message).queue();
        }
        catch(Exception e) {
            System.out.println("Something went wrong while sending: " + e);
        }
    }

    public boolean logIsEnabled() {
        return logEnable;
    }

    public void logToggle() {
        logEnable = !logEnable;
    } 

    public void listDiscoveredChannels() {
            System.out.println("Channels discovered: " + discoveredChannels);
            System.out.println("DMs discovered: " + discoveredDMs);
    }
    
    public boolean isInList(MessageChannel channel) {
        for(MessageChannel element : discoveredChannels) {
            if(element.equals(channel)) { 
                return true;
            }
        }
        return false;
    }
}
