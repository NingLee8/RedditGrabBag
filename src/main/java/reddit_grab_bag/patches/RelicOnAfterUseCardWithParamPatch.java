package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import reddit_grab_bag.relics.BaseRelic;

@SpirePatch(clz= AbstractPlayer.class, method="useCard")
public class RelicOnAfterUseCardWithParamPatch {

    // Overflowing Chalice, Pocket Torch checks
    public static void Postfix(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster, int energyOnUse) {
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r instanceof BaseRelic) {
                BaseRelic br = (BaseRelic) r;
                br.onAfterUseCard(c, monster);
            }
        }
    }
}
