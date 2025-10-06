package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import reddit_grab_bag.cards.PlasmaLance;

@SpirePatch(clz= ShowCardAction.class, method="update")
public class ShowCardActionPatch {

    public static void Prefix() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.cardID.equals(PlasmaLance.ID)) {
                PlasmaLance pl = (PlasmaLance) c;
                pl.checkAndSetCost();
            }
        }
    }
}
