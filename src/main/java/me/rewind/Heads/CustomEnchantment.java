package me.rewind.Heads;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class CustomEnchantment extends Enchantment {

    public CustomEnchantment(int id) {
        super(NamespacedKey.minecraft(String.valueOf(id)));
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true; //can it enchant items?
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false; //will it conflict? In this case, nope
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null; //you can define a target here, I just setted it to null...
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public int getMaxLevel() {
        return 2; //the maxmimum level.
    }

    @Override
    public String getName() {
        return "CoolFlyEnchantThingy"; //the name
    }

    public int getId() {
        return 69;//the id, in this case I choose 69 because no serious person would ever choose that number, lucky me! Oh yeah, the max id = 256
    }

    @Override
    public int getStartLevel() {
        return 1; //the start level of your enchantment
    }

}
