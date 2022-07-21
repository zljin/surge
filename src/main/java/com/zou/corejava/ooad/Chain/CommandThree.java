package com.zou.corejava.ooad.Chain;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class CommandThree implements Command {
    @Override
    public boolean execute(Context context) throws Exception {
        int key = (int) context.get("Key");
        if(key==2){
            System.out.println("3 work");
        }
        return false;
    }
}
