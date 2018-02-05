package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.TexturedModel;
import org.joml.Vector3f;
import renderEngine.*;
import models.RawModel;
import textures.ModelTexture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainGameLoop {
    public static void main(String[] args) {
        DisplayManager.createDisplay();

        Loader loader = new Loader();

        // OpenGL expects vertices to be defined counter clockwise by default
        RawModel model = OBJLoader.loadObjModel("DragonBlender", loader);

        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("DragonVioletTexture")));
        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(10);
        texture.setReflectivity(1);

        Light light = new Light(new Vector3f(3000, 2000, 3000), new Vector3f(1, 1, 1));

        Camera camera = new Camera();

        List<Entity> allDragons = new ArrayList<Entity>();
        Random random = new Random();

        for(int i = 0; i < 200; ++i) {
            float x = random.nextFloat() * 100 - 50;
            float y = random.nextFloat() * 100 - 50;
            float z = random.nextFloat() * (-300);
            allDragons.add(new Entity(staticModel, new Vector3f(x, y, z),
                    random.nextFloat() * 180f, random.nextFloat()*180f, 0f, 1f));
        }

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.

        MasterRenderer renderer = new MasterRenderer();
        while(!DisplayManager.isCloseRequested()) {
            camera.move();

            for(Entity dragon : allDragons) {
                dragon.increaseRotation(1, 1, 1);
                renderer.processEntity(dragon);
            }

            renderer.render(light, camera);
            DisplayManager.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
