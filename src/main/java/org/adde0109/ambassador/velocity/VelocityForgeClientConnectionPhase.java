package org.adde0109.ambassador.velocity;

import com.velocitypowered.api.event.Continuation;
import com.velocitypowered.proxy.connection.MinecraftConnection;
import com.velocitypowered.proxy.connection.client.ClientConnectionPhase;
import com.velocitypowered.proxy.connection.client.ConnectedPlayer;
import com.velocitypowered.proxy.protocol.packet.LoginPluginResponse;
import org.adde0109.ambassador.forge.ForgeHandshakeUtils;

import javax.annotation.Nullable;

public interface VelocityForgeClientConnectionPhase extends ClientConnectionPhase {
  //TODO:Make class when PCF is done

  default void handleLogin(ConnectedPlayer player, @Nullable ForgeHandshakeUtils.CachedServerHandshake initialHandshake, Continuation continuation) {
  }
  default boolean handle(ConnectedPlayer player,LoginPluginResponse packet) {
    return false;
  }
}
