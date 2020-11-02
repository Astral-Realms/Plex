package me.totalfreedom.plex.command.impl;

import com.google.common.collect.ImmutableList;
import me.totalfreedom.plex.command.annotations.CommandParameters;
import me.totalfreedom.plex.command.annotations.CommandPermissions;
import me.totalfreedom.plex.command.PlexCommand;
import me.totalfreedom.plex.command.source.RequiredCommandSource;
import me.totalfreedom.plex.rank.enums.Rank;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

import static me.totalfreedom.plex.util.PlexUtils.tl;

@CommandPermissions(level = Rank.OP, source = RequiredCommandSource.ANY)
@CommandParameters(aliases = "tst,tast", description = "HELLO")
public class TestCMD extends PlexCommand
{
    public TestCMD() {
        super("test");
    }

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        sender.sendMessage(tl("variableTest", sender.getName()));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        if (args.length == 1)
        {
            return Arrays.asList("WHATTHEFAWK", "LUL");
        }
        return ImmutableList.of();
    }

}
