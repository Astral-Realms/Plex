package me.totalfreedom.plex;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import me.totalfreedom.plex.cache.MongoPlayerData;
import me.totalfreedom.plex.cache.SQLPlayerData;
import me.totalfreedom.plex.config.MainConfig;
import me.totalfreedom.plex.listeners.PlayerListener;
import me.totalfreedom.plex.rank.RankManager;
import me.totalfreedom.plex.storage.MongoConnection;
import me.totalfreedom.plex.storage.RedisConnection;
import me.totalfreedom.plex.storage.SQLConnection;
import me.totalfreedom.plex.storage.StorageType;
import me.totalfreedom.plex.util.PlexUtils;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class Plex extends JavaPlugin
{
    @Setter(AccessLevel.NONE)
    private static Plex plugin;

    public MainConfig config;

    private StorageType storageType = StorageType.SQLITE;

    private SQLConnection sqlConnection;
    private MongoConnection mongoConnection;
    private RedisConnection redisConnection;

    private MongoPlayerData mongoPlayerData;
    private SQLPlayerData sqlPlayerData;

    private RankManager rankManager;

    public static Plex get()
    {
        return plugin;
    }

    @Override
    public void onLoad()
    {
        plugin = this;

        config = new MainConfig(this, "config.yml");

        saveResource("database.db", false);

        sqlConnection = new SQLConnection();
        mongoConnection = new MongoConnection();
        redisConnection = new RedisConnection();
        /*try {
            redisConnection.openPool();
            PlexLog.log("Successfully opened redis pool. Closing.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisConnection.getJedis().close();*/
    }

    @Override
    public void onEnable()
    {
        config.load();

        PlexUtils.testConnections();

        if (storageType == StorageType.MONGO)
        {
            mongoPlayerData = new MongoPlayerData();
        }
        else
        {
            sqlPlayerData = new SQLPlayerData();
        }

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        rankManager = new RankManager();
        rankManager.generateDefaultRanks();

    }

    @Override
    public void onDisable()
    {
        config.save();
        /*if (redisConnection.getJedis().isConnected())
        {
            PlexLog.log("Disabling Redis/Jedis. No memory leaks in this Anarchy server !");
            redisConnection.getJedis().close();
        }*/
    }
}