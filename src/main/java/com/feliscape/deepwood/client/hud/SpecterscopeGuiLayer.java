package com.feliscape.deepwood.client.hud;

import com.feliscape.deepwood.Deepwood;
import com.feliscape.deepwood.client.data.ClientSpecterscopeData;
import com.feliscape.deepwood.registry.DeepwoodItems;
import com.feliscape.deepwood.util.MathUtil;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Matrix4f;

import java.util.function.Function;

public class SpecterscopeGuiLayer implements LayeredDraw.Layer {
    public static final ResourceLocation ID = Deepwood.asResource("specterscope");

    private static float time = 0;
    private static float timeSinceInVeil;

    private static final ResourceLocation SCALE = Deepwood.asResource("textures/gui/specterscope/scale.png");
    private static final ResourceLocation POINTER = Deepwood.asResource("textures/gui/specterscope/pointer.png");
    private static final ResourceLocation GLOWING_POINTER = Deepwood.asResource("textures/gui/specterscope/pointer_glowing.png");
    // Because parchment isn't up-to-date, it's blit(RenderType, texture, x, y, u, v, width, height, blitHeight, blitWidth)
    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null && (player.getMainHandItem().is(DeepwoodItems.SPECTERSCOPE) || player.getOffhandItem().is(DeepwoodItems.SPECTERSCOPE))) {
            guiGraphics.blit(RenderType::guiTextured, SCALE, 8, 8, 0F, 0F,
                    10, 66, 10, 66);

            float veilInverted = 16F - ClientSpecterscopeData.getVeil();
            int scaledVeil = Mth.ceil(veilInverted * 4F);

            time += deltaTracker.getRealtimeDeltaTicks();
            timeSinceInVeil = veilInverted >= 14F ? timeSinceInVeil + deltaTracker.getRealtimeDeltaTicks() : 0F;

            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(8F + 7F + 2F, 6F + 64F - (float) scaledVeil - 3.5F, 0F); // We have to move it afterward and separately.
            if (veilInverted >= 14F) {
                float pointerScale = 1F + Mth.sin(time * 0.5F) * 0.1F;
                guiGraphics.pose().scale(pointerScale, pointerScale, pointerScale);
            }
            if (timeSinceInVeil > 0F && timeSinceInVeil < 20F)
                guiGraphics.pose().mulPose(Axis.ZP.rotation(MathUtil.dampedSin(2F, 0.2F, 0F, 1.5F, timeSinceInVeil) * 0.2F));
            centeredBlit(guiGraphics, RenderType::guiTextured, veilInverted >= 14F ? GLOWING_POINTER : POINTER, 0F, 0F, 0F, 0F,
                    6, 7, 6, 7);
            guiGraphics.pose().popPose();
        }
    }

    public void centeredBlit(GuiGraphics guiGraphics, Function<ResourceLocation, RenderType> renderType, ResourceLocation texture, float x, float y, float u, float v, int uvWidth, int uvHeight, int imageWidth, int imageHeight, int color) {
        this.centeredBlit(guiGraphics, renderType, texture, x, y, u, v, uvWidth, uvHeight, uvWidth, uvHeight, imageWidth, imageHeight, color);
    }

    public void centeredBlit(GuiGraphics guiGraphics, Function<ResourceLocation, RenderType> renderType, ResourceLocation texture, float x, float y, float u, float v, int uvWidth, int uvHeight, int imageWidth, int imageHeight) {
        this.centeredBlit(guiGraphics, renderType, texture, x, y, u, v, uvWidth, uvHeight, uvWidth, uvHeight, imageWidth, imageHeight);
    }

    public void centeredBlit(GuiGraphics guiGraphics, Function<ResourceLocation, RenderType> renderType, ResourceLocation texture, float x, float y, float u, float v, int width, int height, int uvWidth, int uvHeight, int imageWidth, int imageHeight) {
        this.centeredBlit(guiGraphics, renderType, texture, x, y, u, v, width, height, uvWidth, uvHeight, imageWidth, imageHeight, -1);
    }

    public void centeredBlit(GuiGraphics guiGraphics, Function<ResourceLocation, RenderType> renderType, ResourceLocation texture, float x, float y, float u, float v, int width, int height, int uvWidth, int uvHeight, int imageWidth, int imageHeight, int color) {
        this.innerCenteredBlit(guiGraphics, renderType, texture, x - width/2F, x + width/2F, y - height/2F, y + height/2F, (u + 0.0F) / (float)imageWidth, (u + (float)uvWidth) / (float)imageWidth, (v + 0.0F) / (float)imageHeight, (v + (float)uvHeight) / (float)imageHeight, color);
    }

    private void innerCenteredBlit(GuiGraphics guiGraphics, Function<ResourceLocation, RenderType> renderType, ResourceLocation location, float leftX, float rightX, float topY, float bottomY, float leftU, float rightU, float topV, float bottomV, int color) {
        guiGraphics.drawSpecial(bufferSource -> {
            RenderType rendertype = (RenderType)renderType.apply(location);
            Matrix4f matrix4f = guiGraphics.pose().last().pose();
            VertexConsumer vertexconsumer = bufferSource.getBuffer(rendertype);
            vertexconsumer.addVertex(matrix4f, (float)leftX, (float)topY, 0.0F).setUv(leftU, topV).setColor(color);
            vertexconsumer.addVertex(matrix4f, (float)leftX, (float)bottomY, 0.0F).setUv(leftU, bottomV).setColor(color);
            vertexconsumer.addVertex(matrix4f, (float)rightX, (float)bottomY, 0.0F).setUv(rightU, bottomV).setColor(color);
            vertexconsumer.addVertex(matrix4f, (float)rightX, (float)topY, 0.0F).setUv(rightU, topV).setColor(color);
        });
    }
}
