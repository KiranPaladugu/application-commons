/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

public class ActionManager {

    private static final ActionManager manager = new ActionManager();
    public static final ActionManager getActionManager(){
        return manager;
    }
    
    public void exitApplicaiton(Object source,Object data){
        Application.getSubscriptionManager().notifySubscriber(Application.EXIT, source,data);
        
    }
}
