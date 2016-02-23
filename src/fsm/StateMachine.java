package fsm;

import java.util.Vector;

/**
 * State Machine class
 */
public class StateMachine 
{
	private static StateMachine instance = null;
	private Vector<State> states;
	private State currentState;
	private State previousState;

	/**
	 * StateMachine controls the multiple states in the game,when called StateMachine creates a buffer of five states
	 * as well as clearing the currentState
	 */
	private StateMachine()
	{
		states = new Vector<>(5);
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

	/**
	 * This adds a new State for the StateMachine to control
	 * @param newState This is the state to be added
	 */
	public void addState(State newState) {
		states.addElement(newState);
	}

	/**
	 * This switches from the current state to the state Specified
	 * @param stateID This is that will be switched too
	 */
	public void setState(String stateID) {
		for(int i = 0; i < states.size(); i++) {
			if( states.elementAt(i).getStateID().equals(stateID) ) {
				previousState = currentState;
				currentState = states.elementAt(i);

				if(previousState != null)
					previousState.onExit();

				currentState.onEntry();
			}
		}
	}

	/**
	 * This simply calls the render method on the current state
	 */
	public void renderCurrentState() {
		if(currentState != null)
			currentState.render();
	}

	/**
	 * This calls the update method on the current state
	 */
	public void updateCurrentState(){
		if(currentState != null)
			currentState.update();
	}

	public String getPreviousStateID(){
		return previousState.getStateID();
	}
}
