package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("------------------------ Start ---------------------------");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			System.out.println("Failed loading system LookAndFell");
		}
		
		new ConcadenateWavs();
		System.out.println("------------------------ End ---------------------------");

	}

}
