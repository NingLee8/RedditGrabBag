package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class SpareShield extends BaseRelic{

    private static final String NAME = "SpareShield";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.HEAVY;
    private AbstractPlayer p;

    public SpareShield() {
        super(ID, NAME, AbstractCard.CardColor.PURPLE, RARITY, SOUND);
        this.p = AbstractDungeon.player;
    }

    public void atBattleStart() {

        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new ApplyPowerAction(this.p, this.p, new BlurPower(this.p, 1), 1));
        this.grayscale = true;
    }

    public AbstractRelic makeCopy() {
        return new SpareShield();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
