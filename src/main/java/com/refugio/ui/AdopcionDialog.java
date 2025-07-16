package com.refugio.ui;

import com.refugio.dao.AdopcionDAO;
import com.refugio.dao.AdoptanteDAO;
import com.refugio.dao.EmpleadoDAO;
import com.refugio.dao.MascotaDAO;
import com.refugio.model.adopcion.Adopcion;
import com.refugio.model.mascota.Mascota;
import com.refugio.model.persona.Adoptante;
import com.refugio.model.persona.Empleado;
import com.refugio.servicio.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdopcionDialog extends JDialog {

    private final AdopcionDAO adopcionDAO;
    private final AdoptanteDAO adoptanteDAO;
    private final EmpleadoDAO empleadoDAO;
    private final MascotaDAO mascotaDAO;
    private Adopcion adopcion;

    private JComboBox<Adoptante> comboAdoptante;
    private JComboBox<Mascota> comboMascota;

    public AdopcionDialog(Frame owner, Adopcion adopcion, AdopcionDAO adopcionDAO, AdoptanteDAO adoptanteDAO, EmpleadoDAO empleadoDAO, MascotaDAO mascotaDAO) {
        super(owner, true);
        this.adopcion = adopcion;
        this.adopcionDAO = adopcionDAO;
        this.adoptanteDAO = adoptanteDAO;
        this.empleadoDAO = empleadoDAO;
        this.mascotaDAO = mascotaDAO;

        setTitle(adopcion == null ? "Nueva Adopción" : "Editar Adopción");
        setSize(450, 200);
        setLocationRelativeTo(owner);
        initUI();
        if (adopcion != null) {
            loadAdopcionData();
        }
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        comboAdoptante = new JComboBox<>(getAdoptantes());
        comboMascota = new JComboBox<>(getMascotas());

        JButton saveBtn = new JButton("Guardar");
        JButton cancelBtn = new JButton("Cancelar");

        saveBtn.addActionListener(e -> saveAdopcion());
        cancelBtn.addActionListener(e -> dispose());

        panel.add(new JLabel("Adoptante:"));
        panel.add(comboAdoptante);
        panel.add(new JLabel("Mascota:"));
        panel.add(comboMascota);
        panel.add(saveBtn);
        panel.add(cancelBtn);

        add(panel);
    }

    private Adoptante[] getAdoptantes() {
        List<Adoptante> lista = adoptanteDAO.findAll();
        return lista.toArray(new Adoptante[0]);
    }

    private Mascota[] getMascotas() {
        List<Mascota> lista = mascotaDAO.findAll();
        return lista.toArray(new Mascota[0]);
    }

    private void loadAdopcionData() {
        comboAdoptante.setSelectedItem(adopcion.getAdoptante());
        comboMascota.setSelectedItem(adopcion.getMascota());
    }

    private void saveAdopcion() {
        try {
            Adoptante selectedAdoptante = (Adoptante) comboAdoptante.getSelectedItem();
            Mascota selectedMascota = (Mascota) comboMascota.getSelectedItem();
            Empleado loggedInEmpleado = SessionManager.getInstance().getLoggedInEmpleado();

            if (selectedAdoptante == null || loggedInEmpleado == null || selectedMascota == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar todas las opciones.");
                return;
            }

            // Fetch managed entities
            Adoptante adoptante = adoptanteDAO.findById(selectedAdoptante.getId()).orElse(null);
            Mascota mascota = mascotaDAO.findById(selectedMascota.getId()).orElse(null);
            Empleado empleado = empleadoDAO.findById(loggedInEmpleado.getId()).orElse(null);

            if (adoptante == null || mascota == null || empleado == null) {
                JOptionPane.showMessageDialog(this, "Error al recuperar datos de la base de datos.");
                return;
            }

            if (this.adopcion == null) {
                this.adopcion = mascota.crearAdopcion(empleado, adoptante);
            } else {
                this.adopcion.setAdoptante(adoptante);
                this.adopcion.setMascota(mascota);
                this.adopcion.setEmpleado(empleado);
            }
            
            adopcionDAO.save(this.adopcion);
            JOptionPane.showMessageDialog(this, "Adopción guardada exitosamente.");

            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar adopción: " + e.getMessage());
            e.printStackTrace();
        }
    }
}