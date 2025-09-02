package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.IncenseBurner;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class MistRaven extends BaseRelic{

    private static final String NAME = "MistRaven";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.RARE;
    private static final AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.MAGICAL;
    private static boolean usedThisCombat = false;

    public MistRaven() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
    }

    @Override
    public void atPreBattle() {
        usedThisCombat = false;
        this.grayscale = false;
    }

    @Override
    public void onLoseHp(int damageAmount) {
        if (damageAmount > 0 && !usedThisCombat) {
            usedThisCombat = true;
            this.grayscale = true;
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, null, new IntangiblePlayerPower(AbstractDungeon.player, 1), 1));
        }
    }

    @Override
    public void onVictory() {
        this.grayscale = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MistRaven();
    }
}
