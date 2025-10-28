package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import reddit_grab_bag.relics.WeightedGlove;

public class WeightedGlovePatch {

    @SpirePatch(clz= AbstractCreature.class, method="hasPower")
    public static class WeightGloveHasPowerPatch {
        public static SpireReturn<Boolean> Prefix(AbstractCreature __instance, String targetId) {
            if (AbstractDungeon.player.hasRelic(WeightedGlove.ID)) {
                WeightedGlove relic = (WeightedGlove) AbstractDungeon.player.getRelic(WeightedGlove.ID);
                if (!relic.canDraw && targetId.equals(NoDrawPower.POWER_ID)) {
                    return SpireReturn.Return(true);
                }
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz= AbstractCreature.class, method="getPower")
    public static class WeightGloveGetPowerPatch {
        public static SpireReturn<AbstractPower> Prefix(AbstractCreature __instance, String targetId) {
            if (AbstractDungeon.player.hasRelic(WeightedGlove.ID)) {
                if (targetId.equals(NoDrawPower.POWER_ID)) {
                    return SpireReturn.Return(new NoDrawPower(AbstractDungeon.player));
                }
            }

            return SpireReturn.Continue();
        }
    }

}
