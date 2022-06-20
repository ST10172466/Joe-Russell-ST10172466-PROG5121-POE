package prog5121;

//Imports
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Task extends JFrame implements ActionListener
{
//------------------------------------------------------------------------------ 
    //Declaring JFrame
    JFrame taskBoard = new JFrame();

    //Declaring buttons 
    JButton taskButton;
    JButton reportButton;
    JButton quitButton;

    //Declaring label
    JLabel introLabel;

//Declaring variables
    int taskAmmount;

    int taskNumber;

    String taskName;

    String devName;

    String statusOfTask;

    String descriptionOfTask;
    
    int taskDuration;
   
    //Strings used to create Task ID
    String taskFirstLetters;
    String devLastLetters;
    String taskID;

    //Variables used for calculating total hours
    int sum;
    ArrayList<Integer> sumList = new ArrayList();
    int totalHours;

    String printDetails;
    
    //Arraylist to display Task Details
    ArrayList printTaskDetails = new ArrayList();

//-----------------------------First Method Start-------------------------------    
    //Creating JFrame
    public void task()
    {
        //Setting Welcome label parameters
        introLabel = new JLabel();
        introLabel.setBounds(100, -50, 500, 200);
        introLabel.setText("Welcome to EasyKanban");
        introLabel.setVisible(true);
        introLabel.setFont(new Font(null, Font.BOLD, 25));

        //Setting Task button parameters        
        taskButton = new JButton();
        taskButton.setBounds(140, 100, 200, 70);
        taskButton.addActionListener(this);
        taskButton.setText("Add Tasks");
        taskButton.setFocusable(false);
        taskButton.setFont(new Font(null, Font.BOLD, 25));
        taskButton.setForeground(Color.black);
        taskButton.setBackground(Color.white);

        //Setting Report button parameters
        reportButton = new JButton();
        reportButton.setBounds(140, 200, 200, 70);
        reportButton.addActionListener(this);
        reportButton.setText("Show Report");
        reportButton.setFocusable(false);
        reportButton.setFont(new Font(null, Font.BOLD, 25));
        reportButton.setForeground(Color.black);
        reportButton.setBackground(Color.white);

        //Setting Quit button parameters
        quitButton = new JButton();
        quitButton.setBounds(140, 300, 200, 70);
        quitButton.addActionListener(this);
        quitButton.setText("Quit");
        quitButton.setFocusable(false);
        quitButton.setFont(new Font(null, Font.BOLD, 25));
        quitButton.setForeground(Color.black);
        quitButton.setBackground(Color.white);

        //Setting JFrame parameters
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500, 500);
        this.setVisible(true);
        this.add(introLabel);
        this.add(taskButton);
        this.add(reportButton);
        this.add(quitButton);
    }
//-----------------------------First Method End---------------------------------



//-----------------------------Second Method Start------------------------------
    //Adding button actions
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Button to open KanBan board
        if (e.getSource() == taskButton)
        {
//------------------------------------------------------------------------------            
            //Run method to get number of tasks
            numberOfTasks();

            //Loop to run methods for task creation
            for (taskNumber = 0; taskNumber < taskAmmount; taskNumber++)
            {
                enterTaskName();
                devDetails();
                enterTaskDescription();
                checkTaskDescription(descriptionOfTask);
                taskStatus();
                taskDuration();
                createTaskID(taskName, taskNumber, devName);
                returnTotalHours(taskDuration);
                printTaskDetails();
            }
        }
        
//------------------------------------------------------------------------------
        //Button to access Report feature (currently not implemented)
        if (e.getSource() == reportButton)
        {
            JOptionPane.showMessageDialog(null, "This Feature is Coming Soon", "Coming Soon", JOptionPane.INFORMATION_MESSAGE);
        }

        //Button to Quit application
        if (e.getSource() == quitButton)
        {
            System.exit(0);
        }
    }
