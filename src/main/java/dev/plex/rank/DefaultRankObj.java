package dev.plex.rank;

import com.google.common.collect.Lists;
import dev.plex.rank.enums.Rank;
import java.util.List;
import lombok.Getter;

@Getter
public class DefaultRankObj
{
    private final String prefix;
    private final String loginMSG;
    private final List<String> permissions;

    public DefaultRankObj(Rank rank)
    {
        this.prefix = rank.getPrefix();
        this.loginMSG = rank.getLoginMSG();
        this.permissions = Lists.newArrayList();
        permissions.add("example.permission");
    }
}
