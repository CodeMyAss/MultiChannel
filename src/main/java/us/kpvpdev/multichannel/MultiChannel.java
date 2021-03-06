package us.kpvpdev.multichannel;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import us.kpvpdev.multichannel.commands.Channel;
import us.kpvpdev.multichannel.commands.Chat;
import us.kpvpdev.multichannel.config.*;
import us.kpvpdev.multichannel.listeners.PlayerListener;
import us.kpvpdev.multichannel.objects.ChatChannel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * MultiChannel
 * @author iCaake
 * @author aerouk
 * @version 0.2.1
 */
public class MultiChannel extends JavaPlugin {

    public static HashMap<String, ChatChannel> channels = new HashMap<String, ChatChannel>();
    public static HashMap<String, ChatChannel> playerChannels = new HashMap<String, ChatChannel>();
    public static ArrayList<String> chatting = new ArrayList<String>();
    public static ArrayList<String> spying = new ArrayList<String>();

    @Override
    public void onEnable() {
        multichannel = this;

        MultiChannel.getInstance().saveResource("settings.yml", false);
        MultiChannel.getInstance().saveResource("lang.yml", false);

        Config.reloadConfig();
        Lang.reloadConfig();
        Settings.loadConfig();

        getCommand("channel").setExecutor(new Channel());
        getCommand("chat").setExecutor(new Chat());
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        multichannel = null;

        channels.clear();
        channels = null;

        playerChannels.clear();
        playerChannels = null;

        chatting.clear();
        chatting = null;

        spying.clear();
        spying = null;
    }

    private static MultiChannel multichannel;

    public static MultiChannel getInstance() {
        return multichannel;
    }
}