package exercise2.game_of_life.ui;
import exercise2.game_of_life.model.CellMatrix;
import exercise2.game_of_life.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;


/**
 * @author ASUS
 */
public class GameOfLifeFrame extends JFrame {

    private final JButton startGameBtn = new JButton("开始游戏");
    private final JTextField durationTextField = new JTextField();
    /**
     * 游戏是否开始的标志
     */
    private boolean isStart = false;

    /**
     * 游戏结束的标志
     */
    private boolean stop = false;

    private CellMatrix cellMatrix;
    private JPanel gridPanel = new JPanel();

    private JTextField[][] textMatrix;


    /**
     * 动画默认间隔200ms
     */
    private static final int DEFAULT_DURATION = 200;


    /**
     * 动画间隔
     */
    private int duration = DEFAULT_DURATION;

    public GameOfLifeFrame() {
        setTitle("生命游戏");
        JButton openFileBtn = new JButton("选择文件");
        openFileBtn.addActionListener(new OpenFileActioner());
        startGameBtn.addActionListener(new StartGameActioner());
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.add(openFileBtn);
        buttonPanel.add(startGameBtn);
        JLabel durationPromtLabel = new JLabel("动画间隔设置(ms为单位)");
        buttonPanel.add(durationPromtLabel);
        buttonPanel.add(durationTextField);
        buttonPanel.setBackground(Color.WHITE);
        getContentPane().add("North", buttonPanel);
        this.setSize(1000, 1200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private class OpenFileActioner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fcDlg = new JFileChooser(".");
            fcDlg.setDialogTitle("请选择初始配置文件");
            int returnVal = fcDlg.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                isStart = false;
                stop = true;
                startGameBtn.setText("开始游戏");
                String filepath = fcDlg.getSelectedFile().getPath();
                cellMatrix = Utils.initMatrixFromFile(filepath);
                initGridLayout();
                showMatrix();
                gridPanel.updateUI();
            }
        }


    }

    private void showMatrix() {

        int[][] matrix = cellMatrix.getMatrix();
        for (int y = 0; y < matrix.length; y++) {
            for (int x = 0; x < matrix[0].length; x++) {
                if (matrix[y][x] ==1) {
                    textMatrix[y][x].setEditable(false);
                    textMatrix[y][x].setBackground(Color.BLACK);
                } else {
                    textMatrix[y][x].setEditable(false);
                    textMatrix[y][x].setBackground(Color.WHITE);
                }
            }
        }
    }

    /**
     * 创建显示的gridlayout布局
     */
    private void initGridLayout() {
        int rows = cellMatrix.getHeight();
        int cols = cellMatrix.getWidth();
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(rows, cols));
        textMatrix = new JTextField[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                JTextField text = new JTextField();
                textMatrix[y][x] = text;
                gridPanel.add(text);
            }
        }
        add("Center", gridPanel);
    }


    private class StartGameActioner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isStart) {
                //获取时间
                try {
                    duration = Integer.parseInt(durationTextField.getText().trim());
                } catch (NumberFormatException e1) {
                    duration = DEFAULT_DURATION;
                }
                GameControlTask gameControlTask = new GameControlTask();
                gameControlTask.start();
                isStart = true;
                stop = false;
                startGameBtn.setText("暂停游戏");
            } else {
                stop = true;
                isStart = false;
                startGameBtn.setText("开始游戏");
            }
        }
    }

    private class GameControlTask extends Thread {

        @Override
        public void run() {
            while (!stop) {
                cellMatrix.transform();
                showMatrix();
                try {
                    TimeUnit.MILLISECONDS.sleep(duration);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
