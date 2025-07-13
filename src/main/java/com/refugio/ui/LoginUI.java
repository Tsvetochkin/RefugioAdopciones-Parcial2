package com.refugio.ui;

import javax.swing.*;
import java.awt.*;
// Предполагается, что у вас будет такой сервисный класс для управления логикой
// import com.refugio.servicio.EmpleadoService;

public class LoginUI extends JFrame {

    // Класс теперь зависит от интерфейса сервиса, а не от конкретной реализации DAO
    // private final EmpleadoService empleadoService;

    private JTextField nombreField;
    private JTextField edadField;
    private JTextField direccionField;
    private JTextField fechaNacimientoField;

    // Сервис должен передаваться в конструктор (Dependency Injection)
    public LoginUI(/*EmpleadoService empleadoService*/) {
        // this.empleadoService = empleadoService;

        setTitle("Ingreso de Empleado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLayout(new GridLayout(5, 2, 10, 10)); // Добавлены отступы для красоты
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


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

        guardarBtn.addActionListener(e -> guardarEmpleado());
        cancelarBtn.addActionListener(e -> System.exit(0));

        // Раскомментируйте setVisible(true) в том месте, где вы создаете этот UI.
        // setVisible(true);
    }

    private void guardarEmpleado() {
        try {
            String nombre = nombreField.getText();
            if (nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error de validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int edad = Integer.parseInt(edadField.getText());
            String direccion = direccionField.getText();
            String fechaNacimiento = fechaNacimientoField.getText();

            // Вся логика сохранения теперь уходит в сервисный слой.
            // UI просто передает данные.
            // empleadoService.guardarNuevoEmpleado(nombre, edad, direccion, fechaNacimiento);

            // Так как сервиса пока нет, для демонстрации выведем сообщение
            System.out.println("UI вызвал бы сервис для сохранения: " + nombre);


            JOptionPane.showMessageDialog(this, "Empleado '" + nombre + "' guardado exitosamente.");
            dispose(); // закрыть окно после сохранения

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            // Здесь будут перехватываться ошибки из сервисного слоя
            JOptionPane.showMessageDialog(this, "Error al guardar empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}