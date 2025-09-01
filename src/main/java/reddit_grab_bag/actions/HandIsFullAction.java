package reddit_grab_bag.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import reddit_grab_bag.relics.SilkScarf;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class HandIsFullAction extends AbstractGameAction {

    public HandIsFullAction() {}

    public void update() {
        if (AbstractDungeon.player.hasRelic(SilkScarf.ID)) {
            SilkScarf relic = (SilkScarf)AbstractDungeon.player.getRelic(SilkScarf.ID);
            relic.onHandIsFull();
        }
        tickDuration();
    }
}
