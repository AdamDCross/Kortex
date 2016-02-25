package graphics;

import assets.ArtAsset;
import assets.AssetManager;
import dijkstra.Dijkstra;
import dijkstra.Pathfind;
import enemy.NPCHandle;
import fsm.StateMachine;
import main.*;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import player.Player;
import player.Turret;
import states.Game;

import java.util.Vector;

/**
 *  TODO
 */
public class HUD implements Render {
    private FloatRect bottomBarRect;
    private FloatRect topBarRect;
    private FloatRect gameWindowRect;
    private Button research;
    private Button pause;
    private Game game;

    private Vector<Button> btns;

    private Dijkstra dijkstra;

    private Button[][] grid;
    public static final int HUD_GRID_COL_COUNT = 20;
    public static final int HUD_GRID_ROW_COUNT = 15;

    private Message waves;
    private Message XP;
    private Message score;

    private long prevTime;
    private long currentTime;
    private long localElapsedTime;
    private long delay;
    private boolean change;

    public static final int MAX_BUTTON_COUNT = 12;

    private int selectedTurret;
    private Vector<Turret> inGridTurrets;

    private Vector<Turret> turrets;

    private boolean followMouse;
    private FloatRect mouseBox;
    private Vector2f currentMousePos;

    public HUD(float btmBarHeightAsPercent, float topBarHeightAsPercent, Game game, Player player){
        this.game = game;
        btns = new Vector<>(10);
        createBottomBar(btmBarHeightAsPercent);
        createTopBar(topBarHeightAsPercent);

        gameWindowRect = new FloatRect(0.0f, topBarRect.height, Window.getInstance().getScreenWidth(), bottomBarRect.top - topBarRect.height);

        createGrid();

        prevTime = 0;
        currentTime = 0;
        localElapsedTime = 0;
        delay = 2500;
        change = false;
        selectedTurret = -1;
        followMouse = false;
        currentMousePos = new Vector2f(0,0);
        dijkstra=new Dijkstra(this);
    }

    private void createBottomBar(float btmBarHeightAsPercent){
        float screenH = Window.getInstance().getScreenHeight();
        float screenW = Window.getInstance().getScreenWidth();

        bottomBarRect = new FloatRect(0.0f, screenH - (btmBarHeightAsPercent * screenH), Window.getInstance().getScreenWidth(),btmBarHeightAsPercent * screenH);

        float buttonWidth = screenW / MAX_BUTTON_COUNT;

        Vector<ArtAsset> artAssets = AssetManager.getInstance().getArtAssetByAssetType("TURRET");

        for(int i = 0; i < MAX_BUTTON_COUNT; i++){
            if (i == 0) {
                btns.addElement(new Button("<", new FloatRect(i * buttonWidth, bottomBarRect.top, buttonWidth, bottomBarRect.height), 20, "LEFT",true));
                continue;
            } else if (i == (MAX_BUTTON_COUNT - 1)) {
                btns.addElement(new Button(">", new FloatRect(i * buttonWidth, bottomBarRect.top, buttonWidth, bottomBarRect.height), 20, "RIGHT",true));
                continue;
            }

            btns.addElement(new Button("src/assets/black.jpg",
                    new FloatRect(i * buttonWidth, bottomBarRect.top, buttonWidth, bottomBarRect.height),"B_BUTTON"+i, true));

        }

        turrets = new Vector<>(5);

        for(int i = 0; i < (artAssets.size()/2); i++){
            //if true, no more turrets need to be actively displayed,
            //and can instead later be cycled
            if(i == MAX_BUTTON_COUNT){
                break;
            }

            turrets.addElement(new Turret(artAssets.elementAt(2*i).getAssetPath(),
                    artAssets.elementAt((2*i)+1).getAssetPath(),true,100,0.0f,1.0f,110,
                    btns.elementAt(i+1).getDimensions(),15,0,0,0,false,0,"TURRET"+i,"src/assets/explosions/explosiontilesheet.png",141,128,10,11,50));
            turrets.elementAt(i).setActive(false);
        }
    }

