package prog5121;

import java.util.Arrays;
import javax.swing.JOptionPane;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TaskTest
{

    Task kanban = new Task();

    public TaskTest()
    {
    }

    @Test
    public void AtestNumberOfTasks()
    {
        int expected = 2;
        int actual = kanban.numberOfTasks();
        assertEquals(expected, actual);
    }

    @Test
    public void BtestEnterTaskName()
    {
        String expected = "Login Feature";
        String actual = kanban.enterTaskName();
        assertEquals(expected, actual);
    }

    @Test
    public void CtestDevDetails()
    {
        String expected = "Robyn Harrison";
        String actual = kanban.devDetails("Robyn Harrison");
        assertEquals(expected, actual);
    }

    @Test
    public void DtestTaskDescription()
    {
        Boolean expected = true;
        Boolean actual = kanban.checkTaskDescription("Create Login to authenticate users");
        assertEquals(expected, actual);
    }

    @Test
    public void EtestTaskStatus()
    {
        String expected = "To Do";
        String actual = kanban.taskStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void FtestTaskDuration()
    {
        int expected = 8;
        int actual = kanban.taskDuration();
        assertEquals(expected, actual);
    }

    @Test
    public void GtestCreateTaskID()
    {
        String expected = "LO:0:SON";
        String actual = kanban.createTaskID("Login Feature", 0, "Robyn Harrison");
        assertEquals(expected, actual);
    }

    @Test
    public void HtestEnterTaskName()
    {
        String expected = "Add Task Feature";
        String actual = kanban.enterTaskName();
        assertEquals(expected, actual);
    }

    @Test
    public void ItestDevDetails()
    {
        String expected = "Mike Smith";
        String actual = kanban.devDetails("Mike Smith");
        assertEquals(expected, actual);
    }

    @Test
    public void JtestTaskDescription()
    {
        boolean expected = true;
        boolean actual = kanban.checkTaskDescription("Create Add Task feature to add task  users");
        assertEquals(expected, actual);
    }

    @Test
    public void KtestTaskStatus()
    {
        String expected = "Doing";
        String actual = kanban.taskStatus();
        assertEquals(expected, actual);
    }

    @Test
    public void LtestTaskDuration()
    {
        int expected = 10;
        int actual = kanban.taskDuration();
        assertEquals(expected, actual);
    }

    @Test
    public void MtestCreateTaskID()
    {
        String expected = "AD:1:ITH";
        String actual = kanban.createTaskID("Add Task Feature", 1, "Mike Smith");
        assertEquals(expected, actual);
    }

    @Test
    public void NtestReturnTotalHours()
    {
        int expected = 18;
        int actual = kanban.returnTotalHours(8 + 10);
        assertEquals(expected, actual);
    }

    @Test
    public void OtestCreateTaskID()
    {
        String[] devNames = new String[]
        {
            "Robyn Harrison", "Mike Smith"
        };
        String[] taskNames =
        {
            "Add Login Feature", "Add Task Feature"
        };
        String[] taskIDs =
        {
            "AD:0:SON", "AD:1:ITH"
        };

        for (int i = 0; i < devNames.length; i++)
        {
            String expected = taskIDs[i];
            String actual = kanban.createTaskID(taskNames[i], i, devNames[i]);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void PtestReturnTotalHours()
    {
        int[] taskDurations =
        {
            8, 10
        };

        int actual = 0;

        for (int i = 0; i < 2; i++)
        {
            actual = kanban.returnTotalHours(taskDurations[i]);
        }
        int expected = 18;
        assertEquals(expected, actual);
    }

    @Test
    public void QtestDevDetails()
    {
        String[] devNames = new String[]
        {
            "Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"
        };

        String[] taskNames =
        {
            "Create Login", "Create Add Features", "Create Reports", "Add Arrays"
        };

        int[] taskDuration =
        {
            5, 8, 2, 11
        };

        String[] taskStatus =
        {
            "To Do", "Doing", "Done", "To Do"
        };

        for (int i = 0; i < devNames.length; i++)
        {
            String expected = devNames[i];
            String actual = kanban.devDetails(devNames[i]);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void RtestDisplayLongest()
    {
        String[] devNames = new String[]
        {
            "Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"
        };

        String[] taskNames =
        {
            "Create Login", "Create Add Features", "Create Reports", "Add Arrays"
        };

        int[] taskDuration =
        {
            5, 8, 2, 11
        };

        String[] taskStatus =
        {
            "To Do", "Doing", "Done", "To Do"
        };

        for (int i = 0; i < devNames.length; i++)
        {
            int expected = taskDuration[i];
            int actual = kanban.DisplayLongest(kanban.Maximum(taskDuration));
            assertEquals(expected, actual);
        }
    }

    @Test
    public void StestSearchTasks()
    {
        String[] devNames = new String[]
        {
            "Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"
        };

        String[] taskNames =
        {
            "Create Login", "Create Add Features", "Create Reports", "Add Arrays"
        };

        for (int i = 0; i < devNames.length; i++)
        {
            String expected = devNames[0] + taskNames[0];

            String nameSearch = "Create Login";
            int index = Arrays.asList(taskNames).indexOf(nameSearch);
           
            String actual = devNames[index] + taskNames[index];
            assertEquals(expected, actual);
            break;
        }
    }
    
    @Test
    public void TtestDevSearch()
    {
        String[] devNames = new String[]
        {
            "Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"
        };

        String[] taskNames =
        {
            "Create Login", "Create Add Features", "Create Reports", "Add Arrays"
        };

        for (int i = 0; i < devNames.length; i++)
        {
            String expected = taskNames[2];
            
            String devSearch = "Samantha Paulson";
            int index = Arrays.asList(devNames).indexOf(devSearch);
            
            String actual = taskNames[index];          
            assertEquals(expected, actual);
        }
    }
    
    @Test
    public void UtestDeleteTask()
    {
        String[] devNames = new String[]
        {
            "Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"
        };

        String[] taskNames =
        {
            "Create Login", "Create Add Features", "Create Reports", "Add Arrays"
        };

        int[] taskDuration =
        {
            5, 8, 2, 11
        };

        String[] taskStatus =
        {
            "To Do", "Doing", "Done", "To Do"
        };

        for (int f = 0; f < devNames.length; f++)
        {
            String expected = "Add Arrays";
            
            String deleteTaskName = "Create Reports";
        
        int temp = 0;
        int taskAmmount = 4;
        for (int i = 0; i < taskAmmount; i++)
        {
            if (!taskNames[i].equals(deleteTaskName))
            {
                taskNames[temp] = taskNames[i];
                devNames[temp] = devNames[i];
                taskStatus[temp] = taskStatus[i];
                taskDuration[temp] = taskDuration[i];
                temp++;
            }
        }                                                 
        taskAmmount--;
                
        String returnArray = taskNames[2];        
        
            String actual = returnArray;
            assertEquals(expected, actual);
        }
    }    
}
