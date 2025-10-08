package reddit_grab_bag;

import basemod.ModLabel;
import basemod.ModPanel;
import java.util.function.Consumer;

public class ScrollableModLabel extends ModLabel {

    private float originalY;

    public ScrollableModLabel(String labelText, float xPos, float yPos, ModPanel p, Consumer<ModLabel> updateFunc) {
        super(labelText, xPos, yPos, p, updateFunc);
        this.originalY = yPos;
    }

    public void update(float scrollOffset) {
        super.update();
        this.setY(this.originalY + scrollOffset);
    }
}
