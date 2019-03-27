package ru.geekbrains.java1.lesson7moba;

import ru.geekbrains.java1.lesson7moba.units.Healer;
import ru.geekbrains.java1.lesson7moba.units.Thief;
import ru.geekbrains.java1.lesson7moba.units.Unit;
import ru.geekbrains.java1.lesson7moba.units.Warrior;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMoba extends JFrame {
    public static Player p1;
    public static Player p2;
    private User user;
    private JFrame winWindow;
    private static GameMoba game;
    private JTextArea statusPlayer1;
    private JTextArea statusPlayer2;
    private JTextArea statusCombat;
    private JTextField userName1;
    private JTextField userName2;
    private JButton addUnitPlayer1;
    private JButton addUnitPlayer2;

    public static void main(String[] args) {
	// write your code here
        game = new GameMoba();
    }

    public GameMoba() throws HeadlessException {
        p1 = new Player("Player1");
        p2 = new Player("Player2");

        winWindow = new JFrame();
        winWindow.setBounds(400,400,400,400);
        winWindow.setLayout(new BorderLayout());

        setTitle("Swing MOBA");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(400,400,400,400);

        setLayout(new GridLayout(3,1));

        JLabel unitLabel = new JLabel("Unit");
        addUnitPlayer1 = new JButton("Add " + p1.getName() + " Unit");

        addUnitPlayer2 = new JButton("Add " + p2.getName() + " Unit");
        JButton runGame = new JButton("Run");
        runGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (p1.getArmy() != null && p2.getArmy() != null) {
                    run();
                }else{
                    statusCombat.append("Армии не инициализированы !\n");
                }
            }
        });
        JLabel healthLabel = new JLabel("Health - 50");
        JSlider healthValue = new JSlider(1,100,50);
        JButton step = new JButton("1Step");
        step.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (p1.getArmy() != null && p2.getArmy() != null) {
                    stepGame();
                }else{
                    statusCombat.append("Армии не инициализированы !\n");
                }
            }
        });
        healthValue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                healthLabel.setText("Health - " + healthValue.getValue());
            }
        });

        JLabel strenghtLabel = new JLabel("Strength - 20");
        JSlider strengthValue = new JSlider(1,100,20);
        strengthValue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                strenghtLabel.setText("Strength - " + strengthValue.getValue());
            }
        });
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });

        JLabel manaLabel = new JLabel("Mana - 20");
        JSlider manaValue = new JSlider(1,100,20);
        manaValue.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                manaLabel.setText("Mana - " + manaValue.getValue());
            }
        });
        manaLabel.setVisible(false);
        manaValue.setVisible(false);
        JComboBox<String> unit = new JComboBox<String>();
        unit.addItem("Warrior");
        unit.addItem("Thief");
        unit.addItem("Healer");
        statusPlayer1 = new JTextArea();
        statusPlayer2 = new JTextArea();
        statusCombat = new JTextArea();
        unit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (unit.getSelectedIndex() == 2){
                    manaValue.setVisible(true);
                    manaLabel.setVisible(true);
                }else{
                    manaLabel.setVisible(false);
                    manaValue.setVisible(false);
                }
            }
        });
        addUnitPlayer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (unit.getSelectedIndex() == 0){
                    p1.addArmyUnit(new Warrior(healthValue.getValue(),strengthValue.getValue(),p1.getName()));
                }else if (unit.getSelectedIndex() == 1){
                    p1.addArmyUnit(new Thief(healthValue.getValue(),strengthValue.getValue(),p1.getName()));
                }else{
                    p1.addArmyUnit(new Healer(healthValue.getValue(),strengthValue.getValue(),manaValue.getValue(),p1.getName()));
                }
                    p2.addEnemyArmy(p1.getArmy());
                updateStatus();
            }

        });

        addUnitPlayer2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (unit.getSelectedIndex() == 0){
                    p2.addArmyUnit(new Warrior(healthValue.getValue(),strengthValue.getValue(),p2.getName()));
                }else if (unit.getSelectedIndex() == 1){
                    p2.addArmyUnit(new Thief(healthValue.getValue(),strengthValue.getValue(),p2.getName()));
                }else{
                    p2.addArmyUnit(new Healer(healthValue.getValue(),strengthValue.getValue(),manaValue.getValue(),p2.getName()));
                }
                p1.addEnemyArmy(p2.getArmy());
                updateStatus();
            }
        });
        statusPlayer1.setFont(new Font ("Verdana", Font.PLAIN, 10));
        statusPlayer2.setFont(new Font ("Verdana", Font.PLAIN, 10));
        statusCombat.setFont(new Font ("Verdana", Font.PLAIN, 12));

        DefaultCaret caret = (DefaultCaret)statusCombat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JPanel userPanel = new JPanel(new BorderLayout());
        JPanel userNames = new JPanel(new GridLayout(1,2));
        userName1 = new JTextField("Player1");
        userName2 = new JTextField("Player2");
        userNames.add(userName1);
        userNames.add(userName2);
        JPanel userPanelLabels = new JPanel(new GridLayout(4,1));
        JPanel userPanelRulers = new JPanel(new GridLayout(4,1));
        JPanel userPanelButtons = new JPanel(new GridLayout(1,5));
        userPanelButtons.add(addUnitPlayer1);
        userPanelButtons.add(runGame);
        userPanelButtons.add(step);
        userPanelButtons.add(newGame);
        userPanelButtons.add(addUnitPlayer2);

        userPanelLabels.add(unitLabel);
        userPanelLabels.add(healthLabel);
        userPanelLabels.add(strenghtLabel);
        userPanelLabels.add(manaLabel);
        userPanelRulers.add(unit);
        userPanelRulers.add(healthValue);
        userPanelRulers.add(strengthValue);
        userPanelRulers.add(manaValue);
        userPanel.add(userNames,BorderLayout.NORTH);
        userPanel.add(userPanelLabels,BorderLayout.WEST);
        userPanel.add(userPanelRulers,BorderLayout.CENTER);
        userPanel.add(userPanelButtons,BorderLayout.SOUTH);

        JPanel armyPanel = new JPanel(new GridLayout(1,2));
        armyPanel.add(new JScrollPane(statusPlayer1));
        armyPanel.add(new JScrollPane(statusPlayer2));

        add(userPanel);
        add(armyPanel);

        add(new JScrollPane(statusCombat));
        pack();
        setVisible(true);

    }

    public void newGame(){
        p1 = new Player(userName1.getText());
        p2 = new Player(userName2.getText());
        statusPlayer1.setText("");
        statusPlayer2.setText("");
        statusCombat.setText("");
        addUnitPlayer1.setText("Add " + p1.getName() + " Unit");
        addUnitPlayer2.setText("Add " + p2.getName() + " Unit");

    }

    public void updateStatus(){
        if (p1.getArmy() != null) {
            statusPlayer1.setText("");
            for (Unit u : p1.getArmy()
            ) {
                statusPlayer1.append(u.getFullInfo() + "\n");
            }
        }
        if(p2.getArmy() != null) {
            statusPlayer2.setText("");
            for (Unit u : p2.getArmy()
            ) {
                statusPlayer2.append(u.getFullInfo() + "\n");
            }
        }
    }

    public void run(){
        while (!p1.checkWin() || !p2.checkWin()){

            p1.newTurn();
            p1.turn();
            updateStatus();
            statusCombat.append(p1.getCombatInfo());
            if (p1.checkWin()){
                statusCombat.append("Победил " + p1.getName() + "\n");
                break;
            }

            p2.newTurn();
            p2.turn();
            updateStatus();
            statusCombat.append(p2.getCombatInfo());
            if (p2.checkWin()){
                statusCombat.append("Победил " + p2.getName() + "\n");
                break;
            }
        }

        result();
    }

    public void stepGame(){
        if (!p1.checkWin() || !p2.checkWin()){
            p1.newTurn();
            p1.turn();
            updateStatus();
            statusCombat.append(p1.getCombatInfo());
            if (p1.checkWin()){
                statusCombat.append("Победил " + p1.getName() + "\n");

            }else {
                p2.newTurn();
                p2.turn();
                updateStatus();
                statusCombat.append(p2.getCombatInfo());
                if (p2.checkWin()) {
                    statusCombat.append("Победил " + p2.getName() + "\n");
                }
            }
        }
    }
// Вывод окна со статистикой победителя
    public void result(){
        Player winner = p1;
        if (p2.checkWin()){
            winner = p2;
        }
        winWindow = new JFrame();
        winWindow.setBounds(400,400,400,winner.getArmy().length * 40);
        winWindow.setTitle(winner.getName() + " - Winner !!!");
        JPanel winPanel = new JPanel(new GridLayout(winner.getArmy().length + 1,1));
        JPanel winButton = new JPanel(new GridLayout());
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winWindow.setVisible(false);
            }
        });
        winButton.add(ok);
        for (Unit u:winner.getArmy()
        ) {
            JLabel temp = new JLabel(u.getInfo() + " Уровень/Опыт: " + u.getLevel() + "/" + u.getExp() + " Убито:" + u.getKills());
            temp.setHorizontalAlignment(SwingConstants.LEFT);
            winPanel.add(temp);
        }
        winWindow.add(winPanel,BorderLayout.CENTER);
        winWindow.add(winButton,BorderLayout.SOUTH);
        winWindow.pack();
        winWindow.setVisible(true);
    }
}
