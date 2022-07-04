package prog5121;

//Imports
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Task extends JFrame implements ActionListener
{
//------------------------------------------------------------------------------ 
    //Declaring JFrame
    private static JFrame taskBoard = new JFrame();

    //Declaring buttons 
    private static JButton taskButton;
    private static JButton reportButton;
    private static JButton quitButton;

    //Declaring label
    private static JLabel introLabel;

//Declaring variables
    private static int taskAmmount;

    private static String taskName;

    private static String devName;

    private static String statusOfTask;

    private static String descriptionOfTask;

    private static int taskDuration;
    
    private static String fullList;

    //Strings used to create Task ID
    private static String taskFirstLetters;
    private static String devLastLetters;
    private static String taskID;

    //Variables used for calculating total hours
    private static int sum;
    private static ArrayList<Integer> sumList = new ArrayList();
    private static int totalHours;

    private static String printDetails;

    //Arraylist to display Task Details

    String[] arrayTaskName = new String[10];
    String[] arrayDevDetails = new String[10];
    int[] arrayTaskNumber = new int[10];
    int[] arrayTaskDuration = new int[10];
    String[] arrayTaskDescription = new String[10];
    String[] arrayTaskStatus = new String[10];
    String[] arrayTaskID = new String[10];

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
            for (int i = 0; i < taskAmmount; i++)
            {
                arrayTaskNumber[i] = i;
                arrayTaskName[i] = enterTaskName();
                arrayDevDetails[i] = devDetails(devName);
                arrayTaskDescription[i] = enterTaskDescription();
                checkTaskDescription(descriptionOfTask);
                arrayTaskStatus[i] = taskStatus();
                arrayTaskDuration[i] = taskDuration();
                arrayTaskID[i] = createTaskID(taskName, i, devName);
                returnTotalHours(taskDuration);
            }
            
            printTaskDetails();
        }

//------------------------------------------------------------------------------
        //Button to access Report feature (currently not implemented)
        if (e.getSource() == reportButton)
        {
            //JOptionPane.showMessageDialog(null, "This Feature is Coming Soon", "Coming Soon", JOptionPane.INFORMATION_MESSAGE);
            ShowReport();
        }

