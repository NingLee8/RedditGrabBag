package reddit_grab_bag.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import reddit_grab_bag.relics.WeightedGlove;

public class WeightedGloveAction extends AbstractGameAction {

    private final WeightedGlove r;

    public WeightedGloveAction(WeightedGlove r) {
        this.r = r;
    }

    public void update() {
        r.canDraw = false;
        tickDuration();
    }
}
