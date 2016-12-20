package com.tcs.application.tray;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.tcs.application.Application;
import com.tcs.application.Subscriber;
import com.tcs.application.SubscriptionEvent;
import com.tcs.tools.resources.ResourceLocator;

public class ApplicationTray implements Subscriber {
	private boolean started;

	public ApplicationTray() {
		Application.getSubscriptionManager().subscribe(this,Application.START);
	}
	
	public void startTray() {
		if (!SystemTray.isSupported()) {
			System.out.println("System Tray is not supported!");
		}
		final PopupMenu popup = new PopupMenu();
		final TrayIcon trayIcon = new TrayIcon(ResourceLocator.getImageIcon("pic1.png").getImage());

		SystemTray sysTray = SystemTray.getSystemTray();
		MenuItem aboutItem = new MenuItem("About");
		// CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
		// CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
		// Menu displayMenu = new Menu("Display");
		// MenuItem errorItem = new MenuItem("Error");
		// MenuItem warningItem = new MenuItem("Warning");
		// MenuItem infoItem = new MenuItem("Info");
		// MenuItem noneItem = new MenuItem("None");
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Add components to pop-up menu
		popup.add(aboutItem);
		popup.addSeparator();
		// popup.add(cb1);
		// popup.add(cb2);
		// popup.addSeparator();
		// popup.add(displayMenu);
		// displayMenu.add(errorItem);
		// displayMenu.add(warningItem);
		// displayMenu.add(infoItem);
		// displayMenu.add(noneItem);
		popup.add(exitItem);
		trayIcon.setPopupMenu(popup);
		try {
			sysTray.add(trayIcon);
			started = true;
			new TrayAnimator(trayIcon).start();
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
		}
	}

	public static void main(String args[]) {
		ApplicationTray tray = new ApplicationTray();
		tray.startTray();
	}

	@Override
	public void onSubscriptionEvent(SubscriptionEvent event) throws Exception {
		switch (event.getEvent()) {
		case Application.START:
			if (!started)
				startTray();
			break;
		}
	}
}
