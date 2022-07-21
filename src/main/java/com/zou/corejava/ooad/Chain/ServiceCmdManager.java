package com.zou.corejava.ooad.Chain;


import org.apache.commons.chain.Chain;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ChainBase;
import org.apache.commons.chain.impl.ContextBase;

/**
 * 责任链之：业务受理责任链管理器
 */
public class ServiceCmdManager {

    private static Chain chainList = new ChainBase();

    static {
        chainList.addCommand(new CommandOne());
        chainList.addCommand(new CommandTwo());
        chainList.addCommand(new CommandThree());
    }

    public static void execute(Context context) throws Exception{
        chainList.execute(context);
    }

    public static void main(String[] args) throws Exception {
        Context context = new ContextBase();
        context.put("Key",3);
        ServiceCmdManager.execute(context);
    }
}
