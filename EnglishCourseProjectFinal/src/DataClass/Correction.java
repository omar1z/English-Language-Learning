package DataClass;

import java.sql.*;
import java.util.ArrayList;

import DataClass.DataBaseConnection;
import VariablePool.ColorPool;

import javax.swing.*;

import DataClass.*;

import java.awt.*;

public class Correction {

    public static Float correct(Lesson lesson,Exercise exercise, User user, ArrayList<? extends JComponent> components) {
    			
    	int index = 0;
        float mark = 0;
        int number = components.size();
        try {
            ResultSet solSet = DataBaseConnection.getStatment()
                    .executeQuery("select solution from Exercises where exercise_id = "
                            + exercise.getExerciseId() + " ;");
            solSet.next();
            if (exercise.getType().equals("List")) {

                for (JComponent component : components) {
                    JComboBox<String> comboBox = (JComboBox<String>) component;
                    int[] solution = convertToIntArray(solSet.getString("solution").split(","));
                    if (solution[index] == comboBox.getSelectedIndex()) {
                        comboBox.setForeground(Color.GREEN);
                        mark++;
                    } else {
                        comboBox.setForeground(Color.BLACK);
                        comboBox.setSelectedIndex(solution[index]);
                    }
                    index++;
                }

            } else if (exercise.getType().equals("FinalTest")) {
                String[] solution = solSet.getString("solution").split(",");
                for (JComponent component : components) {
                    if (component instanceof JComboBox) {
                        JComboBox<String> comboBox = (JComboBox<String>) component;
                        int sol = Integer.parseInt(solution[index]);
                        if (sol == comboBox.getSelectedIndex()) {
                            comboBox.setForeground(Color.GREEN);
                            mark++;
                        } else {
                            comboBox.setForeground(Color.BLACK);
                            comboBox.setSelectedIndex(sol);
                        }

                    } else if (component instanceof JTextField) {
                        JTextField textField = (JTextField) component;
                        if (solution[index].equals(textField.getText())) {
                            textField.setForeground(Color.GREEN);
                            mark++;
                        } else {
                            textField.setForeground(Color.BLACK);
                            textField.setText(solution[index]);
                        }
                    }
                    index++;
                }

            }else if(exercise.getType().equals("DragDrop")) {
            	for (JComponent component : components) {
                    JLabel textField = (JLabel) component;
                    String[] solution = solSet.getString("solution").split(",");
                    if (solution[index].equals(textField.getText())) {
                        textField.setForeground(Color.GREEN);
                        mark++;
                    } else {
                        textField.setForeground(Color.BLACK);
                        textField.setText(solution[index]);
                    }
                    index++;
                }
            }
            
            else {
                for (JComponent component : components) {
                    JTextField textField = (JTextField) component;
                    textField.setBorder(BorderFactory.createMatteBorder(0,0,1, 0,Color.black));
                    String[] solution = solSet.getString("solution").split(",");
                    if (solution[index].equals(textField.getText())) {
                        textField.setForeground(Color.GREEN);
                        mark++;
                    } else {
                        textField.setForeground(Color.BLACK);
                        textField.setText(solution[index]);
                    }
                    index++;
                }

            }
            mark = (mark / number) * 100;
            ResultSet levelId = DataBaseConnection.getStatment().executeQuery("SELECT Lessons.level_id FROM Lessons INNER JOIN Exercises ON Exercises.lesson_id = Lessons.lesson_id and exercise_id = "+exercise.getExerciseId()+";");
            levelId.next();
            int level_id=levelId.getInt("level_id");
            ColorPool c=new ColorPool();
            Color backgroundColor;
           switch (level_id) {
            case 1:
                backgroundColor= c.getColor("dPink");
                break;
            case 2:
            backgroundColor= c.getColor("dGreen");
            break;
            default:
                backgroundColor= c.getColor("dAdv"); 
                break;
           }
            exercise.setFinished(mark,lesson,user,backgroundColor);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mark;
    }

    private static int[]  convertToIntArray(String[] stringArray) {
        int length = stringArray.length;
        int[] intArray = new int[length];

        for (int i = 0; i < length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }

        return intArray;
    }
}