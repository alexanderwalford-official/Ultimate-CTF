package renovate.plugins.ctf;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.md_5.bungee.api.ChatColor;

import java.io.*;


public class Main extends JavaPlugin {
	
	public static final String TEXT_RESET = "\u001B[0m";
	public static final String TEXT_BLACK = "\u001B[30m";
	public static final String TEXT_RED = "\u001B[31m";
	public static final String TEXT_GREEN = "\u001B[32m";
	public static final String TEXT_YELLOW = "\u001B[33m";
	public static final String TEXT_BLUE = "\u001B[34m";
	public static final String TEXT_PURPLE = "\u001B[35m";
	public static final String TEXT_CYAN = "\u001B[36m";
	public static final String TEXT_WHITE = "\u001B[37m";
	FileConfiguration config = getConfig();
	PluginDescriptionFile pdf = this.getDescription();	
	
	// Add team selection with armour stands or signs?
	// Handle right click event
	// Add blue and red teams to score board
	
	
	@Override
	public void onEnable() {	
		System.out.print(TEXT_RED + "\n[CTF] " + TEXT_RESET + "Ultimate Capture The Flag by Renovate Software started! \n");	
		System.out.print(TEXT_RED + "\n[CTF] " + TEXT_RESET + "Settings can be defined via commands or the config.yml file. \n");
		
		// Add kit selection with armour stands?
		// Spawn armour stands at states locations
		// Handle on right click event
		
	}
	
