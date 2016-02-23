package fsm;

/**
 * Base class for all states defining their base minimum
 * functionality to allow for abstraction within FSM.
 * -----------------------------------------------------
 * Created by Vincent de Almeida | Updated: 29/01/2016
 * -----------------------------------------------------
 */
public class State
{
	private String ID;

	/**
	 * This is constructor that sets the name of the state
	 * @param ID id is the String that would become the state name
	 * identifier used to switch between states.
	 */
	public State(String ID) {
		this.ID = ID;
	}

	/**
	 * This method updates the state.When a state is created it should be overwritten with what to do when updated.
	 */
	public void update() {
	}

	/**
	 * This method renders the state to screen. Method is intended to be overwritten.
	 */
	public void render() {
	}

	/**
	 * This method performs any initialisation needed when the program enters this state. Method intended to be overwritten.
	 */
	public void onEntry() {
	}

	/**
	 * This method performs any clean up needed when the program enters this state. Method intended to be overwritten.
	 */
	public void onExit() {
	}

	/**
	 * This method is used to retrieve the Name or id of the state
	 * @return String with name of State
	 */
	public String getStateID() {
		return ID;
	}
}
