package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import reddit_grab_bag.relics.Stake;

@SpirePatch(clz= AbstractCard.class, method="hasEnoughEnergy")
public class StakePatch {

    public static SpireReturn<Boolean> Prefix() {
        if (AbstractDungeon.player.hasRelic(Stake.ID)) {
            Stake stake = (Stake)AbstractDungeon.player.getRelic(Stake.ID);
            if (!stake.usedThisTurn) {
                return SpireReturn.Return(true);
            }
        }
        return SpireReturn.Continue();
    }
}
