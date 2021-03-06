import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class MemoryGame extends JFrame implements ActionListener {  

    private JFrame window = new JFrame("Memory Game");
    private static final int WINDOW_WIDTH = 500; // pixels
    private static final int WINDOW_HEIGHT = 500; // pixels
    private JButton exitBtn, replayBtn, solveBtn;  
    ImageIcon ButtonIcon = createImageIcon("500.png");
    private JButton[] gameBtn = new JButton[16]; 
    private ArrayList<Integer> gameList = new ArrayList<Integer>();  
    private int Hit, Miss = 0;
    private int counter = 0;   
    private int[] btnID = new int[2];   
    private int[] btnValue = new int[2];   
    private JLabel HitScore, MissScore;
    private Panel gamePnl = new Panel(); 
    private Panel buttonPnl = new Panel();   
    private Panel scorePnl = new Panel();

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MemoryGame.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public MemoryGame()
    { 
        createGUI();  
        createpanels();  
        setArrayListText();
        window.setTitle("MemoryGame"); 
        window.setDefaultCloseOperation(EXIT_ON_CLOSE); 
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT); 
        window.setVisible(true);   
    }

    public void createGUI() 
    {       
        for (int i = 0; i < gameBtn.length; i++) 
        {      
            gameBtn[i] = new JButton(ButtonIcon);

            gameBtn[i].addActionListener(this); 
        }       
        HitScore = new JLabel("Hit: " + Hit);
        MissScore = new JLabel("Miss: " + Miss);
        exitBtn = new JButton("Exit"); 
        exitBtn.addActionListener(this);
        replayBtn = new JButton("Shuffle");  
        replayBtn.addActionListener(this);
        solveBtn = new JButton("Solve"); 
        solveBtn.addActionListener(this);       
    }    

    public void createpanels()
    {

        gamePnl.setLayout(new GridLayout(4, 4)); 
        for (int i = 0; i < gameBtn.length; i++)
        {            
            gamePnl.add(gameBtn[i]);   
        }         
        buttonPnl.add(replayBtn); 
        buttonPnl.add(exitBtn);
        buttonPnl.add(solveBtn);
        buttonPnl.setLayout(new GridLayout(1, 0));
        scorePnl.add(HitScore);
        scorePnl.add(MissScore);
        scorePnl.setLayout(new GridLayout(1, 0));
        window.add(scorePnl, BorderLayout.NORTH);
        window.add(gamePnl, BorderLayout.CENTER);
        window.add(buttonPnl, BorderLayout.SOUTH);  
    }  

    public void setArrayListText() 
    { 
        for (int i = 0; i < 2; i++) 
        {       
            for (int ii = 1; ii < (gameBtn.length / 2) + 1; ii++) 
            {
                gameList.add(ii);       
            }       
        }  
    } 


    public boolean sameValues() 
    {
        if (btnValue[0] == btnValue[1])
        {  
            return true;    
        }

        return false;    
    }  

    public void actionPerformed(ActionEvent e) 
    {    
            if (exitBtn == e.getSource()) 
            { 
                System.exit(0);        
            } 
            if (replayBtn == e.getSource()) 
            {     
                for (int i = 0; i < gameBtn.length; i++)
                { 
                gamePnl.remove(gameBtn[i]);
                }
                scorePnl.remove(HitScore);
                scorePnl.remove(MissScore);
                buttonPnl.remove(exitBtn);
                buttonPnl.remove(replayBtn);
                buttonPnl.remove(solveBtn);
                window.remove(gamePnl);
                window.remove(scorePnl);
                window.remove(buttonPnl);
                window.remove(window);
                window.add(gamePnl);
                window.add(scorePnl);
                window.add(buttonPnl);
                scorePnl.add(HitScore);
                scorePnl.add(MissScore);
                buttonPnl.add(exitBtn);
                buttonPnl.add(replayBtn);
                buttonPnl.add(solveBtn);
                for (int i = 0; i < gameBtn.length; i++)
                { 
                gamePnl.add(gameBtn[i]);
                }

            }    

            if (solveBtn == e.getSource())
            {   
                for (int i = 0; i < gameBtn.length; i++) 
                { 
                    gameBtn[i].setText("" + gameList.get(i));  
                    gameBtn[btnID[0]].setEnabled(false); 
                    gameBtn[btnID[1]].setEnabled(false);

                }
            }

            for (int i = 0; i < gameBtn.length; i++) 
            {  

                if (gameBtn[i] == e.getSource()) 
                { 
                    gameBtn[i].setText("" + gameList.get(i));   
                    gameBtn[i].setEnabled(false);
                    counter++;     
                    if (counter == 3) 
                    { 
                        if (sameValues()) 
                        {    
                            gameBtn[btnID[0]].setEnabled(false); 
                            gameBtn[btnID[1]].setEnabled(false);
                            gameBtn[btnID[0]].setVisible(false); 
                            gameBtn[btnID[1]].setVisible(false);
                            Hit = Hit +1;
                        } 
                        else 
                        {  
                                gameBtn[btnID[0]].setEnabled(true); 
                                gameBtn[btnID[0]].setText("");
                                gameBtn[btnID[1]].setEnabled(true);
                                gameBtn[btnID[1]].setText("");
                                Miss = Miss +1;
                        }        
                        counter = 1;      
                    }        
                    if (counter == 1) 
                    {       
                        btnID[0] = i;   
                        btnValue[0] = gameList.get(i); 
                    }      
                    if (counter == 2) 
                    { 
                        btnID[1] = i;     
                        btnValue[1] = gameList.get(i);  
                    }          
                }        
            }           
    } 

    public static void main(String[] args)
    {  
        new MemoryGame();   
    }
}