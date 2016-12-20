package com.tcs.application.tray;

import java.awt.Image;
import java.awt.TrayIcon;

import com.tcs.application.Subscriber;
import com.tcs.application.SubscriptionEvent;
import com.tcs.tools.resources.ResourceLocator;

public class TrayAnimator extends Thread implements Subscriber {

	private TrayIcon icon;
	private boolean flag;
	private Image img1, img2;

	public TrayAnimator(TrayIcon icon) {
		this.icon = icon;
		img1 = ResourceLocator.getImageIcon("pic1.png").getImage();
		img2 = ResourceLocator.getImageIcon("pic2.png").getImage();
	}

	@Override
	public void onSubscriptionEvent(SubscriptionEvent event) throws Exception {
	}

	public void run() {
		while (true) {
			if (flag) {
				icon.setImage(img1);
			} else {
				icon.setImage(img2);
			}
			flag = !flag;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
