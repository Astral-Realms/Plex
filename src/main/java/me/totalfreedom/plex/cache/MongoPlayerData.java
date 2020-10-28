package me.totalfreedom.plex.cache;

import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import java.util.UUID;
import me.totalfreedom.plex.Plex;
import me.totalfreedom.plex.player.PlexPlayer;

public class MongoPlayerData
{
    private final PlexPlayerDAO plexPlayerDAO;

    public MongoPlayerData()
    {
        this.plexPlayerDAO = new PlexPlayerDAO(PlexPlayer.class, Plex.get().getMongoConnection().getDatastore());
    }

    public boolean exists(UUID uuid)
    {

        Query<PlexPlayer> query = plexPlayerDAO.createQuery();

        return query.field("uuid").exists().field("uuid").equal(uuid.toString()).find().tryNext() != null;
    }

    public PlexPlayer getByUUID(UUID uuid)
    {

        if (PlayerCache.getPlexPlayerMap().containsKey(uuid))
        {
            return PlayerCache.getPlexPlayerMap().get(uuid);
        }
        Query<PlexPlayer> query2 = plexPlayerDAO.createQuery().field("uuid").exists().field("uuid").equal(uuid.toString());
        return query2.first();
    }

    public void update(PlexPlayer player)
    {
        Query<PlexPlayer> filter = plexPlayerDAO.createQuery()
                .field("uuid").equal(player.getUuid());

        UpdateOperations<PlexPlayer> updateOps = plexPlayerDAO.createUpdateOperations();

        updateOps.set("name", player.getName());
        updateOps.set("loginMSG", player.getLoginMSG());
        updateOps.set("prefix", player.getPrefix());
        updateOps.set("rank", player.getRank() == null ? "" : player.getRank().name().toLowerCase());
        updateOps.set("ips", player.getIps());
        updateOps.set("coins", player.getCoins());
        plexPlayerDAO.update(filter, updateOps);
    }

    public PlexPlayerDAO getPlexPlayerDAO()
    {
        return plexPlayerDAO;
    }
}
