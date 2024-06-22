/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package javaapplication21;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sihle
 */
public class POEPart3Test {

    private POEPart3 manager;

    @BeforeEach
    public void setUp() {
        manager = new POEPart3(4);
        manager.addTask(new Task("To Do", "Mike", "Smith", 1, "Create Login", "Create a login functionality", 5));
        manager.addTask(new Task("Doing", "Edward", "Harrison", 2, "Create Add Features", "Add new features to the application", 8));
        manager.addTask(new Task("Done", "Samantha", "Paulson", 3, "Create Reports", "Create reports module", 2));
        manager.addTask(new Task("To Do", "Glenda", "Oberholzer", 4, "Add Arrays", "Integrate arrays in the system", 11));
    }

    @Test
    public void testDeveloperArrayPopulated() {
        List<String> expectedDevelopers = new ArrayList<>();
        expectedDevelopers.add("Mike Smith");
        expectedDevelopers.add("Edward Harrison");
        expectedDevelopers.add("Samantha Paulson");
        expectedDevelopers.add("Glenda Oberholzer");

        List<String> actualDevelopers = new ArrayList<>();
        for (Task task : manager.tasks) {
            actualDevelopers.add(task.getDeveloperFullName());
        }

        assertEquals(expectedDevelopers, actualDevelopers);
    }

    @Test
    public void testTaskWithLongestDuration() {
        Task longestTask = null;
        int longestDuration = 0;
        for (Task task : manager.tasks) {
            if (task.getDuration() > longestDuration) {
                longestDuration = task.getDuration();
                longestTask = task;
            }
        }
        assertNotNull(longestTask);
        assertEquals("Glenda Oberholzer", longestTask.getDeveloperFullName());
        assertEquals(11, longestTask.getDuration());
    }

    @Test
    public void testSearchTaskByName() {
        Task foundTask = null;
        String taskName = "Create Login";
        for (Task task : manager.tasks) {
            if (task.getTaskName().equals(taskName)) {
                foundTask = task;
                break;
            }
        }
        assertNotNull(foundTask);
        assertEquals("Mike Smith", foundTask.getDeveloperFullName());
        assertEquals(taskName, foundTask.getTaskName());
    }

    @Test
    public void testSearchTasksByDeveloper() {
        String developerName = "Samantha Paulson";
        List<Task> developerTasks = new ArrayList<>();
        for (Task task : manager.tasks) {
            if (task.getDeveloperFullName().equals(developerName)) {
                developerTasks.add(task);
            }
        }

        assertEquals(1, developerTasks.size());
        assertEquals("Create Reports", developerTasks.get(0).getTaskName());
    }

    @Test
    public void testDeleteTask() {
        String taskNameToDelete = "Create Reports";
        Task taskToDelete = null;
        for (Task task : manager.tasks) {
            if (task.getTaskName().equals(taskNameToDelete)) {
                taskToDelete = task;
                break;
            }
        }
        manager.tasks.remove(taskToDelete);

        Task taskDeleted = null;
        for (Task task : manager.tasks) {
            if (task.getTaskName().equals(taskNameToDelete)) {
                taskDeleted = task;
                break;
            }
        }
        assertNull(taskDeleted);
    }
}