package com.k2senterprise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ScientificCalculator extends JFrame implements ActionListener {

    private JTextField display;
    private String lastButton;
    private List<String> expression;

    public ScientificCalculator() {
        expression = new ArrayList<>();
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 5, 5));

        String[] buttonLabels = {
                "C", "+/-", "%", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "=", "^",
                "sin", "cos", "tan", "sqrt"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ScientificCalculator::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        lastButton = command;

        switch (command) {
            case "C":
                display.setText("");
                expression.clear();
                break;
            case "=":
                try {
                    double result = evaluateExpression(expression);
                    display.setText(String.valueOf(result));
                    expression.clear();
                    expression.add(String.valueOf(result));
                } catch (Exception ex) {
                    display.setText("Error");
                    expression.clear();
                }
                break;
            case "+/-":
                if (!display.getText().isEmpty()) {
                    double currentValue = Double.parseDouble(display.getText());
                    display.setText(String.valueOf(-currentValue));
                    if (!expression.isEmpty()) {
                        expression.set(expression.size() - 1, String.valueOf(-currentValue));
                    }
                }
                break;
            case "sin":
            case "cos":
            case "tan":
            case "sqrt":
                if (!display.getText().isEmpty()) {
                    double value = Double.parseDouble(display.getText());
                    double result = applyFunction(command, value);
                    display.setText(String.valueOf(result));
                    expression.clear();
                    expression.add(String.valueOf(result));
                }
                break;
            default:
                if (isNumber(command) || command.equals(".")) {
                    if (!expression.isEmpty() && isNumber(expression.get(expression.size() - 1)) && isNumber(lastButton)) {
                        expression.set(expression.size() - 1, expression.get(expression.size() - 1) + command);
                    } else {
                        expression.add(command);
                    }
                    display.setText(display.getText() + command);
                } else {
                    expression.add(command);
                    display.setText(display.getText() + command);
                }
                break;
        }
    }

    private double evaluateExpression(List<String> tokens) {
        Stack<Double> numbers = new Stack<>();
        Stack<String> operations = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                numbers.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                while (!operations.isEmpty() && hasPrecedence(token, operations.peek())) {
                    numbers.push(applyOperator(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.push(token);
            } else if (token.equals("(")) {
                operations.push(token);
            } else if (token.equals(")")) {
                while (!operations.peek().equals("(")) {
                    numbers.push(applyOperator(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.pop();
            }
        }
        while (!operations.isEmpty()) {
            numbers.push(applyOperator(operations.pop(), numbers.pop(), numbers.pop()));
        }
        return numbers.pop();
    }

    private double applyFunction(String function, double operand) {
        switch (function) {
            case "sin":
                return Math.sin(Math.toRadians(operand));
            case "cos":
                return Math.cos(Math.toRadians(operand));
            case "tan":
                return Math.tan(Math.toRadians(operand));
            case "sqrt":
                return Math.sqrt(operand);
            default:
                throw new IllegalArgumentException("Invalid function: " + function);
        }
    }

    private double applyOperator(String operator, double b, double a) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            case "^":
                return Math.pow(a, b);
            case "%":
                return a % b;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^") || token.equals("%");
    }

    private boolean hasPrecedence(String op1, String op2) {
        if (op2.equals("(") || op2.equals(")")) return false;
        if ((op1.equals("*") || op1.equals("/")) && (op2.equals("+") || op2.equals("-"))) return false;
        return !((op1.equals("^")) && (op2.equals("*") || op2.equals("/") || op2.equals("+") || op2.equals("-")));
    }

    private boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}