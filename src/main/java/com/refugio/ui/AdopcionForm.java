package com.refugio.ui;

import com.refugio.dao.AdopcionDAO;
import com.refugio.dao.AdoptanteDAO;
import com.refugio.dao.EmpleadoDAO;
import com.refugio.dao.MascotaDAO;
import com.refugio.model.adopcion.Adopcion;
import com.refugio.model.adopcion.AdopcionFactory;
import com.refugio.model.persona.Adoptante;
import com.refugio.model.persona.Empleado;
import com.refugio.model.mascota.Mascota;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdopcionForm extends JFrame {

    private final AdoptanteDAO adoptanteDAO;
    private final EmpleadoDAO empleadoDAO;
    private final MascotaDAO mascotaDAO;
    private final AdopcionDAO adopcionDAO;

    private JComboBox<Adoptante> comboAdoptante;
    private JComboBox<Empleado> comboEmpleado;
    private JComboBox<Mascota> comboMascota;

    public AdopcionForm(AdoptanteDAO adoptanteDAO, EmpleadoDAO empleadoDAO, MascotaDAO mascotaDAO, AdopcionDAO adopcionDAO) {
        this.adoptanteDAO = adoptanteDAO;
        this.empleadoDAO = empleadoDAO;
        this.mascotaDAO = mascotaDAO;
        this.adopcionDAO = adopcionDAO;

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
        cancelarBtn.addActionListener(e -> {
            dispose();
            new LoginUI(adoptanteDAO, empleadoDAO, mascotaDAO, adopcionDAO).setVisible(true);
        });

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
        List<Adoptante> lista = adoptanteDAO.findAll();
        return lista.toArray(new Adoptante[0]);
    }

    private Empleado[] getEmpleados() {
        List<Empleado> lista = empleadoDAO.findAll();
        return lista.toArray(new Empleado[0]);
    }

    private Mascota[] getMascotas() {
        List<Mascota> lista = mascotaDAO.findAll();
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
            adopcionDAO.save(adopcion);

            System.out.println("Adopción registrada: " + mascota.getNombre() + " fue adoptado por " + adoptante.getNombre() + ".");
            System.out.println("Recomendaciones de cuidado: " + mascota.getRecomendacionesCuidado());
            JOptionPane.showMessageDialog(this, "Adopción registrada exitosamente.");

            dispose();
            new LoginUI(adoptanteDAO, empleadoDAO, mascotaDAO, adopcionDAO).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar adopción: " + e.getMessage());
        }
    }
}