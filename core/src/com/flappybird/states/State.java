package com.flappybird.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public abstract class State {
    protected OrthographicCamera cam, cam1,cam2;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm){
        this.gsm =gsm;
        cam = new OrthographicCamera();
        cam1 = new OrthographicCamera();
        cam2 = new OrthographicCamera();
        mouse = new Vector3();

    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb, SpriteBatch p);
    public abstract void dispose();
}
