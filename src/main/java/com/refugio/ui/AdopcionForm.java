package com.refugio.ui;

import com.refugio.adopcion.Adopcion;
import com.refugio.adopcion.AdopcionFactory;
import com.refugio.persona.Adoptante;
import com.refugio.persona.Empleado;
import com.refugio.mascota.Mascota;
import com.refugio.util.Contenedor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdopcionForm extends JFrame {

    private JComboBox<Adoptante> comboAdoptante;
    private JComboBox<Empleado> comboEmpleado;
    private JComboBox<Mascota> comboMascota;

    public AdopcionForm() {
        setTitle("Formulario de Adopción");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        comboAdoptante = new JComboBox<>(getAdoptantes());
        comboEmpleado = new JComboBox<>(getEmpleados());
        comboMascota = new JComboBox<>(getMascotas());

        JButton adoptarBtn = new JButton("Adoptar");
        JButton cancelarBtn = new JButton("Cancelar");

        adoptarBtn.addActionListener(e -> procesarAdopcion());
        cancelarBtn.addActionListener(e -> dispose());

        panel.add(new JLabel("Adoptante:"));
        panel.add(comboAdoptante);
        panel.add(new JLabel("Empleado:"));
        panel.add(comboEmpleado);
        panel.add(new JLabel("Mascota:"));
        panel.add(comboMascota);
        panel.add(adoptarBtn);
        panel.add(cancelarBtn);

        add(panel);
    }

    private Adoptante[] getAdoptantes() {
        List<Adoptante> lista = Contenedor.getInstancia().getAdoptantes();
        return lista.toArray(new Adoptante[0]);
    }

    private Empleado[] getEmpleados() {
        List<Empleado> lista = Contenedor.getInstancia().getEmpleados();
        return lista.toArray(new Empleado[0]);
    }

    private Mascota[] getMascotas() {
        List<Mascota> lista = Contenedor.getInstancia().getMascotas();
        return lista.toArray(new Mascota[0]);
    }

    private void procesarAdopcion() {
        try {
            Adoptante adoptante = (Adoptante) comboAdoptante.getSelectedItem();
            Empleado empleado = (Empleado) comboEmpleado.getSelectedItem();
            Mascota mascota = (Mascota) comboMascota.getSelectedItem();

            if (adoptante == null || empleado == null || mascota == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar todas las opciones.");
                return;
            }

            Adopcion<?> adopcion = AdopcionFactory.crearAdopcion(mascota, adoptante, empleado);
            Contenedor.getInstancia().agregarAdopcion(adopcion);

            JOptionPane.showMessageDialog(this, "Adopción registrada exitosamente.");
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar adopción: " + e.getMessage());
        }
    }
}