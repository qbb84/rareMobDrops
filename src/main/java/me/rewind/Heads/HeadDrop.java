package me.rewind.Heads;

import me.rewind.GlowingEntities;
import me.rewind.main;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class HeadDrop implements Listener {



    HashMap<ItemStack, HashMap<Integer, ? extends ItemStack>> locOfItemInInventory = new HashMap<>();
    ItemStack updatedInventoryItems = null;


    enum rarity {
        COMMON(ChatColor.WHITE),
        UNCOMMON(ChatColor.GREEN),
        RARE(ChatColor.BLUE),
        EPIC(ChatColor.DARK_PURPLE),
        LEGENDARY(ChatColor.GOLD),
        MYTHIC(ChatColor.LIGHT_PURPLE),
        DIVINE(ChatColor.AQUA),
        SPECIAL(ChatColor.RED),

        SHINY(ChatColor.YELLOW);

        public ChatColor chatColor;

        public ChatColor getChatColor() {
            return chatColor;
        }

        rarity(ChatColor color) {
            this.chatColor = color;
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player p = event.getPlayer();

new BukkitRunnable(){
    @Override
    public void run() {
        for(int i = 0; i <= p.getInventory().getSize(); i++){

            if(p.getInventory().getItem(i).getItemMeta().hasDisplayName()) {
                if(p.getInventory().getItem(i).getItemMeta().getDisplayName().contains("SHINY")) {
                    locOfItemInInventory.put(p.getInventory().getItem(i), p.getInventory().all(p.getInventory().getItem(i)));

                    for (ItemStack item : locOfItemInInventory.keySet()) {
                        updatedInventoryItems = item;
                        if (updatedInventoryItems != null) {

                            if (updatedInventoryItems.hasItemMeta()) {
                                ItemMeta meta = item.getItemMeta();
                                if (updatedInventoryItems.getItemMeta().hasDisplayName()) {
                                    meta.setDisplayName(rainbowChat("SHINY"));

                                    updatedInventoryItems.setItemMeta(meta);
                                }


                            }
                        }
                    }


                    for (int j = 0; i <= p.getInventory().getSize(); i++) {
                        locOfItemInInventory.put(updatedInventoryItems, p.getInventory().all(p.getInventory().getItem(j)));
                    }
                    Bukkit.broadcastMessage("2" + locOfItemInInventory.keySet().toString());

                    for (HashMap slotID : locOfItemInInventory.values()) {
                        for (ItemStack itemStack : locOfItemInInventory.keySet()) {
                            if (itemStack != null) {
                                for (Object key : slotID.keySet()) {
                                    if (key != null) {
                                        p.getInventory().setItem((Integer) key, itemStack);
                                        p.updateInventory();


                                    }
                                }
                            }

                        }
                    }
                }
                p.updateInventory();
                // }
            }




        }
        p.updateInventory();



    }
}.runTaskTimerAsynchronously(main.getMain(), 0, 100);







/*
        new BukkitRunnable(){

            @Override
            public void run() {
                for(int i = 0; i < p.getInventory().getContents().length; i++){
                    // if(p.getInventory().getItem(i).getItemMeta().getDisplayName().contains("Creeper")){
                        locOfItemInInventory.put(p.getInventory().getItem(i), p.getInventory().all(p.getInventory().getItem(i)));
                    // }
                }

                for(ItemStack item : locOfItemInInventory.keySet()) {
                    item.getItemMeta().setDisplayName(rainbowChat("hi"));
                    item.setItemMeta(item.getItemMeta());
                    locOfItemInInventory.put(item, p.getInventory().all(item));
                }

                for(HashMap slotID : locOfItemInInventory.values()){
                    for(Integer integer : locOfItemInInventory.get(slotID).keySet()) {
                        p.getInventory().setItem(integer,  locOfItemInInventory.get(slotID).get(integer));
                    }
                }


            }
        }.runTaskTimerAsynchronously(main.getMain(), 0, 40);
        p.updateInventory();
        */




    }



    @EventHandler
    public void onEntityKill(EntityDeathEvent entity) throws ReflectiveOperationException {


        Player killer = entity.getEntity().getKiller();
        Random rand = new Random();


        if (entity.getEntity().getKiller() != null) {
            Entity deadMob = entity.getEntity();
            main.getMain().saveConfig();

            int dropP = (int) main.getMain().getCustomConfig().get("GLOBAL SETTINGS.MOB DROP PERCENTAGE");

            if (dropP > 0) {
                if (rand.nextInt(101) <= dropP) {

                    killer.spawnParticle(Particle.NOTE, (deadMob.getLocation()), 3, 2, 3, 2);
                    dropBroadCastMessage(killer, deadMob.getName());
                    Item item = deadMob.getWorld().dropItemNaturally(deadMob.getLocation(), getHead(deadMob));


                    if (item.getItemStack().hasItemMeta()) {
                        ItemMeta meta = item.getItemStack().getItemMeta();
                        if(meta.hasDisplayName() && !meta.getDisplayName().contains(rarity.COMMON.chatColor + deadMob.getName() + " Skull")) {
                            //Do something
                            GlowingEntities glowingEntities = new GlowingEntities(main.getMain());
                            glowingEntities.setGlowing(item, killer, ChatColor.WHITE);
                            item.setCustomName(item.getItemStack().getItemMeta().getDisplayName());
                            item.setCustomNameVisible(true);
                        }
                    }







                }
            } else {
                if (rand.nextInt(101) <= (int) main.getMain().getCustomConfig().getConfigurationSection("PER-MOB SETTINGS").get(deadMob.getName().toUpperCase(Locale.ROOT))) {

                    killer.spawnParticle(Particle.NOTE, (deadMob.getLocation()), 3, 2, 3, 2);
                    killer.playSound(killer, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                    deadMob.getWorld().dropItemNaturally(deadMob.getLocation(), getHead(deadMob));
                    dropBroadCastMessage(killer, deadMob.getName());
                }
            }


        } else if (entity.getEntityType().equals(EntityType.PLAYER)) {
            //G_DECREASE
            if (rand.nextInt(101) <= (int) main.getMain().getCustomConfig().getConfigurationSection("GLOBAL SETTINGS").get("PLAYER DROP PERCENTAGE")) {
                Player p = (Player) entity;
                killer.spawnParticle(Particle.NOTE, (p.getLocation()), 3, 2, 3, 2);
                killer.playSound(killer, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                p.getWorld().dropItemNaturally(p.getLocation(), getHead(p));
                dropBroadCastMessage(killer, p.getCustomName());
            }
        }


    }

    public ItemStack getHead(Player player) {

        Random random = new Random();

        int getDropPercentage = (int) main.getMain().getCustomConfig().getConfigurationSection("GLOBAL SETTINGS").get("DROP PERCENTAGE");


        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName(rarity.COMMON.chatColor + player.getDisplayName());

        skull.setOwner(player.getUniqueId().toString());
        item.setItemMeta(skull);
        return item;
    }

    //ADD SHINY AND CARD FLOAT/PSA (use MC emjois

    public ItemStack getHead(Entity entity) {
        Random random = new Random();
        double chance = random.nextDouble(0, 101);

        if(chance <= 0.5){
            return createSkull(entity, rarity.SPECIAL);
        }else if(chance <= 0.8){
            return createSkull(entity, rarity.DIVINE);
        }else if(chance <= 1.1){
            return createSkull(entity, rarity.MYTHIC);
        }else if (chance <= 2){
            return createSkull(entity, rarity.LEGENDARY);
        }else if (chance <= 5){
            return createSkull(entity, rarity.EPIC);
        }else if (chance <= 12){
           return createSkull(entity, rarity.RARE);
        }else if(chance<= 33){
            return  createSkull(entity, rarity.UNCOMMON);

        }else {


            return createSkull(entity, rarity.COMMON);
        }
    }


    public void shinyItem(ItemMeta meta, Entity entity){
        if(!meta.getDisplayName().contains("SHINY")) return;

        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {

                    i++;
                    meta.setDisplayName(ChatColor.WHITE + meta.getDisplayName().replace(meta.getDisplayName().charAt(i), meta.getDisplayName().charAt(i)) + "" + ChatColor.BOLD + rarity.SHINY.name().toUpperCase() + " " + rarity.COMMON.chatColor + entity.getName() + " Skull");

            }
        }.runTaskTimerAsynchronously(main.getMain(), 0, 20);

    }

    public void dropBroadCastMessage(Player player, String headDrop) {
        Bukkit.broadcastMessage(translateColorCodes(main.getMain().getCustomConfig().getConfigurationSection("GLOBAL SETTINGS").get("DROP ALERT MESSAGE").toString().replace("{name}", player.getDisplayName()).replace("{headname}", headDrop)));

    }

    public String translateColorCodes(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public ArmorStand armourStandText(Location location, String message) {
        ArmorStand as = location.getWorld().spawn(location, ArmorStand.class);

        as.setCustomName(message);
        as.setCustomNameVisible(true);
        as.setVisible(false);


        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                i++;


                for (Entity entity : Bukkit.getWorld("world").getEntitiesByClass(as.getClass())) {
                    if (i == 5) {
                        entity.remove();
                        this.cancel();
                        i = 0;

                    }
                }

                this.cancel();
            }
        }.runTaskTimerAsynchronously(main.getMain(), 0, 20);

        return as;

    }

    public ItemStack createSkull(Entity entity, rarity rarity1){
        Random random = new Random();
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        List<String> lore = new ArrayList<>();
        if(random.nextDouble(100) <= 99) {//1.3
            skull.setDisplayName(ChatColor.BOLD + rarity1.SHINY.name().toUpperCase()  + " " + rarity1.chatColor + entity.getName() + " Skull");

        }else skull.setDisplayName(rarity1.chatColor + entity.getName() + " Skull");
        skull.setOwner("MHF_" + entity.getName());

        switch (rarity1){
            case UNCOMMON:
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + " Now what are the odds of that? You received an item that isn't COMMON, congrats!");
                lore.add("");
                break;
            case RARE:
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + " These items are rare by nature, some say they hold good value, ");
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + " others say it's just a good looking trophy. Hopefully you're more decisive.");
                lore.add("");
                break;
            case EPIC:
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + " This item was used to forge the armour of the Piglin Army, ");
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + " before Herobrine (BH), it's quite heavy, but for what it lacks, ");
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + "it makes up in lucrative.");
                lore.add("");
                break;
            case LEGENDARY:
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + " It's not everyday you see an item like this... ");
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + " Some say receiving this Legendary rarity changes people for the better ");
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + " We were always taught to keep it safe encase of danger, what were they up to?");
                lore.add("");
                break;
            case MYTHIC:
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + " Used as monetary value by the kings and queens, but hasn't been seen for hundreds of years, ");
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY +  " now that you have one, we may finally be able to rebuild our city.");
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + " ALL HAIL THE RECLAIMER!");
                lore.add("");
                break;
            case DIVINE:
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + " So much has happened... She was alluring... ");
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY +" but unbeknownst to all, truly evil. Admira the Witch Queen is responsible ");
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY +" for our deposition, it may be too late now that she has shown her hand, ");
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + "but to change for salvation, is to rebuild with patience. I've told most I can, goodbye, for now.");
                break;
            case SPECIAL:
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + " I've only ever seen one of these, it's true what they about this rarity");
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + " from the mists of the clouds, to the warmth of the jungle, ancient teachings " );
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + " this rarity was used to put a stop to cataclysmic events. " );
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + " WARNING: This item is bright, but beware of the power it holds.");
                lore.add("");
                break;
            default:
                lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + " This is just a common item, not too much to see here...");
                lore.add("");
                break;

        }

        int psaChance = new Random().nextInt(10);
        if(psaChance >= 5){
            int nextPsa1 = new Random().nextInt(5);
            if(nextPsa1 <= 2)  lore.add(ChatColor.DARK_GRAY + " PSA: " + random.nextInt(5, 7));
            if(nextPsa1 > 2) lore.add(ChatColor.DARK_GRAY + " PSA: " + random.nextInt(7, 11));
        }else{
            lore.add(ChatColor.DARK_GRAY + " PSA: " + random.nextInt(0, 5));
        }
        lore.add(rarity1.chatColor + "" + ChatColor.BOLD + rarity1.name());


        skull.setLore(lore);
        item.setItemMeta(skull);


        return item;
    }



    public String rainbowChat(String message){

        ChatColor [] colors = {
                ChatColor.RED,
                ChatColor.YELLOW,
                ChatColor.GREEN,
                ChatColor.BLUE,
                ChatColor.DARK_BLUE,
                ChatColor.GOLD,
                ChatColor.DARK_PURPLE,
                ChatColor.LIGHT_PURPLE
        };

        StringBuffer buffer = new StringBuffer();
        String[] parts = message.split(" ");
                for (String s : parts) {
                    buffer.append(Arrays.asList(colors).get(new Random().nextInt(colors.length -1)) + s);
                    //You need to do the get random colour part ;)
                }

                return buffer.toString();
    }


    //ADD ITEMSTACK TO PASSENGER ARMOUR STAND, ROTATE ARMOUR STAND


}

