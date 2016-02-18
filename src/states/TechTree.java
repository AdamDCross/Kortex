package states;

import fsm.State;
import fsm.StateMachine;
import main.Button;
import main.Line;
import main.Window;
import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;

import java.util.Vector;

/**
*
 */
public class TechTree extends State {
    private String techTreeCSV;
    private Vector<Vector<Button>> rows;
    private Vector<Vector2f> lineStart;
    private Vector<Vector2f> lineEnd;

    public static final float PADDING = 60.0f;

    public TechTree(){
        super("TECH_TREE");
        rows = new Vector<>(5);
        lineStart = new Vector<>(5);
        lineEnd = new Vector<>(5);
        techTreeCSV = "src/assets/tech_tree.csv";

        float halfWindowWidth = Window.getInstance().getScreenWidth() / 2;
        rows.addElement(new Vector<Button>(2));
        rows.elementAt(0).addElement(new Button("Laser Turret",new FloatRect(0.0f, 0.0f, halfWindowWidth, MainMenu.BUTTON_HEIGHT),35,"LASER",false));
        rows.elementAt(0).addElement(new Button("Rocket Turret",new FloatRect(halfWindowWidth, 0.0f, halfWindowWidth, MainMenu.BUTTON_HEIGHT),35,"ROCKET",false));

        rows.addElement(new Vector<Button>(2));
        FloatRect laserRect = new FloatRect(0.1f, MainMenu.BUTTON_HEIGHT + PADDING, halfWindowWidth - 0.1f, 3 * MainMenu.BUTTON_HEIGHT);
        rows.elementAt(1).addElement(new Button("Laser V1\nDamage: 10\nRange: 5\nHealth: 100\nXP Requirements: 0\nScrap cost: 100",
                laserRect,20,"LASER V1",true));

        FloatRect rocketRect = new FloatRect(halfWindowWidth, MainMenu.BUTTON_HEIGHT + PADDING, halfWindowWidth, 3.5f * MainMenu.BUTTON_HEIGHT);
        rows.elementAt(1).addElement(new Button("Rocket V1\nDamage: 10\nAOE: 0.5\nRange: 8\nHealth: 75\nXP Requirements: 0\nScrap cost: 100",
                rocketRect,20,"ROCKET V1",true));

        rows.addElement(new Vector<Button>(2));
        addLaserRocketRow(2,20,7,120,2000,200,20,1,7,120,2000,200,halfWindowWidth,laserRect.top + laserRect.height, rocketRect.top + rocketRect.height);

        rows.addElement(new Vector<Button>(2));
        float laserOffset = 3 * MainMenu.BUTTON_HEIGHT;
        float rocketOffset = 3.5f * MainMenu.BUTTON_HEIGHT;
        //connect left side
        connectBoxes(rows.elementAt(1).elementAt(0).getDimensions(), rows.elementAt(2).elementAt(0).getDimensions());

        //connect right side
        connectBoxes(rows.elementAt(1).elementAt(1).getDimensions(), rows.elementAt(2).elementAt(1).getDimensions());

        rows.addElement(new Vector<>(2));
        addLaserRocketRow(3,30,10,150,3500,350,30,2,10,150,3500,200,halfWindowWidth,laserRect.top + laserRect.height + PADDING, rocketRect.top + rocketRect.height + PADDING);

        //connect left side
        connectBoxes(rows.elementAt(2).elementAt(0).getDimensions(), rows.elementAt(3).elementAt(0).getDimensions());

        //connect right side
        connectBoxes(rows.elementAt(2).elementAt(1).getDimensions(), rows.elementAt(3).elementAt(1).getDimensions());
    }

    private void addLaserRocketRow(int rowNum, int laserDmg, int laserRng, int laserHlth, int laserXP, int laserScrap,
                                   int rocketDmg, int AOE, int rocketRng, int rocketHlth, int rocketXP, int rocketScrap, float width, float laserY, float rocketY){
        rows.elementAt(rowNum).addElement(
                new Button("Laser V" + rowNum + "\nDamage: " + laserDmg + "\nRange: " + laserRng + "\nHealth: " + laserHlth + "\nXP Requirements: " + laserXP + "\nScrap cost: " + laserScrap,
                new FloatRect(0.1f, ((rowNum-1) * (3.0f * MainMenu.BUTTON_HEIGHT)) + ((rowNum-1) * PADDING) + laserY, width - 0.1f, 3 * MainMenu.BUTTON_HEIGHT),20,"LASER V" + rowNum,true));
        rows.elementAt(rowNum).addElement(
                new Button("Rocket V" + rowNum + "\nDamage: " + rocketDmg + "\nAOE: " + AOE + "\nRange: " + rocketRng + "\nHealth: " + rocketHlth + "\nXP Requirements: " + rocketXP + "\nScrap cost: " + rocketScrap,
                new FloatRect(width, ((rowNum-1) *(3.5f * MainMenu.BUTTON_HEIGHT)) + ((rowNum-1) * PADDING) + rocketY, width, 3.5f * MainMenu.BUTTON_HEIGHT),20,"ROCKET V" + rowNum,true));
    }

    private void connectBoxes(FloatRect startBox, FloatRect endBox){
        lineStart.addElement(new Vector2f(startBox.left + (startBox.width / 2), startBox.top + startBox.height));
        lineEnd.addElement(new Vector2f(endBox.left + (endBox.width / 2), endBox.top));
    }

    @Override
    public void onEntry() {
        super.onEntry();
    }

    @Override
    public void onExit() {
        super.onExit();
    }

    @Override
    public void update() {
        super.update();

        for (Event e : Window.getInstance().getGameWindow().pollEvents()) {
            switch(e.type) {
                case CLOSED:
                    Window.getInstance().getGameWindow().close();
                    break;
                case MOUSE_BUTTON_PRESSED:
                    StateMachine.getInstance().setState("MAIN_MENU");
                    break;
            }
        }
    }

    @Override
    public void render() {
        super.render();

        for(int i = 0; i < rows.size(); i++){
            for(int j = 0; j < rows.elementAt(i).size(); j++){
                rows.elementAt(i).elementAt(j).render();
            }
        }

        for(int i = 0; i < lineStart.size(); i++){
            Line.drawLine(lineStart.elementAt(i), lineEnd.elementAt(i));
        }
    }
}
