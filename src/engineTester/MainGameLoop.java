package engineTester;

import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {
    public static void main(String[] args) {
        DisplayManager.createDisplay();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

        // OpenGL expects vertices to be defined counter clockwise by default
        float[] vertices = {
                // Left bottom triangle
                -0.5f, 0.5f, 0f, //v0
                -0.5f, -0.5f, 0f,//v1
                0.5f, -0.5f, 0f, //v2
                0.5f, 0.5f, 0f,  //v3
        };

        int[] indices = {
                0, 1, 3, //Top left triangle (V0, V1, V3)
                3, 1, 2  //Bottom right triangle (V3, V1, V2)
        };

        float[] textureCoords = {
                0,0, //V0
                0,1, //V1
                1,1, //V2
                1,0  //V3
        };


        RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("image1"));
        TexturedModel texturedModel = new TexturedModel(model, texture);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while(!DisplayManager.isCloseRequested()) {
            renderer.prepare();
            //game logic
            shader.start();
            renderer.render(texturedModel);
            shader.stop();

            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
