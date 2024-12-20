package com.feliscape.deepwood.content.block.entity;

import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ArcFurnaceItemHandler implements IItemHandler {
    private static final int SLOTS_INPUT = 9;
    private static final int SLOT_FUEL = 9;
    private static final int SLOT_RESULT = 10;
    private final IItemHandler itemHandler;
    private final Direction side;

    public ArcFurnaceItemHandler(IItemHandler itemHandler, @Nullable Direction side) {
        this.itemHandler = itemHandler;
        this.side = side;
    }


    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return itemHandler.isItemValid(slot, stack);
    }

    @Override
    public int getSlots() {
        return itemHandler.getSlots();
    }

    @Override
    @Nonnull
    public ItemStack getStackInSlot(int slot) {
        return itemHandler.getStackInSlot(slot);
    }

    @Override
    @Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (side == null || side.equals(Direction.UP)) {
            return slot < SLOTS_INPUT ? itemHandler.insertItem(slot, stack, simulate) : stack;
        } else {
            return slot == SLOT_FUEL ? itemHandler.insertItem(slot, stack, simulate) : stack;
        }
    }

    @Override
    @Nonnull
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (side == null || side.equals(Direction.UP)) {
            return slot < SLOTS_INPUT ? itemHandler.extractItem(slot, amount, simulate) : ItemStack.EMPTY;
        } else {
            return slot == SLOT_RESULT ? itemHandler.extractItem(slot, amount, simulate) : ItemStack.EMPTY;
        }
    }

    @Override
    public int getSlotLimit(int slot) {
        return itemHandler.getSlotLimit(slot);
    }
}
