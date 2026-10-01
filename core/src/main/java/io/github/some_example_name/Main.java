package io.github.some_example_name;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.some_example_name.entity.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private ShapeRenderer renderer;
    private OrthographicCamera camera;
    private Viewport viewport;
    private final int cameraWidth = 1000, cameraHeight = 650;
    private BallOrdinary ordinary;
    private KeyboardMoved keyboardMoved;
    private Weapon weapon;
    private final Array<Ball> balls = new Array<>();
    private final Array<Bullet> bullets = new Array<>();
    private float timeClick = 0.135f;
    private float tick;
    private float angle;
    @Override
    public void create() {
      weapon = new Weapon(100,100,125,15, Color.RED);
      renderer = new ShapeRenderer();
      camera = new OrthographicCamera(cameraWidth, cameraHeight);
      viewport = new FillViewport(cameraWidth, cameraHeight, camera);
      camera.setToOrtho(true, cameraWidth, cameraHeight);
      ordinary = new BallOrdinary(100,100, 15, weapon);
      keyboardMoved = new KeyboardMoved(ordinary);
      camera.update();
      for(int i = 0; i < 5; i++) {
          balls.add(new Ball(335 + (i * 110), 350, 15));
      }
    }

    @Override
    public void resize(int width, int height) {
       viewport.update(width, height, true);
       ordinary.window(viewport.getScreenWidth(), viewport.getScreenHeight());
    }
    @Override
    public void render() {
      keyboardMoved.keyboard();
      ordinary.window(cameraWidth, cameraHeight);
      Gdx.gl.glClearColor(0.f, 0.f, 0.f, 1.f);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
      renderer.begin(ShapeRenderer.ShapeType.Filled);
      renderer.setProjectionMatrix(camera.combined);
      ordinary.update(renderer);
      if(tick >= 0.0) {
          tick -= Gdx.graphics.getDeltaTime();
      }
      if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && tick <= 0.0) {
          angle = -MathUtils.atan2(Gdx.input.getY(), Gdx.input.getX()) * MathUtils.radiansToDegrees;
          bullets.add(new Bullet(ordinary, angle, 8, Color.GOLD));
          tick = timeClick;
      }
        for (Ball ball : balls) {
            ball.update(renderer);
            if (ordinary.collision(ball)) {
                ball.moved(ball.getX() - ordinary.getX(), ball.getY() - ordinary.getY(), Gdx.graphics.getDeltaTime() * 10);
            }
        }
        for(int i = 0; i < balls.size; i++) {
            for(int j = i + 1; j < balls.size; j++) {
                Ball ballA = balls.get(i);
                Ball ballB = balls.get(j);
                if(ballA.collision(ballB)) {
                  float deltaX = ballA.getX() - ballB.getY();
                  float deltaY = ballA.getY() - ballB.getY();
                  float speed = 1 * Gdx.graphics.getDeltaTime();
                  ballA.moved(deltaX, deltaY, speed);
                  ballB.moved(-deltaX, -deltaY, speed);
                }
            }
        }
      weapon.mouseMoved(Gdx.input.getX(), Gdx.input.getY());
      weapon.update(renderer);
      for(Bullet bullet : bullets) {
          bullet.angleBullet();
          bullet.update(renderer);
      }
      renderer.end();
    }

    @Override
    public void dispose() {
       if(renderer != null) renderer.dispose();
    }
}
