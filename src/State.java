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
	}
	
	public void onExit() {
	}
	
	public String getStateID() {
		return ID;
	}
}