    private void createTopBar(float topBarHeightAsPercent){
        float screenH = Window.getInstance().getScreenHeight();
        float screenW = Window.getInstance().getScreenWidth();

        topBarRect = new FloatRect(0.0f, 0.0f, screenW, topBarHeightAsPercent * screenH);

        research = new Button("Research", new FloatRect(screenW - 150.0f, 0.0f, 150.0f, (topBarRect.top + topBarRect.height) / 2), 20, "Research",true);
        pause = new Button("Pause", new FloatRect(screenW - 150.0f, (topBarRect.top + topBarRect.height) / 2, 150.0f, (topBarRect.top + topBarRect.height) / 2), 20, "PAUSE",true);

        btns.addElement(research);
        btns.addElement(pause);

        FloatRect wavesRect = new FloatRect(0.0f, 0.0f, (screenW - 150.0f) / 3, topBarRect.top + topBarRect.height);
        waves = new Message("XP: " + NPCHandle.getInstance().getPlayer().getXP(), Text.BOLD, wavesRect, Color.WHITE, 20);

        FloatRect XPRect = new FloatRect(wavesRect.left + wavesRect.width, 0.0f, (screenW - 150.0f) / 3, topBarRect.top + topBarRect.height);
        XP = new Message("Score: "+NPCHandle.getInstance().getPlayer().getScore(), Text.BOLD, XPRect, Color.WHITE, 20);

        FloatRect scoreRect = new FloatRect(XPRect.left + XPRect.width, 0.0f, (screenW - 150.0f) / 3, topBarRect.top + topBarRect.height);
        score = new Message("Score: "+game.getScore(), Text.BOLD, scoreRect, Color.WHITE, 20);
    }

    private void createGrid(){
        grid = new Button[HUD_GRID_ROW_COUNT][HUD_GRID_COL_COUNT];
        inGridTurrets = new Vector<>(5);

        for(int i = 0; i < HUD_GRID_ROW_COUNT; i++){
            for(int j = 0; j < HUD_GRID_COL_COUNT; j++){
                float x = j * (gameWindowRect.width/HUD_GRID_COL_COUNT);
                float y = gameWindowRect.top + i * ((gameWindowRect.height ) / HUD_GRID_ROW_COUNT);
                grid[i][j] = new Button("src/assets/black.jpg",
                        new FloatRect(x,y,gameWindowRect.width/HUD_GRID_COL_COUNT,gameWindowRect.height /HUD_GRID_ROW_COUNT),"("+i+","+j+")",false);

                if(i == (HUD_GRID_ROW_COUNT-1)){
                    mouseBox = new FloatRect(x,y,gameWindowRect.width / HUD_GRID_COL_COUNT,gameWindowRect.height / HUD_GRID_ROW_COUNT);
                }
            }
        }
    }

    private void renderBottomBar(){
        Line.drawLine(new Vector2f(bottomBarRect.left, bottomBarRect.top), new Vector2f(bottomBarRect.left+bottomBarRect.width, bottomBarRect.top));

        for(int i = 0; i < turrets.size(); i++){
            turrets.elementAt(i).render();
        }
    }

    private void renderTopBar(){
        Line.drawLine(new Vector2f(topBarRect.left, topBarRect.top + topBarRect.height), new Vector2f(topBarRect.left+topBarRect.width, topBarRect.top+ topBarRect.height));
        waves.renderText();
        XP.renderText();
        score.renderText();
    }

    private void updateText(){
        if(localElapsedTime >= delay) {
            localElapsedTime = 0;
            change = !change;
        }

        waves.setText("XP: " + NPCHandle.getInstance().getPlayer().getXP());

        XP.setText("Scrap: " + NPCHandle.getInstance().getPlayer().getPlayerScrap());

        score.setText("Score: "+NPCHandle.getInstance().getPlayer().getScore());
    }

