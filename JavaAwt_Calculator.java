/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prac;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author naris
 */

public class Prac {

    private final JFrame frame;
    private final JTextField textField;
    private String currentInput = "";
    private double firstNum = 0;
    private String operator = "";

    
    public Prac() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setBackground(Color.BLUE);
        frame.setLocationRelativeTo(null); 

        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        
        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.PLAIN, 36)); 
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        textField.setHorizontalAlignment(SwingConstants.RIGHT); 
        textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        panel.add(textField, BorderLayout.NORTH);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4, 10, 10));  
        buttonPanel.setBackground(Color.GRAY);  

        
        String[] buttonLabels = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", ".", "^", "/",
            "%", "√", "C", "=" 
        };

        
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 24)); 
            button.setBackground(Color.BLACK); 
            button.setForeground(Color.WHITE);  
            button.setFocusPainted(false); 
            button.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
            button.addActionListener(new ButtonClickListener(button));  
            buttonPanel.add(button);
        }
        panel.add(buttonPanel, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private final JButton button; 

        public ButtonClickListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "=" -> calculate();
                case "/", "*", "-", "+" -> {
                    if (!currentInput.isEmpty()) {
                        firstNum = Double.parseDouble(currentInput);
                        operator = command;
                        currentInput = "";  
                        textField.setText("");  
                    }
                }
                case "C" -> {
                    currentInput = "";
                    firstNum = 0;
                    operator = "";
                    textField.setText(currentInput);
                }
                case "√" -> {
                    if (!currentInput.isEmpty()) {
                        double num = Double.parseDouble(currentInput);
                        currentInput = String.valueOf(Math.sqrt(num)); 
                        textField.setText(currentInput);
                    }
                }
                case "%" -> {
                    if (!currentInput.isEmpty()) {
                        double num = Double.parseDouble(currentInput);
                        currentInput = String.valueOf(num / 100); 
                        textField.setText(currentInput);
                    }
                }
                case "^" -> {
                    if (!currentInput.isEmpty()) {
                        double num = Double.parseDouble(currentInput);
                        currentInput = String.valueOf(Math.pow(num, 2)); 
                        textField.setText(currentInput);
                    }
                }
                default -> {
                    
                    if (currentInput.equals("0")) {
                        currentInput = command; 
                    } else {
                        currentInput += command;  
                    }   
                    textField.setText(currentInput);  
                }
            }
        }

        private void calculate() {
            if (currentInput.isEmpty()) return;

            double secondNum = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+" -> result = firstNum + secondNum;
                case "-" -> result = firstNum - secondNum;
                case "*" -> result = firstNum * secondNum;
                case "/" -> {
                    if (secondNum != 0) {
                        result = firstNum / secondNum;
                    } else {
                        textField.setText("Error");
                        return;
                    }
                }
            }

            textField.setText(String.valueOf(result));
            currentInput = String.valueOf(result); 
        }
    }

    public static void main(String[] args) {
       
        new Prac();
    }
}