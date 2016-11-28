package com.se339.pixel_hockey.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.se339.pixel_hockey.PixelHockeyGame;
import com.se339.pixel_hockey.Screens.PlayScreen;
import com.se339.pixel_hockey.Elements.Powerups.Powerup;
import com.se339.pixel_hockey.Elements.Powerups.Speedboost;
import com.se339.pixel_hockey.Elements.Powerups.Speeddown;
import com.se339.pixel_hockey.Elements.TileObjects.Brick;
import com.se339.pixel_hockey.Elements.TileObjects.Coin;

/**
 * Created by se339.pixel_hockey on 8/28/15.
 */
public class B2WorldCreator {
    private Array<Speedboost> goombas;
    private Array<Speeddown> turtles;

    public B2WorldCreator(PlayScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        //create body and fixture variables
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //create ground bodies/fixtures
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / PixelHockeyGame.PPM, (rect.getY() + rect.getHeight() / 2) / PixelHockeyGame.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / PixelHockeyGame.PPM, rect.getHeight() / 2 / PixelHockeyGame.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //create pipe bodies/fixtures
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / PixelHockeyGame.PPM, (rect.getY() + rect.getHeight() / 2) / PixelHockeyGame.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / PixelHockeyGame.PPM, rect.getHeight() / 2 / PixelHockeyGame.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = PixelHockeyGame.OBJECT_BIT;
            body.createFixture(fdef);
        }

        //create brick bodies/fixtures
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            new Brick(screen, object);
        }

        //create coin bodies/fixtures
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){

            new Coin(screen, object);
        }

        //create all goombas
        goombas = new Array<Speedboost>();
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            goombas.add(new Speedboost(screen, rect.getX() / PixelHockeyGame.PPM, rect.getY() / PixelHockeyGame.PPM));
        }
        turtles = new Array<Speeddown>();
        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            turtles.add(new Speeddown(screen, rect.getX() / PixelHockeyGame.PPM, rect.getY() / PixelHockeyGame.PPM));
        }
    }

    public Array<Speedboost> getGoombas() {
        return goombas;
    }
    public Array<Powerup> getEnemies(){
        Array<Powerup> enemies = new Array<Powerup>();
        enemies.addAll(goombas);
        enemies.addAll(turtles);
        return enemies;
    }
}
