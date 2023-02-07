package me.rewind;

import me.rewind.Heads.CustomEnchantment;
import me.rewind.Heads.HeadDrop;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public final class main extends JavaPlugin {

    private  File customConfigFile;
    private  FileConfiguration customConfig;

    private static main Main;

    private boolean isOn = true;

    private String onOrOff = (isOn) ? ChatColor.GREEN + " BH Enabled" : " BH Disabled.";

    @Override
    public void onEnable() {
        // Plugin startup logic
        consoleMessage(true);
        getServer().getConsoleSender().sendMessage("bh enabled");
        createCustomConfig();
        createDefault();
        saveConfig();




        getServer().getPluginManager().registerEvents(new HeadDrop(), this);

        Main = this;




        // this.getCommand("bounty").setExecutor(new bountyCommand());


    }



    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("bh disabled");
       consoleMessage(false);
        // Plugin shutdown logic
    }

    public  FileConfiguration getCustomConfig() {
        return this.customConfig;
    }


    public void saveConfig(){
        try{
            loadConfig();
            getCustomConfig().save(customConfigFile);
            reloadConfig();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }

    }

    public void consoleMessage(boolean isOn){
        this.isOn = isOn;
        getServer().getConsoleSender().sendMessage(onOrOff);
    }


    private void createCustomConfig(){
        customConfigFile = new File(getDataFolder(), "config.yml");
        if(!customConfigFile.exists()){
            customConfigFile.getParentFile().mkdirs();
            saveResource("config.yml", false);


        }

        customConfig = new YamlConfiguration();

        try{
            customConfig.load(customConfigFile);
        }catch (IOException | InvalidConfigurationException e){
            e.printStackTrace();
        }

    }

    public  void createDefault() {


                getCustomConfig().createSection("GLOBAL SETTINGS");
        getCustomConfig().getConfigurationSection("GLOBAL SETTINGS").addDefault("MOB DROP PERCENTAGE", 0);
        getCustomConfig().getConfigurationSection("GLOBAL SETTINGS").addDefault("DROP ALERT MESSAGE", "{name} Just dropped a {headname}");

        getCustomConfig().getConfigurationSection("GLOBAL SETTINGS").addDefault("PLAYER DROP PERCENTAGE", 0);
        getCustomConfig().getConfigurationSection("GLOBAL SETTINGS").addDefault("GRADUAL DECREASE", false);



                List<String> Globalcomments = new ArrayList<>();
                Globalcomments.add("WARNING: This acts as a global mob percentage for all mobs - leave 0 to not override per-mob settings.");
                Globalcomments.add("Equation used for drop percentages in this plugin:  https://chart.apis.google.com/chart?cht=tx&chl=n%20%3D%20r%20%0A%20%20%5Cfrac%7B%5Cbegin%7Bbmatrix%7D0%2C%20%20x%5Cend%7Bbmatrix%7D%7D%7B100%7D%20");
                getCustomConfig().getDefaultSection().setComments("GLOBAL SETTINGS.MOB DROP PERCENTAGE", Globalcomments);


                getCustomConfig().createSection("PER-MOB SETTINGS");
                for (String i : mobList.getListOfMobs()) {
                    getCustomConfig().getConfigurationSection("PER-MOB SETTINGS").addDefault(i.toUpperCase(Locale.ROOT), 0);

                }
                getCustomConfig().options().copyDefaults(true);
                this.saveDefaultConfig();
                this.saveConfig();



    }

    public void loadConfig() throws IOException, InvalidConfigurationException {
        customConfig.load(customConfigFile);

    }
    public static me.rewind.main getMain() {
        return Main;
    }







}


