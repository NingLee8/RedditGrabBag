package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import reddit_grab_bag.cards.PlasmaLance;
import reddit_grab_bag.relics.BaseRelic;


@SpirePatch(clz= HandCheckAction.class, method="update")
public class RelicOnAfterUseCardPatch {

    // Overflowing Chalice, Pocket Torch checks
    // After absolutely all action has resolved
    public static void Prefix() {
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r instanceof BaseRelic) {
                BaseRelic br = (BaseRelic) r;
                br.onAfterUseCard();
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