	@Override
	public void onDisable() {
		System.out.print(TEXT_RED + "\n[CTF] " + TEXT_RESET + "Ultimate CTF plugin deactivated.");	
	}
	
	
	void checklobby(CommandSender sender) {
		Player player = (Player) sender;
		Bukkit.getScheduler().runTaskAsynchronously(this, new Runnable() {
	        @Override
	        public void run() {
	        	// Check for minimum of 2 players.
	    		// Implement a level system?
	    		// Some methods may need to be looped for specific asynchronous checks?
	    		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
	        	if (board.getEntries().size() > 2) {
	    			// More than 2 players have joined, start the count down!
	    			sender.sendMessage("§4§l[CTF]§r Minimum amount of players detected! Starting count down..");
	    			
	    			try
	    			{
	    			    Thread.sleep(1000);
	    			    sender.sendMessage("§4§l[CTF]§r Starting game in 60 seconds..");
	    			    Thread.sleep(30000);
	    			    sender.sendMessage("§4§l[CTF]§r Starting game in 30 seconds..");
	    			    Thread.sleep(15000);
	    			    sender.sendMessage("§4§l[CTF]§r Starting game in 15 seconds..");
	    			    Thread.sleep(5000);
	    			    sender.sendMessage("§4§l[CTF]§r Starting game in 10 seconds..");
	    			    // String title, String subtitle, int fadeIn, int stay, int fadeOut.
	    			    player.sendTitle(ChatColor.AQUA + "10", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.sendTitle(ChatColor.AQUA + "9", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.sendTitle(ChatColor.AQUA + "8", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.sendTitle(ChatColor.AQUA + "7", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.sendTitle(ChatColor.AQUA + "6", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.sendTitle(ChatColor.AQUA + "5", "Seconds", 1, 10, 1);
	    			    sender.sendMessage("§4§l[CTF]§r Starting game in 5 seconds..");
	    			    Thread.sleep(1000);
	    			    player.sendTitle(ChatColor.AQUA + "4", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.sendTitle(ChatColor.AQUA + "3", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.sendTitle(ChatColor.AQUA + "2", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.sendTitle(ChatColor.AQUA + "1", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.sendTitle(ChatColor.GREEN + "Go!", "Seconds", 1, 10, 1);
	    			    sender.sendMessage("§4§l[CTF]§r The game has started!");
	    			}
	    			catch(InterruptedException ex)
	    			{
	    			    Thread.currentThread().interrupt();
	    			}
	    			
	    		}
	        }
	    });
	}
	
	
	void startgame() {
		// Here we will teleport the players to the correct world, then teleport them to the correct spawn
		// based on their team. A minimum of 2 players is required to play.
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (args.length == 0) {
			// Just the basic ctf version command
			sender.sendMessage("§4§l[CTF]§r You are running version §c" + pdf.getVersion() + "§f of Ultimate CTF.");
			return true;
		}

		if (args.length >= 1) {
		    // Some arguments were provided
			
			if (args[0].equalsIgnoreCase("team")) {
				if (args.length >= 2 && args[1].equalsIgnoreCase("red")) {
					// Join the red team
					
				}
				if (args.length >= 2 && args[1].equalsIgnoreCase("blue")) {
					// Join the blue team
					
				}
			}
			
			if (args[0].equalsIgnoreCase("spectate")) {
				// ctf spectate
				Player player = (Player) sender;
		        try {	        
		        sender.sendMessage("§4§l[CTF]§r You are now spectating the game. Type §c/ctf leave§f to exit.");
		        String stringworld  = config.getString("# world: ");  
		        var Coordinates = config.getString("# spectate: ");
		        
		        World world = getServer().getWorld(stringworld);
		        
		        String[] array = Coordinates.split(", ", -1);
		        
		        
		        double x = Double.parseDouble(array[0]);
		        double y = Double.parseDouble(array[1]);
		        double z = Double.parseDouble(array[2]);
		        
		        // teleport the player to the spectator spawn in the correct world
		        Location location = new Location(world, x, y, z);
		        player.teleport(location);
		        
		        // Set the player's game mode
		        player.setGameMode(GameMode.SPECTATOR);	        
		        
		        }
		        catch (Exception e) {
					sender.sendMessage("§7ERROR: Failed to teleport the player. Have you set the spawn point yet?");
					sender.sendMessage("§7ERROR: " + e);
		        }
			}
			
			
			
			if (args[0].equalsIgnoreCase("join")) {
		        // The first argument is "help", therefore "/ctf join"
				sender.sendMessage("§7Attempting to join CTF..");
				
				// Add players to relevant teams on score board.
				Player player = (Player) sender;
				
				Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
				Objective obj = board.registerNewObjective("ServerName", "dummy", ChatColor.RED + "Capture The Flag");
				obj.setDisplaySlot(DisplaySlot.SIDEBAR);
				Score onlineName = obj.getScore(ChatColor.GRAY + "Waiting for more players..");
				onlineName.setScore(15);
				player.setScoreboard(board);
				
				Team onlineCounter = board.registerNewTeam("onlineCounter");
				
				obj.getScore(ChatColor.GREEN + Integer.toString(board.getEntries().size()) + ChatColor.WHITE + "/20").setScore(14);		
		        
		        try {	        
		        	
		        String stringworld  = config.getString("# world: ");  
		        var Coordinates = config.getString("# lobby: ");
		        
		        World world = getServer().getWorld(stringworld);
		        
		        String[] array = Coordinates.split(", ", -1);
		        
		        // teleport the player to the lobby in the correct world
		        double x = Double.parseDouble(array[0]);
		        double y = Double.parseDouble(array[1]);
		        double z = Double.parseDouble(array[2]);
		        
		        Location location = new Location(world, x, y, z);
		        player.teleport(location);
		        
		        // Set the player's game mode
		        player.setGameMode(GameMode.SURVIVAL);
		        
		        // Set the difficulty
		        // Add preference?
		        Bukkit.getWorld(stringworld).setDifficulty(Difficulty.NORMAL);
		        
		        // Set the time
		        // Add preference?
		        Bukkit.getWorld(stringworld).setTime(6000);
		        
		        }
		        catch (Exception e) {
					sender.sendMessage("§7ERROR: Failed to teleport the player. Have you set the spawn point yet?");
					sender.sendMessage("§7ERROR: " + e);
		        }
		        
		        sender.sendMessage("§4§l[CTF]§r Joined the game.");
		        player.playSound(player.getLocation(), "block.note_block.guitar", 3.0F, 0.5F);
		        
				System.out.print(TEXT_RED + "\n[CTF] " + TEXT_RESET + "Lobby checkng started.. \n");	
				checklobby(sender);
		        
				return true;
		    }
			
			if (args[0].equalsIgnoreCase("leave")) {
				// leave the game
				// remove player from score board
				Player player = (Player) sender;
				player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
				player.playSound(player.getLocation(), "entity.wolf.growl", 3.0F, 0.5F);
				sender.sendMessage("§4§l[CTF]§r You left the game. Returning to spawn..");
				
		        try {	        
		        	
		        String stringworld  = config.getString("# servworld: ");  
		        var Coordinates = config.getString("# servspawn: ");
		        
		        World world = getServer().getWorld(stringworld);
		        
		        String[] array = Coordinates.split(", ", -1);
		        
		        // teleport the player to the lobby in the correct world
		        double x = Double.parseDouble(array[0]);
		        double y = Double.parseDouble(array[1]);
		        double z = Double.parseDouble(array[2]);
		        
		        Location location = new Location(world, x, y, z);
		        player.teleport(location);
		        player.setGameMode(GameMode.SURVIVAL);
		        
		        }
		        catch (Exception e) {
					sender.sendMessage("§7ERROR: Failed to teleport the player. Have you set the spawn point yet?");
					sender.sendMessage("§7ERROR: " + e);
		        }
				
				
			}
			
		    if (args[0].equalsIgnoreCase("help")) {
		        // The first argument is "help", therefore "/ctf help"
		    	Player player = (Player) sender;
				sender.sendMessage("§4§l - Ultimate CTF by Renovate Software - §f");
				if (player.isOp()) {
				sender.sendMessage("/ctf §7# get the plugin version");
				sender.sendMessage("/ctf set bluespawn §7# set the blue team's spawn");
				sender.sendMessage("/ctf set redspawn §7# set the red team's spawn");
				sender.sendMessage("/ctf set flag<num> §7# set a flag (1-4)");
				sender.sendMessage("/ctf set world §7# set the world");
				sender.sendMessage("/ctf set lobby §7# set the lobby");
				sender.sendMessage("/ctf set spectate §7# set the spectator's spawn");
				sender.sendMessage("/ctf set serverspawn §7# set the server's spawn");
				}
				else {
					sender.sendMessage("&7You are not a server operator so some commands are hidden.");
				}
				sender.sendMessage("/ctf help §7# this command");
				sender.sendMessage("/ctf join §7# Join a CTF game");
				sender.sendMessage("/ctf leave §7# Leave CTF game and return to spawn");
				sender.sendMessage("/ctf team <colour> §7# Join a specific team");
				return true;
		    }
		    
		    if (args[0].equalsIgnoreCase("set")) {
		        // The first argument is "ctf", therefore "/ctf set"
		    	sender.sendMessage("[CTF] Usage: /ctf set <param>");
		    	
		    	if (args.length >= 2 && args[1].equalsIgnoreCase("world")) {
		    		Player player = (Player) sender;
		    		var world = player.getWorld().getName();
		    		sender.sendMessage("§4§l[CTF]§r You set the world as: " + world);
		    		
		    		config.addDefault("# world: ", world);
		            config.options().copyDefaults(true);
		            saveConfig();
		    		
		    	}		    	
		    	
		    	if (args.length >= 2 && args[1].equalsIgnoreCase("lobby")) {
		    		// ctf set lobby
					try {
					Player player = (Player) sender;
					Location loc = player.getLocation();
					int x = loc.getBlockX();
					int y = loc.getBlockY();
					int z = loc.getBlockZ();
					
					sender.sendMessage("§4§l[CTF]§r You set the lobby as: X:" + x + " Y:" + y + " Z:" + z + " !");
					
					player.playSound(player.getLocation(), "entity.villager.yes", 3.0F, 0.5F);
					
					// We then need to save this to the config.yml file!
					
					System.out.print("\n:: lobby: " + x + " " + y + "" + z);
					var corindates = x + ", " + y + ", " + z;
					
		    		config.addDefault("# lobby: ", corindates);
		            config.options().copyDefaults(true);
		            saveConfig();	
					
					}
					catch (Exception e) {
						Player player = (Player) sender;
						player.playSound(player.getLocation(), "entity.wolf.growl", 3.0F, 0.5F);
						sender.sendMessage("[CTF] Something went wrong. Check the console for errors..");
						System.out.print(e);
					}
					return true;  		
		    	}
		    	
		    	if (args.length >= 2 && args[1].equalsIgnoreCase("serverspawn")) {
		    		// ctf set server spawn
					try {
					Player player = (Player) sender;
					Location loc = player.getLocation();
					int x = loc.getBlockX();
					int y = loc.getBlockY();
					int z = loc.getBlockZ();
					
					sender.sendMessage("§4§l[CTF]§r You set the server spawn as: X:" + x + " Y:" + y + " Z:" + z + " !");
					
					player.playSound(player.getLocation(), "entity.villager.yes", 3.0F, 0.5F);
					
					// We then need to save this to the config.yml file!
					
					System.out.print("\n:: servspawn: " + x + " " + y + "" + z);
					var corindates = x + ", " + y + ", " + z;
					var serverworld = player.getWorld().getName();
					
		    		config.addDefault("# servspawn: ", corindates);
		            config.options().copyDefaults(true);
		            saveConfig();	
		            
		            config.addDefault("# servworld: ", serverworld);
		            config.options().copyDefaults(true);
		            saveConfig();	
					
					}
					catch (Exception e) {
						Player player = (Player) sender;
						player.playSound(player.getLocation(), "entity.wolf.growl", 3.0F, 0.5F);
						sender.sendMessage("[CTF] Something went wrong. Check the console for errors..");
						System.out.print(e);
					}
					return true;  		
		    	}
		    	
		    	if (args.length >= 2 && args[1].equalsIgnoreCase("spectate")) {
		    		// ctf set lobby
					try {
					Player player = (Player) sender;
					Location loc = player.getLocation();
					int x = loc.getBlockX();
					int y = loc.getBlockY();
					int z = loc.getBlockZ();
					
					sender.sendMessage("§4§l[CTF]§r You set the spectate spawn as: X:" + x + " Y:" + y + " Z:" + z + " !");
					
					player.playSound(player.getLocation(), "entity.villager.yes", 3.0F, 0.5F);
					
					// We then need to save this to the config.yml file!
					
					System.out.print("\n:: spectate: " + x + " " + y + "" + z);
					var corindates = x + ", " + y + ", " + z;
					
		    		config.addDefault("# spectate: ", corindates);
		            config.options().copyDefaults(true);
		            saveConfig();	
					
					}
					catch (Exception e) {
						Player player = (Player) sender;
						player.playSound(player.getLocation(), "entity.wolf.growl", 3.0F, 0.5F);
						sender.sendMessage("[CTF] Something went wrong. Check the console for errors..");
						System.out.print(e);
					}
					return true;  		
		    	}
		    	
		    	
		    	if (args.length >= 2 && args[1].equalsIgnoreCase("flag1")) {
		    		// ctf set flag 1
					try {
					Player player = (Player) sender;
					Location loc = player.getLocation();
					int x = loc.getBlockX();
					int y = loc.getBlockY();
					int z = loc.getBlockZ();
					
					sender.sendMessage("§4§l[CTF]§r You set flag 1 as: X:" + x + " Y:" + y + " Z:" + z + " !");
					
					player.playSound(player.getLocation(), "entity.villager.yes", 3.0F, 0.5F);
					
					// We then need to save this to the config.yml file!
					
					System.out.print("\n:: flag1: " + x + " " + y + "" + z);
					var corindates = x + ", " + y + ", " + z;
					
		    		config.addDefault("# flag1: ", corindates);
		            config.options().copyDefaults(true);
		            saveConfig();	
					
					}
					catch (Exception e) {
						Player player = (Player) sender;
						player.playSound(player.getLocation(), "entity.wolf.growl", 3.0F, 0.5F);
						sender.sendMessage("[CTF] Something went wrong. Check the console for errors..");
						System.out.print(e);
					}
					return true;  		
		    	}
		    	
		    	if (args.length >= 2 && args[1].equalsIgnoreCase("flag2")) {
		    		// ctf set flag 2
					try {
					Player player = (Player) sender;
					Location loc = player.getLocation();
					int x = loc.getBlockX();
					int y = loc.getBlockY();
					int z = loc.getBlockZ();
					
					sender.sendMessage("§4§l[CTF]§r You set flag 2 as: X:" + x + " Y:" + y + " Z:" + z + " !");
					
					player.playSound(player.getLocation(), "entity.villager.yes", 3.0F, 0.5F);
					
					// We then need to save this to the config.yml file!
					
					System.out.print("\n:: flag2: " + x + " " + y + "" + z);
					var corindates = x + ", " + y + ", " + z;
					
		    		config.addDefault("# flag2: ", corindates);
		            config.options().copyDefaults(true);
		            saveConfig();	
					
					}
					catch (Exception e) {
						Player player = (Player) sender;
						player.playSound(player.getLocation(), "entity.wolf.growl", 3.0F, 0.5F);
						sender.sendMessage("[CTF] Something went wrong. Check the console for errors..");
						System.out.print(e);
					}
					return true;  	
		    	}
		    	
		    	if (args.length >= 2 && args[1].equalsIgnoreCase("flag3")) {
		    		// ctf set flag 3
					try {
					Player player = (Player) sender;
					Location loc = player.getLocation();
					int x = loc.getBlockX();
					int y = loc.getBlockY();
					int z = loc.getBlockZ();
					
					sender.sendMessage("§4§l[CTF]§r You set flag 3 as: X:" + x + " Y:" + y + " Z:" + z + " !");
					
					player.playSound(player.getLocation(), "entity.villager.yes", 3.0F, 0.5F);
					
					// We then need to save this to the config.yml file!
					
					System.out.print("\n:: flag3: " + x + " " + y + "" + z);
					var corindates = x + ", " + y + ", " + z;
					
		    		config.addDefault("# flag3: ", corindates);
		            config.options().copyDefaults(true);
		            saveConfig();	
					
					}
					catch (Exception e) {
						Player player = (Player) sender;
						player.playSound(player.getLocation(), "entity.wolf.growl", 3.0F, 0.5F);
						sender.sendMessage("[CTF] Something went wrong. Check the console for errors..");
						System.out.print(e);
					}
					return true;  	
		    	}
		    	
		    	if (args.length >= 2 && args[1].equalsIgnoreCase("flag4")) {
		    		// ctf set flag 4
					try {
					Player player = (Player) sender;
					Location loc = player.getLocation();
					int x = loc.getBlockX();
					int y = loc.getBlockY();
					int z = loc.getBlockZ();
					
					sender.sendMessage("§4§l[CTF]§r You set flag 4 as: X:" + x + " Y:" + y + " Z:" + z + " !");
					
					player.playSound(player.getLocation(), "entity.villager.yes", 3.0F, 0.5F);
					
					// We then need to save this to the config.yml file!
					
					System.out.print("\n:: flag4: " + x + " " + y + "" + z);
					var corindates = x + ", " + y + ", " + z;
					
		    		config.addDefault("# flag4: ", corindates);
		            config.options().copyDefaults(true);
		            saveConfig();	
					
					}
					catch (Exception e) {
						Player player = (Player) sender;
						player.playSound(player.getLocation(), "entity.wolf.growl", 3.0F, 0.5F);
						sender.sendMessage("[CTF] Something went wrong. Check the console for errors..");
						System.out.print(e);
					}
					return true;  	
		    	}
		    		    	
				if (args.length >= 2 && args[1].equalsIgnoreCase("bluespawn")) {
					// ctf set bluespawn
					try {
					Player player = (Player) sender;
					Location loc = player.getLocation();
					int x = loc.getBlockX();
					int y = loc.getBlockY();
					int z = loc.getBlockZ();
					
					sender.sendMessage("§4§l[CTF]§r You set the §9blue team's spawn§r to X:" + x + " Y:" + y + " Z:" + z + " !");
					
					player.playSound(player.getLocation(), "entity.villager.yes", 3.0F, 0.5F);
					
					// We then need to save this to the config.yml file!
					
					System.out.print("\n:: blueplayerspawn: " + x + " " + y + "" + z);
					var corindates = x + ", " + y + ", " + z;
					
		    		config.addDefault("# blueplayerspawn: ", corindates);
		            config.options().copyDefaults(true);
		            saveConfig();	
					
					}
					catch (Exception e) {
						Player player = (Player) sender;
						player.playSound(player.getLocation(), "entity.wolf.growl", 3.0F, 0.5F);
						sender.sendMessage("[CTF] Something went wrong. Check the console for errors..");
						System.out.print(e);
					}
					return true;
		        }
				
				if (args.length >= 2 && args[1].equalsIgnoreCase("redspawn")) {
					// ctf set redspawn
					try {	
					Player player = (Player) sender;
					Location loc = player.getLocation();
					int x = loc.getBlockX();
					int y = loc.getBlockY();
					int z = loc.getBlockZ();
					
					sender.sendMessage("§4§l[CTF]§r You set the §4red team's spawn§r to X:" + x + " Y:" + y + " Z:" + z + " !");
					
					player.playSound(player.getLocation(), "entity.villager.yes", 3.0F, 0.5F);
					
					// We then need to save this to the config.yml file!
					
					System.out.print("\n:: redplayerspawn: " + x + " " + y + "" + z);
					
					var corindates = x + ", " + y + ", " + z;
					
		    		config.addDefault("# redplayerspawn: ", corindates);
		            config.options().copyDefaults(true);
		            saveConfig();	
							
					}
					catch (Exception e) {
					Player player = (Player) sender;
					player.playSound(player.getLocation(), "entity.wolf.growl", 3.0F, 0.5F);
						
					sender.sendMessage("[CTF] Something went wrong. Check the console for errors..");
					System.out.print(e);
					}
					return true;
		        }
				
				return true;
		    }
    
		}		
		
		return false;
	}
	
}
