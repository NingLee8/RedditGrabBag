package reddit_grab_bag;

import basemod.ModLabeledButton;
import basemod.ModPanel;

import java.util.function.Consumer;

public class ScrollableModButton extends ModLabeledButton {

    private float originalY;

    public ScrollableModButton(String label, float xPos, float yPos, ModPanel p, Consumer<ModLabeledButton> c) {
        super(label, xPos, yPos, p, c);
        this.originalY = yPos;
    }

    public void update(float scrollOffset) {
        super.update();
        this.setY(this.originalY + scrollOffset);
    }
}
