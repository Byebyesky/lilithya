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

import java.util.Arrays;

import javax.security.auth.login.LoginException;

public class Lilithya
{
    Tools tool = new Tools();
    MyListener listener = new MyListener();
    Action actions = new Action(listener);
    GUI graphicalInterface = new GUI(actions);

    public void initialize(String[] args) {
        System.out.println("Startup date: " + tool.getCurrentTime());
        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-log")) {
                listener.logToggle();
            }
        }
        System.out.println("System Initialized!");
    }

public static void main(String[] arguments) throws Exception {
        try
        {
            Lilithya lilly = new Lilithya();
            
            JDA jda = new JDABuilder(AccountType.BOT)
                        .setToken("NDY2MjM1NTAxMjY4MDQxNzg4.DiZqSQ.tDz6ZtNkn84uuoswfvCu8sTsr7o")  //The token of the account that is logging in.
                        .addEventListener(lilly.listener)  //An instance of a class that will handle events.
                        .buildBlocking();  //There are 2 ways to login, blocking vs async. Blocking guarantees that JDA will be completely loaded.
            lilly.initialize(arguments);
            lilly.graphicalInterface.createGuiElements();

            Runnable r1 = new Cli(lilly.actions);
            Thread t1 = new Thread(r1);
            t1.start();

        }
        catch (LoginException e)
        {
                e.printStackTrace();
        }
        catch (InterruptedException e)
        {
                e.printStackTrace();
        }
    }
}
