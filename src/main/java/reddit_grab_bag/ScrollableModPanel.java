package reddit_grab_bag;

import basemod.IUIElement;
import basemod.ModPanel;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;

public class ScrollableModPanel extends ModPanel implements ScrollBarListener {

    private static final float START_Y = Settings.HEIGHT - 300.0F * Settings.scale;

    private float scrollY;

    private float targetY = this.scrollY = START_Y;

    // Top
    private final float scrollLowerBound = Settings.HEIGHT - 300.0F * Settings.scale;

    // Bottom
    private final float scrollUpperBound = 1700.0F * Math.max(Settings.scale, 1);

    private boolean grabbedScreen = false;

    private float grabStartY = 0.0F;

    private final ScrollBar scrollBar;

    public ScrollableModPanel() {
        super();
        this.scrollBar = new ScrollBar(this);
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        this.scrollBar.render(sb);
    }

    @Override
    public void renderBg(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        sb.draw(com.megacrit.cardcrawl.helpers.ImageMaster.loadImage("img/ModPanelBg.png"),
                278.0F * Settings.scale,
                -200.0F,
                0.0F,
                0,
                1364.0F,
                Settings.HEIGHT * 1.5F,
                Settings.scale,
                1.0F,
                0.0F,
                0,
                0,
                1364,
                752,
                false,
                false);
    }

    @Override
    public void update() {
        super.update();
        boolean isScrollingScrollBar = this.scrollBar.update();
        if (!isScrollingScrollBar)
            updateScrolling();
    }


    private void updateScrolling() {
            int y = InputHelper.mY;
            if (!this.grabbedScreen) {
                if (InputHelper.scrolledDown) {
                    this.targetY += Settings.SCROLL_SPEED;
                } else if (InputHelper.scrolledUp) {
                    this.targetY -= Settings.SCROLL_SPEED;
                }
                if (InputHelper.justClickedLeft) {
                    this.grabbedScreen = true;
                    this.grabStartY = y - this.targetY;
                }
            } else if (InputHelper.isMouseDown) {
                this.targetY = y - this.grabStartY;
            } else {
                this.grabbedScreen = false;
            }
            this.scrollY = MathHelper.scrollSnapLerpSpeed(this.scrollY, this.targetY);
            resetScrolling();
            updateBarPosition();

    }

    private void resetScrolling() {
        if (this.targetY < this.scrollLowerBound) {
            this.targetY = MathHelper.scrollSnapLerpSpeed(this.targetY, this.scrollLowerBound);
        } else if (this.targetY > this.scrollUpperBound) {
            this.targetY = MathHelper.scrollSnapLerpSpeed(this.targetY, this.scrollUpperBound);
        }
    }


    public void scrolledUsingBar(float newPercent) {
        float newPosition = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
        this.scrollY = newPosition;
        this.targetY = newPosition;
        updateBarPosition();
    }

    private void updateBarPosition() {
        float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.scrollY);
        this.scrollBar.parentScrolledToPercent(percent);
        for (IUIElement element : this.getUIElements()) {
            if (element instanceof ScrollableToggleButton) {
                ScrollableToggleButton button = (ScrollableToggleButton) element;
                button.update(this.scrollY - 700f * Settings.scale);
            }
            if(element instanceof  ScrollableModLabel) {
                ScrollableModLabel label = (ScrollableModLabel) element;
                label.update(this.scrollY - 700f * Settings.scale);
            }
            if(element instanceof  ScrollableModButton) {
                ScrollableModButton button = (ScrollableModButton) element;
                button.update(this.scrollY - 700f * Settings.scale);
            }
        }
    }
}
