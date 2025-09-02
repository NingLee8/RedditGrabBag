package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import reddit_grab_bag.actions.PocketTorchAction;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class PocketTorch extends BaseRelic{

    private static final String NAME = "PocketTorch";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;
    public boolean usedThisCombat = false;

    public PocketTorch() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
    }

    public void atPreBattle() {
        usedThisCombat = false;
        this.pulse = true;
        this.grayscale = false;
        this.beginPulse();
    }

    @Override
    public void onAfterUseCard() {
        this.addToBot(new PocketTorchAction(this));
    }

    @Override
    public void onAfterPotionUse() {
        this.addToBot(new PocketTorchAction(this));
    }

    public void onVictory() {
        this.pulse = false;
        this.grayscale = false;
    }

    public AbstractRelic makeCopy() {
        return new PocketTorch();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
