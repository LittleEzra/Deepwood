package com.feliscape.deepwood.content.screen;

import com.feliscape.deepwood.Deepwood;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public class ArcFurnaceScreen extends AbstractContainerScreen<ArcFurnaceMenu> {
    private static final ResourceLocation TEXTURE =
            Deepwood.asResource("textures/gui/container/arc_furnace.png");
    private static final ResourceLocation SMELT_PROGRESS =
            Deepwood.asResource("textures/gui/sprites/container/arc_furnace/smelt_progress.png");
    private static final ResourceLocation LIT_PROGRESS =
            Deepwood.asResource("textures/gui/sprites/container/arc_furnace/lit_progress.png");

    @Override
    protected void init() {
        super.init();
        this.imageHeight = 182;
    }

    public ArcFurnaceScreen(ArcFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    // Because parchment isn't up-to-date, it's blit(RenderType, texture, x, y, u, v, width, height, blitHeight, blitWidth)
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        guiGraphics.blit(RenderType::guiTextured, TEXTURE, this.leftPos, this.topPos, 0, 0, 256, 256, imageWidth, imageHeight);


        int scaledCookProgression = this.menu.getCookProgressionScaled();
        Deepwood.LOGGER.debug("{}", scaledCookProgression);
        guiGraphics.blit(RenderType::guiTextured, SMELT_PROGRESS,
                this.leftPos + 23, this.topPos + 38 - scaledCookProgression,
                176, 8,
                66, 22, 66, scaledCookProgression);

        if (menu.isLit()) {
            int scaledLitTime = Mth.ceil(this.menu.getNormalizedLitProgress() * 32.0F);
            guiGraphics.blit(RenderType::guiTextured, LIT_PROGRESS,
                    this.leftPos + 43, this.topPos + 57 + 32 - scaledLitTime,
                    183, 8 - scaledLitTime,
                    26, 32, 26, scaledLitTime);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float pPartialTick) {
        super.render(guiGraphics, mouseX, mouseY, pPartialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
