package com.refugio.ui;

import com.refugio.RefugioApplication;
import com.refugio.model.persona.Empleado;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class LoginUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginUI() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLayout(new GridLayout(3, 2, 10, 10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginBtn = new JButton("Login");
        add(loginBtn);

        JButton registerBtn = new JButton("Register");
        add(registerBtn);

        loginBtn.addActionListener(e -> login());
        registerBtn.addActionListener(e -> openRegistration());

        setLocationRelativeTo(null); // Center the frame
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        Optional<Empleado> empleadoOptional = RefugioApplication.empleadoDAO.findByNombre(username);

        if (empleadoOptional.isPresent() && empleadoOptional.get().getPassword().equals(password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            dispose();
            new AdopcionForm().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openRegistration() {
        // Close the login window and open the registration window
        dispose();
        new RegistroUI().setVisible(true);
    }
}