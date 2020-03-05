package com.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappybird.FlappyBird;

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    private Texture ground;
    private Texture birds;
    private Music musicM;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGTH / 2);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
        ground = new Texture("ground.png");
        birds = new Texture("bird.png");
        musicM = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        musicM.setLooping(true);
        musicM.setVolume(0.1f);
        musicM.play();
    }

    @Override
    public void handleInput() {
    if(Gdx.input.justTouched()){
        musicM.stop();
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
        sb.draw(playBtn, 65,200, 100, 45);
        sb.draw(ground, 0,0, (FlappyBird.WIDTH/2), 50 );
        sb.draw(birds, 50, 70);
        sb.end();
    }



    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}