package reddit_grab_bag.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.Dark;

public class SingularityAction extends AbstractGameAction {

    private final boolean upgraded;

    public SingularityAction(boolean upgraded) {
        this.upgraded = upgraded;
    }

    public void update() {
        int count = AbstractDungeon.player.hand.size();

        Dark darkOrb = new Dark();
        AbstractDungeon.player.channelOrb(darkOrb);

        int i;
        for(i = 0; i < (this.upgraded ? count + 2 : count); ++i) {
            darkOrb.onEndOfTurn();
        }

        for(i = 0; i < count; ++i) {
            if (Settings.FAST_MODE) {
                this.addToTop(new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));
            } else {
                this.addToTop(new ExhaustAction(1, true, true));
            }
        }
        this.isDone = true;
    }
}
