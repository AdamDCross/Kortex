package fsm;

public class State
{
	private String ID;
	
	public State(String ID) {
		this.ID = ID;
	}
	
	public void update() {
	}
	
	public void render() {
	}
	
	public void onEntry() {
		System.out.println("Current State is: "+getStateID());
	}
	
	public void onExit() {
	}
	
	public String getStateID() {
		return ID;
	}
}
