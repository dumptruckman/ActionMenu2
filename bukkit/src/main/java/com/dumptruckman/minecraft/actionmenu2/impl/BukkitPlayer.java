package com.dumptruckman.minecraft.actionmenu2.impl;

import com.dumptruckman.minecraft.actionmenu2.api.MenuUser;
import org.bukkit.Achievement;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.InventoryView.Property;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class BukkitPlayer implements Player, MenuUser {

    public static Map<Player, BukkitPlayer> playerMap = new HashMap<Player, BukkitPlayer>();

    public static BukkitPlayer get(Player player) {
        if (!playerMap.containsKey(player)) {
            playerMap.put(player, new BukkitPlayer(player));
        }
        return playerMap.get(player);
    }

    private Player player;

    private BukkitPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String getDisplayName() {
        return player.getDisplayName();
    }

    @Override
    public void setDisplayName(String s) {
        player.setDisplayName(s);
    }

    @Override
    public String getPlayerListName() {
        return player.getPlayerListName();
    }

    @Override
    public void setPlayerListName(String s) {
        player.setPlayerListName(s);
    }

    @Override
    public void setCompassTarget(Location location) {
        player.setCompassTarget(location);
    }

    @Override
    public Location getCompassTarget() {
        return player.getCompassTarget();
    }

    @Override
    public InetSocketAddress getAddress() {
        return player.getAddress();
    }

    @Override
    public void sendRawMessage(String s) {
        player.sendRawMessage(s);
    }

    @Override
    public void kickPlayer(String s) {
        player.kickPlayer(s);
    }

    @Override
    public void chat(String s) {
        player.chat(s);
    }

    @Override
    public boolean performCommand(String s) {
        return player.performCommand(s);
    }

    @Override
    public boolean isSneaking() {
        return player.isSneaking();
    }

    @Override
    public void setSneaking(boolean b) {
        player.setSneaking(b);
    }

    @Override
    public boolean isSprinting() {
        return player.isSprinting();
    }

    @Override
    public void setSprinting(boolean b) {
        player.setSprinting(b);
    }

    @Override
    public void saveData() {
        player.saveData();
    }

    @Override
    public void loadData() {
        player.loadData();
    }

    @Override
    public void setSleepingIgnored(boolean b) {
        player.setSleepingIgnored(b);
    }

    @Override
    public boolean isSleepingIgnored() {
        return player.isSleepingIgnored();
    }

    @Override
    public void playNote(Location location, byte b, byte b1) {
        player.playNote(location, b, b1);
    }

    @Override
    public void playNote(Location location, Instrument instrument, Note note) {
        player.playNote(location, instrument, note);
    }

    @Override
    public void playEffect(Location location, Effect effect, int i) {
        player.playEffect(location, effect, i);
    }

    @Override
    public <T> void playEffect(Location location, Effect effect, T t) {
        player.playEffect(location, effect, t);
    }

    @Override
    public void sendBlockChange(Location location, Material material, byte b) {
        player.sendBlockChange(location, material, b);
    }

    @Override
    public boolean sendChunkChange(Location location, int i, int i1, int i2, byte[] bytes) {
        return player.sendChunkChange(location, i, i1, i2, bytes);
    }

    @Override
    public void sendBlockChange(Location location, int i, byte b) {
        player.sendBlockChange(location, i, b);
    }

    @Override
    public void sendMap(MapView mapView) {
        player.sendMap(mapView);
    }

    @Override
    @Deprecated
    public void updateInventory() {
        player.updateInventory();
    }

    @Override
    public void awardAchievement(Achievement achievement) {
        player.awardAchievement(achievement);
    }

    @Override
    public void incrementStatistic(Statistic statistic) {
        player.incrementStatistic(statistic);
    }

    @Override
    public void incrementStatistic(Statistic statistic, int i) {
        player.incrementStatistic(statistic, i);
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material) {
        player.incrementStatistic(statistic, material);
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material, int i) {
        player.incrementStatistic(statistic, material, i);
    }

    @Override
    public void setPlayerTime(long l, boolean b) {
        player.setPlayerTime(l, b);
    }

    @Override
    public long getPlayerTime() {
        return player.getPlayerTime();
    }

    @Override
    public long getPlayerTimeOffset() {
        return player.getPlayerTimeOffset();
    }

    @Override
    public boolean isPlayerTimeRelative() {
        return player.isPlayerTimeRelative();
    }

    @Override
    public void resetPlayerTime() {
        player.resetPlayerTime();
    }

    @Override
    public void giveExp(int i) {
        player.giveExp(i);
    }

    @Override
    public float getExp() {
        return player.getExp();
    }

    @Override
    public void setExp(float v) {
        player.setExp(v);
    }

    @Override
    public int getLevel() {
        return player.getLevel();
    }

    @Override
    public void setLevel(int i) {
        player.setLevel(i);
    }

    @Override
    public int getTotalExperience() {
        return player.getTotalExperience();
    }

    @Override
    public void setTotalExperience(int i) {
        player.setTotalExperience(i);
    }

    @Override
    public float getExhaustion() {
        return player.getExhaustion();
    }

    @Override
    public void setExhaustion(float v) {
        player.setExhaustion(v);
    }

    @Override
    public float getSaturation() {
        return player.getSaturation();
    }

    @Override
    public void setSaturation(float v) {
        player.setSaturation(v);
    }

    @Override
    public int getFoodLevel() {
        return player.getFoodLevel();
    }

    @Override
    public void setFoodLevel(int i) {
        player.setFoodLevel(i);
    }

    @Override
    public Location getBedSpawnLocation() {
        return player.getBedSpawnLocation();
    }

    @Override
    public void setBedSpawnLocation(Location location) {
        player.setBedSpawnLocation(location);
    }

    @Override
    public boolean getAllowFlight() {
        return player.getAllowFlight();
    }

    @Override
    public void setAllowFlight(boolean b) {
        player.setAllowFlight(b);
    }

    @Override
    public void hidePlayer(Player player) {
        this.player.hidePlayer(player);
    }

    @Override
    public void showPlayer(Player player) {
        this.player.showPlayer(player);
    }

    @Override
    public boolean canSee(Player player) {
        return this.player.canSee(player);
    }

    @Override
    public boolean isFlying() {
        return player.isFlying();
    }

    @Override
    public void setFlying(boolean b) {
        player.setFlying(b);
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public PlayerInventory getInventory() {
        return player.getInventory();
    }

    @Override
    public boolean setWindowProperty(Property property, int i) {
        return player.setWindowProperty(property, i);
    }

    @Override
    public InventoryView getOpenInventory() {
        return player.getOpenInventory();
    }

    @Override
    public InventoryView openInventory(Inventory itemStacks) {
        return player.openInventory(itemStacks);
    }

    @Override
    public InventoryView openWorkbench(Location location, boolean b) {
        return player.openWorkbench(location, b);
    }

    @Override
    public InventoryView openEnchanting(Location location, boolean b) {
        return player.openEnchanting(location, b);
    }

    @Override
    public void openInventory(InventoryView inventoryView) {
        player.openInventory(inventoryView);
    }

    @Override
    public void closeInventory() {
        player.closeInventory();
    }

    @Override
    public ItemStack getItemInHand() {
        return player.getItemInHand();
    }

    @Override
    public void setItemInHand(ItemStack itemStack) {
        player.setItemInHand(itemStack);
    }

    @Override
    public ItemStack getItemOnCursor() {
        return player.getItemOnCursor();
    }

    @Override
    public void setItemOnCursor(ItemStack itemStack) {
        player.setItemOnCursor(itemStack);
    }

    @Override
    public boolean isSleeping() {
        return player.isSleeping();
    }

    @Override
    public int getSleepTicks() {
        return player.getSleepTicks();
    }

    @Override
    public GameMode getGameMode() {
        return player.getGameMode();
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        player.setGameMode(gameMode);
    }

    @Override
    public boolean isBlocking() {
        return player.isBlocking();
    }

    @Override
    public int getHealth() {
        return player.getHealth();
    }

    @Override
    public void setHealth(int i) {
        player.setHealth(i);
    }

    @Override
    public int getMaxHealth() {
        return player.getMaxHealth();
    }

    @Override
    public double getEyeHeight() {
        return player.getEyeHeight();
    }

    @Override
    public double getEyeHeight(boolean b) {
        return player.getEyeHeight(b);
    }

    @Override
    public Location getEyeLocation() {
        return player.getEyeLocation();
    }

    @Override
    public List<Block> getLineOfSight(HashSet<Byte> bytes, int i) {
        return player.getLineOfSight(bytes, i);
    }

    @Override
    public Block getTargetBlock(HashSet<Byte> bytes, int i) {
        return player.getTargetBlock(bytes, i);
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(HashSet<Byte> bytes, int i) {
        return player.getLastTwoTargetBlocks(bytes, i);
    }

    @Override
    @Deprecated
    public Egg throwEgg() {
        return player.throwEgg();
    }

    @Override
    @Deprecated
    public Snowball throwSnowball() {
        return player.throwSnowball();
    }

    @Override
    @Deprecated
    public Arrow shootArrow() {
        return player.shootArrow();
    }

    @Override
    public <T extends Projectile> T launchProjectile(Class<? extends T> aClass) {
        return player.launchProjectile(aClass);
    }

    @Override
    public int getRemainingAir() {
        return player.getRemainingAir();
    }

    @Override
    public void setRemainingAir(int i) {
        player.setRemainingAir(i);
    }

    @Override
    public int getMaximumAir() {
        return player.getMaximumAir();
    }

    @Override
    public void setMaximumAir(int i) {
        player.setMaximumAir(i);
    }

    @Override
    public void damage(int i) {
        player.damage(i);
    }

    @Override
    public void damage(int i, Entity entity) {
        player.damage(i, entity);
    }

    @Override
    public int getMaximumNoDamageTicks() {
        return player.getMaximumNoDamageTicks();
    }

    @Override
    public void setMaximumNoDamageTicks(int i) {
        player.setMaximumNoDamageTicks(i);
    }

    @Override
    public int getLastDamage() {
        return player.getLastDamage();
    }

    @Override
    public void setLastDamage(int i) {
        player.setLastDamage(i);
    }

    @Override
    public int getNoDamageTicks() {
        return player.getNoDamageTicks();
    }

    @Override
    public void setNoDamageTicks(int i) {
        player.setNoDamageTicks(i);
    }

    @Override
    public Player getKiller() {
        return player.getKiller();
    }

    @Override
    public boolean addPotionEffect(PotionEffect potionEffect) {
        return player.addPotionEffect(potionEffect);
    }

    @Override
    public boolean addPotionEffect(PotionEffect potionEffect, boolean b) {
        return player.addPotionEffect(potionEffect, b);
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> potionEffects) {
        return player.addPotionEffects(potionEffects);
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType potionEffectType) {
        return player.hasPotionEffect(potionEffectType);
    }

    @Override
    public void removePotionEffect(PotionEffectType potionEffectType) {
        player.removePotionEffect(potionEffectType);
    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        return player.getActivePotionEffects();
    }

    @Override
    public Location getLocation() {
        return player.getLocation();
    }

    @Override
    public void setVelocity(Vector vector) {
        player.setVelocity(vector);
    }

    @Override
    public Vector getVelocity() {
        return player.getVelocity();
    }

    @Override
    public World getWorld() {
        return player.getWorld();
    }

    @Override
    public boolean teleport(Location location) {
        return player.teleport(location);
    }

    @Override
    public boolean teleport(Location location, TeleportCause teleportCause) {
        return player.teleport(location, teleportCause);
    }

    @Override
    public boolean teleport(Entity entity) {
        return player.teleport(entity);
    }

    @Override
    public boolean teleport(Entity entity, TeleportCause teleportCause) {
        return player.teleport(entity, teleportCause);
    }

    @Override
    public List<Entity> getNearbyEntities(double v, double v1, double v2) {
        return player.getNearbyEntities(v, v1, v2);
    }

    @Override
    public int getEntityId() {
        return player.getEntityId();
    }

    @Override
    public int getFireTicks() {
        return player.getFireTicks();
    }

    @Override
    public int getMaxFireTicks() {
        return player.getMaxFireTicks();
    }

    @Override
    public void setFireTicks(int i) {
        player.setFireTicks(i);
    }

    @Override
    public void remove() {
        player.remove();
    }

    @Override
    public boolean isDead() {
        return player.isDead();
    }

    @Override
    public Server getServer() {
        return player.getServer();
    }

    @Override
    public Entity getPassenger() {
        return player.getPassenger();
    }

    @Override
    public boolean setPassenger(Entity entity) {
        return player.setPassenger(entity);
    }

    @Override
    public boolean isEmpty() {
        return player.isEmpty();
    }

    @Override
    public boolean eject() {
        return player.eject();
    }

    @Override
    public float getFallDistance() {
        return player.getFallDistance();
    }

    @Override
    public void setFallDistance(float v) {
        player.setFallDistance(v);
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent entityDamageEvent) {
        player.setLastDamageCause(entityDamageEvent);
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        return player.getLastDamageCause();
    }

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public int getTicksLived() {
        return player.getTicksLived();
    }

    @Override
    public void setTicksLived(int i) {
        player.setTicksLived(i);
    }

    @Override
    public void playEffect(EntityEffect entityEffect) {
        player.playEffect(entityEffect);
    }

    @Override
    public EntityType getType() {
        return player.getType();
    }

    @Override
    public boolean isInsideVehicle() {
        return player.isInsideVehicle();
    }

    @Override
    public boolean leaveVehicle() {
        return player.leaveVehicle();
    }

    @Override
    public Entity getVehicle() {
        return player.getVehicle();
    }

    @Override
    public void setMetadata(String s, MetadataValue metadataValue) {
        player.setMetadata(s, metadataValue);
    }

    @Override
    public List<MetadataValue> getMetadata(String s) {
        return player.getMetadata(s);
    }

    @Override
    public boolean hasMetadata(String s) {
        return player.hasMetadata(s);
    }

    @Override
    public void removeMetadata(String s, Plugin plugin) {
        player.removeMetadata(s, plugin);
    }

    @Override
    public boolean isPermissionSet(String s) {
        return player.isPermissionSet(s);
    }

    @Override
    public boolean isPermissionSet(Permission permission) {
        return player.isPermissionSet(permission);
    }

    @Override
    public boolean hasPermission(String s) {
        return player.hasPermission(s);
    }

    @Override
    public boolean hasPermission(Permission permission) {
        return player.hasPermission(permission);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b) {
        return player.addAttachment(plugin, s, b);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return player.addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i) {
        return player.addAttachment(plugin, s, b, i);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int i) {
        return player.addAttachment(plugin, i);
    }

    @Override
    public void removeAttachment(PermissionAttachment permissionAttachment) {
        player.removeAttachment(permissionAttachment);
    }

    @Override
    public void recalculatePermissions() {
        player.recalculatePermissions();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return player.getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return player.isOp();
    }

    @Override
    public void setOp(boolean b) {
        player.setOp(b);
    }

    @Override
    public boolean isConversing() {
        return player.isConversing();
    }

    @Override
    public void acceptConversationInput(String s) {
        player.acceptConversationInput(s);
    }

    @Override
    public boolean beginConversation(Conversation conversation) {
        return player.beginConversation(conversation);
    }

    @Override
    public void abandonConversation(Conversation conversation) {
        player.abandonConversation(conversation);
    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent conversationAbandonedEvent) {
        player.abandonConversation(conversation, conversationAbandonedEvent);
    }

    @Override
    public void sendMessage(String s) {
        player.sendMessage(s);
    }

    @Override
    public void sendMessage(String[] strings) {
        player.sendMessage(strings);
    }

    @Override
    public boolean isOnline() {
        return player.isOnline();
    }

    @Override
    public boolean isBanned() {
        return player.isBanned();
    }

    @Override
    public void setBanned(boolean b) {
        player.setBanned(b);
    }

    @Override
    public boolean isWhitelisted() {
        return player.isWhitelisted();
    }

    @Override
    public void setWhitelisted(boolean b) {
        player.setWhitelisted(b);
    }

    @Override
    public Player getPlayer() {
        return player.getPlayer();
    }

    @Override
    public long getFirstPlayed() {
        return player.getFirstPlayed();
    }

    @Override
    public long getLastPlayed() {
        return player.getLastPlayed();
    }

    @Override
    public boolean hasPlayedBefore() {
        return player.hasPlayedBefore();
    }

    @Override
    public Map<String, Object> serialize() {
        return player.serialize();
    }

    @Override
    public void sendPluginMessage(Plugin plugin, String s, byte[] bytes) {
        player.sendPluginMessage(plugin, s, bytes);
    }

    @Override
    public Set<String> getListeningPluginChannels() {
        return player.getListeningPluginChannels();
    }
}
