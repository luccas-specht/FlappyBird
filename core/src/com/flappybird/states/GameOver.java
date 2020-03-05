package com.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappybird.FlappyBird;

public class GameOver extends State {

    private Texture background;
    private Texture finished;
    private Texture ground;
    private Texture t1;
    private Music musicG;
    private BitmapFont fim;


    public GameOver(GameStateManager gsm) {
        super(gsm);

        cam1.setToOrtho(false, 500 , 700);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGTH / 2);
        background = new Texture("bg.png");
        finished = new Texture("gameover.png");
        ground = new Texture("ground.png");
        t1 = new Texture("merlin.png");

        fim = new BitmapFont();
        fim.setColor(Color.WHITE);
        fim.getData().setScale(3);

        musicG = Gdx.audio.newMusic(Gdx.files.internal("soundgame.m4a"));
        musicG.setLooping(true);
        musicG.setVolume(0.1f);
        musicG.play();


    }




    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            musicG.stop();
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb, SpriteBatch p) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0, (FlappyBird.WIDTH /2), (FlappyBird.HEIGTH / 2) + 100);
        sb.draw(finished, 43,200, 150, 55);
        sb.draw(ground, 0,0, (FlappyBird.WIDTH/2), 50 );
        sb.end();
        p.setProjectionMatrix(cam1.combined);
        p.begin();
        fim.draw(p, "Tap to play again", 80, 200);
        p.end();

    }


    @Override
    public void dispose() {
    }





}
