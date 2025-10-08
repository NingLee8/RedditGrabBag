package reddit_grab_bag;

import basemod.ModLabel;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.ModToggleButton;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.function.Consumer;

public class ScrollableToggleButton extends ModLabeledToggleButton {
    private final float originalY;
    public boolean isRelic;

    public ScrollableToggleButton(boolean isRelic, String labelText, float xPos, float yPos, Color color, BitmapFont font, boolean enabled, ModPanel p, Consumer<ModLabel> labelUpdate, Consumer<ModToggleButton> c) {
        super(labelText, null, xPos, yPos, color, font, enabled, p, labelUpdate, c);
        this.isRelic = isRelic;
        this.originalY = yPos;
    }

    public void update(float scrollOffset) {
        super.update();
        this.toggle.setY(this.originalY + scrollOffset);
        this.text.setY(this.originalY + scrollOffset);
    }
}