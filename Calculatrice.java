
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculatrice extends JFrame {

	private String[] tabString = {"1", "2", "3", "4", "5", "6", "7", "8",
	"9", "0", ".", "=", "C", "+", "-", "*", "/"};
	private JButton[] tabButtons = new JButton[tabString.length];

	private JPanel container = new JPanel();
	private JPanel operatorPanel = new JPanel();
	private JPanel numberPanel = new JPanel();

	private JLabel screen = new JLabel("0");

	private double number1;
	private String operator = "";

	private boolean isToClear = false;

	public Calculatrice() {
		this.setTitle("Ma Calculatrice Java");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initButtons();

		numberPanel.setLayout(new GridLayout(4, 3, 5, 5));
		operatorPanel.setLayout(new GridLayout(5, 1, 5, 5));

		screen.setFont(new Font("Tahoma", Font.BOLD, 24));

		container.setLayout(new BorderLayout());
		container.add(numberPanel, BorderLayout.WEST);
		container.add(operatorPanel, BorderLayout.EAST);
		container.add(screen, BorderLayout.NORTH);

		this.setContentPane(container);
		this.setVisible(true);
	}
	
	public void initButtons() {
	
		for (int i=0; i<tabString.length; i++) {
			tabButtons[i] = new JButton(tabString[i]);
			
			if (tabString[i].equals("=")) {
				tabButtons[i].addActionListener(new ButtonListenerEqual());
				numberPanel.add(tabButtons[i]);
			}
			else if (tabString[i].equals("+")) {
				tabButtons[i].addActionListener(new ButtonListenerSum());
				operatorPanel.add(tabButtons[i]);
			}
			else if (tabString[i].equals("-")) {
				tabButtons[i].addActionListener(new ButtonListenerMinus());
				operatorPanel.add(tabButtons[i]);
			}
			else if (tabString[i].equals("*")) {
				tabButtons[i].addActionListener(new ButtonListenerMulti());
				operatorPanel.add(tabButtons[i]);
			}
			else if (tabString[i].equals("/")) {
				tabButtons[i].addActionListener(new ButtonListenerDivide());
				operatorPanel.add(tabButtons[i]);
			}
			else if (tabString[i].equals("C")) {
				tabButtons[i].setForeground(Color.RED);
				tabButtons[i].addActionListener(new ButtonListenerReset());
				operatorPanel.add(tabButtons[i]);
			} else {
				tabButtons[i].addActionListener(new ButtonListenerNumber());
				numberPanel.add(tabButtons[i]);
			}

		}
	}

	public void calcul() {
			
		if (operator != "") {

			if (operator == "+") {
				number1 = number1 + Double.parseDouble(screen.getText());
				screen.setText(String.valueOf(number1));
			}
			if (operator == "-") {
				number1 = number1 - Double.parseDouble(screen.getText());
				screen.setText(String.valueOf(number1));
			}
			if (operator == "*") {
				number1 = number1 * Double.parseDouble(screen.getText());
				screen.setText(String.valueOf(number1));
			}
			if (operator == "/") {
				try {
					number1 = number1 / Double.parseDouble(screen.getText());
					screen.setText(String.valueOf(number1));
				} catch (ArithmeticException e) {
					screen.setText("division by 0");
				}
			}

		}
	}

	public class ButtonListenerEqual implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			calcul();
			operator = "";
			isToClear = true;
		}
	}

	public class ButtonListenerSum implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (operator == "") {
				number1 = Double.parseDouble(screen.getText());
				// screen.setText(String.valueOf(number1));
			} else {
				calcul();
			}
			operator = "+";
			isToClear = true;
		}
	}

	public class ButtonListenerMinus implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (operator == "") {
				number1 = Double.parseDouble(screen.getText());
				// screen.setText(String.valueOf(number1));
			} else {
				calcul();
			}
			operator = "-";
			isToClear = true;
		}
	}

	public class ButtonListenerMulti implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (operator == "") {
				number1 = Double.parseDouble(screen.getText());
				// screen.setText(String.valueOf(number1));
			} else {
				calcul();
			}
			operator = "*";
			isToClear = true;
		}
	}

	public class ButtonListenerDivide implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (operator == "") {
				number1 = Double.parseDouble(screen.getText());
				// screen.setText(String.valueOf(number1));
			} else {
				calcul();
			}
			operator = "/";
			isToClear = true;
		}
	}

	public class ButtonListenerReset implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			screen.setText("0");
			number1 = 0;
			operator = "";
		}
	}

	public class ButtonListenerNumber implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			String str = ((JButton)arg0.getSource()).getText();

			if (isToClear) {
				screen.setText(str);
				isToClear = false;
			}
			else if ((screen.getText() != "0")){
				str = screen.getText() + str;
			}
			screen.setText(str);

		}
	}
}