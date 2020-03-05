package com.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappybird.states.GameStateManager;
import com.flappybird.states.MenuState;
import com.flappybird.states.State;

public class FlappyBird extends ApplicationAdapter {

    //LARGURA
    public static final int WIDTH = 480;
    //ALTURA
    public static final int HEIGTH = 800;
    public static final String TITLE = "Flappy Bird";
    private GameStateManager gsm;
    private SpriteBatch batch, b;

    private Music music;


    @Override
    public void create() {
        batch = new SpriteBatch();
        b = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        gsm.push(new MenuState(gsm));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch, b);
    }


    @Override
    public void dispose() {
        super.dispose();
        music.dispose();
    }
}
