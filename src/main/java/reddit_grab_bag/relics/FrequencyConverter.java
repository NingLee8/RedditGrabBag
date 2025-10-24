package reddit_grab_bag.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import reddit_grab_bag.powers.FrequencyConverterPower;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class FrequencyConverter extends BaseRelic implements ClickableRelic {

    private static final String NAME = "FrequencyConverter";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.UNCOMMON;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.CLINK;
    private final AbstractPlayer p;
    public boolean usedThisCombat = false;

    public FrequencyConverter() {
        super(ID, NAME, AbstractCard.CardColor.BLUE, RARITY, SOUND);
        this.p = AbstractDungeon.player;
    }

    public void atPreBattle() {
        usedThisCombat = false;
        this.pulse = true;
        this.grayscale = false;
        this.beginPulse();
    }

    @Override
    public void onRightClick() {
        if (!usedThisCombat) {
            this.addToBot(new ApplyPowerAction(this.p, this.p, new FrequencyConverterPower(AbstractDungeon.player, 1)));
            usedThisCombat = true;
            this.grayscale = true;
            this.pulse = false;
        }
    }

    public void onVictory() {
        this.pulse = false;
        this.grayscale = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new FrequencyConverter();
    }

}
