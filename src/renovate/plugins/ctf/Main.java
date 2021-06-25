package renovate.plugins.ctf;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
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
	

	// Handle right click event on armour stands for kits
	
	
	@Override
	public void onEnable() {	
		System.out.print(TEXT_RED + "\n[CTF] " + TEXT_RESET + "Ultimate Capture The Flag by Renovate Software started!");	
		System.out.print(TEXT_RED + "\n[CTF] " + TEXT_RESET + "Settings can be defined via commands or the config.yml file.");       
	}
	
	@Override
	public void onDisable() {
		System.out.print(TEXT_RED + "\n[CTF] " + TEXT_RESET + "Ultimate CTF plugin deactivated.");	
	}
	
	@EventHandler
    public void onJoin(PlayerJoinEvent event, CommandSender sender) {
		// Executed when the player joins the server
		Player player = (Player) sender;
		if (player.isOp()) {
			// Player has full permissions due to being a server operator
			sender.sendMessage("§4§l[CTF]§r You are running version §c" + pdf.getVersion() + "§r of Ultimate Capture The Flag. §7You can see this message as you are a server operator.");
		}
	}
	
	
	int standspawned = 0;
	
	void checklobby(CommandSender sender) {
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team team = board.registerNewTeam("redteam");
		Team team2 = board.registerNewTeam("blueteam");
		Player player = (Player) sender;
		Bukkit.getScheduler().runTaskAsynchronously(this, new Runnable() {
	        @Override
	        public void run() { // Is this looped? Testing w/ 2 or more players required
	        	// Check for minimum of 2 players.
	    		// Implement a level system?
	    		// Some methods may need to be looped for specific asynchronous checks?
	        	if (board.getEntries().size() > 2) {
	    			// More than 2 players have joined, start the count down!
	    			sender.sendMessage("§4§l[CTF]§r Minimum amount of players detected! Starting count down..");
	    			
	    			// Team balancing
	    			if (team.getEntries().size() < team2.getEntries().size()) {
	    				team.addPlayer(player); // join red team		
	    				team2.removePlayer(player); // remove from other team in case of already selecting team
	    				sender.sendMessage("§4§l[CTF]§r To balance the game, you have been moved to the red team.");
	    			}
	    			
	    			if (team.getEntries().size() > team2.getEntries().size()) {
	    				team.removePlayer(player); // remove from other team in case of already selecting team
	    				team2.addPlayer(player); // join blue team		
	    				sender.sendMessage("§4§l[CTF]§r To balance the game, you have been moved to the blue team.");
	    			}
	    			
	    			// Disable friendly fire
	    			team.setAllowFriendlyFire(false);	    					
	    			
	    			try
	    			{
	    				// Add kit selection with armour stands?
	    				// Spawn armour stands at states locations
	    				// Handle on right click event /or stand on block
	    				
	    				// only spawn the stands if they haven't been spawned yet
	    				if (standspawned == 0) {
	    				//spawn new stands
	    				String stringworld  = config.getString("# world: ");  
	    		        var Coordinates1 = config.getString("# blue_kit1: ");
	    		        var Coordinates2 = config.getString("# blue_kit2: ");
	    		        var Coordinates3 = config.getString("# blue_kit3: "); 
	    		        var Coordinates4 = config.getString("# red_kit1: ");
	    		        var Coordinates5 = config.getString("# red_kit2: ");
	    		        var Coordinates6 = config.getString("# red_kit3: ");
	    		        
	    		        
	    		        String[] array1 = Coordinates1.split(", ", -1);
	    		        String[] array2 = Coordinates2.split(", ", -1);
	    		        String[] array3 = Coordinates3.split(", ", -1);
	    		        String[] array4 = Coordinates4.split(", ", -1);
	    		        String[] array5 = Coordinates5.split(", ", -1);
	    		        String[] array6 = Coordinates6.split(", ", -1);	        
	    		        
	    		        World world = getServer().getWorld(stringworld);
	    		               
	    		        
	    		        // teleport the player to the lobby in the correct world
	    		        double x1 = Double.parseDouble(array1[0]);
	    		        double y1 = Double.parseDouble(array1[1]);
	    		        double z1 = Double.parseDouble(array1[2]);
	    		        
	    		        double x2 = Double.parseDouble(array2[0]);
	    		        double y2 = Double.parseDouble(array2[1]);
	    		        double z2 = Double.parseDouble(array2[2]);
	    		        
	    		        double x3 = Double.parseDouble(array3[0]);
	    		        double y3 = Double.parseDouble(array3[1]);
	    		        double z3 = Double.parseDouble(array3[2]);
	    		        
	    		        double x4 = Double.parseDouble(array4[0]);
	    		        double y4 = Double.parseDouble(array4[1]);
	    		        double z4 = Double.parseDouble(array4[2]);
	    		        
	    		        double x5 = Double.parseDouble(array5[0]);
	    		        double y5 = Double.parseDouble(array5[1]);
	    		        double z5 = Double.parseDouble(array5[2]);
	    		        
	    		        double x6 = Double.parseDouble(array6[0]);
	    		        double y6 = Double.parseDouble(array6[1]);
	    		        double z6 = Double.parseDouble(array6[2]);
	    		        
	    		        Location location1 = new Location(world, x1, y1, z1);
	    		        Location location2 = new Location(world, x2, y2, z2);
	    		        Location location3 = new Location(world, x3, y3, z3);
	    		        Location location4 = new Location(world, x4, y4, z4);
	    		        Location location5 = new Location(world, x5, y5, z5);
	    		        Location location6 = new Location(world, x6, y6, z6);
	    				
	    		        Player player = (Player) sender;
	    		        
	    		        player.getWorld().spawn(location1, ArmorStand.class);
	    		        player.getWorld().spawn(location2, ArmorStand.class);
	    		        player.getWorld().spawn(location3, ArmorStand.class);
	    		        player.getWorld().spawn(location4, ArmorStand.class);
	    		        player.getWorld().spawn(location5, ArmorStand.class);
	    		        player.getWorld().spawn(location6, ArmorStand.class);
	    						
	    		        standspawned = 1;
	    				}
	    				
	    			    player.playSound(player.getLocation(), "block.note_block.bell", 3.0F, 0.5F);
	    			    sender.sendMessage("§4§l[CTF]§r Starting game in 60 seconds..");
	    			    Thread.sleep(30000);
	    			    player.playSound(player.getLocation(), "block.note_block.bell", 3.0F, 0.5F);
	    			    sender.sendMessage("§4§l[CTF]§r Starting game in 30 seconds..");
	    			    Thread.sleep(15000);
	    			    player.playSound(player.getLocation(), "block.note_block.bell", 3.0F, 0.5F);
	    			    sender.sendMessage("§4§l[CTF]§r Starting game in 15 seconds..");
	    			    Thread.sleep(5000);
	    			    player.playSound(player.getLocation(), "block.note_block.bell", 3.0F, 0.5F);
	    			    sender.sendMessage("§4§l[CTF]§r Starting game in 10 seconds..");
	    			    // String title, String subtitle, int fadeIn, int stay, int fadeOut.
	    			    player.sendTitle(ChatColor.AQUA + "10", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.playSound(player.getLocation(), "block.note_block.bell", 3.0F, 0.5F);
	    			    player.sendTitle(ChatColor.AQUA + "9", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.playSound(player.getLocation(), "block.note_block.bell", 3.0F, 0.5F);
	    			    player.sendTitle(ChatColor.AQUA + "8", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.playSound(player.getLocation(), "block.note_block.bell", 3.0F, 0.5F);
	    			    player.sendTitle(ChatColor.AQUA + "7", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.playSound(player.getLocation(), "block.note_block.bell", 3.0F, 0.5F);
	    			    player.sendTitle(ChatColor.AQUA + "6", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.playSound(player.getLocation(), "block.note_block.bell", 3.0F, 0.5F);
	    			    player.sendTitle(ChatColor.AQUA + "5", "Seconds", 1, 10, 1);
	    			    sender.sendMessage("§4§l[CTF]§r Starting game in 5 seconds..");
	    			    Thread.sleep(1000);
	    			    player.playSound(player.getLocation(), "block.note_block.bell", 3.0F, 0.5F);
	    			    player.sendTitle(ChatColor.AQUA + "4", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.playSound(player.getLocation(), "block.note_block.bell", 3.0F, 0.5F);
	    			    player.sendTitle(ChatColor.AQUA + "3", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.playSound(player.getLocation(), "block.note_block.bell", 3.0F, 0.5F);
	    			    player.sendTitle(ChatColor.AQUA + "2", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.playSound(player.getLocation(), "block.note_block.bell", 3.0F, 0.5F);
	    			    player.sendTitle(ChatColor.AQUA + "1", "Seconds", 1, 10, 1);
	    			    Thread.sleep(1000);
	    			    player.playSound(player.getLocation(), "block.note_block.bell", 3.0F, 0.5F);
	    			    player.sendTitle(ChatColor.GREEN + "Go!", "Seconds", 1, 10, 1);
	    			    sender.sendMessage("§4§l[CTF]§r The game has started!");
	    			    
	    			    startgame(sender);
	    			}
	    			catch(InterruptedException ex)
	    			{
	    				sender.sendMessage("§4§l[CTF]§r An error has been detected.");
	    			    Thread.currentThread().interrupt();
	    			}
	    			
	    		}
	        }
	    });
	}
	
	
	int redteampoints = 0;
	int blueteampoints = 0;
	String flag1colour = "white"; // white by default (not claimed)
	String flag2colour = "white"; 
	String flag3colour = "white";
	String flag4colour = "white"; 
	
	
	@SuppressWarnings("deprecation")
	void startgame(CommandSender sender) {
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Player player = (Player) sender;
		// Here we will teleport the players to the correct world, then teleport them to the correct spawn
		// based on their team. A minimum of 2 players is required to play.
		// Here we can also check for the player's location for if they are stood on a flag to claim it.
		player.getWorld().setGameRuleValue("keepInventory", "true");
		System.out.print(TEXT_RED + "\n[CTF] " + TEXT_RESET + "A game has started. Running required asyncronous threads to enable full game functionality. \n"); 
		
		// Remove the score board so that we can make a new one
		player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
		
	Bukkit.getScheduler().runTaskAsynchronously(this, new Runnable() {
			
	    @Override
	    public void run() {
		
		// Required Variables
		String location = player.getLocation().toString();
			
		var flag1 = config.getString("# flag1: ");
		var flag2 = config.getString("# flag2: ");
		var flag3 = config.getString("# flag3: ");
		var flag4 = config.getString("# flag4: ");
		
        String[] array1 = flag1.split(", ", -1);
        String[] array2 = flag2.split(", ", -1);
        String[] array3 = flag3.split(", ", -1);
        String[] array4 = flag4.split(", ", -1);
        String[] playerloc = location.split(", ", -1);        
        
        double x1 = Double.parseDouble(array1[0]);
        double y1 = Double.parseDouble(array1[1]);
        double z1 = Double.parseDouble(array1[2]);
        double x2 = Double.parseDouble(array2[0]);
        double y2 = Double.parseDouble(array2[1]);
        double z2 = Double.parseDouble(array2[2]);
        double x3 = Double.parseDouble(array3[0]);
        double y3 = Double.parseDouble(array3[1]);
        double z3 = Double.parseDouble(array3[2]);
        double x4 = Double.parseDouble(array4[0]);
        double y4 = Double.parseDouble(array4[1]);
        double z4 = Double.parseDouble(array4[2]);
        
        double playerlocx = Double.parseDouble(playerloc[0]);
        double playerlocy = Double.parseDouble(playerloc[1]);
        double playerlocz = Double.parseDouble(playerloc[2]);
        
        String world  = config.getString("# world: ");         
		
		
        // Add points to team if a flag belongs to them every second
        
        if (flag1colour == "redteam") {
        	// + 1 point to red team
        	try {
				Thread.sleep(1000);
				// Add point to team here
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if (flag1colour == "blueteam") {
        	// + 1 point to blue team
        	try {
				Thread.sleep(1000);
				// Add point to team here
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if (flag2colour == "redteam") {
        	// + 1 point
        	
        }
        if (flag2colour == "blueteam") {
        	// + 1 point
        	
        }
        if (flag3colour == "redteam") {
        	// + 1 point
        	
        }
        if (flag3colour == "blueteam") {
        	// + 1 point
        	
        }
        if (flag4colour == "redteam") {
        	// + 1 point
        	
        }
        if (flag4colour == "blueteam") {
        	// + 1 point
        	
        }
        
        
        
        // flag 1 regions
        
		if (x1 == playerlocx && y1 == playerlocy && z1 == playerlocz) {
			// Player is standing on flag 1 centre block
			
			// Is the flag claimed?
			// if so, which team has claimed it?
			if (flag1colour == "white") {
				// Nobody has captured the point yet
				if (board.getPlayerTeam(player).getName() == "redteam") {
					// Capture the flag as the red team!
					// All 9 block locations that need to be changed to the team's colour

					try {
						Location loc1 = new Location(Bukkit.getWorld(world), x1, y1-1, z1);
						loc1.getBlock().setType(Material.RED_WOOL);
						player.playSound(player.getLocation(), "block.wool.break", 3.0F, 0.5F);
						Thread.sleep(1000);
						Location loc2 = new Location(Bukkit.getWorld(world), x1+1, y1-1, z1);
						loc2.getBlock().setType(Material.RED_WOOL);
						player.playSound(player.getLocation(), "block.wool.break", 3.0F, 0.5F);
						Thread.sleep(1000);
						Location loc3 = new Location(Bukkit.getWorld(world), x1, y1-1, z1+1);
						loc3.getBlock().setType(Material.RED_WOOL);
						player.playSound(player.getLocation(), "block.wool.break", 3.0F, 0.5F);
						Thread.sleep(1000);
						Location loc4 = new Location(Bukkit.getWorld(world), x1-1, y1-1, z1);
						loc4.getBlock().setType(Material.RED_WOOL);
						player.playSound(player.getLocation(), "block.wool.break", 3.0F, 0.5F);
						Thread.sleep(1000);
						Location loc5 = new Location(Bukkit.getWorld(world), x1, y1-1, z1-1);
						loc5.getBlock().setType(Material.RED_WOOL);
						player.playSound(player.getLocation(), "block.wool.break", 3.0F, 0.5F);
						Thread.sleep(1000);
						Location loc6 = new Location(Bukkit.getWorld(world), x1-1, y1-1, z1-1);
						loc6.getBlock().setType(Material.RED_WOOL);
						player.playSound(player.getLocation(), "block.wool.break", 3.0F, 0.5F);
						Thread.sleep(1000);
						Location loc7 = new Location(Bukkit.getWorld(world), x1+1, y1-1, z1+1);
						loc7.getBlock().setType(Material.RED_WOOL);
						player.playSound(player.getLocation(), "block.wool.break", 3.0F, 0.5F);
						Thread.sleep(1000);
						Location loc8 = new Location(Bukkit.getWorld(world), x1-1, y1-1, z1+1);
						loc8.getBlock().setType(Material.RED_WOOL);
						player.playSound(player.getLocation(), "block.wool.break", 3.0F, 0.5F);
						Thread.sleep(1000);
						Location loc9 = new Location(Bukkit.getWorld(world), x1+1, y1-1, z1-1);
						loc9.getBlock().setType(Material.RED_WOOL);
						player.playSound(player.getLocation(), "entity.player.levelup", 3.0F, 0.5F);
						
						// Broadcast the capture
						Bukkit.broadcastMessage("The red team have captured flag 1 !");
						
						// set the flag's team
						flag1colour = "redteam";
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if (board.getPlayerTeam(player).getName() == "blueteam") {
					// Capture the flag as the blue team!
					// All 9 block locations that need to be changed to the team's colour
					try {
						Location loc1 = new Location(Bukkit.getWorld(world), x1, y1-1, z1);
						loc1.getBlock().setType(Material.BLUE_WOOL);
						player.playSound(player.getLocation(), "block.wool.break", 3.0F, 0.5F);
						Thread.sleep(1000);
						Location loc2 = new Location(Bukkit.getWorld(world), x1+1, y1-1, z1);
						loc2.getBlock().setType(Material.BLUE_WOOL);
						player.playSound(player.getLocation(), "block.wool.break", 3.0F, 0.5F);
						Thread.sleep(1000);
						Location loc3 = new Location(Bukkit.getWorld(world), x1, y1-1, z1+1);
						loc3.getBlock().setType(Material.BLUE_WOOL);
						player.playSound(player.getLocation(), "block.wool.break", 3.0F, 0.5F);
						Thread.sleep(1000);
						Location loc4 = new Location(Bukkit.getWorld(world), x1-1, y1-1, z1);
						loc4.getBlock().setType(Material.BLUE_WOOL);
						player.playSound(player.getLocation(), "block.wool.break", 3.0F, 0.5F);
						Thread.sleep(1000);
						Location loc5 = new Location(Bukkit.getWorld(world), x1, y1-1, z1-1);
						loc5.getBlock().setType(Material.BLUE_WOOL);
						player.playSound(player.getLocation(), "block.wool.break", 3.0F, 0.5F);
						Thread.sleep(1000);
						Location loc6 = new Location(Bukkit.getWorld(world), x1-1, y1-1, z1-1);
						loc6.getBlock().setType(Material.BLUE_WOOL);
						player.playSound(player.getLocation(), "block.wool.break", 3.0F, 0.5F);
						Thread.sleep(1000);
						Location loc7 = new Location(Bukkit.getWorld(world), x1+1, y1-1, z1+1);
						loc7.getBlock().setType(Material.BLUE_WOOL);
						player.playSound(player.getLocation(), "block.wool.break", 3.0F, 0.5F);
						Thread.sleep(1000);
						Location loc8 = new Location(Bukkit.getWorld(world), x1-1, y1-1, z1+1);
						loc8.getBlock().setType(Material.BLUE_WOOL);
						player.playSound(player.getLocation(), "block.wool.break", 3.0F, 0.5F);
						Thread.sleep(1000);
						Location loc9 = new Location(Bukkit.getWorld(world), x1+1, y1-1, z1-1);
						loc9.getBlock().setType(Material.BLUE_WOOL);
						player.playSound(player.getLocation(), "entity.player.levelup", 3.0F, 0.5F);
						
						// Broadcast the capture
						Bukkit.broadcastMessage("The blue team have captured flag 1 !");
						
						// set the flag's team
						flag1colour = "blueteam";
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
			if (flag1colour == "red") {
				// Red team owns this flag
				if (board.getPlayerTeam(player).getName() == "redteam") {
					// Player is on the red team and they already own this flag
					// Do nothing.
				}
				if (board.getPlayerTeam(player).getName() == "blueteam") {
					// Player is on the blue team and they don't own this flag
					// Capture the flag! (with a count down)
					// Cancel if the player leaves the location
					
				}			
			}
			if (flag1colour == "blue") {
				// Blue team owns this flag
				if (board.getPlayerTeam(player).getName() == "blueteam") {
					// Player is on the blue team and they already own this flag
					// Do nothing.
				}
				if (board.getPlayerTeam(player).getName() == "redteam") {
					// Player is on the red team and they don't own this flag
					// Capture the flag! (with a count down)
					// Cancel if the player leaves the location
					
				}
				
			}
						
		}	
		if (x1-1 == playerlocx && y1 == playerlocy && z1 == playerlocz) {
			// Player is standing on flag 1 outer block
			
		}
		if (x1 == playerlocx && y1 == playerlocy && z1-1 == playerlocz) {
			// Player is standing on flag 1 outer block
			
		}
		if (x1+1 == playerlocx && y1 == playerlocy && z1 == playerlocz) {
			// Player is standing on flag 1 
			
		}
		if (x1 == playerlocx && y1 == playerlocy && z1+1 == playerlocz) {
			// Player is standing on flag 1 
			
		}
		if (x1-1 == playerlocx && y1 == playerlocy && z1+1 == playerlocz) {
			// Player is standing on flag 1 
			
		}
		if (x1-1 == playerlocx && y1 == playerlocy && z1-1 == playerlocz) {
			// Player is standing on flag 1 centre block
			
		}
		if (x1+1 == playerlocx && y1 == playerlocy && z1-1 == playerlocz) {
			// Player is standing on flag 1 centre block
			
		}
		if (x1+1 == playerlocx && y1 == playerlocy && z1+1 == playerlocz) {
			// Player is standing on flag 1 centre block
			
		}
		
		
		// flag 2 regions
        
				if (x2 == playerlocx && y2 == playerlocy && z2 == playerlocz) {
					// Player is standing on flag 1 centre block
					
				}	
				if (x2-1 == playerlocx && y2 == playerlocy && z2 == playerlocz) {
					// Player is standing on flag 1 outer block
					
				}
				if (x2 == playerlocx && y2 == playerlocy && z2-1 == playerlocz) {
					// Player is standing on flag 1 outer block
					
				}
				if (x2+1 == playerlocx && y2 == playerlocy && z2 == playerlocz) {
					// Player is standing on flag 1 
					
				}
				if (x2 == playerlocx && y2 == playerlocy && z2+1 == playerlocz) {
					// Player is standing on flag 1 
					
				}
				if (x2-1 == playerlocx && y2 == playerlocy && z2+1 == playerlocz) {
					// Player is standing on flag 1 
					
				}
				if (x2-1 == playerlocx && y2 == playerlocy && z2-1 == playerlocz) {
					// Player is standing on flag 1 centre block
					
				}
				if (x2+1 == playerlocx && y2 == playerlocy && z2-1 == playerlocz) {
					// Player is standing on flag 1 centre block
					
				}
				if (x2+1 == playerlocx && y2 == playerlocy && z2+1 == playerlocz) {
					// Player is standing on flag 1 centre block
					
				}
		
				// flag 3 regions
		        
				if (x3 == playerlocx && y3 == playerlocy && z3 == playerlocz) {
					// Player is standing on flag 1 centre block
					
				}	
				if (x3-1 == playerlocx && y3 == playerlocy && z3 == playerlocz) {
					// Player is standing on flag 1 outer block
					
				}
				if (x3 == playerlocx && y3 == playerlocy && z3-1 == playerlocz) {
					// Player is standing on flag 1 outer block
					
				}
				if (x3+1 == playerlocx && y3 == playerlocy && z3 == playerlocz) {
					// Player is standing on flag 1 
					
				}
				if (x3 == playerlocx && y3 == playerlocy && z3+1 == playerlocz) {
					// Player is standing on flag 1 
					
				}
				if (x3-1 == playerlocx && y3 == playerlocy && z3+1 == playerlocz) {
					// Player is standing on flag 1 
					
				}
				if (x3-1 == playerlocx && y3 == playerlocy && z3-1 == playerlocz) {
					// Player is standing on flag 1 centre block
					
				}
				if (x3+1 == playerlocx && y3 == playerlocy && z3-1 == playerlocz) {
					// Player is standing on flag 1 centre block
					
				}
				if (x3+1 == playerlocx && y3 == playerlocy && z3+1 == playerlocz) {
					// Player is standing on flag 1 centre block
					
				}
				
				
				// flag 4 regions
		        
				if (x4 == playerlocx && y4 == playerlocy && z4 == playerlocz) {
					// Player is standing on flag 1 centre block
					
				}	
				if (x4-1 == playerlocx && y4 == playerlocy && z4 == playerlocz) {
					// Player is standing on flag 1 outer block
					
				}
				if (x4 == playerlocx && y4 == playerlocy && z4-1 == playerlocz) {
					// Player is standing on flag 1 outer block
					
				}
				if (x4+1 == playerlocx && y4 == playerlocy && z4 == playerlocz) {
					// Player is standing on flag 1 
					
				}
				if (x4 == playerlocx && y4 == playerlocy && z4+1 == playerlocz) {
					// Player is standing on flag 1 
					
				}
				if (x4-1 == playerlocx && y4 == playerlocy && z4+1 == playerlocz) {
					// Player is standing on flag 1 
					
				}
				if (x4-1 == playerlocx && y4 == playerlocy && z4-1 == playerlocz) {
					// Player is standing on flag 1 centre block
					
				}
				if (x4+1 == playerlocx && y4 == playerlocy && z4-1 == playerlocz) {
					// Player is standing on flag 1 centre block
					
				}
				if (x4+1 == playerlocx && y4 == playerlocy && z4+1 == playerlocz) {
					// Player is standing on flag 1 centre block
					
				}
	    }
		});
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
					sender.sendMessage("§4§l[CTF]§r You have joined the §cred team &f!");
					
				}
				if (args.length >= 2 && args[1].equalsIgnoreCase("blue")) {
					// Join the blue team
					sender.sendMessage("§4§l[CTF]§r You have joined the §9blue team &f!");
					
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
				sender.sendMessage("/ctf set kit<num>_<colour> §7# set the kits spawn");
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
		    	
		    	if (args.length >= 2 && args[1].equalsIgnoreCase("kit1_red")) {
		    		// set the kit 1 location for the red team
					try {
					Player player = (Player) sender;
					Location loc = player.getLocation();
					int x = loc.getBlockX();
					int y = loc.getBlockY();
					int z = loc.getBlockZ();
					
					sender.sendMessage("§4§l[CTF]§r You set the red team's kit 1 to: X:" + x + " Y:" + y + " Z:" + z + " !");
					
					player.playSound(player.getLocation(), "entity.villager.yes", 3.0F, 0.5F);
					
					// We then need to save this to the config.yml file!
					
					System.out.print("\n:: red_kit1: " + x + " " + y + "" + z);
					var corindates = x + ", " + y + ", " + z;
					
		    		config.addDefault("# red_kit1: ", corindates);
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
		    	
		    	if (args.length >= 2 && args[1].equalsIgnoreCase("kit2_red")) {
		    		// set the kit 2 location for the red team
					try {
					Player player = (Player) sender;
					Location loc = player.getLocation();
					int x = loc.getBlockX();
					int y = loc.getBlockY();
					int z = loc.getBlockZ();
					
					sender.sendMessage("§4§l[CTF]§r You set the red team's kit 2 to: X:" + x + " Y:" + y + " Z:" + z + " !");
					
					player.playSound(player.getLocation(), "entity.villager.yes", 3.0F, 0.5F);
					
					// We then need to save this to the config.yml file!
					
					System.out.print("\n:: red_kit2: " + x + " " + y + "" + z);
					var corindates = x + ", " + y + ", " + z;
					
		    		config.addDefault("# red_kit2: ", corindates);
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
		    	
		    	if (args.length >= 2 && args[1].equalsIgnoreCase("kit3_red")) {
		    		// set the kit 3 location for the red team
					try {
					Player player = (Player) sender;
					Location loc = player.getLocation();
					int x = loc.getBlockX();
					int y = loc.getBlockY();
					int z = loc.getBlockZ();
					
					sender.sendMessage("§4§l[CTF]§r You set the red team's kit 3 to: X:" + x + " Y:" + y + " Z:" + z + " !");
					
					player.playSound(player.getLocation(), "entity.villager.yes", 3.0F, 0.5F);
					
					// We then need to save this to the config.yml file!
					
					System.out.print("\n:: red_kit3: " + x + " " + y + "" + z);
					var corindates = x + ", " + y + ", " + z;
					
		    		config.addDefault("# red_kit3: ", corindates);
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
		    	
		    	if (args.length >= 2 && args[1].equalsIgnoreCase("kit1_blue")) {
		    		// set the kit 1 location for the red team
					try {
					Player player = (Player) sender;
					Location loc = player.getLocation();
					int x = loc.getBlockX();
					int y = loc.getBlockY();
					int z = loc.getBlockZ();
					
					sender.sendMessage("§4§l[CTF]§r You set the blue team's kit 1 to: X:" + x + " Y:" + y + " Z:" + z + " !");
					
					player.playSound(player.getLocation(), "entity.villager.yes", 3.0F, 0.5F);
					
					// We then need to save this to the config.yml file!
					
					System.out.print("\n:: blue_kit1: " + x + " " + y + "" + z);
					var corindates = x + ", " + y + ", " + z;
					
		    		config.addDefault("# blue_kit1: ", corindates);
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
		    	
		    	if (args.length >= 2 && args[1].equalsIgnoreCase("kit2_blue")) {
		    		try {
						Player player = (Player) sender;
						Location loc = player.getLocation();
						int x = loc.getBlockX();
						int y = loc.getBlockY();
						int z = loc.getBlockZ();
						
						sender.sendMessage("§4§l[CTF]§r You set the blue team's kit 2 to: X:" + x + " Y:" + y + " Z:" + z + " !");
						
						player.playSound(player.getLocation(), "entity.villager.yes", 3.0F, 0.5F);
						
						// We then need to save this to the config.yml file!
						
						System.out.print("\n:: blue_kit2: " + x + " " + y + "" + z);
						var corindates = x + ", " + y + ", " + z;
						
			    		config.addDefault("# blue_kit2: ", corindates);
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
		    	
		    	if (args.length >= 2 && args[1].equalsIgnoreCase("kit3_blue")) {
		    		try {
						Player player = (Player) sender;
						Location loc = player.getLocation();
						int x = loc.getBlockX();
						int y = loc.getBlockY();
						int z = loc.getBlockZ();
						
						sender.sendMessage("§4§l[CTF]§r You set the blue team's kit 3 to: X:" + x + " Y:" + y + " Z:" + z + " !");
						
						player.playSound(player.getLocation(), "entity.villager.yes", 3.0F, 0.5F);
						
						// We then need to save this to the config.yml file!
						
						System.out.print("\n:: blue_kit3: " + x + " " + y + "" + z);
						var corindates = x + ", " + y + ", " + z;
						
			    		config.addDefault("# blue_kit3: ", corindates);
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
