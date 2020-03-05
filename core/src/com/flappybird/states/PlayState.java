package com.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flappybird.FlappyBird;
import com.flappybird.sprites.Bird;
import com.flappybird.sprites.Tube;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -30;

    private Bird bird;
    private Texture bg1;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Music musicP;
    private int cont = 0;
    private boolean marcouPonto;
    private BitmapFont points;



    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
//        where the bird begin
        bird = new Bird(35, 300);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGTH / 2);
        cam2.setToOrtho(false, 500 , 700);
        bg1 = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        musicP = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        musicP.setLooping(true);
        musicP.setVolume(0.1f);
        musicP.play();
        tubes = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

        points = new BitmapFont();
        points.setColor(Color.WHITE);
        points.getData().setScale(4);
        marcouPonto =false;



    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;


        for (Tube tube : tubes) {
            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                marcouPonto = false;
            }


            if (tube.collides(bird.getBounds())) {
                musicP.stop();
                gsm.set(new GameOver(gsm));
            }
        }
        if (bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
            musicP.stop();
            gsm.set(new GameOver(gsm));
        }

        cam.update();

        if(!marcouPonto) {
            cont++;
            marcouPonto=true;

            if(cont>10) {
                bird.setMovement(200);
            }else {
                if (cont > 20) {
                    bird.setGravity(-20);
                    bird.setMovement(300);
                } else {
                    if (cont > 30) {
                        bird.setGravity(-25);
                        bird.setMovement(500);
                    }
                }
            }

        }
    }

    @Override
    public void render(SpriteBatch sb, SpriteBatch p) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg1, cam.position.x - (cam.viewportWidth / 2), 0, FlappyBird.WIDTH /2, 660);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();

        p.setProjectionMatrix(cam2.combined);
        p.begin();
        points.draw(p, String.valueOf(cont-1), 200, 600);
        p.end();
    }


    @Override
    public void dispose() {
        bg1.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube tube : tubes)
            tube.dispose();
        System.out.println("Play State Disposed");

    }

    private void updateGround() {
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);

        if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }

}
