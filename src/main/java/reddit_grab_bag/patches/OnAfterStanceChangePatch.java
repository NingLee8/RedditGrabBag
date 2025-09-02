package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.DivinityStance;
import com.megacrit.cardcrawl.stances.WrathStance;
import reddit_grab_bag.relics.OverflowingChalice;

@SpirePatch(clz= ChangeStanceAction.class, method="update")
public class OnAfterStanceChangePatch {

    public static void Postfix(ChangeStanceAction __instance, String ___id) {
        if (___id.equals(WrathStance.STANCE_ID) || ___id.equals(DivinityStance.STANCE_ID)) {
            if (AbstractDungeon.player.hasRelic(OverflowingChalice.ID)) {
                OverflowingChalice chalice = (OverflowingChalice) AbstractDungeon.player.getRelic(OverflowingChalice.ID);
                chalice.limitEnergy();
            }
        }
    }
}
