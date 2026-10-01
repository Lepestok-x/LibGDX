package io.github.some_example_name.entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import io.github.some_example_name.entity.interfacePacket.Collision;
import io.github.some_example_name.entity.interfacePacket.Gun;
import io.github.some_example_name.entity.interfacePacket.Keyboard;
public class BallOrdinary extends BallParent implements Keyboard, Collision, Gun {
    private final Weapon weapon;
    public BallOrdinary(float x, float y, float radius, Weapon weapon) {
        super(x, y, radius, Color.GREEN);
        this.weapon = weapon;
    }
    @Override
    public void update(ShapeRenderer renderer) {
       renderer.setColor(getColor());
       float targetX = getX() + getRadius() / 2;
       float targetY = getY() + getRadius() / 2;
       renderer.circle(targetX, targetY, getRadius());
       weapon.moveXnY(targetX, targetY);
    }

    @Override
    public void window(int width, int height) {
        setX(MathUtils.clamp(getX(), 0, width - getRadius()));
        setY(MathUtils.clamp(getY(), 0, height - getRadius()));
    }
    @Override
    public void moveX(float x) {
        setX(getX() + x);
    }

    @Override
    public void moveY(float y) {
        setY(getY() + y);
    }

    @Override
    public boolean collision(Ball ball) {
        float moveX = ball.getX() - getX();
        float moveY = ball.getY() - getY();
        float radius = (moveX * moveX) + (moveY * moveY);
        float radiusE = getRadius() + ball.getRadius();
        float radiusEnd = (radiusE * radiusE);
        return (radius <= radiusEnd);
    }

    @Override
    public void fire() {

    }
}
