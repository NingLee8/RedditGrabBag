package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import reddit_grab_bag.powers.EndlessFlaskPower;

@SpirePatch(clz= GameActionManager.class, method="incrementDiscard")
public class EndlessFlaskPatch {

    public static void Postfix(boolean endOfTurn) {
        if (!AbstractDungeon.actionManager.turnHasEnded && !endOfTurn) {
            if (AbstractDungeon.player.hasPower(EndlessFlaskPower.POWER_ID)) {
                EndlessFlaskPower power = (EndlessFlaskPower) AbstractDungeon.player.getPower(EndlessFlaskPower.POWER_ID);
                power.onManualDiscard();
            }
        }
    }
}
