package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.MinionPower;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import reddit_grab_bag.relics.Rafflesia;

@SpirePatch2(
        clz= PoisonLoseHpAction.class,
        method="update"
)
public class RafflesiaPatch {

    @SpireInsertPatch(
            locator = RafflesiaPatch.Locator.class
    )
    public static void Insert(PoisonLoseHpAction __instance) {
        if (!__instance.target.hasPower(MinionPower.POWER_ID)) {
            if (AbstractDungeon.player.hasRelic(Rafflesia.ID)) {
                AbstractDungeon.player.getRelic(Rafflesia.ID).onTrigger();
            }
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "poisonKillCount");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
