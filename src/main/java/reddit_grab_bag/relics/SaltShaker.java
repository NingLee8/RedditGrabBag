package reddit_grab_bag.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnAnyPowerAppliedRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.OnApplyPowerRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class SaltShaker extends BaseRelic implements OnAnyPowerAppliedRelic {

    private static final String NAME = "SaltShaker";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.UNCOMMON;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.CLINK;
    private final AbstractPlayer p;

    public SaltShaker() {
        super(ID, NAME, AbstractCard.CardColor.RED, RARITY, SOUND);
        this.p = AbstractDungeon.player;
    }

    @Override
    public boolean onAnyPowerApply(AbstractPower abstractPower, AbstractCreature target, AbstractCreature source) {
        if (abstractPower.type.equals(AbstractPower.PowerType.DEBUFF) && !target.isPlayer && !target.hasPower(ArtifactPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
            this.addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, 1), 1));
            this.addToBot(new RelicAboveCreatureAction(p, this));
        }
        return true;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new SaltShaker();
    }


}
