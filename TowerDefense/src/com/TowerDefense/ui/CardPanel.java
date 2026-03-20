package com.TowerDefense.ui;

import com.TowerDefense.model.Card;
import com.TowerDefense.model.Troop;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class CardPanel extends JPanel {

    private static final Color BG       = new Color(15, 15, 35);
    private static final Color CARD_BG  = new Color(30, 30, 60);
    private static final Color GOLD     = new Color(255, 200, 50);
    private static final Color BORDER   = new Color(80, 80, 160);

    private int elixir    = 5;
    private int maxElixir = 10;
    private final Timer elixirTimer;
    private final List<Card> cards;
    private final Consumer<Card> onCardPlayed;

    public CardPanel(Consumer<Card> onCardPlayed) {
        this.onCardPlayed = onCardPlayed;
        setBackground(BG);
        setPreferredSize(new Dimension(0, 110));
        setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, BORDER));

        cards = List.of(
                new Card(Troop.Type.KNIGHT),
                new Card(Troop.Type.ARCHER),
                new Card(Troop.Type.GIANT)
        );

        elixirTimer = new Timer(1000, e -> {
            if (elixir < maxElixir) elixir++;
            repaint();
        });
        elixirTimer.start();

        setLayout(new BorderLayout(0, 5));
        add(buildElixirBar(), BorderLayout.NORTH);
        add(buildCards(),     BorderLayout.CENTER);
    }

    private JPanel buildElixirBar() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int bw = getWidth() - 40, bh = 12, bx = 20, by = 6;
                g2.setColor(new Color(30, 10, 50));
                g2.fillRoundRect(bx, by, bw, bh, 8, 8);
                g2.setColor(new Color(160, 50, 220));
                g2.fillRoundRect(bx, by, (int)(bw * ((float) elixir / maxElixir)), bh, 8, 8);
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
                String txt = "💜 Elixir: " + elixir + " / " + maxElixir;
                g2.drawString(txt, bx + bw / 2 - g2.getFontMetrics().stringWidth(txt) / 2, by + bh + 14);
            }
        };
        panel.setBackground(BG);
        panel.setPreferredSize(new Dimension(0, 32));
        return panel;
    }

    private JPanel buildCards() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 5));
        panel.setBackground(BG);

        for (Card card : cards) {
            JButton btn = new JButton();
            btn.setLayout(new BorderLayout());
            btn.setBackground(CARD_BG);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(BORDER, 2));
            btn.setPreferredSize(new Dimension(80, 60));
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            JLabel emoji = new JLabel(card.emoji, SwingConstants.CENTER);
            emoji.setFont(new Font("Segoe UI", Font.PLAIN, 22));

            JLabel info = new JLabel(card.name + "  💜" + card.cost, SwingConstants.CENTER);
            info.setFont(new Font("Segoe UI", Font.BOLD, 10));
            info.setForeground(GOLD);

            btn.add(emoji, BorderLayout.CENTER);
            btn.add(info,  BorderLayout.SOUTH);

            btn.addActionListener(e -> {
                if (elixir >= card.cost) {
                    elixir -= card.cost;
                    onCardPlayed.accept(card);
                    repaint();
                } else {
                    btn.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    Timer t = new Timer(400, ev -> btn.setBorder(BorderFactory.createLineBorder(BORDER, 2)));
                    t.setRepeats(false);
                    t.start();
                }
            });

            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(CARD_BG.brighter()); }
                public void mouseExited(java.awt.event.MouseEvent e)  { btn.setBackground(CARD_BG); }
            });

            panel.add(btn);
        }
        return panel;
    }

    public void stopElixir() { elixirTimer.stop(); }
}