package com.refugio.ui;

import com.refugio.dao.AdopcionDAO;
import com.refugio.dao.AdoptanteDAO;
import com.refugio.dao.EmpleadoDAO;
import com.refugio.dao.MascotaDAO;
import com.refugio.model.adopcion.Adopcion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdopcionesUI extends JFrame {

    private final AdopcionDAO adopcionDAO;
    private final AdoptanteDAO adoptanteDAO;
    private final EmpleadoDAO empleadoDAO;
    private final MascotaDAO mascotaDAO;

    private JTable adopcionesTable;
    private DefaultTableModel tableModel;

    public AdopcionesUI(AdopcionDAO adopcionDAO, AdoptanteDAO adoptanteDAO, EmpleadoDAO empleadoDAO, MascotaDAO mascotaDAO) {
        this.adopcionDAO = adopcionDAO;
        this.adoptanteDAO = adoptanteDAO;
        this.empleadoDAO = empleadoDAO;
        this.mascotaDAO = mascotaDAO;

        setTitle("Gestión de Adopciones");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initUI();
        loadAdopciones();
    }

    private void initUI() {
        // Table setup
        String[] columnNames = {"ID", "Mascota", "Adoptante", "Empleado", "Fecha"};
        tableModel = new DefaultTableModel(columnNames, 0);
        adopcionesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(adopcionesTable);

        // Buttons setup
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Agregar");
        JButton editButton = new JButton("Editar");
        JButton deleteButton = new JButton("Eliminar");
        JButton closeButton = new JButton("Cerrar");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(closeButton);
        
        // Layout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Actions
        addButton.addActionListener(e -> addAdopcion());
        editButton.addActionListener(e -> editAdopcion());
        deleteButton.addActionListener(e -> deleteAdopcion());
        closeButton.addActionListener(e -> {
            dispose();
            new LoginUI(adoptanteDAO, empleadoDAO, mascotaDAO, adopcionDAO).setVisible(true);
        });
    }

    private void loadAdopciones() {
        tableModel.setRowCount(0); // Clear existing data
        List<Adopcion> adopciones = adopcionDAO.findAll();
        for (Adopcion adopcion : adopciones) {
            Object[] row = {
                    adopcion.getId(),
                    adopcion.getMascota() != null ? adopcion.getMascota().getNombre() : "N/A",
                    adopcion.getAdoptante() != null ? adopcion.getAdoptante().getNombre() : "N/A",
                    adopcion.getEmpleado() != null ? adopcion.getEmpleado().getNombre() : "N/A",
                    adopcion.getFechaAdopcion()
            };
            tableModel.addRow(row);
        }
    }
    
    private void addAdopcion() {
        AdopcionDialog dialog = new AdopcionDialog(this, null, adopcionDAO, adoptanteDAO, empleadoDAO, mascotaDAO);
        dialog.setVisible(true);
        loadAdopciones();
    }

    private void editAdopcion() {
        int selectedRow = adopcionesTable.getSelectedRow();
        if (selectedRow >= 0) {
            Long adopcionId = (Long) tableModel.getValueAt(selectedRow, 0);
            Adopcion adopcion = adopcionDAO.findById(adopcionId).orElse(null);
            if (adopcion != null) {
                AdopcionDialog dialog = new AdopcionDialog(this, adopcion, adopcionDAO, adoptanteDAO, empleadoDAO, mascotaDAO);
                dialog.setVisible(true);
                loadAdopciones();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una adopción para editar.");
        }
    }

    private void deleteAdopcion() {
        int selectedRow = adopcionesTable.getSelectedRow();
        if (selectedRow >= 0) {
            Long adopcionId = (Long) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar esta adopción?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                adopcionDAO.findById(adopcionId).ifPresent(adopcionDAO::delete);
                loadAdopciones();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una adopción para eliminar.");
        }
    }
}