package io.github.some_example_name.entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import io.github.some_example_name.entity.interfacePacket.Collision;
import io.github.some_example_name.entity.interfacePacket.Moved;

public class Ball extends BallParent implements Collision, Moved {
    public Ball(float x, float y, float radius) {
        super(x, y, radius, Color.RED);
    }

    @Override
    public void update(ShapeRenderer renderer) {
        renderer.setColor(getColor());
        renderer.circle(getX(), getY(), getRadius());
    }

    @Override
    public void window(int width, int height) {
      setX(MathUtils.clamp(getX(), 0, width - getRadius()));
      setX(MathUtils.clamp(getY(), 0, height - getRadius()));
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
    public void moved(float x, float y, float speed) {
      setX(getX() + x * speed);
      setY(getY() + y * speed);
    }
}
