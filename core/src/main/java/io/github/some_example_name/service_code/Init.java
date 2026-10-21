package io.github.some_example_name.service_code;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class Init {
    private final Player player;
    private final Array<Ground> grounds;
    private final Listener listener;
    private final Camera camera;
    private final BodyCollision bodyCollision;
    public Init(Array<ModelBuilder> builders, Camera camera) {
        this.camera = camera;
        ModelBuilder player_b = builders.get(0);
        player = new Player(new Vector3(.0f, .0f, .0f), new Vector3(2.f, 2.f, 2.f), player_b);
        grounds = new Array<>();
        for(int i = 0; i < 18; i++) {
            ModelBuilder builderGround = builders.get(1);
            float distance = -3.5f;
            float angle = (7 * i);
            float z = (MathUtils.cos(angle) * -45.f + (distance * i));
            float y = (-35 + (-12 * i));
            float x = (MathUtils.sin(angle) * -35.f + (distance * i));
            grounds.add(new Ground(new Vector3(x, y, z), new Vector3(100.f, .5f, 100.f), builderGround));
        }
        listener = new Listener(player);
        bodyCollision = new BodyCollision();

    }
    public void update(ModelBatch batch, float distance) {
        player.drawPlayer(batch);
        for(Ground ground : grounds) {
            bodyCollision.initCenterEntity(player.getSize(), ground.getSize());
            if(bodyCollision.collision(player.matrix4(), ground.matrix4())) {
                listener.rollbackVelocityY();
                break;
            }
        }
        for(Ground ground : grounds) {
            ground.drawGround(batch);
        }
        listener.updateListener(camera, distance);
    }
    public void dispose() {
        player.disposePlayer();
        for(Ground ground : grounds) {
            ground.disposeGround();
        }
    }
}
