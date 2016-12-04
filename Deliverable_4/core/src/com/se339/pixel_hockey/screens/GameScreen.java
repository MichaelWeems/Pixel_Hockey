package com.se339.pixel_hockey.screens;

/**
 * Created by Zach on 11/30/2016.
 */

import com.badlogic.gdx.graphics.Texture;
import com.se339.fileUtilities.Directory;
import com.se339.fileUtilities.DirectoryList;
import com.se339.fileUtilities.FileList;
import com.se339.log.*;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.physics.GameWorld;
import com.se339.pixel_hockey.sounds.SoundHandler;

import java.io.File;
import java.util.ArrayList;

public class GameScreen extends Screens {

    //private final ArrayList<String> image_pucks;

    private final ArrayList<String> musiclist;
    private int music_index = 0;

    private int puckSize = 256;

    //Texture dropImage;
    //Texture puckImage;

    Sound hitSound;
    Music gameMusic;

    GameWorld world;

    //Rectangle puck;
    //Array<Rectangle> raindrops;
    long lastDropTime;
    int dropsGathered;


    public GameScreen(final PixelHockeyGame game) {
        super(game);
        log = new Log("GameScreen");

        world = new GameWorld();

        //image_pucks = Directory.getFileNamesFullPath(DirectoryList.dImages_Puck);
        musiclist = Directory.getFileNamesFullPath(DirectoryList.dMusic);

        //log.a("Opening image: " + image_pucks.get(0));
        //puckImage = new Texture(Gdx.files.internal(image_pucks.get(0)));
        //log.l("\t\timage opened");

        // load the drop sound effect and the rain background "music"
        hitSound = Gdx.audio.newSound(Gdx.files.internal(FileList.sound_drop));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal(musiclist.get(0)));
        gameMusic.setLooping(true);

        // create a Rectangle to logically represent the puck
        //puck = new Rectangle();
        //puck.x = sWidth / 2 - puckSize / 2; // center the puck horizontally
        //puck.y = sHeight / 2 - puckSize / 2; // center the puck vertically

        //puck.width = puckSize;
        //puck.height = puckSize;

        // create the raindrops array and spawn the first raindrop
        //raindrops = new Array<Rectangle>();
        //spawnRaindrop();

    }

    /*
    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        //raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }
*/

    @Override
    public void render(float delta) {

        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();
        world.render(camera, game.batch);

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        //game.batch.setProjectionMatrix(camera.combined);

        // begin a new  batch and draw the puck and
        // all drops
        //game.batch.begin();
        //game.font.draw(game.batch, "Drops Collected: " + dropsGathered, 0, 480);
        //game.batch.draw(puckImage, puck.x, puck.y);
        /*
        for (Rectangle raindrop : raindrops) {
            game.batch.draw(dropImage, raindrop.x, raindrop.y);
        }
        */
        //game.batch.end();

        /*
        // process user input
        if (Gdx.input.isTouched()) {
            log.l("user input == touch");
            changeMusic();

            log.a("moving puck");
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            puck.x = touchPos.x - 64 / 2;
        }

        //log.l("checking user input == keypress");
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            puck.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            puck.x += 200 * Gdx.graphics.getDeltaTime();

        // make sure the puck stays within the screen bounds
        if (puck.x < 0)
            puck.x = 0;
        if (puck.x > sWidth - 64)
            puck.x = sWidth - 64;

        /*
        // check if we need to create a new raindrop
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
            spawnRaindrop();


        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the puck. In the later case we play back
        // a sound effect as well.
        Iterator<Rectangle> iter = raindrops.iterator();
        while (iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (raindrop.y + 64 < 0)
                iter.remove();
            if (raindrop.overlaps(puck)) {
                dropsGathered++;
                hitSound.play();
                iter.remove();
            }
        }
        */
    }

    private void movePuck(){
        //puck.y -= 200 * Gdx.graphics.getDeltaTime();
        //if (puck.y + puckSize < 0)

            /*
        if (raindrop.overlaps(puck)) {
            dropsGathered++;
            hitSound.play();
            iter.remove();
        }
        */
    }

    /*
     * Changes the music to the next song in the playlist
     */
    private void changeMusic(){
        log.a("changing music");

        // make sure music object isn't null
        if (gameMusic == null) return;
        if (gameMusic.isPlaying()) {
            gameMusic.stop();
        }

        // play first song if at end of playlist
        if (music_index == musiclist.size() - 1) music_index = 0;
        else music_index++;

        // get the next song index from musiclist
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal(musiclist.get(music_index)));
        gameMusic.setLooping(true);
        gameMusic.play();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        gameMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        //dropImage.dispose();
        //puckImage.dispose();
        hitSound.dispose();
        gameMusic.dispose();
    }

}