package exercise03.game_of_life.ui;

import exercise03.game_of_life.model.CellMatrix;
import exercise03.game_of_life.thread.MainThread;
import exercise03.game_of_life.util.Utils;
import test.test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;


public class GameOfLifeFrame extends JFrame {

    private final JButton startGameBtn = new JButton("开始游戏");
    private final JButton exitGameBtn = new JButton("退出游戏");
    private final JLabel durationPromtLabel=new JLabel();
    /**是否打开读入文件
     * */
    private boolean isOpen=false;
    /**
     * 游戏是否开始的标志
     */
    private boolean isStart = false;
    /**
     * 游戏结束的标志
     */
    private static boolean End =false;
    private static boolean stop = false;
    public static CellMatrix cellMatrix;
    private JPanel gridPanel = new JPanel();
    private static JTextField[][] textMatrix;
    /**
     * 动画默认间隔200ms
     */
    private static final int DEFAULT_DURATION = 200;
    /**
     * 动画间隔
     */
    public GameOfLifeFrame() {
        setTitle("生命游戏");
        JButton openFileBtn = new JButton("选择文件");
        openFileBtn.addActionListener(new OpenFileActioner());
        startGameBtn.addActionListener(new StartGameActioner());
        exitGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                End=true;
                System.exit(0);
            }
        });
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.add(openFileBtn);
        buttonPanel.add(startGameBtn);
        durationPromtLabel.setText("游戏未开始");
        buttonPanel.add(durationPromtLabel);
        buttonPanel.add(exitGameBtn);
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
                isOpen=true;
                stop = true;
                startGameBtn.setText("开始游戏");
                GetAliveCell aliveCell = new GetAliveCell();
                aliveCell.start();
                String filepath = fcDlg.getSelectedFile().getPath();
                test.start();

                //移除所有组件
                if(textMatrix!=null){
                    gridPanel.removeAll();
                }

//                Runtime runtime = Runtime.getRuntime();
//                long concurrentMemory1 = runtime.totalMemory()-runtime.freeMemory();
                cellMatrix = Utils.initMatrixFromFile(filepath);
                initGridLayout();
                showMatrix(0,cellMatrix.getHeight(),cellMatrix.matrix);
                End=false;

                gridPanel.updateUI();
                test.end();
//                runtime = Runtime.getRuntime();
//                long concurrentMemory2 = runtime.totalMemory()-runtime.freeMemory();
//                System.out.println("消耗内存 "+(concurrentMemory2-concurrentMemory1));


            }
        }

    }

    public static void showMatrix(int startHeight,int endHeight,int[][] matrix) {
        for (int y = startHeight; y < endHeight; y++) {
            for (int x = 0; x < cellMatrix.getWidth(); x++) {
                if (matrix[y-startHeight][x] ==1) {
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

    private  class GetAliveCell extends Thread{
        String text;
        @Override
        public void run() {
            while (true) {
                while (stop){
//                    System.out.println("计数器 is waiting......");
                }
                if(isOpen){
                    durationPromtLabel.setText("游戏未开始");
                }
                else{
                    if (cellMatrix!=null) {
                        text=cellMatrix.getAliveCell();
                        System.out.println(text);
                        durationPromtLabel.setText(text);
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private class StartGameActioner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isStart) {
                //获取时间
                GameControlTask gameControlTask = new GameControlTask();
                gameControlTask.start();
                isStart = true;
                stop = false;
                isOpen=false;
                startGameBtn.setText("暂停游戏");
            } else {
                stop = true;
                isStart = false;
                startGameBtn.setText("开始游戏");
            }
        }
    }

    private static class GameControlTask extends Thread {

        @Override
        public void run() {
            new Thread(new MainThread()).start();
        }
    }

    public static boolean isEnd() {
        return End;
    }

    public static boolean isStop() {
        return stop;
    }
}
