package DataClass;

import java.awt.Color;

import Container.MyHashMap;
import Observer.DataSetChangedObserver;

public class Rule implements DataBaseItem{
    private int ruleId;
    private String instruction;
    private String title = "The Rule";
    private MyHashMap<String,String> paragraphs;
    private boolean finished;
    private Color backgroundColor;

    public Rule(int ruleId, String instruction){
        this.ruleId = ruleId;
        this.instruction = instruction;
    }

    public int getRuleId(){
        return ruleId;
    }
    public String getTitle() {
    	return title;
    }
    public boolean isFinished() {
    	return finished;
    }
    public Color getBackgroundColor() {
    	return backgroundColor;
    }

    public MyHashMap<String,String> getParagraphs(){
        return paragraphs;
    }
    public void setFinished(Color backgroundColor) {
    	finished = true;
    	this.backgroundColor = backgroundColor;
    	
    }

    public void setParagraphs(MyHashMap<String,String> paragraphs){
        this.paragraphs = paragraphs;
    }
    public String getInstruction() {
    	return instruction;
    }

	@Override
	public void setComponentObserver(DataSetChangedObserver observer) {
		// TODO Auto-generated method stub
		
	}
}