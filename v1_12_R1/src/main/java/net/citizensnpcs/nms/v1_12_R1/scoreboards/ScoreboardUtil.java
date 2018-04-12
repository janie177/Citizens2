package net.citizensnpcs.nms.v1_12_R1.scoreboards;

import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.nms.v1_12_R1.entity.EntityHumanNPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.UUID;

public class ScoreboardUtil {

	public static void addEntryToPlayer(Player player, String prefix, String suffix, UUID uuid, EntityHumanNPC handle) {
		Scoreboard scoreboard = player.getScoreboard();
		if(scoreboard == null) {
			scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		}

		String teamName = "zzzzz" + uuid.toString().substring(0, 16);

		Team team = scoreboard.getTeam(teamName);
		if (team == null) {
			team = scoreboard.registerNewTeam(teamName);
			if (prefix != null) {
				team.setPrefix(prefix);
			}
			if (suffix != null) {
				team.setSuffix(suffix);
			}
		}

		team.addPlayer(handle.getBukkitEntity());

		handle.getNPC().data().set(NPC.SCOREBOARD_FAKE_TEAM_NAME_METADATA, teamName);
	}

	//Add an entry to all players.
	public static void addToAllPlayers(final String prefix, final String suffix, UUID uuid, EntityHumanNPC handle) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			addEntryToPlayer(p, prefix, suffix, uuid, handle);
		}
	}
}
