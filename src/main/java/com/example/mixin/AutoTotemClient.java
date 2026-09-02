package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.Client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class AutoTotemClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.interactionManager == null) return;

            if (client.currentScreen != null) return;

            if (client.player.getOffHandStack().getItem() != Items.TOTEM_OF_UNDYING) {

                for (int i = 0; i < 36; i++) {
                    if (client.player.getInventory().getStack(i).getItem() == Items.TOTEM_OF_UNDYING) {

                        int slotId = (i < 9) ? i + 36 : i;

                        client.interactionManager.clickSlot(
                            client.player.playerScreenHandler.syncId,
                            slotId,
                            40,
                            SlotActionType.SWAP,
                            client.player
                        );
                        break;
                    }
                }
            }
        });
    }
}