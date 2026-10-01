package io.github.some_example_name.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class KeyboardMoved {
    private static final float down = -300;
    private static final float up = 300;
    private final BallOrdinary ball;
    public KeyboardMoved(BallOrdinary ball) {
        this.ball = ball;
    }
    public void keyboard() {
        float time = Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.A)) ball.moveX(down * time);
        if(Gdx.input.isKeyPressed(Input.Keys.D)) ball.moveX(up * time);
        if(Gdx.input.isKeyPressed(Input.Keys.W)) ball.moveY(down * time);
        if(Gdx.input.isKeyPressed(Input.Keys.S)) ball.moveY(up * time);
    }
}
