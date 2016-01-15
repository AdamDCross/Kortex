import java.util.Vector;

public class StateMachine 
{
	private static StateMachine instance = null;
	private Vector<State> states;
	private State currentState;
	
	private StateMachine()
	{
		states = new Vector<State>(5);
		currentState = null;
	}
	
	public static StateMachine getInstance()
	{
		if( instance == null )
		{
			instance = new StateMachine();
		}
		
		return instance;
	}
	
	public void addState(State newState) {
		states.addElement(newState);
	}
	
	public void setState(String stateID) {
		for(int i = 0; i < states.size(); i++) {
			if( states.elementAt(i).getStateID().equals(stateID) ) {
				State previousState = currentState;
				currentState = states.elementAt(i);

				if(previousState != null)
					previousState.onExit();

				currentState.onEntry();
			}
		}
	}
	
	public void renderCurrentState() {
		if(currentState != null)
			currentState.render();
	}

	public void updateCurrentState(){
		if(currentState != null)
			currentState.update();
	}
}
