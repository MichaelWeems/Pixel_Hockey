package com.se339.pixel_hockey.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.se339.log.Log;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.fileUtilities.CSVReader;
import com.se339.ui_elements.Hud;

import static com.se339.fileUtilities.FileList.default_skin;
import static com.se339.fileUtilities.FileList.test_csv;

/**
 * Created by Zach on 12/3/2016.
 */

public class FriendScreen extends Screens {
    Stage stage;
    Skin skin;
    Table container;

    public FriendScreen(PixelHockeyGame game) {

        super(game);
        Log log = new Log("Friend Screen");
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));


        container = new Table();
        Label title = new Label("Friends List", skin);
        title.setFontScale(6,6);
        container.add(title);
        stage.addActor(container);
        container.setSize(stage.getWidth(), 2*stage.getHeight()/3);

        Table table = new Table();
        // table.debug();

        final ScrollPane scroll = new ScrollPane(table, skin);

        InputListener stopTouchDown = new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                event.stop();
                return false;
            }
        };

        table.pad(10).defaults().expandX().space(4);
        for (int i = 0; i < 100; i++) {
            table.row();
            table.add(new Label(i + "uno", skin)).expandX().fillX();

            TextButton button = new TextButton(i + "dos", skin);
            table.add(button);
            button.addListener(new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    System.out.println("click " + x + ", " + y);
                }
            });

            Slider slider = new Slider(0, 100, 1, false, skin);
            slider.addListener(stopTouchDown); // Stops touchDown events from propagating to the FlickScrollPane.
            table.add(slider);

            table.add(new Label(i + "tres long0 long1 long2 long3 long4 long5 long6 long7 long8 long9 long10 long11 long12", skin));
        }

        final TextButton flickButton = new TextButton("Flick Scroll", skin.get("toggle", TextButton.TextButtonStyle.class));
        flickButton.setChecked(true);
        flickButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                scroll.setFlickScroll(flickButton.isChecked());
            }
        });

        final TextButton fadeButton = new TextButton("Fade Scrollbars", skin.get("toggle", TextButton.TextButtonStyle.class));
        fadeButton.setChecked(true);
        fadeButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                scroll.setFadeScrollBars(fadeButton.isChecked());
            }
        });

        final TextButton smoothButton = new TextButton("Smooth Scrolling", skin.get("toggle", TextButton.TextButtonStyle.class));
        smoothButton.setChecked(true);
        smoothButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                scroll.setSmoothScrolling(smoothButton.isChecked());
            }
        });

        final TextButton onTopButton = new TextButton("Scrollbars On Top", skin.get("toggle", TextButton.TextButtonStyle.class));
        onTopButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                scroll.setScrollbarsOnTop(onTopButton.isChecked());
            }
        });

        container.add(scroll).expand().fill().colspan(4);
        container.row().space(10).padBottom(10);
        container.add(flickButton).right().expandX();
        container.add(onTopButton);
        container.add(smoothButton);
        container.add(fadeButton).left().expandX();


        log.l("adding to Tile");

        Table title = new Table();
        title.add(new Label("Friends", skin));
//        title.row();
//        title.add(new Label("List", skin));
        title.setSize(stage.getWidth(), 120);
        stage.addActor(title);
        title.setSize(stage.getWidth(), 120);
        title.setPosition(0.5f, 0.5f);
//        title.debug();

        log.l("Creating hud");
        Hud hud = new Hud(stage.getWidth(), game);
        stage.addActor(hud);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.3f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
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
        stage.dispose();
//        skin.dispose();
    }


}
