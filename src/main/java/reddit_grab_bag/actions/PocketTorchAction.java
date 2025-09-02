package reddit_grab_bag.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import reddit_grab_bag.relics.PocketTorch;

public class PocketTorchAction extends AbstractGameAction {

    private PocketTorch pocketTorch;

    public PocketTorchAction(PocketTorch pocketTorch) {
        this.pocketTorch =  pocketTorch;
    }

    public void update() {
        if (!this.pocketTorch.usedThisCombat && AbstractDungeon.player.hand.size() == 1) {
            this.pocketTorch.stopPulse();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this.pocketTorch));
            this.addToBot(new GainEnergyAction(2));
            this.pocketTorch.usedThisCombat = true;
            this.pocketTorch.grayscale = true;
        }
        tickDuration();
    }
}
