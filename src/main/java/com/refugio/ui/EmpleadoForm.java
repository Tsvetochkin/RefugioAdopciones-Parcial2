package com.refugio.ui;

import com.refugio.dao.EmpleadoDAO;
import com.refugio.model.persona.Empleado;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class EmpleadoForm extends JFrame {

    private final EmpleadoDAO empleadoDAO;
    private JTextField nombreField;
    private JTextField edadField;
    private JTextField direccionField;
    private JTextField fechaNacimientoField;
    private JPasswordField passwordField;

    public EmpleadoForm(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
        setTitle("Ingreso de Empleado");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JLabel edadLabel = new JLabel("Edad:");
        edadField = new JTextField();

        JLabel direccionLabel = new JLabel("Dirección:");
        direccionField = new JTextField();

        JLabel fechaNacimientoLabel = new JLabel("Fecha Nacimiento (yyyy-MM-dd):");
        fechaNacimientoField = new JTextField();

        JButton guardarBtn = new JButton("Guardar");
        JButton cancelarBtn = new JButton("Cancelar");

        guardarBtn.addActionListener(e -> guardarEmpleado());
        cancelarBtn.addActionListener(e -> dispose());

        panel.add(nombreLabel);
        panel.add(nombreField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(edadLabel);
        panel.add(edadField);
        panel.add(direccionLabel);
        panel.add(direccionField);
        panel.add(fechaNacimientoLabel);
        panel.add(fechaNacimientoField);
        panel.add(guardarBtn);
        panel.add(cancelarBtn);

        add(panel);
    }

    private void guardarEmpleado() {
        try {
            String nombre = nombreField.getText();
            String password = new String(passwordField.getPassword());
            int edad = Integer.parseInt(edadField.getText());
            String direccion = direccionField.getText();
            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoField.getText());

            if (nombre.trim().isEmpty() || password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre y la contraseña no pueden estar vacíos.", "Error de validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Empleado empleado = new Empleado(nombre, edad, direccion, fechaNacimiento.toString(), password);
            empleadoDAO.save(empleado);

            JOptionPane.showMessageDialog(this, "Empleado guardado exitosamente.");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el empleado: " + ex.getMessage());
        }
    }
}