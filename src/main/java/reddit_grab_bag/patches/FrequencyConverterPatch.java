package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import reddit_grab_bag.powers.FrequencyConverterPower;

@SpirePatch(clz= UseCardAction.class, method=SpirePatch.CONSTRUCTOR, paramtypez = {AbstractCard.class, AbstractCreature.class})
public class FrequencyConverterPatch {

    public static void Prefix(UseCardAction __instance, AbstractCard card) {
        if (AbstractDungeon.player.hasPower(FrequencyConverterPower.POWER_ID)) {
            OriginalCardTypeField.originalType.set(__instance, card.type);
            card.type = AbstractCard.CardType.POWER;
        }
    }

    public static void Postfix(UseCardAction __instance, AbstractCard ___targetCard) {
        if (AbstractDungeon.player.hasPower(FrequencyConverterPower.POWER_ID)) {
            ___targetCard.type = OriginalCardTypeField.originalType.get(__instance);
            FrequencyConverterPower power = (FrequencyConverterPower) AbstractDungeon.player.getPower(FrequencyConverterPower.POWER_ID);
            power.onSpecificTrigger();
        }
    }
}

