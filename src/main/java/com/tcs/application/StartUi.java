/* ********************************************************************************
 * All rights reserved.
 ******************************************************************************* */
package com.tcs.application;

import java.awt.Window;

public class StartUi implements Runnable{

    private Window windowTostart; 
    public StartUi(Window windowToStart) {
        this.windowTostart = windowToStart;        
    }
    
    @Override
    public void run() {
        windowTostart.requestFocus();
        windowTostart.setVisible(true);
    }

}
