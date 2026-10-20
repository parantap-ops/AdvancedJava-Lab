import java.awt.event.*;
import javax.swing.JOptionPane;

public class CalculatorController implements ActionListener {

    CalculatorView view;
    CalculatorModel model;

    public CalculatorController(CalculatorView view, CalculatorModel model) {
        this.view = view;
        this.model = model;

        view.btnAdd.addActionListener(this);
        view.btnSub.addActionListener(this);
        view.btnMul.addActionListener(this);
        view.btnDiv.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        double a, b;

        // Read and validate input from the View
        try {
            a = Double.parseDouble(view.txtNum1.getText().trim());
            b = Double.parseDouble(view.txtNum2.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Please enter valid numbers in both fields");
            return;
        }

        double result = 0;

        if (e.getSource() == view.btnAdd)
            result = model.add(a, b);
        else if (e.getSource() == view.btnSub)
            result = model.subtract(a, b);
        else if (e.getSource() == view.btnMul)
            result = model.multiply(a, b);
        else if (e.getSource() == view.btnDiv) {
            if (b == 0) {
                JOptionPane.showMessageDialog(view, "Division by zero is not allowed");
                return;
            }
            result = model.divide(a, b);
        }

        // Display result back in the View
        view.txtResult.setText(String.valueOf(result));
    }
}