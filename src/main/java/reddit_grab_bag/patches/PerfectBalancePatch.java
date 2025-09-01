package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.WeakPower;
import reddit_grab_bag.powers.PerfectBalancePower;

@SpirePatch(clz= WeakPower.class, method=SpirePatch.CONSTRUCTOR)
public class PerfectBalancePatch {

    public static void Postfix(WeakPower __instance, AbstractCreature ___owner) {
        if (!___owner.isPlayer && AbstractDungeon.player.hasPower(PerfectBalancePower.POWER_ID)) {
            AbstractDungeon.player.getPower(PerfectBalancePower.POWER_ID).onSpecificTrigger();
        }
    }
}