//-----------------------------Second Method End--------------------------------
    

    
//-----------------------------Third Method Start-------------------------------
    public int numberOfTasks()
    {
        //Try and catch to only allow number inputs
        try
        {
            //Input number of tasks
            taskAmmount = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks you want."));
            if( 1 > taskAmmount)
            {
                numberOfTasks();
            }
        }
        
        catch(Exception A)
        {                    
            JOptionPane.showMessageDialog(null, "Please Enter A Number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            numberOfTasks();
        }        
        return taskAmmount;        
    }
//-----------------------------Third Method End---------------------------------

    
    
//-----------------------------Fourth Method Start------------------------------
    public String enterTaskName()
    {
        //Input task name
        taskName = (JOptionPane.showInputDialog("Enter the New Name for the task"));
        if (taskName.length() < 2)
        {
            enterTaskName();
        }        
        return taskName;
    }
//-----------------------------Fourth Method End--------------------------------   

    
    
//-----------------------------Fifth Method Start------------------------------- 
    public String devDetails()
    {
        //Input full name of developer
        devName = JOptionPane.showInputDialog("Enter the Dev Name for the task");
        if (devName.length() < 3)
        {
            devDetails();
        }        
        return devName;
    }
//-----------------------------Fifth Method End---------------------------------

        
        
//-----------------------------Sixth Method Start-------------------------------
    public String enterTaskDescription()
    {
        //Input description of task
        descriptionOfTask = JOptionPane.showInputDialog("Enter the description for the task");
        return descriptionOfTask;
    }
    
    public boolean checkTaskDescription(String checkDescription)
    {    
        //While loop to check that description of task is less than 50 words
        while (checkDescription.length() >= 50)
        {
            JOptionPane.showMessageDialog(null, "Please enter a description of less than 50 words");
            enterTaskDescription();
            checkTaskDescription(descriptionOfTask);
            return false;
        }
        
        return true;
        
    }
//-----------------------------Sixth Method End---------------------------------

    
    
//-----------------------------Seventh Method Start-----------------------------  
    public String taskStatus()
    {
        //Array to hold different statuses of the task
        String[] status = new String[3];
        status[0] = "To Do";
        status[1] = "Doing";
        status[2] = "Done";

        //Input task status
        Object stateOfTask = JOptionPane.showInputDialog(null, "Task Status", "Status Selection", JOptionPane.QUESTION_MESSAGE, null, status, "To Do");
        statusOfTask = stateOfTask.toString();
        return statusOfTask;
    }
//-----------------------------Seventh Method End-------------------------------    

    
    
//-----------------------------Eighth Method Start------------------------------
    public int taskDuration()
    {       
        //Try and catch to only allow number inputs
        try
        {
            //Input task duration
            taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter the estimated duration of the task"));
            if( 0 > taskDuration)
            {
                taskDuration();
            }
        }
        
        catch(Exception A)
        {                    
            JOptionPane.showMessageDialog(null,"Please Enter A Number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            taskDuration();
        }                
        return taskDuration;
    }
//-----------------------------Eighth Method End--------------------------------

    
       
//-----------------------------Ninth Method Start-------------------------------
    public String createTaskID(String taskIDName, int taskIDNumber, String devIDName)
    {        
        //Using substrings to get specific letters of arrays
        taskFirstLetters = taskIDName.substring(0, 2);
        devLastLetters = devIDName.substring(devIDName.length() - 3);

//------------------------------------------------------------------------------
        //Create task ID
        taskID = (taskFirstLetters + ":" + taskIDNumber + ":" + devLastLetters).toUpperCase();
        return taskID;
    }
//-----------------------------Ninth Method End---------------------------------

    

//-----------------------------Tenth Method Start-------------------------------
    public int returnTotalHours(int totalHoursDuration)
    {
        //Initialise totalHours
        totalHours = 0;
        
        //Add task duration inputs to sumList array
        sum = totalHoursDuration;
        sumList.add(sum);

        //For loop to add durations to another array
        int[] arr = new int[sumList.size()];
        for (int i = 0; i < sumList.size(); i++)
        {
            arr[i] = sumList.get(i);
        }
        
        //For loop to calculate total hours
        for (int i = 0; i <sumList.size(); i++)
        {
            totalHours = totalHours + arr[i];
        }
        return totalHours;
    }
//-----------------------------Tenth Method End---------------------------------
    
    
    
//-----------------------------Eleventh Method Start----------------------------
    public String printTaskDetails()
    {
        //Adding different parts of task to arraylist
        printTaskDetails.add("The status of the task : " + statusOfTask);
        printTaskDetails.add("The name of the developer : " + devName);
        printTaskDetails.add("The number of the task : " + taskNumber);
        printTaskDetails.add("The name of the task : " + taskName);
        printTaskDetails.add("The description of the task : " + descriptionOfTask);
        printTaskDetails.add("The Task ID : " + taskID);
        printTaskDetails.add("The duration of the task is : " + taskDuration);
        printTaskDetails.add(" ");

        String output = "";

        //For loop to convert arraylist to String
        for (int i = 0; i < printTaskDetails.size(); i++)
        {
            printDetails = printTaskDetails.get(i).toString();
            output += printDetails + "\n";
        }
        
        //Display information as JOptionPane
        JOptionPane.showMessageDialog(null, output + "The total ammount of hours to complete all tasks is : " 
                                                   + totalHours, "All Tasks", JOptionPane.INFORMATION_MESSAGE);
        return printDetails;
    }
//-----------------------------Eleventh Method End------------------------------
    
}
//---------------------------**** END OF FILE ****------------------------------