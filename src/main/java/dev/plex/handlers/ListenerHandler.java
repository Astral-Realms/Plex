package dev.plex.handlers;

import com.google.common.collect.Lists;
import dev.plex.listener.PlexListener;
import dev.plex.listener.impl.AdminListener;
import dev.plex.listener.impl.BanListener;
import dev.plex.listener.impl.ChatListener;
import dev.plex.listener.impl.CommandListener;
import dev.plex.listener.impl.FreezeListener;
import dev.plex.listener.impl.GameModeListener;
import dev.plex.listener.impl.PlayerListener;
import dev.plex.listener.impl.ServerListener;
import dev.plex.listener.impl.TabListener;
import dev.plex.listener.impl.WorldListener;
import dev.plex.util.PlexLog;
import java.util.List;

//TODO: Switch to Reflections API
public class ListenerHandler
{
    public ListenerHandler()
    {
        List<PlexListener> listeners = Lists.newArrayList();
        listeners.add(new AdminListener());
        listeners.add(new BanListener());
        listeners.add(new ChatListener());
        listeners.add(new CommandListener());
        listeners.add(new FreezeListener());
        listeners.add(new GameModeListener());
        listeners.add(new PlayerListener());
        listeners.add(new ServerListener());
        listeners.add(new TabListener());
        listeners.add(new WorldListener());
        PlexLog.log(String.format("Registered %s listeners!", listeners.size()));
    }
}
