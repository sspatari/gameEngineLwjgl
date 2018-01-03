package engineTester;

import renderEngine.DisplayManager;

public class MainGameLoop {
    public static void main(String[] args) {
        DisplayManager.createDisplay();

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while(!DisplayManager.isCloseRequested()) {
            //game logic
            //render
            DisplayManager.updateDisplay();
        }

        DisplayManager.closeDisplay();
    }
}
