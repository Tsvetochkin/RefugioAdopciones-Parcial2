package com.refugio.ui;

import com.refugio.dao.AdopcionDAO;
import com.refugio.dao.AdoptanteDAO;
import com.refugio.dao.EmpleadoDAO;
import com.refugio.dao.MascotaDAO;
import com.refugio.model.persona.Empleado;
import com.refugio.servicio.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class LoginUI extends JFrame {

    private final AdoptanteDAO adoptanteDAO;
    private final EmpleadoDAO empleadoDAO;
    private final MascotaDAO mascotaDAO;
    private final AdopcionDAO adopcionDAO;

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginUI(AdoptanteDAO adoptanteDAO, EmpleadoDAO empleadoDAO, MascotaDAO mascotaDAO, AdopcionDAO adopcionDAO) {
        this.adoptanteDAO = adoptanteDAO;
        this.empleadoDAO = empleadoDAO;
        this.mascotaDAO = mascotaDAO;
        this.adopcionDAO = adopcionDAO;

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

        setLocationRelativeTo(null);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        Optional<Empleado> empleadoOptional = empleadoDAO.findByNombreAndPassword(username, password);

        if (empleadoOptional.isPresent()) {
            Empleado empleado = empleadoOptional.get();
            SessionManager.getInstance().login(empleado);
            JOptionPane.showMessageDialog(this, "Login successful!");
            dispose();
            new AdopcionesUI(adopcionDAO, adoptanteDAO, empleadoDAO, mascotaDAO).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openRegistration() {
        dispose();
        new RegistroUI(adoptanteDAO, empleadoDAO, mascotaDAO, adopcionDAO).setVisible(true);
    }
}