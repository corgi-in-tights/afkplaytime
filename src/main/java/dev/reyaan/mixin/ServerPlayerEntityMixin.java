package dev.reyaan.mixin;

import com.fibermc.essentialcommands.playerdata.PlayerDataManager;
import com.mojang.authlib.GameProfile;
import dev.reyaan.PlaytimePerms;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {
    @Shadow protected abstract void updateScores(ScoreboardCriterion criterion, int score);

    int playtime;

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
        this.playtime = 0;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At(value = "TAIL"))
    private void playtimeperms$writeNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("Playtime", playtime);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At(value = "TAIL"))
    private void playtimeperms$readNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("Playtime")) this.setPlaytime(nbt.getInt("Playtime"));
    }

    @Inject(method = "playerTick", at = @At(value = "TAIL"))
    private void playtimeperms$playerTick(CallbackInfo ci) {
        int new_pt = this.getPlaytime()+1;

        var p = PlayerDataManager.getInstance().getByUuid(this.getUuid());
        if (p != null && !p.isAfk()) {
            this.setPlaytime(new_pt);
            this.updateScores(PlaytimePerms.PLAYTIME, new_pt);
        }
    }

    public int getPlaytime() {
        return playtime;
    }

    public void setPlaytime(int v) {
        this.playtime = v;
    }

}
