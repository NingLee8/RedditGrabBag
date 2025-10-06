package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import reddit_grab_bag.cards.MindWipe;

@SpirePatch2(
        clz= ScryAction.class,
        method="update"
)
public class MindWipePatch {

    @SpireInsertPatch(
            locator = Locator.class,
            localvars = {"c"}
    )
    public static void Insert(AbstractCard c) {
        if (c.cardID.equals(MindWipe.ID)) {
            MindWipe mindWipe = (MindWipe) c;
            mindWipe.onScryDiscard();
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(CardGroup.class, "moveToDiscardPile");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
