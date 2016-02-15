package graphics;

import assets.ArtAsset;
import assets.AssetManager;
import fsm.StateMachine;
import main.*;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import player.Player;
import states.Game;

import java.util.Vector;

/**
 *
 */
public class HUD implements Render {
    private FloatRect bottomBarRect;
    private FloatRect topBarRect;
    private FloatRect gameWindowRect;
    private Button menu;
    private Button pause;
    private Game game;
    private Vector<Button> btns;

    private Message waves;
    private Message XP;
    private Message score;

    private long prevTime;
    private long currentTime;
    private long localElapsedTime;
    private long delay;
    private boolean change;

    public static int MAX_BUTTON_COUNT = 12;

    private Player player;

    public HUD(float btmBarHeightAsPercent, float topBarHeightAsPercent, Game game, Player player){
        this.game = game;
        this.player = player;
        btns = new Vector<>(5);
        createBottomBar(btmBarHeightAsPercent);
        createTopBar(topBarHeightAsPercent);

        gameWindowRect = new FloatRect(0.0f, topBarRect.top + topBarRect.height, Window.getInstance().getScreenWidth(), bottomBarRect.top);

        prevTime = 0;
        currentTime = 0;
        localElapsedTime = 0;
        delay = 2500;
        change = false;
    }

    private void createBottomBar(float btmBarHeightAsPercent){
        float screenH = Window.getInstance().getScreenHeight();
        float screenW = Window.getInstance().getScreenWidth();

        bottomBarRect = new FloatRect(0.0f, screenH - (btmBarHeightAsPercent * screenH), Window.getInstance().getScreenWidth(),btmBarHeightAsPercent * screenH);

        float buttonWidth = screenW / MAX_BUTTON_COUNT;

        Vector<ArtAsset> artAssets = AssetManager.getInstance().getArtAssetByAssetType("TURRET");

        for(int i = 0; i < MAX_BUTTON_COUNT; i++){
            if( artAssets.size() == (MAX_BUTTON_COUNT-2) ) {
                if (i == 0) {
                    btns.addElement(new Button("<", new FloatRect(i * buttonWidth, bottomBarRect.top, buttonWidth, bottomBarRect.height), 20, "LEFT",true));
                    continue;
                } else if (i == (MAX_BUTTON_COUNT - 1)) {
                    btns.addElement(new Button(">", new FloatRect(i * buttonWidth, bottomBarRect.top, buttonWidth, bottomBarRect.height), 20, "RIGHT",true));
                    continue;
                }

                btns.addElement(new Button(artAssets.elementAt(i-1),
                        new FloatRect(i * buttonWidth, bottomBarRect.top, buttonWidth, bottomBarRect.height), true));
            }
        }
    }

    private void createTopBar(float topBarHeightAsPercent){
        float screenH = Window.getInstance().getScreenHeight();
        float screenW = Window.getInstance().getScreenWidth();

        topBarRect = new FloatRect(0.0f, 0.0f, screenW, topBarHeightAsPercent * screenH);

        menu = new Button("Menu", new FloatRect(screenW - 150.0f, 0.0f, 150.0f, (topBarRect.top + topBarRect.height) / 2), 20, "MAIN_MENU",true);
        pause = new Button("Pause", new FloatRect(screenW - 150.0f, (topBarRect.top + topBarRect.height) / 2, 150.0f, (topBarRect.top + topBarRect.height) / 2), 20, "PAUSE",true);

        btns.addElement(menu);
        btns.addElement(pause);

        FloatRect wavesRect = new FloatRect(0.0f, 0.0f, (screenW - 150.0f) / 3, topBarRect.top + topBarRect.height);
        waves = new Message("Remaining waves: "+game.getNumOfRemainingWaves(), Text.BOLD, wavesRect, Color.WHITE, 20);

        FloatRect XPRect = new FloatRect(wavesRect.left + wavesRect.width, 0.0f, (screenW - 150.0f) / 3, topBarRect.top + topBarRect.height);
        XP = new Message("XP: "+player.getXP(), Text.BOLD, XPRect, Color.WHITE, 20);

        FloatRect scoreRect = new FloatRect(XPRect.left + XPRect.width, 0.0f, (screenW - 150.0f) / 3, topBarRect.top + topBarRect.height);
        score = new Message("Score: "+game.getScore(), Text.BOLD, scoreRect, Color.WHITE, 20);
    }

    private void renderBottomBar(){
        Line.drawLine(new Vector2f(bottomBarRect.left, bottomBarRect.top), new Vector2f(bottomBarRect.left+bottomBarRect.width, bottomBarRect.top));
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

        if(change){
            waves.setText("Remaining waves: " + game.getNumOfRemainingWaves());
        }else{
            waves.setText("Current wave: "+game.getCurrentWave());
        }

        XP.setText("XP: "+player.getXP());

        score.setText("Score: "+game.getScore());
    }

    public void mousePress(Vector2i mousePos){
        if(menu.isWithinRect(mousePos)) {
            StateMachine.getInstance().setState("MAIN_MENU");
        }
        else if(pause.isWithinRect(mousePos)){
            StateMachine.getInstance().setState("PAUSE");
        }

        for(int i = 0; i < btns.size(); i++){
            if(btns.elementAt(i).isWithinRect(mousePos)){

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
    }

    @Override
    public void render() {
        renderBottomBar();
        renderTopBar();

        for(int i = 0; i < btns.size(); i++){ btns.elementAt(i).render(); }
    }
}
