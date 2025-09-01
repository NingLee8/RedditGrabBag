package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class SilkScarf extends BaseRelic {
    private static final String NAME = "SilkScarf";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    private boolean triggeredThisTurn = false;

    public SilkScarf() {
        super(ID, NAME, AbstractCard.CardColor.GREEN, RARITY, SOUND);
    }

    @Override
    public void atTurnStart() {
        triggeredThisTurn = false;
        this.pulse = true;
        this.beginPulse();
    }

    public void onHandIsFull() {
        if (!triggeredThisTurn) {
            triggeredThisTurn = true;
            this.stopPulse();
            this.pulse = false;
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainEnergyAction(1));
        }
    }

    @Override
    public String getUpdatedDescription() {
            return DESCRIPTIONS[0];
    }
}
