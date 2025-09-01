package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class RunicDodecahedron  extends BaseRelic{

    private static final String NAME = "RunicDodecahedron";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.SOLID;

    public RunicDodecahedron() {
            super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.currentHealth % 12 == 0) {
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainEnergyAction(1));
        }
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
