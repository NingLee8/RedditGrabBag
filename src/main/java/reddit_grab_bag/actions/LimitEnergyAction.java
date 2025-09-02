package reddit_grab_bag.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import reddit_grab_bag.relics.OverflowingChalice;

public class LimitEnergyAction extends AbstractGameAction {

    private final int energyLimit;
    private final OverflowingChalice r;

    public LimitEnergyAction(int energyLimit, AbstractRelic r) {
        this.energyLimit = energyLimit;
        this.r = (OverflowingChalice) r;
    }

    public void update() {
        if (EnergyPanel.getCurrentEnergy() > this.energyLimit) {
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, r));
            AbstractDungeon.player.loseEnergy(EnergyPanel.getCurrentEnergy() - this.energyLimit);
        }
        tickDuration();
    }
}
