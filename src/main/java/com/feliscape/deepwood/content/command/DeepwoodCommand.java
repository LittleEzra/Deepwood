package com.feliscape.deepwood.content.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;

public class DeepwoodCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(LiteralArgumentBuilder.literal("deepwood")
                .then(FindRitualCommand.register()));
    }
}