//------------------------------------------------------------------------------        
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
            if (1 > taskAmmount)
            {
                numberOfTasks();
            }
        } catch (Exception A)
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
    public String devDetails(String tempDevName)
    {
        //Input full name of developer
        devName = tempDevName = JOptionPane.showInputDialog("Enter the Dev Name for the task");

        if (devName.length() < 3)
        {
            devDetails(devName);
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

    //Method to check task description
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
            if (0 > taskDuration)
            {
                taskDuration();
            }
        } catch (Exception A)
        {
            JOptionPane.showMessageDialog(null, "Please Enter A Number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
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
        for (int i = 0; i < sumList.size(); i++)
        {
            totalHours = totalHours + arr[i];
        }
        
        return totalHours;
    }
//-----------------------------Tenth Method End---------------------------------

    
    
//-----------------------------Eleventh Method Start----------------------------
    public String printTaskDetails()
    {
        for(int i = 0; i < taskAmmount;i++)
        {             
            System.out.println(" ");
            System.out.println("Task " + i);
            System.out.println("--------------------------------------");
            System.out.println("Task Status: " + arrayTaskStatus[i]);
            System.out.println("Developer Details: " + arrayDevDetails[i]); 
            System.out.println("Task Number: " + arrayTaskNumber[i]);
            System.out.println("Task Name: " + arrayTaskName[i]);  
            System.out.println("Task Name: " + arrayTaskDescription[i]);
            System.out.println("Task Name: " + arrayTaskID[i]);            
            System.out.println("Task Duration: " + arrayTaskDuration[i]);           
            System.out.println("--------------------------------------");                     
        }                 
        
        System.out.println("The Total Hours required to complete all tasks: " + totalHours);
        System.out.println("--------------------------------------");
        
        return printDetails;
    }
//-----------------------------Eleventh Method End------------------------------

    
    
//-----------------------------Twelfth Method Start-----------------------------
    public void ShowReport()
    {
        //Try and catch to only allow number inputs
        try
        {
            //Option select for switch statement
            int switchInput = Integer.parseInt(JOptionPane.showInputDialog("Enter 1 to Display Completed Tasks"
                    + "\nEnter 2 to View Longest Task"
                    + "\nEnter 3 to Search Tasks"
                    + "\nEnter 4 to Search Tasks Being Worked on by Specific Dev"
                    + "\nEnter 5 to Delete Task"
                    + "\nEnter 6 to Display All Tasks"
                    + "\nEnter 7 to Return to Main Menu"));
            
            //If an invalid input is entered the method repeats
            if (0 > switchInput)
            {
                ShowReport();
            }

            //Switch statement to perform various functions
            switch (switchInput)
            {
                //Display all completed tasks
                case 1:
                    DisplayFinished();
                    ShowReport();
                    break;

                //Display task with the longest duration    
                case 2:
                    DisplayLongest(0);
                    ShowReport();
                    break;

                //Display specific task after searching by name
                case 3:                    
                    SearchTasks();
                    ShowReport();
                    break;

                //Display all tasks worked on by specific dev
                case 4:                    
                    SearchDevs();
                    ShowReport();
                    break;
                    
                //Delete task after searching by name
                case 5:
                    DeleteTask();
                    ShowReport();
                    break;
                    
                //Display all tasks
                case 6:
                    DisplayAllTasks();
                    ShowReport();
                    break;
                    
                case 7:
                    break;
            }
        } 
        
        //Text displayed when invalid input is entered
        catch (Exception A)
        {
            JOptionPane.showMessageDialog(null, "There are no tasks that meet requirements", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            ShowReport();
        }
    }
//-----------------------------Twelfth Method End-------------------------------

    
    
//-----------------------------Thirteenth Method Start--------------------------
    public void DisplayFinished()
    {                  
        //Get indexes of all tasks with "Done" statuses
        int index = Arrays.asList(arrayTaskStatus).indexOf("Done");
        System.out.println(index);
        
        //Display task name, dev details and task duration
        JOptionPane.showMessageDialog(null, "Task Name: " + arrayTaskName[index] 
                + "\nDeveloper Details: " + arrayDevDetails[index] 
                + "\nTask Duration: " + arrayTaskDuration[index], "Task " + index, JOptionPane.INFORMATION_MESSAGE);
    }
//-----------------------------Thirteenth Method End----------------------------
    
    
    
//-----------------------------Fourteenth Method Start--------------------------
    public int DisplayLongest(int tempIndex)
    {                   
        if(arrayDevDetails[0].equalsIgnoreCase("null"))
        {
            ShowReport();           
        }
                    
        int index = Task.Maximum(arrayTaskDuration);                
        tempIndex = index;
        
        //Display dev details and task duration
        JOptionPane.showMessageDialog(null, "Developer Details: " + arrayDevDetails[tempIndex] 
                + "\nTask Duration: " + arrayTaskDuration[tempIndex], "Task " + tempIndex, JOptionPane.INFORMATION_MESSAGE);
        return arrayTaskDuration[tempIndex];
        }
    
        static int Maximum(int temp[]) 
	{ 
		int maximum,index = 0, i = 1; 
		maximum = temp[0]; 
		while ( i < temp.length) 
		{ 
			if (maximum < temp[i]) 
			{ 
                            maximum = temp[i]; 
                            index = i; 
			} 
			i++; 
		} 
		return index; 
	} 
//-----------------------------Fourteenth Method End----------------------------

            
    
//-----------------------------Fifteenth Method Start---------------------------
    public void SearchTasks()
    {
        //Get index of the task you have searched for
        String nameSearch = JOptionPane.showInputDialog(null, "Enter the name of the task you want to search for");        
        int index = Arrays.asList(arrayTaskName).indexOf(nameSearch);
        
        //Display task name, dev details and task status
        JOptionPane.showMessageDialog(null, "Task Name: " + arrayTaskName[index] 
                + "\nDeveloper Details: " + arrayDevDetails[index] 
                + "\nTask Status: " + arrayTaskStatus[index], "Task " + index, JOptionPane.INFORMATION_MESSAGE);
        
    }
//-----------------------------Fifteenth Method End-----------------------------

        
    
//-----------------------------Sixteenth Method End-----------------------------        
    public void SearchDevs()
    {
        //Get indexes of the tasks worked on by the dev you have searched for 
        String devSearch = JOptionPane.showInputDialog(null, "Enter the name of the dev you want to search for");
        int index = Arrays.asList(arrayDevDetails).indexOf(devSearch);
        
        //Display task name and task status
        JOptionPane.showMessageDialog(null, "Task Name: " + arrayTaskName[index] 
                + "\nTask Status: " + arrayTaskStatus[index], "Task " + index, JOptionPane.INFORMATION_MESSAGE);
    }
//-----------------------------Sixteenth Method End-----------------------------  
    
    
    
//-----------------------------Seventeenth Method Start-------------------------
    public String DeleteTask()
    {                
        String deleteTaskName = JOptionPane.showInputDialog("Please enter the name of the task you wish to delete");
        
        int temp = 0;
        for (int i = 0; i < taskAmmount; i++)
        {
            if (!arrayTaskName[i].equals(deleteTaskName))
            {
                arrayTaskNumber[temp] = arrayTaskNumber[i];
                arrayTaskName[temp] = arrayTaskName[i];
                arrayDevDetails[temp] = arrayDevDetails[i];
                arrayTaskDescription[temp] = arrayTaskDescription[i];
                arrayTaskStatus[temp] = arrayTaskStatus[i];
                arrayTaskDuration[temp] = arrayTaskDuration[i];
                temp++;
            }
        }                                
                 
        taskAmmount--;
                
        String returnArray = "Entry successfully deleted";
        JOptionPane.showMessageDialog(null, returnArray);
        
        return returnArray;
    }
//-----------------------------Seventeenth Method End---------------------------    
    
    
    
//-----------------------------Eighteenth Method Start--------------------------
    public void DisplayAllTasks()
    {
        //If list is empty display invalid message
        if(arrayDevDetails[0].equalsIgnoreCase("null"))
        {
            ShowReport();           
        }                

        //Display all tasks on separate JOptionPane
        for(int i = 0; i < taskAmmount;i++)
        {             
            System.out.println(" ");
            System.out.println("Task " + i);
            System.out.println("--------------------------------------");
            System.out.println("Task Status: " + arrayTaskStatus[i]);
            System.out.println("Developer Details: " + arrayDevDetails[i]); 
            System.out.println("Task Number: " + i);
            System.out.println("Task Name: " + arrayTaskName[i]);  
            System.out.println("Task Name: " + arrayTaskDescription[i]);
            System.out.println("Task Name: " + arrayTaskID[i]);            
            System.out.println("Task Duration: " + arrayTaskDuration[i]);
            System.out.println("--------------------------------------");
        }
        
        System.out.println("The Total Hours required to complete all tasks: " + totalHours);
        System.out.println("--------------------------------------");
    }
//-----------------------------Eighteenth Method End----------------------------    
}
//---------------------------**** END OF FILE ****------------------------------
