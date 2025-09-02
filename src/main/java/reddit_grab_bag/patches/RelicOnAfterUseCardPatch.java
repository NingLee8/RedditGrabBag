package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import reddit_grab_bag.relics.BaseRelic;
import reddit_grab_bag.relics.OverflowingChalice;


@SpirePatch(clz= HandCheckAction.class, method="update")
public class RelicOnAfterUseCardPatch {

    // Overflowing Chalice, Pocket Torch checks
    public static void Prefix() {
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r instanceof BaseRelic) {
                BaseRelic br = (BaseRelic) r;
                br.onAfterUseCard();
            }
        }
    }
}
