/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

public class ShutdownThreadHook extends Thread {
    public void run() {
        System.out.println("[INFO] Running shutdown hook.");
        Application.getResourceResolver().destroy();
        System.out.println("[INFO] Completing shutdown..");
    }
}
