package reddit_grab_bag.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.GremlinHorn;
import reddit_grab_bag.relics.OverflowingChalice;

@SpirePatch(clz= GremlinHorn.class, method="onMonsterDeath")
public class AfterMonsterDeathPatch {

    public static void Postfix() {
        if (AbstractDungeon.player.hasRelic(OverflowingChalice.ID)) {
            OverflowingChalice chalice = (OverflowingChalice) AbstractDungeon.player.getRelic(OverflowingChalice.ID);
            chalice.limitEnergy();
        }
    }
}
