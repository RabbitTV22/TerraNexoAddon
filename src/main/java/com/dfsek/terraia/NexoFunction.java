package com.dfsek.terraia;

import com.dfsek.terra.addons.terrascript.parser.lang.ImplementationArguments;
import com.dfsek.terra.addons.terrascript.parser.lang.Returnable;
import com.dfsek.terra.addons.terrascript.parser.lang.Scope;
import com.dfsek.terra.addons.terrascript.parser.lang.functions.Function;
import com.dfsek.terra.addons.terrascript.script.TerraImplementationArguments;
import com.dfsek.terra.addons.terrascript.tokenizer.Position;
import com.dfsek.terra.api.util.vector.Vector3Int;
import com.nexomc.nexo.api.NexoBlocks;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.generator.WorldInfo;

public class NexoFunction implements Function<Void> {
    private final Position position;
    private final Returnable<Number> x, y, z;
    private final Returnable<String> block;

    public NexoFunction(Position position, Returnable<Number> x, Returnable<Number> y, Returnable<Number> z, Returnable<String> block) {
        this.position = position;
        this.x = x;
        this.y = y;
        this.z = z;
        this.block = block;
    }

    @Override
    public ReturnType returnType() {
        return ReturnType.VOID;
    }

    @Override
    public Void apply(ImplementationArguments implementationArguments, Scope scope) {
        TerraImplementationArguments terraImplArguments = (TerraImplementationArguments) implementationArguments;
        Vector3Int position = terraImplArguments.getOrigin();
        Location location = new Location(
            Bukkit.getWorld(((WorldInfo) terraImplArguments.getWorld().getHandle()).getUID()),
            position.getX() + x.apply(implementationArguments, scope).doubleValue(),
            position.getY() + y.apply(implementationArguments, scope).doubleValue(),
            position.getZ() + z.apply(implementationArguments, scope).doubleValue()
        );

        NexoBlocks.place(block.apply(implementationArguments, scope), location);

        return null;
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
