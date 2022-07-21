package com.zou.corejava.ooad.Chain;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class CommandOne implements Command {
    @Override
    public boolean execute(Context context) throws Exception {
        int key = (int) context.get("Key");
        if(key==1){
            System.out.println(" 1 work");
        }
        return false;
    }
}
