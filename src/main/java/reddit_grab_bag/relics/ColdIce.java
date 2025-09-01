package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class ColdIce extends BaseRelic{

    private static final String NAME = "ColdIce";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private static boolean usedThisCombat = false;

    public ColdIce() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
        usedThisCombat = false;
        this.grayscale = false;
    }

    @Override
    public void onUsePotion() {

        if (!usedThisCombat) {
            this.flash();
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 12));
            this.grayscale = true;
            usedThisCombat = true;
        }
    }

    @Override
    public void onVictory() {
        usedThisCombat = false;
        this.grayscale = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new ColdIce();
    }
}
