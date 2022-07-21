package com.zou.corejava.ooad.Chain;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class CommandTwo implements Command {
    @Override
    public boolean execute(Context context) throws Exception {
        int key = (int) context.get("Key");
        if(key==3){
            System.out.println("two work");
        }
        return false;
    }
}
