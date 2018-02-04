package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.TexturedModel;
import org.joml.Vector3f;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {
    public static void main(String[] args) {
        DisplayManager.createDisplay();

        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);

        // OpenGL expects vertices to be defined counter clockwise by default


        RawModel model = OBJLoader.loadObjModel("DragonBlender", loader);

        ModelTexture texture = new ModelTexture(loader.loadTexture("DragonWhiteTexture"));

        TexturedModel staticModel = new TexturedModel(model, texture);

        Entity entity = new Entity(staticModel, new Vector3f(0, 0, -25),
                0, 0, 0, 1);
        Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));

        Camera camera = new Camera();

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while(!DisplayManager.isCloseRequested()) {
            entity.increaseRotation(0, 1, 0);
            camera.move();
            renderer.prepare();
            shader.start();
            shader.loadLight(light);
            shader.loadViewMatrix(camera);
            renderer.render(entity, shader);
            shader.stop();
            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
