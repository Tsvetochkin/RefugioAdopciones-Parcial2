package com.refugio.ui;

import com.refugio.model.persona.Empleado;
import com.refugio.util.Contenedor;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class EmpleadoForm extends JFrame {

    private JTextField nombreField;
    private JTextField edadField;
    private JTextField direccionField;
    private JTextField fechaNacimientoField;

    public EmpleadoForm() {
        setTitle("Ingreso de Empleado");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();

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
            int edad = Integer.parseInt(edadField.getText());
            String direccion = direccionField.getText();
            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoField.getText());

            Empleado empleado = Empleado.getInstancia(nombre, edad, direccion, fechaNacimiento.toString());

            Contenedor.getInstancia().agregarEmpleado(empleado);

            JOptionPane.showMessageDialog(this, "Empleado guardado exitosamente.");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el empleado: " + ex.getMessage());
        }
    }
}