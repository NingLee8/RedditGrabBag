package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.HandCheckAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import reddit_grab_bag.relics.OverflowingChalice;


@SpirePatch(clz= HandCheckAction.class, method="update")
public class RelicOnAfterUseCardPatch {

    public static void Prefix() {
        if (AbstractDungeon.player.hasRelic(OverflowingChalice.ID)) {
            OverflowingChalice chalice = (OverflowingChalice) AbstractDungeon.player.getRelic(OverflowingChalice.ID);
            chalice.onAfterUseCard();
        }
    }
}
