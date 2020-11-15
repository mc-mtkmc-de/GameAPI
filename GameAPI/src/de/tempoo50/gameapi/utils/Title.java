package de.tempoo50.gameapi.utils;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_12_R1.PlayerConnection;

public class Title {
	
	public static void newTitle(Player player, String Title, Integer In, Integer Stay, Integer Out) {
		
		PlayerConnection pc = ((CraftPlayer)player).getHandle().playerConnection;
		
		PacketPlayOutTitle tp = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, In, Stay.intValue(), Out.intValue());
		pc.sendPacket(tp);
		
		IChatBaseComponent titelMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + Title + "\"}");
		PacketPlayOutTitle ppot = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titelMain);
		pc.sendPacket(ppot);
		
	}
	
	public static void newTitle(Player player, String Title, String SubTitle, Integer In, Integer Stay, Integer Out) {
		
		PlayerConnection pc = ((CraftPlayer)player).getHandle().playerConnection;
		
		PacketPlayOutTitle tp = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, In, Stay.intValue(), Out.intValue());
		pc.sendPacket(tp);
		
		IChatBaseComponent tileSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + SubTitle + "\"}");
		PacketPlayOutTitle ppsot = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, tileSub);
		pc.sendPacket(ppsot);
		
		IChatBaseComponent titelMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + Title + "\"}");
		PacketPlayOutTitle ppot = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titelMain);
		pc.sendPacket(ppot);
		
	}

}
