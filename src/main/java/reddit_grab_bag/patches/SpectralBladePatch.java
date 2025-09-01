package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import reddit_grab_bag.cards.SpectralBlade;

@SpirePatch(clz= UseCardAction.class, method=SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, AbstractCreature.class})
public class SpectralBladePatch {

    public static void Postfix(UseCardAction __instance, AbstractCard card, AbstractCreature target) {
        if (card instanceof SpectralBlade) {
            __instance.reboundCard = true;
        }
    }
}
