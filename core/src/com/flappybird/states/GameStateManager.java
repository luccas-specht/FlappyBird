package com.flappybird.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();

    }
    public void push(State state){
        states.push(state);

    }
    public void pop() {
        states.pop().dispose();

    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
    states.peek().update(dt);

    }

    public void render(SpriteBatch sb, SpriteBatch p){
    states.peek().render(sb, p);
    }










}

