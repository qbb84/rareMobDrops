package me.rewind;

import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.Arrays;

public enum mobList {

    ALLAY(EntityType.ALLAY),
    AXOLOTL(EntityType.AXOLOTL),
    BAT(EntityType.BAT),
    BEE(EntityType.BEE),
    Blaze(EntityType.BLAZE),
    CAT(EntityType.CAT),
    CAVE_SPIDER(EntityType.CAVE_SPIDER),
    CHICKEN(EntityType.CHICKEN),
    COD(EntityType.COD),
    COW(EntityType.COW),
    CREEPER(EntityType.CREEPER),
    DOLPHIN(EntityType.DOLPHIN),
    DONKEY(EntityType.DONKEY),
    ENDER_DRAGON(EntityType.ENDER_DRAGON),
    DROWNED(EntityType.DROWNED),
    ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN),
    ENDERMAN(EntityType.ENDERMAN),
    ENDERMITE(EntityType.ENDERMITE),
    EVOKER(EntityType.EVOKER),
    FOX(EntityType.FOX),
    FROG(EntityType.FROG),
    GHAST(EntityType.GHAST),
    GIANT_ZOMBIE(EntityType.GIANT),
    GLOW_SQUID(EntityType.GLOW_SQUID),
    GOAT(EntityType.GOAT),
    GUARDIAN(EntityType.GUARDIAN),
    HOGLIN(EntityType.HOGLIN),
    HORSE(EntityType.HORSE),
    HUSK(EntityType.HUSK),
    ILLUSIONER(EntityType.ILLUSIONER),
    IRON_GOLEM(EntityType.IRON_GOLEM),
    LLAMA(EntityType.LLAMA),
    MAGMA_CUBE(EntityType.MAGMA_CUBE),
    MOOSHROOM(EntityType.MUSHROOM_COW),
    MULE(EntityType.MULE),
    OCELOT(EntityType.OCELOT),
    PANDA(EntityType.PANDA),
    PARROT(EntityType.PARROT),
    PHANTOM(EntityType.PHANTOM),
    PIG(EntityType.PIG),
    PIGLIN(EntityType.PIGLIN),
    PIGLIN_BRUTE(EntityType.PIGLIN_BRUTE),
    PILLAGER(EntityType.PILLAGER),
    POLAR_BEAR(EntityType.POLAR_BEAR),
    RABBIT(EntityType.RABBIT),
    RAVAGER(EntityType.RAVAGER),
    SALMON(EntityType.SALMON),
    SHEEP(EntityType.SHEEP),
    SILVERFISH(EntityType.SILVERFISH),
    SKELETON(EntityType.SKELETON),
    SKELETON_HORSE(EntityType.SKELETON_HORSE),
    SLIME(EntityType.SLIME),
    SNOWMAN(EntityType.SNOWMAN),
    SPIDER(EntityType.SPIDER),
    SQUID(EntityType.SQUID),
    STRAY(EntityType.STRAY),
    STRIDER(EntityType.STRIDER),
    TADPOLE(EntityType.TADPOLE),
    TRADER_LLAMA(EntityType.TRADER_LLAMA),
    TURTLE(EntityType.TURTLE),
    VEX(EntityType.VEX),
    VILLAGER(EntityType.VILLAGER),
    VINDICATOR(EntityType.VINDICATOR),
    WANDERING_TRADER(EntityType.WANDERING_TRADER),
    WARDEN(EntityType.WARDEN),
    WITCH(EntityType.WITCH),
    WITHER_BOSS(EntityType.WITHER),
    WITHER_SKELETON(EntityType.WITHER_SKELETON),
    WOLF(EntityType.WOLF),
    ZOGLIN(EntityType.ZOGLIN),
    ZOMBIE(EntityType.ZOMBIE),
    ZOMBIE_HORSE(EntityType.ZOMBIE_HORSE),
    ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER),
    ZOMBIFIED_PIGLIN(EntityType.ZOMBIFIED_PIGLIN);


    public EntityType entity;

    mobList(org.bukkit.entity.EntityType entityType){
        this.entity = entityType;
    }
    public static ArrayList<String> getListOfMobs(){
        ArrayList<String> getMobList = new ArrayList<>();

        for(mobList i : mobList.values()){
            getMobList.add(i.toString());
        }
        return getMobList;
    }
}
