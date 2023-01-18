package gui;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUIPopUpMessages {
	
	/*public static int confirmBox(String text, String title) {
		
	}*/
	public static int customBox(String text, String title, Object[] buttons, int messageType) {
		return JOptionPane.showOptionDialog(new JFrame(), text, title,
				JOptionPane.YES_NO_OPTION, messageType, null,
				buttons, JOptionPane.YES_OPTION);
	}
	
	public static int okBackBox(String text, String title) {
		return JOptionPane.showOptionDialog(new JFrame(), text, title,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "OK", "Tilbage" }, JOptionPane.YES_OPTION);
	}
	public static void informationMessage(String text, String title) {
		JOptionPane.showMessageDialog(null, text, title,
				JOptionPane.INFORMATION_MESSAGE);
	}
	public static void warningMessage(String text, String title) {
		JOptionPane.showMessageDialog(null, text, title,
				JOptionPane.ERROR_MESSAGE);
	}
}
