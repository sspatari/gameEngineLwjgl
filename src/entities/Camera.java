package entities;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import renderEngine.KeyboardHandler;

import static org.lwjgl.glfw.GLFW.*;

public class Camera {
    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch; //HEIGHT or LOW
    private float yaw;   //LEFT or RIGHT
    private float roll;  //How much it's tillted

    public Camera() {}

    public void move() {
        if(KeyboardHandler.isKeyDown(GLFW_KEY_W)) {
            position.z -= 2f;
        }
        if(KeyboardHandler.isKeyDown(GLFW_KEY_S)) {
            position.z += 2f;
        }
        if(KeyboardHandler.isKeyDown(GLFW_KEY_D)) {
            position.x += 2f;
        }
        if(KeyboardHandler.isKeyDown(GLFW_KEY_A)) {
            position.x -= 2f;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}