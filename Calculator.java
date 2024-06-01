import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Write a description of class Calculator here:
 * 
 * Description: This class is a simple calculator app that I made. It will
 * display a functioning calculator onto the screen and allow the user to
 * click on the buttons to perform calculations. A previous iteration of this
 * project had the calculator appear to the console, but after learning about
 * graphics and ActionListener in my most recent CSS course, I decided to update
 * the code in this project to appear as a window and a separate screen.
 * Example: If the user wanted to do 5 * 5, they would need to click these buttons
 * in this order, 5, *, 5, =
 * Then the result will be printed in the bar.
 * 
 * PRE: NONE
 * POST: Will display a functioning calculator app
 *
 * @author Sushanth Ramesh
 * @version 4/15/24
 * @recentversion 5/21/24
 */
public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double num1, num2, result;
    private char operator;
    private boolean isOperatorClicked;
    
    /**
     * constructor #1(no-arg) - Calculator
     * -----------------------------------
     * Description: This no-arg constructor simply creates the display
     *              of the calculator app with all the correct buttons.
     * PRE: NONE
     * POST: Will create a Calculator object.
     */
    public Calculator() {
        setTitle("Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 32));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };
        
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.addActionListener(this);
            panel.add(button);
        }
        
        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }
    
    /**
     * actionPerformed
     * ---------------
     * Description: This method overrides the actionPerformed method from the
     *              ActionEvent class and it is used to give the buttons operations
     *              and values. This way if the user decided to do 5 * 5, the '*' would
     *              know to do multiplication.
     * PRE: The input parameter e must be an ActionEvent object.
     * POST: Will perform the calculations of the calculator accordingly.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (command.charAt(0) == 'C') {
            display.setText("");
        } else if (command.charAt(0) == '=') {
            num2 = Double.parseDouble(display.getText());
            
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }
            
            display.setText(String.valueOf(result));
            num1 = result;
        } else if ("+-*/".contains(command)) {
            num1 = Double.parseDouble(display.getText());
            operator = command.charAt(0);
            display.setText("");
        } else {
            display.setText(display.getText() + command);
        }
    }
    
    /**
     * main
     * ----
     * Description: Small main class here to just create the calculator object and run it.
     * PRE: NONE
     * POST: Creates a window with the Calculator fully functionable.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}
