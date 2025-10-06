package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.EnableEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import reddit_grab_bag.cards.PlasmaLance;
import reddit_grab_bag.relics.BaseRelic;

@SpirePatch(clz= EnableEndTurnButtonAction.class, method="update")
public class OnEndOfStartTurnPatch {

    // Overflowing Chalice, Stake
    public static void Prefix() {
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r instanceof BaseRelic) {
                BaseRelic br = (BaseRelic) r;
                br.onEndOfStartTurn();
            }
        }
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cardID.equals(PlasmaLance.ID)) {
                PlasmaLance pl = (PlasmaLance) c;
                pl.checkAndSetCost();
            }
        }
    }
}
