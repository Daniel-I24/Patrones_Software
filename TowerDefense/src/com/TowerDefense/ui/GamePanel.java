package com.TowerDefense.ui;

import com.TowerDefense.model.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel {

    private final List<Troop> troops = new ArrayList<>();
    private final Timer gameTimer;

    private Tower playerTower  = new BaseTower();
    private boolean iceActive  = false;
    private int playerTowerHP  = 200;
    private int enemyTowerHP   = 200;
    private static final int MAX_HP = 200;
    private boolean gameOver   = false;

    private static final Color BG_TOP    = new Color(10, 10, 30);
    private static final Color BG_BOT    = new Color(15, 15, 45);
    private static final Color ALLY_COL  = new Color(50, 150, 255);
    private static final Color ENEMY_COL = new Color(220, 60, 60);

    public GamePanel() {
        setBackground(BG_TOP);
        gameTimer = new Timer(16, e -> update());
        gameTimer.start();
        spawnEnemyTroops();
    }

    public void deployTroop(Card card) {
        if (gameOver) return;
        float y = getHeight() / 2f + new Random().nextInt(60) - 30;
        troops.add(new Troop(card.troopType, 80, y, true));
    }

    public void upgradePlayerTower(Tower tower) {
        this.playerTower = tower;
        this.iceActive   = tower.getArmor() > 0;
        playerTowerHP    = Math.min(MAX_HP + tower.getArmor(), playerTowerHP + tower.getArmor());
        repaint();
    }

    private void spawnEnemyTroops() {
        new Timer(4000, e -> {
            if (gameOver) return;
            Troop.Type[] types = Troop.Type.values();
            float y = getHeight() / 2f + new Random().nextInt(60) - 30;
            troops.add(new Troop(types[new Random().nextInt(types.length)], getWidth() - 80, y, false));
        }).start();
    }

    private void update() {
        if (gameOver) return;

        int enemyTowerX  = getWidth() - 60;
        int playerTowerX = 60;
        int fireRate     = playerTower.getFireRate() > 1 ? 15 : 25;

        for (Troop t : troops) {
            if (!t.isAlive()) continue;
            Troop target = findTarget(t);

            if (target != null && t.inRange(target.x)) {
                target.health -= t.damage / 30f;
            } else {
                int towerX      = t.isAlly ? enemyTowerX : playerTowerX;
                float slowFactor = (!t.isAlly && iceActive) ? 0.5f : 1f;

                if (t.inRange(towerX)) {
                    if (t.isAlly) {
                        enemyTowerHP = Math.max(0, enemyTowerHP - t.damage / fireRate);
                    } else {
                        int reducedDmg = (int)(t.damage * (1f - playerTower.getArmor() / 100f));
                        playerTowerHP  = Math.max(0, playerTowerHP - Math.max(1, reducedDmg) / 25);
                    }
                } else {
                    t.x += (t.isAlly ? t.speed : -t.speed) * slowFactor;
                }
            }
        }

        troops.removeIf(t -> !t.isAlive());

        if (playerTowerHP <= 0 || enemyTowerHP <= 0) {
            gameOver = true;
            gameTimer.stop();
        }
        repaint();
    }

    private Troop findTarget(Troop troop) {
        return troops.stream()
                .filter(t -> t.isAlly != troop.isAlly && t.isAlive())
                .min((a, b) -> (int)(Math.abs(a.x - troop.x) - Math.abs(b.x - troop.x)))
                .orElse(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawBackground(g2);
        drawModuleEffects(g2);
        drawTowers(g2);
        drawTroops(g2);
        if (gameOver) drawGameOver(g2);
    }

    private void drawBackground(Graphics2D g2) {
        g2.setPaint(new GradientPaint(0, 0, BG_TOP, 0, getHeight(), BG_BOT));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(new Color(40, 40, 80, 40));
        for (int i = 0; i < getWidth();  i += 50) g2.drawLine(i, 0, i, getHeight());
        for (int i = 0; i < getHeight(); i += 50) g2.drawLine(0, i, getWidth(), i);
        g2.setColor(new Color(80, 80, 160, 80));
        g2.setStroke(new BasicStroke(2f));
        g2.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
    }

    private void drawModuleEffects(Graphics2D g2) {
        if (iceActive) {
            g2.setColor(new Color(100, 200, 255, 18));
            g2.fillRect(getWidth() / 2, 0, getWidth() / 2, getHeight());
        }
        if (playerTower.getFireRate() > 1) {
            g2.setColor(new Color(255, 255, 100, 15));
            g2.fillRect(0, 0, getWidth() / 2, getHeight());
        }
        if (playerTower.getGoldGeneration() > 0) {
            g2.setColor(new Color(255, 200, 50, 12));
            g2.fillRect(0, 0, getWidth() / 2, getHeight());
        }
    }

    private void drawTowers(Graphics2D g2) {
        boolean shield = playerTower.getArmor() > 0;
        drawTower(g2, 60,              getHeight() / 2, playerTowerHP, ALLY_COL,  "🏰 Tú",  shield);
        drawTower(g2, getWidth() - 60, getHeight() / 2, enemyTowerHP,  ENEMY_COL, "🏰 CPU", false);
    }

    private void drawTower(Graphics2D g2, int x, int cy, int hp, Color color, String label, boolean shield) {
        int w = 30, h = 80;

        if (shield) {
            g2.setColor(new Color(100, 180, 255, 40));
            g2.fillOval(x - 50, cy - h - 20, 100, h + 80);
            g2.setColor(new Color(100, 200, 255, 120));
            g2.setStroke(new BasicStroke(2f));
            g2.drawOval(x - 50, cy - h - 20, 100, h + 80);
        }

        g2.setPaint(new GradientPaint(x - w, 0, color.brighter(), x + w, 0, color.darker()));
        g2.fillRect(x - w, cy - h, w * 2, h + 40);
        g2.setColor(color.darker());
        g2.setStroke(new BasicStroke(2f));
        g2.drawRect(x - w, cy - h, w * 2, h + 40);

        int[] mx = {x - w - 10, x - w, x - w, x - w + 18, x - w + 18,
                x + w - 18, x + w - 18, x + w, x + w, x + w + 10};
        int   top = cy - h;
        int[] my  = {top, top, top - 14, top - 14, top, top, top - 14, top - 14, top, top};
        g2.setPaint(new GradientPaint(x - w, 0, color.brighter(), x + w, 0, color.darker()));
        g2.fillPolygon(mx, my, mx.length);
        g2.setColor(color.darker());
        g2.drawPolyline(mx, my, mx.length);

        float pct = (float) hp / MAX_HP;
        int bw = 70, bh = 8, bx = x - bw / 2, by = cy - h - 24;
        g2.setColor(new Color(30, 30, 60));
        g2.fillRoundRect(bx, by, bw, bh, 5, 5);
        g2.setColor(pct > 0.5f ? new Color(50, 200, 80) : pct > 0.25f ? new Color(220, 180, 40) : new Color(210, 50, 50));
        g2.fillRoundRect(bx, by, (int)(bw * pct), bh, 5, 5);

        g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
        g2.setColor(Color.WHITE);
        g2.drawString(label + " " + hp, bx, by - 4);
    }

    private void drawTroops(Graphics2D g2) {
        for (Troop t : troops) {
            if (!t.isAlive()) continue;
            Color c = t.isAlly ? ALLY_COL : ENEMY_COL;
            int   r = t.type == Troop.Type.GIANT ? 18 : t.type == Troop.Type.KNIGHT ? 13 : 10;

            if (!t.isAlly && iceActive) {
                g2.setColor(new Color(150, 220, 255, 60));
                g2.fillOval((int)t.x - r - 5, (int)t.y - r - 5, (r + 5) * 2, (r + 5) * 2);
            }

            g2.setPaint(new RadialGradientPaint(t.x, t.y, r,
                    new float[]{0f, 1f}, new Color[]{c.brighter(), c.darker()}));
            g2.fillOval((int)t.x - r, (int)t.y - r, r * 2, r * 2);
            g2.setColor(new Color(0, 0, 0, 80));
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawOval((int)t.x - r, (int)t.y - r, r * 2, r * 2);

            g2.setFont(new Font("Segoe UI", Font.BOLD, 9));
            g2.setColor(Color.WHITE);
            String emoji = t.type == Troop.Type.KNIGHT ? "⚔" : t.type == Troop.Type.ARCHER ? "🏹" : "👊";
            g2.drawString(emoji, (int)t.x - 5, (int)t.y + 4);

            float hp = (float) t.health / t.maxHealth;
            g2.setColor(new Color(30, 30, 60));
            g2.fillRoundRect((int)t.x - 15, (int)t.y - r - 10, 30, 4, 2, 2);
            g2.setColor(hp > 0.5f ? new Color(50, 200, 80) : new Color(210, 50, 50));
            g2.fillRoundRect((int)t.x - 15, (int)t.y - r - 10, (int)(30 * hp), 4, 2, 2);
        }
    }

    private void drawGameOver(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 160));
        g2.fillRect(0, 0, getWidth(), getHeight());
        String msg = enemyTowerHP <= 0 ? "🏆 ¡Victoria!" : "💀 ¡Derrota!";
        Color  col = enemyTowerHP <= 0 ? new Color(80, 220, 80) : new Color(220, 60, 60);
        g2.setFont(new Font("Segoe UI", Font.BOLD, 36));
        g2.setColor(col);
        FontMetrics fm = g2.getFontMetrics();
        g2.drawString(msg, getWidth() / 2 - fm.stringWidth(msg) / 2, getHeight() / 2);
        g2.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        g2.setColor(Color.WHITE);
        String sub = "Presiona  ↺ Reiniciar  para jugar de nuevo";
        g2.drawString(sub, getWidth() / 2 - g2.getFontMetrics().stringWidth(sub) / 2, getHeight() / 2 + 40);
    }
}