    public void mousePress(Vector2i mousePos){
        Vector2f hold=Window.getInstance().getGameWindow().mapPixelToCoords(mousePos);
        mousePos=new Vector2i((int)hold.x,(int)hold.y);//TODO convert the Button.isWithinREct to take Vector2f too

        if(research.isWithinRect(mousePos)) {
            NPCHandle.getInstance().getPlayer().performResearch();
        }
        else if(pause.isWithinRect(mousePos)){
            StateMachine.getInstance().setState("PAUSE");
            return;
        }
        NPCHandle.getInstance().scrapClick(mousePos);

        for(int i = 0; i < btns.size(); i++){
            if(btns.elementAt(i).isWithinRect(mousePos) && (i <= turrets.size())){
                if(i != 0 && i != (MAX_BUTTON_COUNT-1) && !followMouse && NPCHandle.getInstance().getPlayer().purchaseTurret(turrets.elementAt(i-1).getScrapCost())){
                    followMouse = true;
                    selectedTurret = i-1;
                    inGridTurrets.addElement(new Turret(turrets.elementAt(selectedTurret)));
                    inGridTurrets.elementAt(inGridTurrets.size()-1).setActive(false);
                }
            }
        }

        for(int i = 0; i < HUD_GRID_ROW_COUNT; i++) {
            for (int j = 0; j < HUD_GRID_COL_COUNT; j++) {
                if(grid[i][j].isWithinRect(mousePos))
                {
                    if(followMouse) {
                        followMouse = false;
                        //turrets.elementAt(selectedTurret).setDimensions(grid[i][j].getDimensions());
                        //turrets.elementAt(selectedTurret).setActive(true);
                        inGridTurrets.elementAt(inGridTurrets.size()-1).setDimensions(grid[i][j].getDimensions());
                        inGridTurrets.elementAt(inGridTurrets.size()-1).setActive(true);
                        NPCHandle.getInstance().addTurret(inGridTurrets.elementAt(inGridTurrets.size()-1));

                        selectedTurret = -1;
                    }
                }
            }
        }

    }

    public void mouseMove(Vector2i mousePos){
        currentMousePos=Window.getInstance().getGameWindow().mapPixelToCoords(mousePos);

        for(int i = 0; i < HUD_GRID_ROW_COUNT; i++) {
            for (int j = 0; j < HUD_GRID_COL_COUNT; j++) {
                if(grid[i][j].isWithinRect(new Vector2i((int)currentMousePos.x,(int)currentMousePos.y)))
                {
                    grid[i][j].setBorderActiver(true);
                }
                else{
                    grid[i][j].setBorderActiver(false);
                }
            }
        }
    }

    public FloatRect getGameWindowRect(){
        return gameWindowRect;
    }

    @Override
    public void update() {
        prevTime = currentTime;
        currentTime = Window.getInstance().getElapsedTime();

        localElapsedTime += currentTime - prevTime;

        updateText();

        if(followMouse && selectedTurret != -1){
            float w=(gameWindowRect.width/ Pathfind.GRID_WIDTH);
            float h=(gameWindowRect.height/Pathfind.GRID_HEIGHT);
            float x = (currentMousePos.x - (w / 2));
            float y = (currentMousePos.y - (h / 2));
            mouseBox = new FloatRect(x,y,w,h);
            //turrets.elementAt(selectedTurret).setDimensions(mouseBox);
            inGridTurrets.elementAt(inGridTurrets.size()-1).setDimensions(mouseBox);
        }

        /*for(int i = 0; i < turrets.size(); i++){
            turrets.elementAt(i).update();
        }*/

        dijkstra.update();

        for(int i = 0; i < inGridTurrets.size(); i++){
            inGridTurrets.elementAt(i).update();
        }
    }

    @Override
    public void render() {
        if(followMouse) Line.drawRect(mouseBox);
        for(int i = 0; i < btns.size(); i++){ btns.elementAt(i).render(); }

        for(int i = 0; i < HUD_GRID_ROW_COUNT; i++) {
            for (int j = 0; j < HUD_GRID_COL_COUNT; j++) {
                grid[i][j].render();
            }
        }
        dijkstra.render();
        renderBottomBar();
        renderTopBar();

        if(followMouse) Line.drawRect(mouseBox);

        for(int i = 0; i < inGridTurrets.size(); i++){
            inGridTurrets.elementAt(i).render();
        }
    }
}