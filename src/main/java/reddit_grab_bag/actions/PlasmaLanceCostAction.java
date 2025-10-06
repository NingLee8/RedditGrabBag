package reddit_grab_bag.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import reddit_grab_bag.cards.PlasmaLance;

public class PlasmaLanceCostAction extends AbstractGameAction {

    public PlasmaLanceCostAction() {}

    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cardID.equals(PlasmaLance.ID)) {
                PlasmaLance pl = (PlasmaLance) c;
                pl.setCostForTurn(pl.cost - AbstractDungeon.player.hand.size());
            }
        }
        tickDuration();
    }
}
