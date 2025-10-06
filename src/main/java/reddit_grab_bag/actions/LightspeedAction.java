package reddit_grab_bag.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class LightspeedAction extends AbstractGameAction {

    private final int magicNumber;
    public LightspeedAction(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public void update() {
        if (this.magicNumber + AbstractDungeon.player.hand.size() > 10) {
            this.addToBot(new GainEnergyAction(this.magicNumber + AbstractDungeon.player.hand.size() - 10));
        }
        tickDuration();
    }
}
