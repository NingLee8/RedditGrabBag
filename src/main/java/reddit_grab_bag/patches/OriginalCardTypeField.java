package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch(clz= UseCardAction.class, method=SpirePatch.CLASS)
public class OriginalCardTypeField {
    public static SpireField<AbstractCard.CardType> originalType = new SpireField<AbstractCard.CardType>(() -> AbstractCard.CardType.ATTACK);
}