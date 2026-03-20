package com.TowerDefense.ui;

import com.TowerDefense.model.*;
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private GamePanel gamePanel;
    private CardPanel cardPanel;

    private JCheckBox speedCheck, iceCheck, shieldCheck, goldCheck;

    private static final Color BG     = new Color(8, 8, 20);
    private static final Color PANEL  = new Color(18, 18, 40);
    private static final Color CARD   = new Color(28, 28, 58);
    private static final Color BORDER = new Color(60, 60, 120);
    private static final Color GOLD   = new Color(255, 200, 50);

    public MainWindow() {
        setTitle("⚔ Torre Defensiva — Clash Style");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(900, 600));
        getContentPane().setBackground(BG);
        setupFullscreenKey();
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 0));
        add(buildTitle(),   BorderLayout.NORTH);
        add(buildCenter(),  BorderLayout.CENTER);
        add(buildModules(), BorderLayout.EAST);
    }

    private JPanel buildTitle() {
        JLabel title = new JLabel("⚔  TORRE DEFENSIVA  ⚔", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(GOLD);

        JLabel sub = new JLabel("Mejora tu torre · Despliega tropas · Destruye al enemigo  |  F11 = Pantalla completa", SwingConstants.CENTER);
        sub.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        sub.setForeground(new Color(120, 120, 180));

        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBackground(BG);
        panel.setBorder(BorderFactory.createEmptyBorder(8, 0, 4, 0));
        panel.add(title);
        panel.add(sub);
        return panel;
    }

    private JPanel buildCenter() {
        gamePanel = new GamePanel();
        cardPanel = new CardPanel(card -> gamePanel.deployTroop(card));

        JButton restart = btn("↺ Reiniciar", new Color(45, 45, 80), e -> restartGame());

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 4));
        topBar.setBackground(new Color(12, 12, 30));
        topBar.add(restart);

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(BG);
        center.add(topBar,    BorderLayout.NORTH);
        center.add(gamePanel, BorderLayout.CENTER);
        center.add(cardPanel, BorderLayout.SOUTH);
        return center;
    }

    private JPanel buildModules() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 2, 0, 0, BORDER),
                BorderFactory.createEmptyBorder(14, 12, 14, 12)));
        panel.setPreferredSize(new Dimension(210, 0));

        speedCheck  = createCheckBox("⚡ Velocidad");
        iceCheck    = createCheckBox("❄  Hielo");
        shieldCheck = createCheckBox("🛡 Escudo");
        goldCheck   = createCheckBox("💰 Oro");

        panel.add(section("🔧 MÓDULOS DE TORRE"));
        panel.add(moduleCard(speedCheck,  "Mayor cadencia de disparo", new Color(255, 255, 80)));
        panel.add(Box.createVerticalStrut(6));
        panel.add(moduleCard(iceCheck,    "Congela tropas enemigas",   new Color(100, 200, 255)));
        panel.add(Box.createVerticalStrut(6));
        panel.add(moduleCard(shieldCheck, "Más armadura y vida",       new Color(100, 180, 255)));
        panel.add(Box.createVerticalStrut(6));
        panel.add(moduleCard(goldCheck,   "Genera elixir extra",       new Color(255, 200, 50)));
        panel.add(Box.createVerticalStrut(14));

        panel.add(section("⚙ ACCIONES"));
        panel.add(btn("▶ Aplicar Módulos",   new Color(50, 110, 230), e -> applyModules()));
        panel.add(Box.createVerticalStrut(6));
        panel.add(btn("⛶ Pantalla Completa", new Color(40, 70,  40),  e -> toggleFullScreen()));

        return panel;
    }

    private void applyModules() {
        Tower tower = new BaseTower();
        if (speedCheck.isSelected())  tower = new SpeedModule(tower);
        if (iceCheck.isSelected())    tower = new IceModule(tower);
        if (shieldCheck.isSelected()) tower = new ShieldModule(tower);
        if (goldCheck.isSelected())   tower = new GoldModule(tower);
        gamePanel.upgradePlayerTower(tower);
    }

    private void restartGame() {
        cardPanel.stopElixir();
        getContentPane().removeAll();
        speedCheck = iceCheck = shieldCheck = goldCheck = null;
        initComponents();
        revalidate();
        repaint();
    }

    private JPanel section(String title) {
        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbl.setForeground(new Color(120, 160, 255));
        JSeparator sep = new JSeparator();
        sep.setForeground(BORDER);
        JPanel p = new JPanel(new BorderLayout(0, 3));
        p.setBackground(PANEL);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        p.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        p.add(lbl, BorderLayout.NORTH);
        p.add(sep, BorderLayout.SOUTH);
        return p;
    }

    private JPanel moduleCard(JCheckBox cb, String desc, Color accent) {
        JLabel lbl = new JLabel(desc);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lbl.setForeground(accent.darker());
        JPanel card = new JPanel(new BorderLayout(0, 2));
        card.setBackground(CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(accent.darker(), 1),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 52));
        card.add(cb,  BorderLayout.CENTER);
        card.add(lbl, BorderLayout.SOUTH);
        return card;
    }

    private JCheckBox createCheckBox(String text) {
        JCheckBox cb = new JCheckBox(text);
        cb.setFont(new Font("Segoe UI", Font.BOLD, 12));
        cb.setForeground(new Color(220, 220, 255));
        cb.setBackground(CARD);
        cb.setFocusPainted(false);
        return cb;
    }

    private JButton btn(String text, Color bg, java.awt.event.ActionListener action) {
        JButton b = new JButton(text);
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        b.setBackground(bg); b.setForeground(Color.WHITE);
        b.setFocusPainted(false); b.setBorderPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addActionListener(action);
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { b.setBackground(bg.brighter()); }
            public void mouseExited(java.awt.event.MouseEvent e)  { b.setBackground(bg); }
        });
        return b;
    }

    private void setupFullscreenKey() {
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("F11"), "fs");
        getRootPane().getActionMap().put("fs", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) { toggleFullScreen(); }
        });
    }

    private void toggleFullScreen() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (gd.getFullScreenWindow() == null) {
            setUndecorated(true); gd.setFullScreenWindow(this);
        } else {
            gd.setFullScreenWindow(null);
            setUndecorated(false);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }
}