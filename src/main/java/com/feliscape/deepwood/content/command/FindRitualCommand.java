package com.feliscape.deepwood.content.command;

import com.google.common.collect.AbstractIterator;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.ChunkPos;
import org.apache.commons.lang3.Validate;

public class FindRitualCommand {

    static LiteralArgumentBuilder register() {
        return ((LiteralArgumentBuilder) Commands.literal("findritualspot").executes((CommandContext<CommandSourceStack> ctx) -> {
            BlockPos blockPos = BlockPos.containing(ctx.getSource().getPosition());
            var chunk = ctx.getSource().getLevel().getChunk(blockPos);

            return 0;
        }));
    }

    static Iterable<ChunkPos> spiralAround(ChunkPos center, int size, Direction rotationDirection, Direction expansionDirection) {
        Validate.validState(rotationDirection.getAxis() != expansionDirection.getAxis(), "The two directions cannot be on the same axis", new Object[0]);
        return () -> {
            return new AbstractIterator<ChunkPos>() {
                private final Direction[] directions = new Direction[]{rotationDirection, expansionDirection, rotationDirection.getOpposite(), expansionDirection.getOpposite()};
                private final BlockPos.MutableBlockPos cursor = center.getWorldPosition().mutable().move(expansionDirection);
                private final int legs = 4 * size;
                private int leg = -1;
                private int legSize;
                private int legIndex;
                private int lastX;
                private int lastY;
                private int lastZ;

                {
                    this.lastX = this.cursor.getX();
                    this.lastY = this.cursor.getY();
                    this.lastZ = this.cursor.getZ();
                }

                protected ChunkPos computeNext() {
                    this.cursor.set(this.lastX, this.lastY, this.lastZ).move(this.directions[(this.leg + 4) % 4]);
                    this.lastX = this.cursor.getX();
                    this.lastY = this.cursor.getY();
                    this.lastZ = this.cursor.getZ();
                    if (this.legIndex >= this.legSize) {
                        if (this.leg >= this.legs) {
                            return (ChunkPos)this.endOfData();
                        }

                        ++this.leg;
                        this.legIndex = 0;
                        this.legSize = this.leg / 2 + 1;
                    }

                    ++this.legIndex;
                    return new ChunkPos(this.cursor.getX(), this.cursor.getZ());
                }
            };
        };
    }
}
