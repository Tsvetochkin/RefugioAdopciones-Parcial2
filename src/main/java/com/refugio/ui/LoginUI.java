package com.refugio.ui;

import com.refugio.dao.EmpleadoDAOH2;
import com.refugio.persona.Empleado;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {

    private JTextField nombreField;
    private JTextField edadField;
    private JTextField direccionField;
    private JTextField fechaNacimientoField;

    public LoginUI() {
        setTitle("Ingreso de Empleado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Edad:"));
        edadField = new JTextField();
        add(edadField);

        add(new JLabel("Dirección:"));
        direccionField = new JTextField();
        add(direccionField);

        add(new JLabel("Fecha Nacimiento (yyyy-MM-dd):"));
        fechaNacimientoField = new JTextField();
        add(fechaNacimientoField);

        JButton guardarBtn = new JButton("Guardar");
        add(guardarBtn);

        JButton cancelarBtn = new JButton("Cancelar");
        add(cancelarBtn);

        guardarBtn.addActionListener(e -> {
            try {
                String nombre = nombreField.getText();
                int edad = Integer.parseInt(edadField.getText());
                String direccion = direccionField.getText();
                String fechaNacimiento = fechaNacimientoField.getText();

                Empleado empleado = Empleado.getInstancia(nombre, edad, direccion, fechaNacimiento);
                EmpleadoDAOH2 dao = new EmpleadoDAOH2(); // ✅ конкретная реализация DAO
                dao.guardar(empleado);

                JOptionPane.showMessageDialog(this, "Empleado guardado:\n" + nombre + ", edad " + edad);
                dispose(); // закрыть окно после сохранения

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Edad debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelarBtn.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}