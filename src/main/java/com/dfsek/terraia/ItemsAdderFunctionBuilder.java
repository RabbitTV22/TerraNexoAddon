package com.dfsek.terraia;


import com.dfsek.terra.addons.terrascript.parser.lang.Returnable;
import com.dfsek.terra.addons.terrascript.parser.lang.functions.FunctionBuilder;
import com.dfsek.terra.addons.terrascript.tokenizer.Position;

import java.util.List;

public class ItemsAdderFunctionBuilder implements FunctionBuilder<ItemsAdderFunction> {

    @SuppressWarnings("unchecked")
    @Override
    public ItemsAdderFunction build(List argumentList, Position position) {
        return new ItemsAdderFunction(
            position,
            (Returnable<Number>) argumentList.get(0),
            (Returnable<Number>) argumentList.get(1),
            (Returnable<Number>) argumentList.get(2),
            (Returnable<String>) argumentList.get(3));
    }

    @Override
    public int argNumber() {
        return 4;
    }

    @Override
    public Returnable.ReturnType getArgument(int position) {
        return switch (position) {
            case 0, 1, 2 -> Returnable.ReturnType.NUMBER;
            case 3 -> Returnable.ReturnType.STRING;
            default -> null;
        };
    }
}
